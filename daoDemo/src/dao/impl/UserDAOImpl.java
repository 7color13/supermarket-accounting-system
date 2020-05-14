package dao.impl;

import dao.IUserDAO;
import util.MD5Util;
import vo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {

    private Connection con;
    private PreparedStatement pst;
    public UserDAOImpl(Connection con){
        this.con=con;
    }



    @Override
    public User getById(String id) throws Exception {   //根据用户名查询密码函数
        User user = null;
        String sql= "select * from tuser where userName=?";
        this.pst=this.con.prepareStatement(sql);
        this.pst.setString(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()){
            user =new User();
            user.setUserName(rs.getString("userName"));
            user.setChrName(rs.getString("chrName"));
            user.setPassword(rs.getString( "password") );
            user.setRole(rs.getString("role"));
        }
        return user;
    }

    @Override
    public List<User> doQuery(User user) throws Exception {  //查询函数（实际没用上）
        List<User>  usersList = new ArrayList<>();
        String sql="select * from tuser";
        this.pst=this.con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()){
            user = new User();
            user.setUserName(rs.getString("userName"));
            user.setChrName(rs.getString("chrName"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            usersList.add(user);
        }
        return usersList;
    }

    @Override
    public boolean doInsert(User user) throws Exception { //插入函数
        String sql = "Insert into tuser values(?,?,?,?)";
        this.pst=this.con.prepareStatement(sql);
        this.pst.setString(1, user.getUserName());
        this.pst.setString(2, user.getPassword());
        this.pst.setString(3, user.getChrName());
        this.pst.setString(4, user.getRole());

        if (this.pst.executeUpdate()>0){
            return true;
        }
        return false;
    }

    @Override
    public  boolean doUpdate(User user) throws Exception {   //更新函数
        String sql = "UPDATE tuser set chrName=? ,password =? ,role=? where userName =?";
        this.pst = this.con.prepareStatement(sql);
        this.pst.setString(1,user.getChrName());
        this.pst.setString(2, user.getPassword());
        this.pst.setString(3, user.getRole());
        this.pst.setString(4, user.getUserName());
        if (this.pst.executeUpdate()>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean doDelete(String id) throws Exception {  //删除函数
        User user = new User();
        String sql = "DELETE from tuser where userName=?";
        this.pst=this.con.prepareStatement(sql);
        this.pst.setString(1,user.getUserName());
        if (this.pst.executeUpdate()>0){
            return true;
        }
        return false;
    }

}
