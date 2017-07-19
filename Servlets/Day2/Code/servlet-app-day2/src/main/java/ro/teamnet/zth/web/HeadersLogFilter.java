package ro.teamnet.zth.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

import ro.teamnet.zth.file.LogFileWriter;

/**
 * Created by Ginel.Guiu on 7/19/2017.
 */
public class HeadersLogFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;
        Enumeration<String> headerNames = request.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String headerName = (String) headerNames.nextElement();


                String headerValue = request.getHeader(headerName);
                LogFileWriter.logHeader(headerName, headerValue);
            }
        }

        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
