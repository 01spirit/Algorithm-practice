package 动态规划;

//最长公共子序列 LongestCommonSubSequence LCS
//动态规划算是考试的一个重点，需要对几个例题的递归公式很熟悉，矩阵链乘和LCS都有根据公式填充矩阵的题
//公式不一定会给，也有可能要求画完整的矩阵，建议亲手按照书上的伪代码画一次，加深理解
//最大子段和也有动态规划的解法，建议尝试
public class LCS {
    public static String[][] LCS_Length(String X,String Y){
        int m=X.length();
        int n=Y.length();
        String[][] b=new String[m+1][n+1];
        int[][] c=new int[m+1][n+1];

        //初始化两个矩阵，c[][] 存储每一步的最大子串长度，s[][] 存储LCS的指示符
        for(int i=0;i<=m;i++){
            c[i][0]=0;
            b[i][0]="";
        }
        for(int j=0;j<=n;j++){
            c[0][j]=0;
            b[0][j]="";
        }

        //求出两个矩阵的内容，用于求解LCS
        //注意数组下标，建议对照书上的矩阵
        //*此时求的是 c[i+1][j+1] ，用 左、上、左上 的 c[][] 进行比较
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(X.charAt(i)==Y.charAt(j)){//若成功匹配，LCS长度增加
                    c[i+1][j+1]=c[i][j]+1;
                    b[i+1][j+1]="\\";//指示 左上
                }
                else if(c[i][j+1]>=c[i+1][j]){//未成功匹配，比较 左 和 上 的 c[][]
                    c[i+1][j+1]=c[i][j+1];
                    b[i+1][j+1]="^";//指示 上
                }
                else{
                    c[i+1][j+1]=c[i+1][j];
                    b[i+1][j+1]="<-";//指示 左
                }
            }
        }

        //输出
        System.out.println("LCS长度： "+c[m][n]);
        System.out.println("matrix:");
        System.out.print("\t");
        for(int i=0;i<=n;i++){
            System.out.print(i+"\t");
        }
        System.out.println();
        for(int i=0;i<=m;i++){
            System.out.print(i+"\t");
            for(int j=0;j<=n;j++){
                System.out.print(c[i][j]+b[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.print("\nLCS: ");
        printLCS(b,X,m,n);

        return b;
    }

    //用于从 b[][] 中提取出LCS，注意 b[][] 的下标和原字符串下标的对应
    //初始输入的 i，j 为 X，Y 的长度 m，n ，从右下向左上按照 b[][] 的指示递归寻找，直到走到左上角，开始返回并按正确顺序输出匹配的字符
    public static void printLCS(String[][] b,String X,int i,int j){
        if(i==0||j==0){
            return;
        }
        if(b[i][j]=="\\"){//指示 左上 的表示匹配成功的字符，输出
            printLCS(b,X,i-1,j-1);
            System.out.print(X.charAt(i-1));
        }
        else if(b[i][j]=="^"){//向 左 和 上 寻找下一个匹配的字符
            printLCS(b,X,i-1,j);
        }
        else{
            printLCS(b,X,i,j-1);
        }
    }

    public static void main(String[] args) {
        String X="ABCBDAB";
        String Y="BDCABA";
        LCS_Length(X,Y);

    }
}
