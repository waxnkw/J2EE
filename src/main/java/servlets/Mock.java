package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/Mock")
public class Mock extends HttpServlet {
    private static final long serialVersionUID = 2L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<p>mock1</p>");
        response.sendRedirect("/Mock2");
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/Mock2");
//        dispatcher.include(request,response);
        out.println("<p>after</p>");
        System.out.println("after");
    }
}
