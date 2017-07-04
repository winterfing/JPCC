package com.winter_ing.jpcc.httpServer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class Response
{

    private static final int BUFFER_SIZE = 1024;

    private Request request;

    private OutputStream outputStream;

    private String content;

    private String sheader = "";

    private String encoding;

    public Response(OutputStream outputStream, Request request, String encoding)
    {
        this.outputStream = outputStream;
        this.request = request;
        this.encoding = encoding;
    }

    public void sendHeader(OutputStream outputStream)
            throws UnsupportedEncodingException, IOException
    {
        // 如果检测到是HTTP/1.0及以后的协议，按照规范，需要发送一个MIME首部
        if (request.getReq().indexOf("HTTP/") != -1)
        {
            sheader = "HTTP/1.0 200 OK\r\n" + "Server: OneFile 1.0\r\n"
                    + "Content-length: "
                    + this.content.getBytes(encoding).length + "\r\n"
                    + "Content-type: text/html\r\n\r\n";

            outputStream.write(this.sheader.getBytes(encoding));
        }
    }

    public void sendStaticResource() throws IOException
    {
        byte bytes[] = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        try
        {
            sendHeader(outputStream);
            File file = new File(HttpServer.WEB_ROOT, request.getUri());
            if (file.exists())
            {
                fis = new FileInputStream(file);
                int ch = fis.read(bytes, 0, BUFFER_SIZE);
                while (ch != -1)
                {
                    outputStream.write(bytes, 0, ch);
                    ch = fis.read(bytes, 0, BUFFER_SIZE);
                }
            }
            else
            {
                String errorMessage = "<h1>File not found</h1>";
                outputStream.write(errorMessage.getBytes());
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            if (fis != null)
                fis.close();
            outputStream.flush();
        }
    }

    public void sendData() throws IOException
    {
        try
        {
            sendHeader(outputStream);
            outputStream.write(this.content.getBytes(encoding));
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        finally
        {
            outputStream.flush();
        }
    }

    public Request getRequest()
    {
        return request;
    }

    public void setRequest(Request request)
    {
        this.request = request;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
