package servlets;

import dao.BuyInfoDao;
import dao.GoodsDao;
import daoImpl.BuyInfoDaoImpl;
import daoImpl.GoodsDaoImpl;
import html.MallHtml;
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

@WebServlet(urlPatterns = "/Mall")
public class ShoppingMall extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession(false);
        System.out.println(session==null);
        if (session==null){
            System.out.println("session is null");
            response.sendRedirect("/Login");
            return;
        }

        Long uid = (Long) session.getAttribute("uid");
        String op = request.getParameter("op");
        PrintWriter out = response.getWriter();

        String jid = response.encodeURL("/Mall").contains(";")?";jsessionid="+session.getId():"";

        GoodsDao goodsDao = new GoodsDaoImpl();
        List<Goods> goodsToShow;

        //请求页面
        if (op==null || op.equals("mall")){
            session.setAttribute("category", "全部");
            session.setAttribute("page", 0);
            goodsToShow = goodsDao.listGoods(0);
            out.write(MallHtml.getHtml(goodsToShow,uid,jid));
        }
        //分类显示
        else if (op.equals("category")){
            session.setAttribute("page", 0);
            String category = request.getParameter("category");
            session.setAttribute("category", category);
            goodsToShow = category.equals("全部")?
                    goodsDao.listGoods(0):
                    goodsDao.listGoodsByCategory(category,0 );
            out.write(MallHtml.getHtml(goodsToShow,uid,jid));
        }
        //上一页
        else if (op.equals("last")){
            int page = (Integer) session.getAttribute("page");
            page = page-1>=0?page-1:0;
            session.setAttribute("page", page);

            String category = (String) session.getAttribute("category");
            goodsToShow = category.equals("全部")?
                    goodsDao.listGoods(page):
                    goodsDao.listGoodsByCategory(category,page);
            out.write(MallHtml.getHtml(goodsToShow,uid,jid));
        }
        else if (op.equals("next")){
            int page = (Integer) session.getAttribute("page");
            page++;

            String category = (String) session.getAttribute("category");
            goodsToShow = category.equals("全部")?
                    goodsDao.listGoods(page):
                    goodsDao.listGoodsByCategory(category,page);
            page = goodsToShow.size()>0?page:page-1;
            session.setAttribute("page", page);
            goodsToShow = category.equals("全部")?
                    goodsDao.listGoods(page):
                    goodsDao.listGoodsByCategory(category,page);

            out.write(MallHtml.getHtml(goodsToShow,uid,jid));
        }
        else if (op.equals("cart")){
            response.sendRedirect(response.encodeURL("/Cart"));
        }
        else if (op.equals("add")){
            BuyInfoDao buyInfoDao = new BuyInfoDaoImpl();
            Long gid = Long.parseLong(request.getParameter("gid"));
            int num = Integer.parseInt(request.getParameter("num"));
            buyInfoDao.addBuyInfo(uid, gid, num);

            int page = (Integer) session.getAttribute("page");
            String category = (String) session.getAttribute("category");
            goodsToShow = category.equals("全部")?
                    goodsDao.listGoods(page):
                    goodsDao.listGoodsByCategory(category,page);
            out.write(MallHtml.getHtml(goodsToShow,uid,jid));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}