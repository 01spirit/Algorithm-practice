package 动态规划;

public class rodCutting {

    //r[] 存储每个长度的最大价格，s[] 存储最大价格时的切割长度
    //n 是输入的长度，注意每个数组的长度
    public static void bottomUpCutRod(int[] price,int n){
        int[] result=new int[n+1];
        int[] solution=new int[n+1];
        result[0]=0;
        for(int i=1;i<=n;i++){
            int q=-1;
            for(int j=1;j<=i;j++){
                if(q<price[j]+result[i-j]){
                    q=price[j]+result[i-j];
                    solution[i]=j;

                }
            }
            result[i]=q;
        }

        System.out.print("i"+"\t\t\t");
        for(int i=0;i<=n;i++){
            System.out.print(i+"\t");
        }
        System.out.println();
        System.out.print("result[i]"+"\t");
        for(int num:result){
            System.out.print(num+"\t");
        }
        System.out.println();
        System.out.print("solution[i]"+"\t");
        for(int num:solution){
            System.out.print(num+"\t");
        }

        System.out.println("\n");
        System.out.println("长度为 "+n+" 的最高价格为: "+result[n]);
        System.out.println("分割方法为：");
        while(n>0){
            System.out.print(solution[n]+"\t");
            n-=solution[n];
        }
    }

    public static void main(String[] args) {
        int[] price= {0,1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        bottomUpCutRod(price,9);
    }
}
