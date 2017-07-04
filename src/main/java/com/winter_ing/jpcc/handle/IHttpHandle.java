package com.winter_ing.jpcc.handle;

import com.winter_ing.jpcc.httpServer.Request;
import com.winter_ing.jpcc.httpServer.Response;

/**
 * 模拟前端控制器
 * 
 * @author jhb
 */
public interface IHttpHandle
{

    /**
     * @param request
     *            封装后的请求对象
     * @param response
     *            封装后的响应对象 实现此方法，完成请求参数的获取以及响应返回数据的给予，响应数据存在content中
     * 
     *            注意：方法后必须调用Response的send方法才能给浏览器端返回数据
     */
    void handleHttpServer(Request request, Response response);

}
