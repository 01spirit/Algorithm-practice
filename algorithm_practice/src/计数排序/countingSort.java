package 计数排序;

import java.util.Arrays;


/**
 * 书中给出的伪代码基本都有数组下标越界的问题，很多数组都是从1开始的，编写程序时要注意数组边界
*/

//计数排序


//基数排序和桶排序都要用到计数排序
public class countingSort {
    public static int[] countingSort(int[] nums,int[] results,int k){
        int[] count=new int[k+1];
        for(int i=0;i<=k;i++){
            count[i]=0;
        }
        for(int j=0;j<nums.length;j++){
            count[nums[j]]+=1;//统计每个数字出现的次数
        }
        for(int i=1;i<=k;i++){
            count[i]+=count[i-1];//nums中值<=nums[i]的元素的个数
        }
//      for(int j=0;j<= nums.length-1;j++)//从前面或者后面开始遍历都可以
        for(int j=nums.length-1;j>=0;j--){
            results[count[nums[j]]]=nums[j];//nums[j]对应的count[nums[j]]即为在results中插入的位置，
            count[nums[j]]-=1;              // 当前的nums[j]存入results后，count[nums[j]]相应减少一个元素
        }
        return results;
    }

    public static void main(String[] args) {
        int[] nums= {2, 5, 3, 0, 2, 3, 0, 3};
        int[] results=new int[nums.length+1];
        countingSort(nums,results, Arrays.stream(nums).max().getAsInt());//传入的k是nums中的最大值，count的左边界可以从0开始，也可以从min开始，第二次求count数组之后就没有影响了
        for(int i=1;i< results.length;i++){
            int res=results[i];
            System.out.println(res);
        }
    }
}
