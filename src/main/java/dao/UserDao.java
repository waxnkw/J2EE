package dao;

import model.User;

public interface UserDao {
    boolean isValidUser(Long uid, String password);
    User queryUser(Long uid);
    boolean buy(Long uid, double cost);
}
