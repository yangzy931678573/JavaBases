package com.yzy.mydream.myservlet;



import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author by  Administrator
 * \* Date: 2017/9/10
 * \* Description:
 * \
 */

public class ServletProcessor {
    public void process(Request request, Response response) {
        //截取到servlet的名字
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        //定义一个类加载器，用来加载servlet
        URLClassLoader loader = null;
        try {
            URL[] urls = new URL[1];
            //防止构造器重载报错
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            //repository定义了去哪里加载servlet ,
            // classPath.getCanonicalPath()返回一个经过  特殊处理解析   的绝对路径
            String repository =
                    (new URL
                            ("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            //实例一个url
            urls[0] = new URL(null, repository, streamHandler);
            //实例化该类加载器
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        //利用类加载器加载对应的类
        Class myClass = null;
        try {
            myClass = loader.loadClass("com.yzy.mydream.myservlet."+servletName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //把加载的类向下转成servlet，调用servlet的service()方法，处理请求
        Servlet servlet = null;
        try {
            servlet = (Servlet) myClass.newInstance();

            //使用其外观类。防止外部调用request和response本身的方法
            RequestFacade requestFacade  = new RequestFacade(request);
            ResponseFacade responseFacade = new ResponseFacade(response);

            
            servlet.service(requestFacade,responseFacade);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
