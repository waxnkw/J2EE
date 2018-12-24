package daoTest;

import dao.GoodsDao;
import daoImpl.GoodsDaoImpl;
import model.Goods;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GoodsDaoTest {
    private GoodsDao dao = new GoodsDaoImpl();
    @Test
    public void testListGoods(){
        List<Goods> goodsList = dao.listGoods(0);
        for (int i=0; i<10; i++){
            Assert.assertEquals((i+1), (long)goodsList.get(i).getGid());
        }
        goodsList = dao.listGoods(4);
        Assert.assertEquals(2, goodsList.size());
    }

    @Test
    public void testListGoodsByCate(){
        List<Goods> goodsList = dao.listGoodsByCategory("饮料", 0);
        for (int i=0; i<10; i++){
            Assert.assertEquals((i+18), (long)goodsList.get(i).getGid());
        }
        goodsList = dao.listGoodsByCategory("饮料", 2);
        Assert.assertEquals(5, goodsList.size());
    }

    @Test
    public void addGoods(){
        String category = "饮料";
        String namePattern = "个核桃";
        int baseIndex = 17;
        int base = 10;
        int factor = 2;
        GoodsDaoImpl impl = new GoodsDaoImpl();
        List<Goods> goodsList = new ArrayList<>();
        for (int i=0; i<25; i++){
            Goods goods = new Goods();
            goods.setGid((long)(baseIndex+i+1));
            goods.setGname((i+1)+namePattern);
            goods.setCategory(category);
            goods.setPrice(base+i*factor);
            goods.setDescription(goods.getGname()+goods.getCategory());
            goodsList.add(goods);
        }
        impl.addGoods(goodsList);
    }
}
