package daoImpl;

import dao.PromotionDao;
import utility.MySqlConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionImpl implements PromotionDao {
    private Connection connection = MySqlConnectionUtil.getInstance().getConnection();

    @Override
    public Double getPromotion(int cost) {
        PreparedStatement stmt ;
        ResultSet result;
        Double rate = null;
        try {
            stmt = connection.prepareStatement("select rate from promotion where low<=? and high>?;");
            stmt.setInt(1, cost);
            stmt.setInt(2, cost);
            result = stmt.executeQuery();
            if (result.next()){rate = result.getDouble("rate");}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rate;
    }
}
