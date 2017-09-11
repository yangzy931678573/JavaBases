package com.yzy.mydream.myservlet;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;
/*
 * \* Created with IntelliJ IDEA.
 * \* @author by  Administrator
 * \* Date: 2017/9/10
 * \* Description:
 * \
 */

public class PrimitiveServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("my init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter writer = servletResponse.getWriter();
        //这里必须有一个响应头，否则是不符合要求的 http 响应
        String responseHead = "HTTP/1.1 200 SUCCESS\r\n" + //响应行 HTTP/1.1 状态码 描述信息 \r\n
                "Content-Type: text/html\r\n " + //响应的类型 \r\n
                "Content-Length: 200\r\n" + //响应的大小 \r\n
                "\r\n"; // crlf
        writer.println(responseHead);
        writer.println("Hello!Roses are red.");
        writer.println("Violets are blue");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
