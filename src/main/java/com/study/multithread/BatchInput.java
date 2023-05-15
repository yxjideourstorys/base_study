package com.study.multithread;

import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiFunction;

/**
 * 多线程批量插入
 */
@Service
public class BatchInput {

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    /**
     * 拆分大小
     */
    private static final int splitSize = 1000;

    /**
     * @description  线程方法
     * @param  bigList 需拆分集合
     * @Param  mapperClass mapper的calss对象
     * @param  function 双重函数
     */
    public <T, U, R> void threadBatchMethod(List<T> bigList, Class<U> mapperClass, BiFunction<List<T>, U, R> function) throws SQLException {
        long start = System.currentTimeMillis();

        // 获取sql会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 获取连接
        Connection connection = sqlSession.getConnection();

        // 获取CPU核心数
        int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        int maximumPoolSize = (int) Math.floor(bigList.size() / splitSize * 2);
        int capacity = (int) Math.floor(maximumPoolSize / 2);

        /*
         * corePoolSize：线程数(决定添加的任务是开辟新的线程执行,还是放到任务队列)
         * maximumPoolSize：最大线程数，根据任务队列的类型，决定线程池会开辟的最大线程数量
         * keepAliveTime：空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁
         * workQueue：任务队列，被添加到线程池中，但尚未被执行的任务；分为直接提交队列、有界任务队列、无界任务队列、优先任务队列
         * AbortPolicy：拒绝策略，AbortPolicy策略会直接抛出异常，阻止系统正常工作
         */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(capacity),
                new ThreadPoolExecutor.AbortPolicy());


        // 将集合拆分成小集合，并封装
        List<List<T>> resultList = Lists.partition(bigList, splitSize);

        List<Callable<Integer>> callList = Lists.newArrayList();

        try {
            // 关闭事务自动提交
            connection.setAutoCommit(false);
            // 根据Class对象，获取相应mapper
            U mapper = sqlSession.getMapper(mapperClass);
            // 对拆分的集合进行多线程执行批量处理
            for (List<T> parList : resultList) {
                // 使用Callable多线程，获取执行结果
                // callable和Runnable的区别在于：callable可以有返回值，也可以抛出异常的特性，而Runnable没有
                Callable<Integer> callable = new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        List<T> updateList = Lists.newArrayList();
                        for (T t : parList) {
                            updateList.add(t);
                        }
                        return (Integer) function.apply(updateList, mapper);
                    }
                };
                callList.add(callable);
            }

            // 批量提交，返回Future对象
            List<Future<Integer>> futures = threadPool.invokeAll(callList);
            // 遍历判断返回值
            for (Future<Integer> future : futures) {
                if (future.get() <= 0) {
                    // 事务回滚
                    connection.rollback();
                    return;
                }
            }

//            int i = 100 / 0;
            // 事务提交
            connection.commit();
            System.out.println(bigList.size() + " 条数据耗时：" + (System.currentTimeMillis() - start) / 1000 + " s");
        } catch (Exception e) {
            // 事务回滚
            connection.rollback();
            System.out.println("异常耗时：" + (System.currentTimeMillis() - start) / 1000 + " s");
            e.printStackTrace();
        }finally {
            //线程池用完，程序结束，关闭线程池
            threadPool.shutdown();  //（为确保关闭，将关闭方法放入到finally中）
        }
    }

//    public static void main(String[] args) {
//        List<Period> list = Lists.newArrayList();
//
//        int size = 10001;
//        for (int i = 1; i < size; i++) {
//            Period period = new Period(i + "", i + "", i + "", i + "", i + "", i + "", i + "");
//            list.add(period);
//        }
//
//        threadPoolService.threadBatchMethod(list, BallDao.class, (data, BallDao) -> BallDao.insertPeriod(data));
//    }


}
