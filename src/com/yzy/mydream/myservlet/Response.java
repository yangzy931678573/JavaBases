package com.yzy.mydream.myservlet;


import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import java.io.*;
import java.util.Locale;

/**
 * @author by yzy
 * http响应类
 */
public class Response implements ServletResponse {
    //缓冲大小
    private static final int BUFFER_SIZE = 1024;
    //维护一个 http请求对象
    private Request request;
    private OutputStream outputStream;
    private PrintWriter writer;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    //把  http请求  请求的资源返回
    public void sendStaticResource() throws IOException {
        FileInputStream fileInputStream = null;
        try {
            byte[] bytes = new byte[BUFFER_SIZE];
            //这里必须有一个响应头，否则是不和要求的 http 响应
            String responseHead = "HTTP/1.1 200 SUCCESS\r\n" + //响应行 HTTP/1.1 状态码 描述信息 \r\n
                    "Content-Type: text/html\r\n " + //响应的类型 \r\n
                    "Content-Length: 200\r\n" + //响应的大小 \r\n
                    "\r\n"; // crlf
            //找到请求资源，创建 File 对象
            //这里可以看到路径是通过 web项目的根目录 和 请求资源的uri地址 拼接而成的
            File file = new File(Constants.WEB_ROOT, request.getUri());
            fileInputStream = new FileInputStream(file);

            //先写出响应头
            outputStream.write(responseHead.getBytes());

            //标准格式：
            int len = 0;
            while ((len = fileInputStream.read(bytes, 0, BUFFER_SIZE)) != -1) {
                outputStream.write(bytes, 0, len);
            }


        } catch (FileNotFoundException e) {
            //也就是 请求资源 没有找到,这里 \r\n 是 回车换行符
            //定义错误信息
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n " +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>File Not Found</h1>";
            //把错误信息写出到输出流中
            outputStream.write(errorMessage.getBytes());
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
        }

    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        //自动刷新，清空缓冲
        this.writer =  new PrintWriter(outputStream,true);
        return this.writer;
    }

    @Override
    public void setCharacterEncoding(String s) {

    }

    @Override
    public void setContentLength(int i) {

    }

    @Override
    public void setContentLengthLong(long l) {

    }

    @Override
    public void setContentType(String s) {

    }

    @Override
    public void setBufferSize(int i) {

    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {

    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
