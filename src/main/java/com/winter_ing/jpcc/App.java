package com.winter_ing.jpcc;

import com.winter_ing.jpcc.httpServer.HttpServer;

/**
 * 服务器端主类
 */
public class App
{

    public static void main(String[] args) throws Exception
    {
        // 启动连接服务
        System.out.println("server_start");
        HttpServer.startServer();
    }

}
