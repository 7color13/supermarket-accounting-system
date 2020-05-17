package view;

import util.Validate;
import vo.Product;

import java.util.List;
import java.util.Scanner;

public class ProtectView {
    public static String inputProductFromKeyboard(){
        Scanner sc = new Scanner(System.in);
        System.out.println("从键盘依次录入商品信息，格式为“商品条形码，商品名称，单价，供应商”");
        String data = sc.nextLine();
        return data;
    }
    public static String inputProductName(){
        Scanner sc =new Scanner(System.in);
        System.out.println("请输入查询的商品名称：");
        String productName=sc.next();
        return productName;
    }

    public static void printProductDetail(List<Product> productList) {
        int flag=1;
        System.out.println("满足条件的记录总共"+productList.size()+"条，信息如下：\n" +
                "序号\t条形码\t商品名称\t单价\t供应商\t\n" +
                "===\t\t=====\t=======\t=\t===\t\t\n");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(flag + "\t" + productList.get(i).getBarCode() +"\t" +
                    productList.get(i).getProductName() +"\t" + productList.get(i).getPrice() +"\t" +
                    productList.get(i).getSupply());
            flag++;
        }
    }
}
