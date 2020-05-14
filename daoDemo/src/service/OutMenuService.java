package service;

import view.OutMenuView;

public class OutMenuService {   //输出相关的菜单
    public static boolean outFile() throws Exception{
        int choice = OutMenuView.printOutMenu();
        while(choice!=3){
            switch (choice){
                case 1:OutFileService.outputToExcel();
                    break;
                case 2:OutFileService.outputToTxt();
                    break;
            }
            choice=OutMenuView.printOutMenu();
        }
        return true;
    }
}
