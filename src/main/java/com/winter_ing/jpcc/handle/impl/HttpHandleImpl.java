package com.winter_ing.jpcc.handle.impl;

import java.io.IOException;

import com.winter_ing.jpcc.handle.IHttpHandle;
import com.winter_ing.jpcc.httpServer.Request;
import com.winter_ing.jpcc.httpServer.Response;

public class HttpHandleImpl implements IHttpHandle
{
    private static int i = 0;
    
    public void handleHttpServer(Request request, Response response)
    {
        response.setContent("第"+ ++i +"个客户");
        System.out.println("currentThread: "+Thread.currentThread().getName());
        if(i >= 50)
        {
            System.out.println("第"+ i +"个客户，NO");
        }else{
            System.out.println("第"+ i +"个客户，OK");
        }
        try
        {
            response.sendData();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
