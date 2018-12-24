package dao;

import model.Goods;

import java.util.List;

public interface GoodsDao {
    /**
     * @param n (10n, 10n+10)
     * */
    List<Goods> listGoodsByCategory(String category, int n);
    List<Goods> listGoods(int n);
}
