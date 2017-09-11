package com.yzy.mydream.myservlet;

import java.io.File;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author by  Administrator
 * \* Date: 2017/9/10
 * \* Description:
 * \
 */

public class Constants {
    //1.维护一个根目录,允许外部访问;如果外部需要请求资源，只需在uri上拼接WEB_ROOT即可
    public static  final String WEB_ROOT = System.getProperty("user.dir")+
            File.separator+
            "webRoot";  // 实际值----D:\ProgramFiles\workspaces\IDEA\JavaBases\webRoot
}
