package 堆排序;

//堆排序
//优先队列基于大顶堆，理解了堆排序就能写出来，这里不再进行实现
public class heapSort {

    //父结点的下标
    public static int parent(int i){
        return i/2;
    }
    //左子结点的下标
    public static int left(int i){
        return 2*i;
    }
    //右子节点的下标
    public static int right(int i){
        return 2*i+1;
    }

    //维护最大堆性质的调整过程，输入为待排序数组，要调整的结点下标，此时堆的长度
    //需要注意 heapsize 初始赋值为 nums.length ，但是在排序过程中需要逐渐减少待排序的数组的长度，也就是堆的长度，已排序元素放在数组的后面，不再参与堆调整过程
    public static int[] maxHeapify(int[] nums,int i,int heapSize){
        int left=left(i);
        int right=right(i);
        int largest=0;

        //把要调整的结点的值和其左右子结点比较找出最大的，largest 存储最大值对应的下标
        if(left<heapSize && nums[left]>nums[i]){
            largest=left;
        }
        else{
            largest=i;
        }
        if(right<heapSize && nums[right]>nums[largest]){
            largest=right;
        }

        //如果当前待调整结点不是最大值，把它和前面找到的子节点中的最大结点交换，然后对交换下去的结点再进行递归调整，直到它的子节点都小于它或它本身成为叶子结点为止
        if(largest!=i){
            int temp=nums[i];
            nums[i]=nums[largest];
            nums[largest]=temp;
            maxHeapify(nums,largest,heapSize);
        }

        return nums;
    }

    //构建大顶堆
    public static int[] buildMaxHeap(int[] nums){
        int heapSize=nums.length;//初始化heapSize，此时需要对堆中所有元素进行调整，heapSize不需要改变

        //maxHeapify() 会把结点和子结点比较，不需要调整全部结点，遍历前半个数组就足够了，后半个数组都是叶子结点，没有子结点。
        //此时构建出来的大顶堆未必是从大到小有序的数组，但是从大到小有序的数组一定是大顶堆
        //书中有对这部分的时间复杂度的讨论，不是 O(nlgn) 而是 O(n)
        for(int i=nums.length/2;i>=0;i--){
            maxHeapify(nums,i,heapSize);
        }
        return nums;
    }

    //堆排序
    public static int[] heapSort(int[] nums){
        int heapSize=nums.length;
        buildMaxHeap(nums);

        //从末尾开始，把大顶堆中的根结点（必定是当前堆中的最大元素）和堆中的最后一个元素交换，剩余未排序堆的长度减少，对交换后的未排序堆进行调整，恢复其大顶堆性质
        //排序过程是从后向前从大到小排列，大的元素依次放在数组后面，最终结果是从前向后从小到大的顺序
        for(int i=nums.length-1;i>=1;i--){
            int temp=nums[i];
            nums[i]=nums[0];
            nums[0]=temp;
            heapSize-=1;
            maxHeapify(nums,0,heapSize);//此时heapSize代表数组中待排序元素的数量
        }

        return nums;
    }



    public static void main(String[] args) {
        int[] nums={4,1,3,2,16,9,10,14,8,7,5};
        int[] sortedNums=heapSort(nums);

//        int[] sortedNums=buildMaxHeap(nums);
        for(int val:sortedNums){
            System.out.println(val);
        }




    }

}
