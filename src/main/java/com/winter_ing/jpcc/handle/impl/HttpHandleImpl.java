package com.winter_ing.jpcc.handle.impl;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;

import com.winter_ing.jpcc.handle.IHttpHandle;
import com.winter_ing.jpcc.handle.robot.MyRobot;
import com.winter_ing.jpcc.httpServer.HttpServer;
import com.winter_ing.jpcc.httpServer.Request;
import com.winter_ing.jpcc.httpServer.Response;
import com.winter_ing.jpcc.util.Constants;

/**
 * 
 * @ClassName: HttpHandleImpl
 * @Description: 请求处理类
 * @author jhb
 * @date 2017-9-21 下午2:56:24
 * 
 */
public class HttpHandleImpl implements IHttpHandle
{
    private static int i = 0;

    /**
     * @Description: 所有请求都走到这里统一分发
     * @author jhb
     * @date 2017-9-21 下午2:56:24
     * 
     */
    public void handleHttpServer(Request request, Response response)
    {
        try
        {
            // 检测是否是请求静态资源——静态资源直接写出
            String[] staticsources = HttpServer.staticSources.split(",");
            for (String staticsource : staticsources)
            {
                if (request.getUri().endsWith(staticsource))
                {
                    response.sendStaticResource();
                    return;
                }
            }

            // 若不是静态资源，则分发——/pu/1/1  控制命令  模拟1操作执行1按键命令
            String [] strs = request.getUri().split("/");
            if (Constants.CON_COMMAND.startsWith(strs[1]))
            {
                MyRobot myRobot = MyRobot.getInstance();
                
                myRobot.control(Integer.parseInt(strs[2]), strs[3]);
            }
            else if ("".equals(request.getUri()))
            {
                
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
