package 快速排序;

public class quickSort {
    //快速排序
    public static int[] quickSort(int[] nums,int left,int right){//参数为要排序的数组及其左右边界
        if(left<right){//数组长度不为一
            int q=partation(nums,left,right);//进行数组划分，划分后第q个元素左侧的数都<=nums[q],右侧数都>=nums[q]
            quickSort(nums,left,q-1);//对划分后的左右两个数组继续进行排序，第q个元素固定不动，两侧划分成更小的数组，直到不可划分，每个元素左侧的元素都<=本身，右侧都>=本身，完成排序
            quickSort(nums,q+1,right);
        }
        return nums;//逐层划分成单个元素，直接返回
    }

    public static int partation(int[] nums,int left,int right){
        int x=nums[right];//总是选择最右侧的元素作为主元，用它来划分数组
        int i=left-1;//i用于指示左侧子数组的交换，从初始数组左侧的前一位开始，他的后一位是将要交换的位置
        for(int j=left;j<=right-1;j++){
            if(nums[j]<=x){//把j处小于主元的元素和前面i处的已经比较过的大于主元的元素交换，使小的元素移动到左侧，大的元素移动到右侧
                i+=1;//交换之后i后移一位，此时i左侧的元素都<=主元，右侧元素都>=主元，等待前面移动的j找到下一个交换的位置
                int temp=nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
            }
        }
        int temp=nums[i+1];//把主元交换到i后面的位置，由于i右侧元素都>=主元，此次交换不会影响右侧子数组相对于主元的大小状态，只是i+1处的元素成为了右侧子数组的新主元
        nums[i+1]=nums[right];
        nums[right]=temp;

        return i+1;//返回值是进行新数组划分的位置，根据前面的元素交换，旧主元的位置变为了i+1。
                    // 他相对于整个数组的位置已经可以确定了，左侧元素都<=nums[i+1],右侧元素都>=nums[i+1],在下一次数组划分中不需要考虑他，所以下一次划分的边界是q-1和q+1
    }

    public static void main(String[] args) {
        int[] nums={3,1,6,5,4,8,2};
        quickSort(nums,0,nums.length-1);
        for(int val:nums){
            System.out.println(val);
        }
    }

}
