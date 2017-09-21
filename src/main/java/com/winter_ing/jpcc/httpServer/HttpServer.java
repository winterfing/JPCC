package com.winter_ing.jpcc.httpServer;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import com.winter_ing.jpcc.handle.IHttpHandle;
import com.winter_ing.jpcc.handle.impl.HttpHandleImpl;
import com.winter_ing.jpcc.util.ClassUtil;
import com.winter_ing.jpcc.util.Constants;
import com.winter_ing.jpcc.util.FileUtil;

/**
 * 
 * @author jhb 服务端
 * 
 */
public class HttpServer
{

    /**
     * WEB_ROOT是存放HTML文件的目录
     */
    public static final String WEB_ROOT = System.getProperty("user.dir")
            + File.separator + "webroot";

    private static int port;

    @SuppressWarnings("unused")
    private static String ip;

    private static String encoding;

    private static String implClassName;

    private static boolean shutdown;

    static ServerSocket serverSocket;

    private static IHttpHandle httpHandle;
    
    public static String staticSources;

    /**
     * 构造方法私有化
     */
    private HttpServer()
    {

    }

    static
    {
        // 往配置文件加载数据
        Properties pro = FileUtil.getProperties("config.properties");
        port = Integer.parseInt(pro.getProperty("port"));
        ip = pro.getProperty("ip");
        encoding = pro.getProperty("code");
        implClassName = pro.getProperty("implclassname");
        shutdown = Boolean.getBoolean(pro.getProperty("shutdown"));
        staticSources = pro.getProperty("staticSources");
    }

    public static void startServer()
    {
        try
        {
            if (serverSocket == null)
            {
                serverSocket = new ServerSocket(port);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // 非正常退出程序
            System.exit(1);
        }
        System.out.println(shutdown ? "程序启动未开启服务" : "程序启动正在开启服务");
        System.out.println(WEB_ROOT);
        // 开启socket
        while (!shutdown)
        {
            Socket socket = null;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try
            {
                System.out.println("服务已开启在PORT：" + port);
                socket = serverSocket.accept();

                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();

                // 创建请求
                Request request = new Request(inputStream);

                // 创建响应
                Response response = new Response(outputStream, request,
                        encoding);

                // 处理请求以及创建响应数据
                // 现获取对象
                if (httpHandle == null)
                {
                    httpHandle = (HttpHandleImpl) ClassUtil.getNewObject(
                            IHttpHandle.class, implClassName);
                }

                httpHandle.handleHttpServer(request, response);

                // 关闭socket
                if (socket != null)
                {
                    socket.close();
                }

                // 检查URI是否是一个关闭命令
                if (Constants.SHUTDOWN_COMMAND.equals(request.getUri().trim()))
                {
                    shutdown = !shutdown;
                    System.out.println(shutdown ? "服务已关闭" : "服务已开启");
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
                continue;
            }
        }
    }
}
