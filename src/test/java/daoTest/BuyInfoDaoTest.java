package daoTest;

import dao.BuyInfoDao;
import daoImpl.BuyInfoDaoImpl;
import model.BuyInfo;
import org.junit.Test;

import java.util.List;

public class BuyInfoDaoTest {
    private BuyInfoDao dao = new BuyInfoDaoImpl();

    @Test
    public void testAddBuyInfo(){
        dao.addBuyInfo(1L, 2L, 4);
        dao.addBuyInfo(1L, 2L, 4);
    }

    @Test
    public void testDeleteBuyInfo(){
        dao.addBuyInfo(1L, 2L, 4);
        dao.deleteBuyInfo(1L,2L );
        dao.deleteBuyInfo(1L, 2L);
    }

    @Test
    public void deleteBuyInfoByUid(){
        dao.addBuyInfo(1L, 2L, 1);
        dao.deleteBuyInfo(1L, 2L);
    }

    @Test
    public void list(){
        dao.addBuyInfo(1L, 2L, 1);
        dao.addBuyInfo(1L, 3L, 2);
        dao.addBuyInfo(1L, 4L, 3);
        dao.addBuyInfo(1L, 5L, 4);
        dao.addBuyInfo(1L, 6L, 5);
        List<BuyInfo> buyInfos = dao.listBuyInfoByUid(1L);
        for (BuyInfo buyInfo: buyInfos){
            System.out.print(buyInfo.getUid()+" ");
            System.out.print(buyInfo.getGid()+" ");
            System.out.print(buyInfo.getGname()+" ");
            System.out.print(buyInfo.getNum()+" ");
            System.out.print(buyInfo.getPrice()+" ");
            System.out.println(buyInfo.getCost());
        }
    }
}
