package dao;

import model.BuyInfo;

import java.util.List;

public interface BuyInfoDao {
    void addBuyInfo(Long uid, Long gid, int num);
    void deleteBuyInfo(Long uid, Long gid);
    void deleteBuyInfoByUid(Long uid);
    List<BuyInfo> listBuyInfoByUid(Long uid);
}
