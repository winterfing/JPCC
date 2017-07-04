package com.winter_ing.jpcc;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;


/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase
{
    /**
     * Create the test case
     * 
     * @param testName
     *            name of the test case
     */
    public AppTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     * @throws  
     * @throws HttpException 
     * 测试失败，原因：junit不支持多线程测试，具体现象为，run方法没有被调用
     */
    public void testApp() throws Exception
    {
        int i = 0;
        while(i++ < 10)
        {
            new Thread(new MyRunnable()).start();
        }
        
    }

}


class MyRunnable implements Runnable{

    public void run()
    {
        HttpClient client = new HttpClient(); 
        // 设置代理服务器地址和端口      
        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
        HttpMethod method=new GetMethod("http://localhost:8088/jhb");
        //使用POST方法 
        //HttpMethod method = new PostMethod("http://java.sun.com");
        try
        {
            client.executeMethod(method);
            //打印服务器返回的状态
            System.out.println(method.getStatusLine());
            //打印返回的信息
            System.out.println(method.getResponseBodyAsString());
            //释放连接
            method.releaseConnection();
        }
        catch (HttpException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
    
}
