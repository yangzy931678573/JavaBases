package com.yzy.mydream;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟一个http服务器
 *@author by yzy
 */
public class HttpServer {
    //1.维护一个根目录,允许外部访问;如果外部需要请求资源，只需在uri上拼接WEB_ROOT即可
    public static  final String WEB_ROOT = System.getProperty("user.dir")+
            File.separator+
            "webRoot";//D:\ProgramFiles\workspaces\IDEA\JavaBases\webRoot

    //2.服务器不再处理请求的指令
    private static final String SHUTDOWN_COMMAND = "SHUTDOWN";
    //3.是否接收到 shutdown command
    private boolean toShutdown = false;

    //4.入口函数
    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }
     //等待 http 请求的方法
    private void await() {
        //需要一个服务器ServerSocket
        ServerSocket serverSocket = null;
        int port = 8080;//默认端口
        int backlog = 1;//默认处理的请求队列数
        String ip_address = "127.0.0.1";
        try {
            serverSocket = new ServerSocket(port,backlog, InetAddress.getByName(ip_address));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);//发生异常退出虚拟机！
        }
        // 循环等待处理 http 请求
        while (!toShutdown){
            //维护一个Socket和与它相关的流
            Socket socket;
            InputStream inputStream ;
            OutputStream outputStream ;

            try {
                socket = serverSocket.accept();
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                //创建 Request 对象，并解析 http 请求
                Request request = new Request(inputStream);
                request.parse();

                //创建一个 Response对象，并返回请求的 资源
                Response response = new Response(outputStream);
                response.setRequest(request);
                response.sendStaticResource();

                //关闭socket,完成本次请求
                socket.close();

                //判断是否收到了关闭服务器的命令
                toShutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            } catch (IOException e) {
                e.printStackTrace();
                //本次请求发生异常，跳过本次请求，处理下次请求
                continue;
            }

        }

    }

}

