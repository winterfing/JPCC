package com.winter_ing.jpcc.httpServer;

import java.io.IOException;
import java.io.InputStream;

public class Request
{

    private InputStream inputStream;

    private String req;

    private String uri;

    public Request(InputStream inputStream)
    {
        this.inputStream = inputStream;
        parse();
        System.out.println("request_req: " + req);
        System.out.println("request_uri: " + uri);
    }

    public void parse()
    {
        StringBuffer request = new StringBuffer();
        while (true)
        {
            int c;
            try
            {
                c = inputStream.read();
                if (c == '\r' || c == '\n' || c == -1)
                {
                    break;
                }
                request.append((char) c);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        this.req = request.toString();
        parseUri(request);
    }

    public void parseUri(StringBuffer request)
    {
        int index = request.indexOf(" ");
        if (index != -1)
        {
            uri = request.substring(index, request.indexOf(" ", index + 1));
        }
    }

    public String getUri()
    {
        return uri;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public String getReq()
    {
        return req;
    }

    public void setReq(String req)
    {
        this.req = req;
    }

}
