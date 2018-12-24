package daoImpl;

import dao.GoodsDao;
import model.Goods;
import utility.MySqlConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDaoImpl implements GoodsDao {
    private MySqlConnectionUtil util = MySqlConnectionUtil.getInstance();

    @Override
    public List<Goods> listGoodsByCategory(String category, int n) {
        PreparedStatement stmt ;
        ResultSet result;
        List<Goods> goodsList = new ArrayList<>();
        Connection connection = util.getConnection();
        try {
            stmt = connection.prepareStatement("select * from goods where category=? limit ?,?;");
            stmt.setString(1, category);
            stmt.setInt(2, n*10);
            stmt.setInt(3, 10);
            result = stmt.executeQuery();
            while(result.next()){
                goodsList.add(fullfillGoods(result));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    @Override
    public List<Goods> listGoods(int n) {
        PreparedStatement stmt ;
        ResultSet result;
        List<Goods> goodsList = new ArrayList<>();
        Connection connection = util.getConnection();
        try {
            stmt = connection.prepareStatement("select * from goods limit ?,?;");
            stmt.setInt(1, n*10);
            stmt.setInt(2, 10);
            result = stmt.executeQuery();
            while(result.next()){
                goodsList.add(fullfillGoods(result));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

    private Goods fullfillGoods(ResultSet result){
        Goods goods = new Goods();
        try {
            goods.setGid(result.getLong("gid"));
            goods.setGname(result.getString("gname"));
            goods.setCategory(result.getString("category"));
            goods.setPrice(result.getDouble("price"));
            goods.setDescription(result.getString("description"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    public void addGoods(List<Goods> goods){
        PreparedStatement stmt ;
        ResultSet result;
        String sql = "INSERT INTO " +
                "goods(`gid`,`gname`, `category`, `price`, `description`) " +
                "VALUES (?,?,?,?,?);";
        Connection connection = util.getConnection();
        try {
            connection.setAutoCommit(true);
            for (Goods goods1: goods){
                stmt = connection.prepareStatement(sql);
                stmt.setLong(1, goods1.getGid());
                stmt.setString(2, goods1.getGname());
                stmt.setString(3, goods1.getCategory());
                stmt.setDouble(4, goods1.getPrice());
                stmt.setString(5, goods1.getDescription());
                stmt.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
