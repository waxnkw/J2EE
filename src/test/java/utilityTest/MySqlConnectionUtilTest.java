package utilityTest;

import org.junit.Assert;
import org.junit.Test;
import utility.MySqlConnectionUtil;
import utility.MySqlDebugUtil;

import java.sql.Connection;

public class MySqlConnectionUtilTest {
    @Test
    public void getConnection(){
        Connection connection = MySqlDebugUtil.getInstance().getConnection();
        Assert.assertEquals(false, connection==null);
    }
}
