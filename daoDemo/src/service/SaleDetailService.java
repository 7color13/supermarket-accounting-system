package service;

import dao.IProductDAO;
import dao.ISaleDetailDAO;
import dbc.DatabaseConnection;
import factory.DAOFactory;
import util.GetDate;
import util.Validate;
import view.QueryView;
import view.SellingView;
import vo.SaleDetail;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SaleDetailService {
    private Connection con;

    public boolean selling() throws Exception {    //收银函数
        Scanner sc = new Scanner(System.in);
        SaleDetail saleDetail;
        List<SaleDetail> saleDetailList = new ArrayList<>();
        con = new DatabaseConnection().getConnection();
        IProductDAO dao1 = DAOFactory.getProductDAOInstance(con);
        ISaleDetailDAO dao2 = DAOFactory.getSaleDetailDAOInstance(con);
        String barCode = SellingView.inputFlowNum();
        Validate.verifyBarCode(barCode);
        while (dao1.searchByBarCode(barCode).size() == 0) {
            System.out.println("您输入的商品条形码不存在，请确认后重新输入");
            barCode = sc.nextLine();
        }
        int count = SellingView.inputCount();
        String todayStr = GetDate.getToday();
        String todayAnotherStr = GetDate.getAnotherToday();
        saleDetailList = dao2.getByDay(todayAnotherStr);
        int flag = saleDetailList.size();
        String flowNum = todayStr + GetDate.getAfterNum(flag);
        float price = dao1.searchByBarCode(barCode).get(0).getPrice();
        String productName = dao1.searchByBarCode(barCode).get(0).getProductName();
        String operator = UserService.user1.getUserName();
        String saletime = GetDate.getTime();
        saleDetail = new SaleDetail(flowNum, barCode, productName, price, count, operator, saletime);
        dao2.doInsert(saleDetail);
        return true;
    }

    public boolean queryDay() throws Exception {    //根据日期查询
        con = new DatabaseConnection().getConnection();
        Scanner sc = new Scanner(System.in);
        String day = QueryView.inputDay();
        if (!Validate.verifyDate(day)) {
            queryDay();
        }
        ISaleDetailDAO dao = DAOFactory.getSaleDetailDAOInstance(con);
        List<SaleDetail> saleDetailList = dao.getByDay(day);
        day = GetDate.replaceTime(day);
        int countSum = saleDetailList.stream().collect(Collectors.summingInt(SaleDetail::getCount));
        double priceSum = saleDetailList.stream().mapToDouble(SaleDetail::getPrice).sum();
        QueryView.printDetail(day, saleDetailList, countSum, priceSum);
         sc.nextLine();
         return true;
    }
}
