package 动态规划;

//动态规划求解01-背包问题
//结果是对的，但是过程有问题，被数组下标绕晕了，导致没办法输出具体解法，01背包用回溯和剪枝应该会更好理解
//用矩阵表示当前的最大值，行为item，列为weight，对每一个weight向下比较重量，看下一个item能否加入，如果能加入，当前重量减去该item的重量后在矩阵中找剩余重量对应的最大值
//公式：c[i][j]=Math.max(c[i-1][j],items[i-1].value+c[i-1][j-items[i-1].weight])
public class zeroOneKnapSack {

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

    public static int  zeroOneKnapSack(Item[] items, int totalWeight){
        int totalValue=0;
        int[][] c=new int[items.length+1][totalWeight+1];
        int[][] record=new int[items.length+1][totalWeight+1];
        for(int i=0;i<items.length;i++){
            c[i][0]=0;
        }
        for(int j=0;j<items[0].weight;j++){
            c[0][j]=0;
        }
        for(int j=items[0].weight;j<=totalWeight;j++){
            c[0][j]=items[0].value;
        }
        for(int i=1;i<items.length;i++){
            for(int j=1;j<=totalWeight;j++){
                if(items[i-1].weight>j){
                    c[i][j]=c[i-1][j];
                }
                else{
                    c[i][j]=Math.max(c[i-1][j],items[i-1].value+c[i-1][j-items[i-1].weight]);
                }
            }
        }

        totalValue=c[items.length-1][totalWeight];

        for(int i=0;i<items.length;i++){
            for(int j=0;j<=totalWeight;j++){
                System.out.print(c[i][j]+"\t");
            }
            System.out.println();
        }
        return totalValue;
    }

    public static void main(String[] args) {
        int[] weight={10,20,30,40,50};
        int[] value={20,30,65,40,60};
        Item[] items=new Item[value.length];
        for(int i=0;i<value.length;i++){//初始化item
            items[i]=new Item(i,weight[i],value[i]);
        }

        int totalWeight=100;
        int totalValue=zeroOneKnapSack(items,totalWeight);
        System.out.println("total value:");
        System.out.println(totalValue);

    }
}
