package service;

import junit.framework.TestCase;
import util.MD5Util;
import vo.User;

public class UserServiceTest extends TestCase {

        public void test() throws Exception {
            UserService userService=new UserService();
            User user=new User();
            user.setUserName("root");
            user.setPassword(MD5Util.md5("jryf7741LR") );

            if(userService.checkLogin(user)) {
                System.out.println("登陆成功");

            }
            else {
                System.out.println("失败");
            }
            if (userService.changePassword()){
                System.out.println("成功");
            }
            else{
                System.out.println("失败了");
            }
        }
}