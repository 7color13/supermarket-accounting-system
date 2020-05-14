package factory;

import dao.IProductDAO;
import dao.ISaleDetailDAO;
import dao.IUserDAO;
import dao.impl.ProductDAOImpl;
import dao.impl.SaleDetailDAOImpl;
import dao.impl.UserDAOImpl;

import java.sql.Connection;

public class DAOFactory {

    public static IUserDAO getUserDAOInstance(Connection con) {

        return new UserDAOImpl(con);
    }
    public static ISaleDetailDAO getSaleDetailDAOInstance(Connection con) {

        return new SaleDetailDAOImpl(con);
    }

    public static IProductDAO getProductDAOInstance(Connection con) {

        return new ProductDAOImpl(con);
    }

}
