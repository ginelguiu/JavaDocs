package ro.teamnet.zth.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by Ginel.Guiu on 7/18/2017.
 */
@WebServlet(name = "InfoHttpServlet")
public class InfoHttpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        String table1 = "";
        PrintWriter out = response.getWriter();

        Enumeration en = request.getHeaderNames();
        while(en.hasMoreElements()) {

            String headerName = (String) en.nextElement();


            String headerValue = request.getHeader(headerName);

            out.write("Header Name = " + headerName + " " + " Header Value = " + headerValue + "<br>");
        }
    }
}
