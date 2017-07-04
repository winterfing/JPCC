package com.winter_ing.jpcc.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.winter_ing.jpcc.httpServer.HttpServer;

public class FileUtil
{

    public static Properties getProperties(String name)
    {
        Properties pro = new Properties();
        FileInputStream in = null;
        try
        {
            String url = FileUtil.class.getResource("/").getPath();
            in = new FileInputStream(url + name);
            pro.load(in);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (in != null)
                    in.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return pro;
    }
}
