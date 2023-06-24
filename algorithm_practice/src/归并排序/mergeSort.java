package 归并排序;

public class mergeSort {
    public static int[] merge(int[] nums ,int left,int middle,int right){
        int len1=middle-left+1;
        int len2=right-middle;
        int[] leftArr=new int[len1+1];
        int[] rightArr=new int[len2+1];

        for(int i=0;i<len1;i++){
            leftArr[i]=nums[left+i];
        }
        leftArr[len1]=Integer.MAX_VALUE;//设置哨兵为最大值，若不设置哨兵，就在下面的合并时判断数组是否为空，若某一个数组为空，把另一个数组的剩余元素全部复制进去
        for(int i=0;i<len2;i++){
            rightArr[i]=nums[middle+1+i];
        }
        rightArr[len2]=Integer.MAX_VALUE;

        //这一步用于进行排序，当子数组划分到最后长度为1时，进行数组合并，就会把两个数按顺序排列，然后合并后的大的子数组继续进行合并，
        //由于从长度为二的数组开始就有了顺序，每一步合并数组之后的新数组都是按顺序排列的，从而得到最终的有序数组
        int indexL=0,indexR=0;
        for(int i=left;i<=right;i++){//合并两个子数组，每次选取两个数组中最小的一个数字存入原数组，指针后移一位继续比较
            if(leftArr[indexL]<rightArr[indexR]){//从小到大排列;从大到小排列要重新设计比较方法，不能直接改符号
                nums[i]=leftArr[indexL];
                indexL++;
            }
            else{
                nums[i]=rightArr[indexR];
                indexR++;
            }
        }
        /*for(int num:nums){
            System.out.println(num);
        }*/
        return nums;
    }

    public static int[] mergeSort(int[] nums,int left,int right){
        if(left<right){//子数组长度不为零时递归拆分成子数组进行排序，然后把返回的排序结果进行合并
            int middle=(left+right)/2;//从中间拆分成两个新的子数组
            mergeSort(nums,left,middle);
            mergeSort(nums,middle+1,right);
            merge(nums,left,middle,right);
        }
        return nums;//子数组长度为零时直接返回一个数，相邻两个数字按顺序排列成一个大的子数组
    }


    public static void main(String[] args) {
        int[] nums={3,5,2,7,1};
//        merge(nums,0,2,4);

        for(int num: mergeSort(nums,0,4)){
            System.out.println(num);
        }

    }

}
