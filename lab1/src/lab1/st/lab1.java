package lab1.st;

import java.util.ArrayList;
import java.util.List;

public class lab1 {

    ArrayList solution = new ArrayList();//储存所有情况的结果

    public boolean judge(int num){//输入钱数看看是否能
        int[] money = new int[]{50,20,5,5,1,1,1};
        for(int i = 1; i < 8; i++){
            print(combine(money,i));
        }
        System.out.println(solution.contains(new Integer(num)));
        return solution.contains(new Integer(num));
    }

//    public static void main(String args[]) {
//        lab1 lab = new lab1();
//        System.out.println(lab.judge(70));
//    }




    public List combine(int[] a,int m){
        int n = a.length;


        List result = new ArrayList();

        int[] bs = new int[n];
        for(int i=0;i<n;i++){
            bs[i]=0;
        }
        //初始化
        for(int i=0;i<m;i++){
            bs[i]=1;
        }
        boolean flag = true;
        boolean tempFlag = false;
        int pos = 0;
        int sum = 0;
        do{
            sum = 0;
            pos = 0;
            tempFlag = true;
            result.add(print(bs,a,m));

            for(int i=0;i<n-1;i++){
                if(bs[i]==1 && bs[i+1]==0 ){
                    bs[i]=0;
                    bs[i+1]=1;
                    pos = i;
                    break;
                }
            }

            for(int i=0;i<pos;i++){
                if(bs[i]==1){
                    sum++;
                }
            }
            for(int i=0;i<pos;i++){
                if(i<sum){
                    bs[i]=1;
                }else{
                    bs[i]=0;
                }
            }

            for(int i= n-m;i<n;i++){
                if(bs[i]==0){
                    tempFlag = false;
                    break;
                }
            }
            if(tempFlag==false){
                flag = true;
            }else{
                flag = false;
            }

        }while(flag);
        result.add(print(bs,a,m));

        return result;
    }

    private int[] print(int[] bs,int[] a,int m){
        int[] result = new int[m];
        int pos= 0;
        for(int i=0;i<bs.length;i++){
            if(bs[i]==1){
                result[pos]=a[i];
                pos++;
            }
        }
        return result ;
    }

    private void print(List l){
        for(int i=0;i<l.size();i++){
            int[] a = (int[])l.get(i);
            int sum = 0;
            for(int j=0;j<a.length;j++){
                //System.out.print(a[j] + " ");

                sum = sum + a[j];

            }
            solution.add(sum);
        }
    }
}
