package com.steve360.servlets;

import javax.servlet.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;


public class CORSFilter implements Filter {
    /**
     * Default constructor.
     */
    public CORSFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     */

    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
        System.out.println("Cors Init");
    }

    /**
     *@see Filter#destroy()
     */

    public void destroy() {
        // TODO Auto-generated method stub
    }


    /**
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("CORSFilter HTTP Request: " + request.getMethod());

        // Authorize (allow) all domains to consume the content
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","*");
        ((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers","*");

        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }

        // pass the request along the filter chain
        chain.doFilter(request, servletResponse);
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
