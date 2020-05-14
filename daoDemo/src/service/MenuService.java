package service;

import view.ExitView;
import view.MenuView;

public class MenuService {   //菜单函数
    public static void menuChoose() throws Exception{
        SaleDetailService saleDetailService = new SaleDetailService();
        UserService userService = new UserService();
        int choose = MenuView.showMenu();
        while(true){
            switch(choose){
                case 1:saleDetailService.selling();break;
                case 2:saleDetailService.queryDay();break;
                case 3:ProductMenuService.chooseConfig();break;
                case 4:userService.changePassword();break;
                case 5:OutMenuService.outFile();break;
                case 6:ExitView.exitSystem();break;
            }
            choose = MenuView.showMenu();
        }
    }

}
