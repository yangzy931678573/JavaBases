package com.yzy.mydream;

import java.io.*;

/**
 * @author by yzy
 * http响应类
 */
public class Response {
    //缓冲大小
    private static final int BUFFER_SIZE = 1024;
    //维护一个 http请求对象
    private Request request;
    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    //把  http请求  请求的资源返回
    public void sendStaticResource() {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fileInputStream = null;
        //这里必须有一个响应头，否则是不和要求的 http 响应
        String responseHead = "HTTP/1.1 200 SUCCESS\r\n" + //响应行 HTTP/1.1 状态码 描述信息 \r\n
                "Content-Type: text/html\r\n " + //响应的类型 \r\n
                "Content-Length: 200\r\n" + //响应的大小 \r\n
                "\r\n"; // crlf

        try {
            //找到请求资源，创建 File 对象
            //这里可以看到路径是通过 web项目的根目录 和 请求资源的uri地址 拼接而成的
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                int ch = fileInputStream.read(bytes);//把文件读入缓冲字节数组中
                while (ch != -1) {
                    outputStream.write(responseHead.getBytes());
                    outputStream.write(bytes, 0, ch);//把字节数组中的内容写出到输出流中
                    ch = fileInputStream.read(bytes, 0, BUFFER_SIZE);
                }
            } else {
                //也就是 请求资源 没有找到,这里 \r\n 是 回车换行符
                //定义错误信息
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n " +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                //把错误信息写出到输出流中
                outputStream.write(errorMessage.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
