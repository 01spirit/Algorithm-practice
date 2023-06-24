package 插入排序;

import java.util.Scanner;

public class insertionSort {

    public static int[] insertionSort(int[] nums){
        for(int j=1;j<nums.length;j++){
            int i=j-1;
            int key=nums[j];
            while(i>=0&&nums[i]>key){
                nums[i+1]=nums[i];
                i=i-1;
            }
            nums[i+1]=key;

        }
        return nums;
    }
    public static void main(String[] args){

        /*int[] nums=new int[100];
        int len=0;
        Scanner in=new Scanner(System.in);
        len=in.nextInt();
        for(int i=0;i<len;i++){
            nums[i]=in.nextInt();
            System.out.println(nums[i]);
        }*/

        int[] nums={3,6,4,8,1,4};

        for(int i=0;i<nums.length;i++){
            System.out.println(insertionSort(nums)[i]);
        }


    }
}
