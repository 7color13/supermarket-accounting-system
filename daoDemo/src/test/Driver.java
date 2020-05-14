package test;

import service.MenuService;
import service.UserService;
import view.LoginView;
import view.MenuView;
import vo.User;

public class Driver {   //主函数
    public static User user;

    public static void main(String[] args) throws Exception {
        UserService userService = new UserService();
        user= LoginView.show();
        if (userService.checkLogin(user)){
            MenuService.menuChoose();
        }
        else{
            System.out.println("程序已退出");
        }
    }
}
