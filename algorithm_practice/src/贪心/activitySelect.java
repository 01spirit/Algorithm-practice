package 贪心;

import java.util.ArrayList;
import java.util.List;

//活动选择问题
public class activitySelect {
    public static List<Integer> greedyActivitySelect(int[] startTime,int[] finishTime){
        int n=startTime.length;
        List activity=new ArrayList<Integer>();
        activity.add(0);//活动0用来占位，无实际意义，因为伪代码里的起始下标是1，麻烦
        activity.add(1);
        int k=1;

        //贪心的思路就是选择当前活动结束后的第一个活动，然后再找新一个活动之后的下一个活动，
        //使用循环的前提是活动都是按照结束时间由小到大排序好的，没排序的话就把s[]和f[]一起先排序
        for(int i=2;i<n;i++){
            if(startTime[i]>=finishTime[k]){
                activity.add(i);
                k=i;
            }
        }
        return activity;
    }

    public static void main(String[] args) {
        int[] startTime={0,1,3,0,5,3,5,6,8,8,2,12};
        int[] finishTime={0,4,5,6,7,9,9,10,11,12,14,16};//提供的活动是已经按照结束时间由大到小排好序的
        List<Integer> list=greedyActivitySelect(startTime, finishTime);
        Integer[] acts=list.toArray(new Integer[list.size()]);

        System.out.println("selected activities:");
        for(int i=1;i<acts.length;i++){
            System.out.print(acts[i]+"\t");
        }

    }
}
