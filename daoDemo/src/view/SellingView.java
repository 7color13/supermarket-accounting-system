package view;

import java.util.Scanner;

public class SellingView {
    public static String inputFlowNum(){
        Scanner sc= new Scanner(System.in);
        System.out.println("请输入商品条形码（6位数字字符）：");
        String barCode = sc.next();
        return barCode;
    }
    public static int inputCount(){
        Scanner sc= new Scanner(System.in);
        System.out.println("输入商品数量：");
        int count=sc.nextInt();
        return count;
    }
}
