package 贪心;

import java.util.Arrays;
import java.util.Comparator;

public class fractionKnapSack {

    //定义一个类，用于存储重量和价值的关联
    public static class Item{
        public int number;
        public int weight;
        public int value;
        public double valuePerWeight;

        public Item(int number,int weight,int value){
            this.number=number;
            this.weight=weight;
            this.value=value;
            this.valuePerWeight= (double)this.value/this.weight;
        }

        //重写toString()方法用于输出
        @Override
        public String toString() {
            return "Item{" +
                    "number=" + number +
                    ", weight=" + weight +
                    ", value=" + value +
                    ", valuePerWeight=" + valuePerWeight +
                    '}';
        }
    }

    //分数背包，一个item可以拆开，只向背包放入一部分
    //只需要计算物品的单位重量价值vpw，按照vpw从大到小放入背包，装满之后即可得到最大价值
    public static double  fractionKnapSack(Item[] items,int totalWeight){
        Arrays.sort(items, Comparator.comparingDouble((Item item)->item.valuePerWeight).reversed());//按照 valuePerWeight 从大到小排列
        double totalValue=0;

        for(Item item:items){
            if(totalWeight<=0){//背包装满
                break;
            }
            else if(totalWeight>=item.weight){//当前 item 可以全部放入
                totalValue+=item.value;
                totalWeight-=item.weight;
                System.out.println(item.toString());//输出放入项目的信息
            }
            else{//只能放入一部分， fraction部分，计算可以放入部分的价值
                int fractionWeight=totalWeight;
                double fractionValue=item.valuePerWeight*fractionWeight;
                totalValue+=fractionValue;
                totalWeight-=fractionWeight;
                System.out.println(item.toString()+"\tfraction weight: "+fractionWeight+"\tfraction value: "+fractionValue);//对 fraction部分做额外输出
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
//        int[] weight={10,20,30};
//        int[] value={60,100,120};
        int[] weight={10,20,30,40,50};
        int[] value={20,30,65,40,60};
        Item[] items=new Item[value.length];
        for(int i=0;i<value.length;i++){//初始化item
            items[i]=new Item(i,weight[i],value[i]);
        }

        int totalWeight=100;
        double totalValue=fractionKnapSack(items,totalWeight);
        System.out.println(totalValue);

    }

}
