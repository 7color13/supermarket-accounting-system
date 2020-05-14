package dao;

import vo.User;

import java.util.List;

public interface IUserDAO {   //用户接口
    public User getById(String id) throws Exception;
    public List<User> doQuery(User user) throws Exception;
    public boolean doInsert (User user) throws Exception;
    public boolean doUpdate(User user) throws Exception;
    public boolean doDelete (String id) throws Exception;
}
