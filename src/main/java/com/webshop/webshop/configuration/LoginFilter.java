package com.webshop.webshop.configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpSession session;
        PrintWriter user;

        boolean isLoggedIn = false;

//        if (user == null) {
//            session.setAttribute("isLoggedIn", isLoggedIn);
//        } else {
//            isLoggedIn = true;
//            session.setAttribute("isLoggedIn", isLoggedIn);
//        }

    }

    @Override
    public void destroy() {

    }

}
