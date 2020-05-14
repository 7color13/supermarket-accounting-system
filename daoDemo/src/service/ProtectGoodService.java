package service;

import dao.IProductDAO;

import dbc.DatabaseConnection;
import factory.DAOFactory;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import resources.FilePath;
import util.Validate;
import view.ProtectView;
import vo.Product;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ProtectGoodService {
    static Connection con;

    public static boolean inputFromExcel() throws Exception {      //从excel导入
        con = new DatabaseConnection().getConnection();
        List<Product> productList = new ArrayList<>();
        IProductDAO dao = DAOFactory.getProductDAOInstance(con);
        productList = dao.getAllProduct();
        int index = productList.size();
        File file = new File(FilePath.dataPath + FilePath.productExcelFile);
        Workbook workbook = Workbook.getWorkbook(file);
        Sheet sheet = workbook.getSheet(0);
        Cell cell = null;
        for (int i = 1; i < sheet.getRows(); i++) {   //依次读取导入
            String str = "";
            for (int j = 0; j < sheet.getColumns(); j++) {
                cell = sheet.getCell(j, i);
                str += cell.getContents() + " ";
            }
            String arr[] = str.split("\\s+");
            Product product = new Product(arr[0], arr[1], Float.parseFloat(arr[2]), arr[3]);
            productList.add(product);
        }
        productList=productList=productList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->
                new TreeSet<>(Comparator.comparing(product->product.getBarCode()))),ArrayList::new));
        dao.insertProduct(productList,index);
        System.out.println("成功导入" + (productList.size() - index) + "条数据");
        return true;
    }

    public static boolean inputFromTxt() throws Exception {    //从文本文件导入
        con = new DatabaseConnection().getConnection();
        List<Product> productList = new ArrayList<>();
        IProductDAO dao = DAOFactory.getProductDAOInstance(con);
        productList = dao.getAllProduct();
        int index = productList.size();
        File file = new File(FilePath.dataPath + FilePath.productTxtFile);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String data = "";
        while ((data = br.readLine()) != null) {
            String str[] = data.split("\\s+");
            Product product = new Product(str[0], str[1], Float.parseFloat(str[2]), str[3]);
            productList.add(product);
        }
        productList=productList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->
                new TreeSet<>(Comparator.comparing(product->product.getBarCode()))),ArrayList::new));
        dao.insertProduct(productList,index);
        System.out.println("成功导入" + (productList.size() - index) + "条数据");
        return true;
    }
  public static boolean inputFromKeyBoard() throws Exception{    //从键盘导入
      con = new DatabaseConnection().getConnection();
      List<Product> productList = new ArrayList<>();
      IProductDAO dao = DAOFactory.getProductDAOInstance(con);
      productList = dao.getAllProduct();
      int index = productList.size();
      String data = ProtectView.inputProductFromKeyboard();
      String arr[]=data.split(",");
      if (!Validate.verifyProduct(arr)){
          inputFromKeyBoard();
      }
      Product product = new Product(arr[0], arr[1], Float.parseFloat(arr[2]), arr[3]);
      productList.add(product);
      productList=productList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(()->
              new TreeSet<>(Comparator.comparing(product1->product1.getBarCode()))),ArrayList::new));
      if (index==productList.size()){
          System.out.println("条形码不能重复，请重新输入");
          inputFromKeyBoard();
      }
      dao.insertProduct(productList,index);
      System.out.println("成功导入" + (productList.size() - index) + "条数据");
      return true;
  }

    public static boolean searchByProductName() throws Exception{   //根据产品名查询
        List<Product> productList = null;
        con = new DatabaseConnection().getConnection();
        String productName = ProtectView.inputProductName();
        IProductDAO dao = DAOFactory.getProductDAOInstance(con);
        productList=dao.searchProductName(productName);
        ProtectView.printProductDetail(productList);
        return true;
    }
}
