package 最大子数组和;

//递归求解
public class maxSubArray {

    public static int[] findMaxCrossingSubArray(int[] nums,int left,int middle,int right){
        int leftSum=Integer.MIN_VALUE;
        int maxLeft=middle;//取最大值的左边界下标
        int sumL=0;
        for(int i=middle;i>=left;i--){//求从middle向左侧的元素的最大和
            sumL+=nums[i];
            if(sumL>leftSum){
                leftSum=sumL;
                maxLeft=i;
            }
        }

        int rightSum=Integer.MIN_VALUE;
        int maxRight=middle+1;//取最大和时的右边界下标
        int sumR=0;
        for(int j=middle+1;j<=right;j++){//middle右侧元素的最大和
            sumR+=nums[j];
            if(sumR>rightSum){
                rightSum=sumR;
                maxRight=j;
            }
        }

        int[] resultIndex=new int[3];
        resultIndex[0]=maxLeft;
        resultIndex[1]=maxRight;//跨越中点的最大子数组的左右边界
        resultIndex[2]=leftSum+rightSum;//该数组的和

        return resultIndex;
    }

    public static int[] findMaxSubArray(int[] nums,int left,int right){

        if(left==right){
            int[] resultIndex=new int[3];
            resultIndex[0]=left;
            resultIndex[1]=right;
            resultIndex[2]=nums[left];
            return resultIndex;
        }
        else{//递归
            int middle=(left+right)/2;

            //左侧最大值
            int[] leftResultIndex=findMaxSubArray(nums,left,middle);

            //右侧最大值
            int[] rightResultIndex=findMaxSubArray(nums,middle+1,right);

            //跨越中点的最大值
            int[] crossResultIndex=findMaxCrossingSubArray(nums,left,middle,right);

            //对三部分的和进行比较，找到和最大的部分
            if(leftResultIndex[2]>=rightResultIndex[2]&&leftResultIndex[2]>=crossResultIndex[2]){
                return leftResultIndex;
            }
            else if(rightResultIndex[2]>=leftResultIndex[2]&&rightResultIndex[2]>=crossResultIndex[2]){
                return rightResultIndex;
            }
            else{
                return crossResultIndex;
            }

        }
    }

    public static void main(String[] args) {
        int[] nums={13,-3,-25,-20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};

        int[] result=findMaxSubArray(nums,0,nums.length-1);
        /*for(int num:result){
            System.out.println(num);//left=7,right=10,maxSum=43
        }*/
        System.out.println("left boundary:\n"+result[0]);
        System.out.println("right boundary:\n"+result[1]);
        System.out.println("max subArray sum:\n"+result[2]);

        System.out.println("\nsubArray elements:");
        for(int i=result[0];i<=result[1];i++){
            System.out.println(nums[i]);// 18 ,20 ,-7 ,12
        }

    }

}
