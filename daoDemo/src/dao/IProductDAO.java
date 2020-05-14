package dao;

import vo.Product;

import java.util.List;

public interface IProductDAO {   //产品接口
    public List<Product> searchByBarCode(String barCode) throws Exception;
    public boolean insertProduct(List<Product> productList,int index) throws Exception;
    public List<Product> searchProductName(String productName) throws Exception;
    public List<Product> getAllProduct() throws Exception;
}
