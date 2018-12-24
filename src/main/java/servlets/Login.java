package servlets;

import dao.UserDao;
import daoImpl.UserDaoImpl;
import html.LoginHtml;
import utility.CookieUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public Login() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        HttpSession session = request.getSession(false);
        PrintWriter out = response.getWriter();
        String uid = CookieUtil.getInstance().findCookie(request.getCookies(), "loginId");
        if (session==null){
            out.write(LoginHtml.getHtml(uid));
//            response.sendRedirect("/html/Login.html");
        }
        else {
            System.out.println();
            response.sendRedirect("/Mall");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDaoImpl();
        Long uid = Long.parseLong(request.getParameter("uid"));
        String password = request.getParameter("password");

        boolean validUser = dao.isValidUser(uid, password);

        PrintWriter out = response.getWriter();
        if (validUser){
            //创建session
            HttpSession session = request.getSession(true);
            session.setAttribute("uid", uid);

            Cookie loginCookie = new Cookie("loginId",uid+"" );
            response.addCookie(loginCookie);

            response.sendRedirect(response.encodeURL("/Mall"));
        }
        else {
            response.sendRedirect("/html/LoginError.html");
        }
    }
}
