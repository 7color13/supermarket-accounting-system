package view;

import util.MD5Util;
import vo.User;

import java.util.Scanner;

public class LoginView {
    public static User show() {
        System.out.println("欢迎使用****超市收银系统，请登录");
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userName = scan.next();
        System.out.println("请输入密码：");
        String password = scan.next();
        String miwen = MD5Util.md5(password);
        User user = new User(userName, miwen,null,null);
        return user;
    }
}
