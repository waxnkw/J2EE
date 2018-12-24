package daoTest;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import org.junit.Assert;
import org.junit.Test;

public class UserDaoTest {
    public UserDao dao = new UserDaoImpl();

    @Test
    public void testIsValidUser(){
        boolean isValid;
        isValid = dao.isValidUser(1L, "123456");
        Assert.assertEquals(true, isValid);

        isValid = dao.isValidUser(2L, "123456");
        Assert.assertEquals(true, isValid);

        isValid = dao.isValidUser(3L, "123456");
        Assert.assertEquals(false, isValid);

        isValid = dao.isValidUser(78L, "123456");
        Assert.assertEquals(false, isValid);
    }

    @Test
    public void testQueryUser(){
        double money = dao.queryUser(1L).getMoney();
        Assert.assertEquals(1000, (int)money);

        money = dao.queryUser(2L).getMoney();
        Assert.assertEquals(1000, (int)money);
    }

    @Test
    public void testBuy(){
        boolean success;
        success = dao.buy(1L, 100);
        Assert.assertEquals(900, (int) dao.queryUser(1L).getMoney());
        Assert.assertEquals(true, success);

        success = dao.buy(2L, 10000);
        Assert.assertEquals(1000, (int) dao.queryUser(2L).getMoney());
        Assert.assertEquals(false, success);
    }
}
