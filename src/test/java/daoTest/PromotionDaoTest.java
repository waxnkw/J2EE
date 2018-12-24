package daoTest;

import dao.PromotionDao;
import daoImpl.PromotionImpl;
import org.junit.Assert;
import org.junit.Test;

import java.text.DecimalFormat;

public class PromotionDaoTest {
    private PromotionDao dao = new PromotionImpl();

    @Test
    public void testPromotion(){
        Double rate = dao.getPromotion(100);
        DecimalFormat df = new DecimalFormat("0.0");
        Assert.assertEquals("0.9", df.format(rate));

        rate = dao.getPromotion(0);
        Assert.assertEquals(null,rate );

        rate = dao.getPromotion(666666);
        Assert.assertEquals("0.7", df.format(rate));
    }
}
