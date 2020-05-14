package dao.impl;

import dao.IProductDAO;

import vo.Product;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements IProductDAO {

    private Connection con;
    private PreparedStatement pst;
    public ProductDAOImpl(Connection con) {

        this.con = con;
    }


    @Override
    public List<Product> searchByBarCode(String barCode) throws Exception {  //通过条形码搜索函数
        List<Product> productList = new ArrayList<>();
        String sql = "select * from tproduct where barCode=?";
        this.pst = this.con.prepareStatement(sql);
        this.pst.setString(1, barCode);
        ResultSet rs = pst.executeQuery();
        productList = result(rs);
        return productList;
    }

    public List<Product> result(ResultSet rs) throws Exception{  //统一resultSet函数
        float price ;
        String supply,productName ,barCode;
        List<Product> productList = new ArrayList<>();
        while (rs.next()) {
            price = rs.getFloat("price");
            productName = rs.getString("productName");
            barCode = rs.getString("barCode");
            supply = rs.getString("supply");
            Product product = new Product(barCode, productName, price, supply);
            productList.add(product);
        }
        return productList;
    }
    @Override
    public boolean insertProduct(List<Product> productList, int index) throws Exception{  //插入产品信息函数
        String sql = "insert into tproduct values(?,?,?,?)";
        this.pst=this.con.prepareStatement(sql);
        for (int i = index; i < productList.size(); i++) {
            this.pst.setString(1,productList.get(i).getBarCode());
            this.pst.setString(2, productList.get(i).getProductName());
            this.pst.setFloat(3, productList.get(i).getPrice());
            this.pst.setString(4, productList.get(i).getSupply());
            this.pst.executeUpdate();
        }
        return true;
    }

    @Override
    public List<Product> searchProductName(String productName) throws Exception {  //根据产品名查找函数
        List<Product> productList = new ArrayList<>();
        String sql = "select * from tproduct where productName like?";
        this.pst = this.con.prepareStatement(sql);
        this.pst.setString(1, "%" + productName + "%");
        ResultSet rs = this.pst.executeQuery();
        productList =  result (rs);
        return productList;
    }

    @Override
    public List<Product> getAllProduct() throws Exception {  //获取所有产品函数
        List<Product> productList = new ArrayList<>();
        String sql = "select * from tproduct";
        this.pst=this.con.prepareStatement(sql);
        ResultSet rs = this.pst.executeQuery();
        productList=result(rs);
        return productList;
    }

}
