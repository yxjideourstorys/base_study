package com.study.base.data_structure.array;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组 11*11
        //0：表示没有棋子，1：表示白子，2：表示蓝子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[5][7] = 2;
        //输出原始的二维数组
        System.out.println("输出原始的二维数组：");
        for (int[] ints : chessArr1) {
            for (int arr : ints) {
                System.out.printf("%d\t", arr);
            }
            System.out.println();
        }

        //将二维数组转成稀疏数组
        //1、先遍历二维数组，得到非0的数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2、根据sum创建稀疏数组 int[sum + 1][3]
        int[][] sparseArr2 = new int[sum + 1][3];
        sparseArr2[0][0] = 11;
        sparseArr2[0][1] = 11;
        sparseArr2[0][2] = sum;
        //3、将二维数组的有效数据存入到稀疏数组中
        int count = 0; //用于记录是第几个非0数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr2[count][0] = i;
                    sparseArr2[count][1] = j;
                    sparseArr2[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组
        System.out.println("输出稀疏数组：");
        for (int[] ints : sparseArr2) {
            System.out.printf("%d\t%d\t%d\t\n", ints[0], ints[1], ints[2]);
        }


        System.out.println("---------------------------------------------分割线----------------------------------------------------");

        //将稀疏数组转成原始数组
        //1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        //2、再取稀疏数组的后几行数据，并赋值给原始的二维数组
        for (int i = 1; i < sparseArr2.length ; i++) {
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }
        //打印恢复后的原始二维数组
        for (int[] arrs: chessArr2) {
            for (int arr: arrs) {
                System.out.printf("%d\t", arr);
            }
            System.out.println();
        }
    }
}
