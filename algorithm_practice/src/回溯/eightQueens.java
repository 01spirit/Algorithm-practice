package 回溯;

import java.util.Arrays;

public class eightQueens {
    //定义一个max表示有多少个皇后
    int max = 8;
    //定义数组array，保存皇后放置位置的结果，比如 arr = { 0 , 4, 7, 5, 2, 6, 1, 3}
    int[] arr = new int[max];
    static int count = 0;//这个变量是统计有多少种解法
    static int judgeCount; //这个变量是统计回溯了多少次的
    public static void main(String[] args) {
        eightQueens queue8 = new eightQueens();
        //这里的0，是从第一个皇后放起
        queue8.check(0);
        System.out.println("一共有 = " + count+"解法");
        System.out.println("一共有判断冲突的 = " + judgeCount+" 次回溯次数");
    }

    private void check(int n){
        if (n == max){  //如果n是第八个皇后，说明已经放完全部的皇后
            print();
            return;
        }

        for (int i = 0; i < max; i++) {
            //先把当前的皇后n，放到该行的第一列
            arr[n] = i;

            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)){ //不冲突
                //接着放置n+1个皇后，即开始递归
                check(n+1);
            }
            //如果冲突，就继续执行arr[n] = i;但是i在循环中，下一次循环i=i+1;即 将第n个皇后，放置在本行的后移一个位置
        }
    }

    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if (arr[i] ==arr[n] || Math.abs(n-i) == Math.abs(arr[n]-arr[i])){
                return false;
            }
        }
        return true;
    }

//      写一个方法，可以将皇后摆放的位置打印出来

    private void print(){
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
