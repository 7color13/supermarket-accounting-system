package service;

import view.protectMenuView;

import java.sql.Connection;

public class ProductMenuService {
    private Connection con;
    ProtectGoodService protectGoodService;
    public static boolean chooseConfig() throws Exception{   //产品相关的菜单
        int choose = protectMenuView.showProtectMenu();
        ProtectGoodService pgs = new ProtectGoodService();
        if (!UserService.user1.getRole().equals("管理员")){
            choose=5;
        }
        while(choose!=5){
            switch (choose){
                case 1:
                    pgs.inputFromExcel();break;
                case 2:
                    pgs.inputFromTxt();
                    break;
                case 3:
                    pgs.inputFromKeyBoard();
                    break;
                case 4:pgs.searchByProductName();
                    break;

                default:
                    System.out.println("无效的选择");
            }
            choose = protectMenuView.showProtectMenu();
        }
        return true;
    }

}
