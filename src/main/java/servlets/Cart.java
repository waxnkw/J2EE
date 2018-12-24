package servlets;

import dao.BuyInfoDao;
import dao.GoodsDao;
import dao.PromotionDao;
import dao.UserDao;
import daoImpl.BuyInfoDaoImpl;
import daoImpl.GoodsDaoImpl;
import daoImpl.PromotionImpl;
import daoImpl.UserDaoImpl;
import html.CartHtml;
import html.MallHtml;
import model.BuyInfo;
import model.Goods;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/Cart")
public class Cart extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession(false);
        if (session==null){
            System.out.println("session is null");
            response.sendRedirect("/Login");
            return;
        }

        Long uid = (Long) session.getAttribute("uid");
        String op = request.getParameter("op");
        PrintWriter out = response.getWriter();

        String jid = response.encodeURL("/Cart").contains(";")?";jsessionid="+session.getId():"";

        BuyInfoDao buyInfoDao = new BuyInfoDaoImpl();
        List<BuyInfo> buyInfosToShow;

        //请求页面
        if (op==null || op.equals("cart")){
            buyInfosToShow = buyInfoDao.listBuyInfoByUid(uid);
            out.write(CartHtml.getHtml(buyInfosToShow,uid,jid, calcTotal(buyInfosToShow)));
        }
        //分类显示
        else if (op.equals("delete")){
            Long gid = Long.parseLong(request.getParameter("gid"));
            buyInfoDao.deleteBuyInfo(uid, gid);
            buyInfosToShow = buyInfoDao.listBuyInfoByUid(uid);
            out.write(CartHtml.getHtml(buyInfosToShow,uid,jid, calcTotal(buyInfosToShow)));
        }
        //上一页
        else if (op.equals("buy")){
            PromotionDao promotionDao = new PromotionImpl();
            double cost = calcTotal(buyInfoDao.listBuyInfoByUid(uid));
            double actual;
            Double rate = promotionDao.getPromotion((int)cost);
            if (rate == null){actual = cost;}
            else {actual = cost*rate;}

            UserDao userDao = new UserDaoImpl();
            boolean buySuccess = userDao.buy(uid, actual);
            Double remain = userDao.queryUser(uid).getMoney();
            //购买成功
            if (buySuccess){
                session.setAttribute("cost", cost);
                session.setAttribute("actual", actual);
                session.setAttribute("rate", rate);
                session.setAttribute("remain", remain);
            }
            session.setAttribute("buySuccess", buySuccess);
            response.sendRedirect(response.encodeURL("/BuyResult"));
        }
        else if (op.equals("mall")){
            response.sendRedirect(response.encodeURL("/Mall"));
        }
    }

    private static Double calcTotal(List<BuyInfo> buyInfos){
        return buyInfos.stream().mapToDouble(e->e.getCost()).sum();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
