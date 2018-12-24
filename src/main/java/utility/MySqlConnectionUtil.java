package utility;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlConnectionUtil implements DataBaseUtil{
    private static MySqlConnectionUtil ourInstance = new MySqlConnectionUtil();

    public static  MySqlConnectionUtil getInstance() {
        return ourInstance;
    }
    private static DataSource datasource;

    private MySqlConnectionUtil() {
        init();
    }

    private static void init(){
        InitialContext jndiContext = null;
        Properties properties = new Properties();
        properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
        properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        try {
            jndiContext = new InitialContext(properties);
            datasource = (DataSource) jndiContext.lookup("java:comp/env/jdbc/shoppingcart");
            System.out.println("got context");
            System.out.println("About to get ds---ShowShoppingCart");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public synchronized Connection getConnection(){
        Connection connection = null;
        try {
            connection = datasource.getConnection();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
