package service;

import dao.IUserDAO;
import dbc.DatabaseConnection;
import factory.DAOFactory;
import test.Driver;
import util.MD5Util;
import util.Validate;
import view.LoginView;
import vo.User;

import java.sql.Connection;
import java.util.Scanner;

public class UserService {
    private Connection con;
    static int loginCount=1;
    public static User user1;
    public  boolean checkLogin(User user) throws Exception{   //检查登录函数
      con = new DatabaseConnection().getConnection();
      IUserDAO dao = DAOFactory.getUserDAOInstance(con);
       user1=dao.getById(user.getUserName());
      if (user1.getPassword().equals(user.getPassword())){
          return true;
      }
      else{
          loginCount++;
          if (loginCount > 3) {
              System.out.println("最多只能尝试3次，即将退出！");
              return false;
          }
          System.out.println("用户名或密码不正确");
          Driver.user= LoginView.show();
          checkLogin(Driver.user);
      }
      return true;
    }

    public boolean changePassword() throws Exception {    //修改密码函数
        con = new DatabaseConnection().getConnection();
        Scanner sc = new Scanner(System.in);
        String password = null, newPass = null;
        System.out.println("请输入原密码");
        password = sc.next();
        while (!MD5Util.md5(password).equals(user1.getPassword())) {
            System.out.println("原密码输入不正确，请重新输入:");
            password = sc.next();
        }
        newPass = settingPassword();
        IUserDAO dao = DAOFactory.getUserDAOInstance(con);
        user1.setPassword(MD5Util.md5(newPass));
        if (dao.doUpdate(user1)) {
            System.out.println("密码修改成功");
            return true;
        }
        return false;
    }
    public String settingPassword(){   //设置新密码函数
        Scanner sc =new Scanner(System.in);
        String password=null,newPass=null,verifyPass=null;
        System.out.println("请输入新密码");
        newPass = sc.next();
        if (!Validate.verifyPassword(newPass)){
            System.out.println("您的密码不符合复杂性要求");
            password = settingPassword();
        }
        else{
            System.out.println("请再次输入新密码");
            verifyPass=sc.next();
            if (!verifyPass.equals(newPass)){
                System.out.println("两次输入的密码必须一致，请重新输入密码");
                password=settingPassword();
            }
            else{
                password=newPass;
            }
        }
        return password;
    }
}
