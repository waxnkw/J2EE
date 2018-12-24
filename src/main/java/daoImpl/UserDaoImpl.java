package daoImpl;

import dao.UserDao;
import model.User;
import utility.MySqlConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    private MySqlConnectionUtil util  = MySqlConnectionUtil.getInstance();

    @Override
    public boolean isValidUser(Long uid, String password) {
        PreparedStatement stmt ;
        ResultSet result;
        boolean isValid = false;

        Connection connection = util.getConnection();
        try {
            stmt = connection.prepareStatement("select uid from user where uid = ? and password = ?");
            stmt.setLong(1, uid);
            stmt.setString(2, password);
            result = stmt.executeQuery();
            isValid = result.next();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    @Override
    public User queryUser(Long uid) {
        User user = null;
        PreparedStatement stmt ;
        ResultSet result;

        Connection connection = util.getConnection();
        try {
            stmt = connection.prepareStatement("select * from user where uid = ?");
            stmt.setLong(1, uid);
            result = stmt.executeQuery();
            boolean isExisted = result.next();

            assert isExisted==true;

            //set user
            user = new User();
            user.setUid(uid);
            user.setMoney(result.getDouble("money"));
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean buy(Long uid, double cost) {
        PreparedStatement stmt ;
        ResultSet result;
        Connection connection = util.getConnection();
        try {
            stmt = connection.prepareStatement("select money from user where uid = ?;");
            stmt.setLong(1, uid);
            result = stmt.executeQuery();
            boolean isExisted = result.next();

            assert isExisted==true;

            double money = result.getDouble("money");
            if (money<cost){return false;}
            money -= cost;

            stmt = connection.prepareStatement("UPDATE user SET money=? WHERE uid=?;");
            stmt.setDouble(1, money);
            stmt.setDouble(2, uid);
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
