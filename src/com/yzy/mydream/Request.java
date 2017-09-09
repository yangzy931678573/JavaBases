package com.yzy.mydream;

import java.io.IOException;
import java.io.InputStream;
/**
 * @author  by yzy
 *
 * http请求类
 *
 */

public class Request {
    private InputStream inputStream;
    private String uri;
    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    //将整个 http请求  解析成  字符串
    public void parse() {
        StringBuffer request = new StringBuffer(2048);
        int i ;
        byte[] buffer = new byte[2048];
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;//发生异常即无需读入任何数据
        }
        for (int j=0;j<i;j++){
            request.append((char)buffer[j]);
        }
        this.uri = parseUri(request.toString());
        System.out.println(request.toString());
    }
    //解析 http请求字符串  中的 uri地址
    private String parseUri(String requestString){
        int start,end;
        start = requestString.indexOf(" ") + 1;//第一个空格的下标
        if (start!=-1) {
            end = requestString.indexOf(" ", start);//第二个空格的下标
            if (end > start)
                return requestString.substring(start, end);//左开右闭
        }
       return null;//否则：http请求中没有 uri地址
    }
    public String getUri() {
        return this.uri;
    }
}
