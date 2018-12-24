package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlDebugUtil implements DataBaseUtil{
    private static boolean autoCommit = false;
    private static MySqlDebugUtil ourInstance = new MySqlDebugUtil();

    private static Connection conn;

    public static MySqlDebugUtil getInstance() {
        return ourInstance;
    }

    private MySqlDebugUtil() {
        debugInit();
    }

    private static void debugInit(){
        try {
            Class.forName("com.mysql.jdbc.Driver") ;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shoppingcart?characterEncoding=UTF8","root","123456") ;
            conn.setAutoCommit(autoCommit);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConnection(){
        return conn;
    }
}
