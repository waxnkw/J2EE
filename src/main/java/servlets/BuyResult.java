package servlets;

import dao.BuyInfoDao;
import dao.UserDao;
import daoImpl.BuyInfoDaoImpl;
import daoImpl.UserDaoImpl;
import html.BuyFailedHtml;
import html.BuySuccessHtml;
import model.BuyInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/BuyResult")
public class BuyResult extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession(false);
        if (session==null){
            System.out.println("session is null");
            response.sendRedirect("/html/Login.html");
            return;
        }

        PrintWriter out = response.getWriter();
        String jid = response.encodeURL("/Cart").contains(";")?";jsessionid="+session.getId():"";

        Long uid = (Long) session.getAttribute("uid");
        UserDao userDao = new UserDaoImpl();
        BuyInfoDao buyInfoDao = new BuyInfoDaoImpl();
        Double reamin = userDao.queryUser(uid).getMoney();


        boolean success = (Boolean) session.getAttribute("buySuccess");
        if (success){
            Double cost = (Double) session.getAttribute("cost");
            Double actual = (Double) session.getAttribute("actual");
            Double rate = (Double) session.getAttribute("rate");
            Double remain = (Double) session.getAttribute("remain");
            List<BuyInfo> buyInfos = buyInfoDao.listBuyInfoByUid(uid);
            buyInfoDao.deleteBuyInfoByUid(uid);
            out.print(BuySuccessHtml.getHtml(buyInfos, uid, jid, cost, actual, rate, remain));
        }
        else {
            out.print(BuyFailedHtml.getHtml(jid, reamin));
        }
    }

}
