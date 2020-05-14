package dao.impl;

import dao.ISaleDetailDAO;
import vo.SaleDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SaleDetailDAOImpl implements ISaleDetailDAO {

    private Connection con;
    private PreparedStatement pst;
    public SaleDetailDAOImpl(Connection con){
        this.con=con;
    }

    @Override
    public boolean doInsert(SaleDetail saleDetail) throws Exception {   //插入函数
        String sql = "Insert into tsaledetail values(?,?,?,?,?,?,?)";
        this.pst=this.con.prepareStatement(sql);
        this.pst.setString(1, saleDetail.getFlowNum());
        this.pst.setString(2, saleDetail.getBarCode());
        this.pst.setString(3, saleDetail.getProductName());
        this.pst.setFloat(4, saleDetail.getPrice());
        this.pst.setInt(5, saleDetail.getCount());
        this.pst.setString(6, saleDetail.getOperator());
        this.pst.setString(7, saleDetail.getSaletime());


        if (this.pst.executeUpdate()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean doUpdate(SaleDetail saleDetail) throws Exception {

        return true;
    }

    @Override
    public List<SaleDetail> getByDay(String todayStr) throws Exception {  //根据日期获取函数，可代码复用成select *函数

        List<SaleDetail> saleDetailList = new ArrayList<>();

        String sql = "select * from tsaledetail where saletime like ? ";
        this.pst = this.con.prepareStatement(sql);
        this.pst.setString(1, todayStr + "%");

        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setFlowNum(rs.getString("flowNum"));
            saleDetail.setBarCode(rs.getString("barCode"));
            saleDetail.setProductName(rs.getString("productName"));
            saleDetail.setCount(rs.getInt("count"));
            saleDetail.setOperator(rs.getString("operator"));
            saleDetail.setSaletime(rs.getString("saletime"));
            saleDetail.setPrice(rs.getFloat("price"));
            saleDetailList.add(saleDetail);
        }
        return saleDetailList;
    }

}
