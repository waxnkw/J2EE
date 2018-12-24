package daoImpl;

import dao.BuyInfoDao;
import model.BuyInfo;
import utility.MySqlConnectionUtil;
import utility.MySqlDebugUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BuyInfoDaoImpl implements BuyInfoDao {
    private MySqlConnectionUtil util = MySqlConnectionUtil.getInstance();

    @Override
    public void addBuyInfo(Long uid, Long gid, int num) {
        PreparedStatement stmt ;
        Connection connection = util.getConnection();
        try {
            if (isExist(uid, gid)){deleteBuyInfo(uid, gid);}
            stmt = connection.prepareStatement("insert into buyinfo(`uid`,`gid`,`num`) value(?,?,?);");
            stmt.setLong(1, uid);
            stmt.setLong(2, gid);
            stmt.setInt(3, num);
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBuyInfo(Long uid, Long gid) {
        PreparedStatement stmt ;
        String sql = "DELETE FROM buyinfo WHERE uid=? and gid=?;";
        Connection connection = util.getConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, uid);
            stmt.setLong(2, gid);
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBuyInfoByUid(Long uid) {
        PreparedStatement stmt ;
        String sql = "DELETE FROM buyinfo WHERE uid=?;";
        Connection connection = util.getConnection();
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, uid);
            stmt.execute();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BuyInfo> listBuyInfoByUid(Long uid) {
        List<BuyInfo> buyInfos = new ArrayList<>();
        PreparedStatement stmt ;
        ResultSet result;
        String sql = "select uid,g.gid as gid,gname,num,description,price,category " +
                "from goods g,buyinfo b where b.gid=g.gid and b.uid=?;";

        Connection connection = util.getConnection();
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, uid);
            result = stmt.executeQuery();
            while(result.next()){
                buyInfos.add(fullfillBuyInfo(result));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return buyInfos;
    }

    private boolean isExist(Long uid, Long gid){
        List<BuyInfo> buyInfos = new ArrayList<>();
        PreparedStatement stmt ;
        ResultSet result;
        boolean exist = false;
        String sql = "select uid " +
                "from buyinfo where uid=? and gid=?;";
        Connection connection = util.getConnection();
        try{
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, uid);
            stmt.setLong(2, gid);
            result = stmt.executeQuery();
            exist = result.next();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exist;
    }

    private BuyInfo fullfillBuyInfo(ResultSet result) throws SQLException {
        BuyInfo buyInfo = new BuyInfo();
        buyInfo.setUid(result.getLong("uid"));
        buyInfo.setGid(result.getLong("gid"));
        buyInfo.setDescription(result.getString("description"));
        buyInfo.setNum(result.getInt("num"));
        buyInfo.setPrice(result.getDouble("price"));
        buyInfo.setGname(result.getString("gname"));
        buyInfo.setCategory(result.getString("category"));
        buyInfo.calcCost();
        return buyInfo;
    }
}
