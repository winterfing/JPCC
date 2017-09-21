package com.winter_ing.jpcc.handle.robot;

import java.awt.AWTException;
import java.awt.Robot;

/**
* @ClassName: MyRobot
* @Description: 鼠标键盘命令处理类
* @author Dangzhang
* @date 2017-9-21 下午3:55:14
*
 */
public class MyRobot
{
    
    private static Robot robot ;
    
    private static MyRobot myRobot;
    
    /* 按下 */
    private static final int key_press = 1;
    
    /* 释放 */
    private static final int key_release = 2;
    
    /* 按下释放  */
    private static final int key_all = 3;
    
    /* 鼠标按下 */
    private static final int mouse_press = 4;
    
    /* 鼠标释放 */
    private static final int mouse_release = 5;
    
    /* 鼠标按下释放  */
    private static final int mouse_all = 6;
    
    /* 鼠标滚轮 */
    private static final int mouse_wheel = 7;
    
    /* 鼠标移动 */
    private static final int mouse_move = 8;
    
    private MyRobot(){
        if(robot == null){
            try
            {
                robot = new Robot();
            }
            catch (AWTException e)
            {
                e.printStackTrace();
                System.out.println("create robot error");
            }
        }
    }
    
    public static MyRobot getInstance(){
       if( myRobot == null){
           myRobot = new MyRobot();
       }
       return myRobot;
    }
    
    public void control(int type , String value){
        
        switch (type)
        {
        case key_press:
            robot.keyPress(Integer.parseInt(value));
            break;
        case key_release:
            robot.keyRelease(Integer.parseInt(value));
            break;
        case key_all:
            robot.keyPress(Integer.parseInt(value));
            robot.keyRelease(Integer.parseInt(value));
            break;
        case mouse_press:
            robot.mousePress(Integer.parseInt(value));
            break;
        case mouse_release:
            robot.mouseRelease(Integer.parseInt(value));
            break;
        case mouse_all:
            robot.mousePress(Integer.parseInt(value));
            robot.mouseRelease(Integer.parseInt(value));
            break;
        case mouse_wheel:
            robot.mouseWheel(Integer.parseInt(value));
            break;
        case mouse_move:
            String [] xy = value.split(",");
            robot.mouseMove(Integer.parseInt(xy[0]), Integer.parseInt((xy[1])));
            break;
        default:
            System.out.println("command undefined");
            break;
        }
    }
}
