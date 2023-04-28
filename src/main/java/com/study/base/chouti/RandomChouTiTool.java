package com.study.base.chouti;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class RandomChouTiTool {

    private static JLabel jl = new JLabel("文件：");
    private static JButton  aboutButton  = new JButton ("关于");
    private static JTextField jt = new JTextField(); //文件显示框
    private static JButton OpenButton = new JButton("选择文件");
    private static JTextField jt2 = new JTextField(); //文本框 显示抽取名单
    private static JButton StartButton = new JButton("开始抽取");
    private static JButton BuhuiButton = new JButton("不会,添加复试");
    private static JButton FushiButton = new JButton("复试");
    private static JLabel InitNumTopic = new JLabel("初试试题还有：");
    private static JTextField InitNumTopicShow = new JTextField(0);//显示初试题数
    private static JLabel FushiNumTopic = new JLabel("复试试题数：");
    private static JTextField FushiNumTopicShow = new JTextField(0);//显示复试题数
    private static JOptionPane jo = new JOptionPane(); //弹出一个提示框
    private static ArrayList<String> initTopicList = new ArrayList<>(); //用来存放初始化题目
    private static ArrayList<String> topicedList = new ArrayList<>();  //用来存放抽过的题目
    private static Font font = new Font("宋体", Font.BOLD, 18); //设置字体对象
    private static String showTopic = "";
    private static File file;
    private static boolean fushiAlertMsgflag = true;// 复试弹窗flag
    private static boolean initAlertMsgflag = true;// 初试弹窗flag
    private static boolean fushiflag = false; // 是否进行了复试
    private static boolean initflag = false; // 是否进行了初试
    private static int fushiCirculateNumber = 1; // 复试试题是否循环
    private static JLabel jl2 = new JLabel("复试答题是否循环(默认循环)：");
    private static JComboBox jc = new JComboBox();//下拉列表框

    private static Stack stack = new Stack<>();

    private void windows() {
        JFrame jf = new JFrame("随机抽题器");
        jf.setIconImage(new ImageIcon("Icon.jpg").getImage());
        Container c = jf.getContentPane();
        c.setLayout(new GridLayout(5,5,10,10));
        OpenButton.setFocusPainted(false);
        StartButton.setFocusPainted(false);
        JPanel jp1 = new JPanel();
        JPanel jp2 = new JPanel(new BorderLayout());
        JPanel jp3 = new JPanel();
        JPanel jp4 = new JPanel();//添加面板
        JPanel jp5 = new JPanel();//添加面板

        jt.setColumns(10);
        InitNumTopicShow.setColumns(4);
        FushiNumTopicShow.setColumns(4);
        InitNumTopicShow.setText("0");
        FushiNumTopicShow.setText("0");

        jt2.setHorizontalAlignment(JTextField.CENTER);

        jc.addItem("是");
        jc.addItem("否");

        jp1.add(jl);
        jp1.add(jt);
        jp1.add(OpenButton);
        jp1.add(aboutButton);
        jp2.add(jt2, BorderLayout.CENTER);
        jp3.add(InitNumTopic);
        jp3.add(InitNumTopicShow);
        jp3.add(FushiNumTopic);
        jp3.add(FushiNumTopicShow);
        jp4.add(StartButton);
        jp4.add(BuhuiButton);
        jp5.add(FushiButton);
        jp5.add(jl2);
        jp5.add(jc);
        c.add(jp1);
        c.add(jp2);
        c.add(jp3);
        c.add(jp4);
        c.add(jp5);

        jf.setVisible(true);
        // 得到显示器屏幕的宽高
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        // 定义窗体的宽高
        int windowsWedth = 600;
        int windowsHeight = 600;
        jf.setBounds((width - windowsWedth) / 2, (height - windowsHeight) / 2, windowsWedth, windowsHeight);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getOpenButton();
        getSrartButton();
        getAboutButton();
        getBuhuiButton();
        getFushiButton();

        getjcomboBox(); //监听事件
    }

    private void getOpenButton() {
        OpenButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();//这个对象就是我们点击打开文件，出来的文件选择器
                fc.setCurrentDirectory(new File("."));//指定当前默认目录
                fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);//可以选择只打开文件或者文件夹
                fc.setMultiSelectionEnabled(false);//是否允许多选文件
                int i = fc.showOpenDialog(null);
                if (i == JFileChooser.APPROVE_OPTION) {//判断是否打开
                    file = fc.getSelectedFile();
                    //显示选中内容
                    jt.setText(fc.getSelectedFile().getName());
                    try {
                        if (file.isFile() && file.exists()) { //判断文件是否存在

                            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
                            BufferedReader bufferedReader = new BufferedReader(reader);

                            String lineTxt = null;
                            while ((lineTxt = bufferedReader.readLine()) != null) {
                                initTopicList.add(lineTxt);
                            }

                            jt2.setFont(font);
                            jt2.setForeground(Color.blue);
                            jt2.setText("数据初始化完成，可以开始面试了！！！");

                            numChange();

                            reader.close();
                        } else {
                            alertMsg("TMD文件格式不对");
                        }

                    } catch (Exception e1) {
                        alertMsg("糟糕，崩了！！！");
                        e1.printStackTrace();
                    }
                }
            }
        });
    }


    /**
     * “开始抽取”按钮监听事件
     */
    private void getSrartButton() {
        StartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (null == file) {
                    alertMsg("请先导入数据题目！");
                } else if (initflag && fushiflag) {
                    alertMsg("初试已完成，正在进行复试中，请继续复试！");
                } else {
                    if (initAlertMsgflag) {
                        alertMsg("将要开始进行初试！！！");
                        initflag = true;
                        initAlertMsgflag = false;
                    }

                    if (initTopicList.isEmpty()) {
                        alertMsg("恭喜你，所有题目均已答完！！！");
                        jt2.setText("恭喜你，所有题目均已答完！！！");
                        showTopic = "";
                    } else {
                        int showNum = (int) (Math.random() * initTopicList.size());
                        showTopic = initTopicList.get(showNum);
                        //设置页面显示
                        jt2.setText(showTopic);
                        //从初始数据中删除掉
                        initTopicList.remove(showNum);

                        numChange();
                    }
                }
            }
        });
    }

    /**
     * “不会，添加复试”按钮监听事件
     */
    private void getBuhuiButton() {
        BuhuiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!initflag) {
                    alertMsg("请先进行初试！");
                } else if ("恭喜你，所有题目均已答完！！！".equals(jt2.getText())) {
                    alertMsg("所有初试题目均已答完，可选择进行复试答题！");
                } else if (initflag && fushiflag) {
                    alertMsg("初试已完成，正在进行复试中，请继续复试！");
                } else if (StringUtils.isBlank(showTopic)) {
                    alertMsg("请先选择初试试题！");
                } else {
                    //加入到复习题目中
                    topicedList.add(showTopic);
                    showTopic = "";
                    jt2.setText("");
                    alertMsg("此题不会，添加复试列表成功，请继续进行初试！");

                    numChange();
                }
            }
        });
    }

    /**
     * “复试”按钮监听事件
     */
    private void getFushiButton() {
        FushiButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (null == file) {
                    alertMsg("请先导入数据题目！");
                } else if (null != initTopicList && !initTopicList.isEmpty()) {
                    alertMsg("请先完成初试！");
                } else {
                    if (topicedList.isEmpty()) {
                        alertMsg("恭喜你，所有题目均已答完！！！");
                        jt2.setText("恭喜你，所有题目均已答完！！！");
                    } else {
                        if (fushiAlertMsgflag) {
                            alertMsg("将要开始进行复试！！！");
                            fushiflag = true;
                            fushiAlertMsgflag = false;
                        }

                        int showFushiNum = (int) (Math.random() * topicedList.size());
                        System.out.println("处理前：" + showFushiNum);
                        if (!stack.empty() && topicedList.size() >= 2){
                            showFushiNum = duplicated((int) stack.pop(), showFushiNum);
                        }
                        System.out.println("处理后：" + showFushiNum);


                        String topic = topicedList.get(showFushiNum);
                        //设置页面显示
                        jt2.setText(topic);
                        stack.push(showFushiNum);

                        if (fushiCirculateNumber == 2) {
                            //从复试数据中删除掉
                            topicedList.remove(showFushiNum);
                        }

                        numChange();
                    }
                }
            }
        });
    }

    private int duplicated(int stackValue, int value){
        if (stackValue != value){
            return value;
        }
        value = (int) (Math.random() * topicedList.size());

        return duplicated(stackValue, value);
    }

    private void alertMsg(String msg) {
        jo.showMessageDialog(null, msg);
    }

    private void numChange() {
        InitNumTopicShow.setText(initTopicList.size() + "");
        FushiNumTopicShow.setText(topicedList.size() + "");
    }

    private void getAboutButton() {
        aboutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                alertMsg("可建立txt文件:\n一行一个题目");
            }
        });
    }

    private void getjcomboBox() {
        jc.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String item = (String) e.getItem();
                    if (item == "是") {
                        fushiCirculateNumber = 1;
                    }
                    if (item == "否") {
                        fushiCirculateNumber = 2;
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        RandomChouTiTool call = new RandomChouTiTool();
        call.windows();
    }

}
