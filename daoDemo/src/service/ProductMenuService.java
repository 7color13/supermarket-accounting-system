package service;

import view.protectMenuView;

import java.sql.Connection;

public class ProductMenuService {
    private Connection con;
    ProtectGoodService protectGoodService;
    public static boolean chooseConfig() throws Exception{   //产品相关的菜单
        int choose = protectMenuView.showProtectMenu();
        if (!UserService.user1.getRole().equals("管理员")){
            choose=5;
        }
        while(choose!=5){
            switch (choose){
                case 1:
                    ProtectGoodService.inputFromExcel();break;
                case 2:
                    ProtectGoodService.inputFromTxt();
                    break;
                case 3:
                    ProtectGoodService.inputFromKeyBoard();
                    break;
                case 4:ProtectGoodService.searchByProductName();
                    break;

                default:
                    System.out.println("无效的选择");
            }
            choose = protectMenuView.showProtectMenu();
        }
        return true;
    }

}
