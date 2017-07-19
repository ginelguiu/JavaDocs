package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Ginel.Guiu on 7/19/2017.
 */
public class HttpSessionLogin extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = "";
        String password = "";

        user = request.getParameter("username");
        password = request.getParameter("password");

        if(user.equals("admin") && password.equals("admin"))
        {
            response.getWriter().write("Welcome back, "+ user+"!");
            response.getWriter().write(request.getSession().getId());
        }
       else{
            HttpSession session = (HttpSession)request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("session", session);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/loginFail.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
