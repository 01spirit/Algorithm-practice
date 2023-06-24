package 回溯;

//回溯求解01背包
/**
 * 把回溯的过程看作遍历一棵子集树的每一条从根结点到叶子节点的路径，，每一层代表一个item的两种状态：0-1（是否放入背包）
 * 根节点表示第一个item，它有0-1两个分支，即两个子节点；第二层表示第二个item，对于前面的每个分支都有0-1两种状态，即四个子节点
 * 遍历到叶子结点后找到取得最大值的一条路径，上面的0-1状态就代表了最优解方案中是否包含了那个item
 *              item1
 *             0/   \1
 *           item2  item2
 *          0/  \1  0/  \1
 *
 * 简单地回溯需要遍历所有路径，可以用限界函数和剪枝去掉一些不可能的节点路径
 */

public class backTrackingZeroOneKnapsack {
    //定义一个类，用于存储重量和价值的关联
    public static class Item{
        public int number;
        public int weight;
        public int value;

        public Item(int number,int weight,int value){
            this.number=number;
            this.weight=weight;
            this.value=value;
        }

        //重写toString()方法用于输出
        @Override
        public String toString() {
            return "Item{" +
                    "number=" + number +
                    ", weight=" + weight +
                    ", value=" + value +
                    '}';
        }
    }

    //声明回溯过程中将要用到的一些变量
    public  int curValue;
    public  int maxValue;
    public  int curWeight;
    static final int totalWeight=100;
    static final int totalItems=5;
    static int[] bestX=new int[5];//最优解方案
    static int[] x=new int[5];//每一个分支的选择
    public int backTrackingZeroOneKnapsack(Item[] items, int i){
        if(i>totalItems-1){//对一条分支的所有item都进行了查看，对应子集树中一条从根结点到叶子节点的路径，退出并获得最优解
            if (maxValue < curValue) {//比较这条分支的value，如果可取为最大值，把 x[] 中存储的分支状态存入 bestX[] 做为备选的最优解方案
                maxValue = curValue;
                for (int j = 0; j <totalItems; j++) {
                    bestX[j] = x[j];
                }
            }
        }
        else{
            for(int k=0;k<=1;k++){//01分支
                x[i]=k;//存储当前item在这条分支上的状态，用于构造最优解方案
                if(k==0){//0分支，当前item不放入，直接查看下一分支
                    backTrackingZeroOneKnapsack(items,i+1);
                }
                else{//1分支，看当前item重量是否满足，进行放入和回溯
                    if(curWeight+items[i].weight<=totalWeight){//重量足够，放入
                        curWeight+=items[i].weight;
                        curValue+=items[i].value;
                        backTrackingZeroOneKnapsack(items,i+1);//放入后状态改变，继续查看下一个item
                        curWeight-=items[i].weight;//回溯，前面是k==1并且成功放入的情况，，假设最优解中并没有当前item，在这一条分支都递归求解之后，把状态退回到放入这一item之前，再对下一个item做同样处理
                        curValue-=items[i].value;
                    }
                    else{//重量不足，继续查看下一个Item
                        backTrackingZeroOneKnapsack(items,i+1);
                    }
                }
            }
        }
        return maxValue;
    }

    public static void main(String[] args) {
        int[] weight={10,20,30,40,50};
        int[] value={20,30,65,40,60};
        Item[] items=new backTrackingZeroOneKnapsack.Item[value.length];
        for(int i=0;i<value.length;i++){//初始化item
            items[i]=new backTrackingZeroOneKnapsack.Item(i,weight[i],value[i]);
        }

        backTrackingZeroOneKnapsack bt=new backTrackingZeroOneKnapsack();
        int totalValue=bt.backTrackingZeroOneKnapsack(items,0);
        System.out.println("max value:");
        System.out.println(totalValue);
        for (int i = 0; i < 5; i++) {
            System.out.print("Item "+(i+1)+" : ");
            System.out.println(bestX[i]);
        }
    }
}
