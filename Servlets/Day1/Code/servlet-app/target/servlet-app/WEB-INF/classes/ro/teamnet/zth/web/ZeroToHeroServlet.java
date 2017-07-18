package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Ginel.Guiu on 7/18/2017.
 */
@WebServlet(name = "ZeroToHeroServlet")
public class ZeroToHeroServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.getWriter().write(handleRequest(request));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected String handleRequest(HttpServletRequest req)
    {
        String obj = "";
        obj = "Hello <b>"+req.getParameter("firstName")+" "+ req.getParameter("lastName")+"</b>! Enjoy Zero To Hero!!!";
        return obj;
    }
}
