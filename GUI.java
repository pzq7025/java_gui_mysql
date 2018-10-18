package signer;


/**
 * 通过登录信息进入GUI的界面
 * 在通过Gui.swing 这个类来实现功能
 * 增加功能的内容也是在GUi和Gui.swing 这两个类中来添加
 */

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.event.ActionListener;
import java.net.JarURLConnection;

public class GUI extends JFrame {
    JButton Change_Pass, Save_Money, WithDraw_Money, Exit, Query, Logoff, Next, Last;
    Box box1, box2, box3, box4, base1;
    GUI_Swing gui;


    GUI(){
        init();
    }


    void init(){
        setLayout(new FlowLayout());
        // 建立对应的标签对象
        Change_Pass = new JButton("Change Password");                         // 修改密码
        Save_Money = new JButton("      Save   Money    ");                   // 存钱
        WithDraw_Money = new JButton("Withdraw Money");                       // 取钱
        Exit = new JButton("      Exit  System   ");                          // 退出
        Query = new JButton("   Query   Money  ");                            // 查询
        Logoff = new JButton("     Logoff  User    ");                        // 注销用户
        Last = new JButton("    Back    Login    ");                          // 返回上一层
        Next = new JButton("     Next    Menu   ");                           // 下一层



        // 建立Component的对象
        gui = new GUI_Swing();


        // 建立新的布局格式
        // 竖着建立布局简化代码  以及更好的布局格式
        box1 = Box.createVerticalBox();
        // 这是与顶层的间距
        box1.add(Box.createVerticalStrut(30));
        box1.add(Save_Money);
        box1.add(Box.createVerticalStrut(60));
        box1.add(Query);
        box1.add(Box.createVerticalStrut(60));
        box1.add(Change_Pass);
        box1.add(Box.createVerticalStrut(60));
        box1.add(Last);


        box2 = Box.createVerticalBox();
        box2.add(Box.createVerticalStrut(30));
        box2.add(WithDraw_Money);
        box2.add(Box.createVerticalStrut(60));
        box2.add(Logoff);
        box2.add(Box.createVerticalStrut(60));
        box2.add(Exit);
        box2.add(Box.createVerticalStrut(60));
        box2.add(Next);

        base1 = Box.createHorizontalBox();
        base1.add(Box.createHorizontalStrut(50));
        base1.add(box1);
        base1.add(Box.createHorizontalStrut(100));
        base1.add(box2);


//        // 建立Label的布局
//        box1 = Box.createHorizontalBox();
//        box1.add(Box.createVerticalStrut(60));
//        box1.add(Save_Money);
//        box1.add(Box.createHorizontalStrut(130));
//        box1.add(WithDraw_Money);
//
//
//        // 建立Label的布局
//        box2 = Box.createHorizontalBox();
//        box2.add(Box.createVerticalStrut(60));
//        box2.add(Query);
//        box2.add(Box.createHorizontalStrut(130));
//        box2.add(Print);
//
//
//        // 建立Label的布局
//        box3 = Box.createHorizontalBox();
//        box3.add(Box.createVerticalStrut(60));
//        box3.add(Change_Pass);
//        box3.add(Box.createHorizontalStrut(130));
//        box3.add(Exit);
//
//
//        box4 = Box.createHorizontalBox();
//        box4.add(Box.createVerticalStrut(60));
//        box4.add(Next);
//        box4.add(Box.createHorizontalStrut(130));
//        box4.add(Last);
//
//        // 建立box的布局
//        base1 = Box.createVerticalBox();
//        base1.add(box1);
//        base1.add(Box.createVerticalStrut(40));
//        base1.add(box2);
//        base1.add(Box.createVerticalStrut(40));
//        base1.add(box3);
//        base1.add(Box.createVerticalStrut(40));
//        base1.add(box4);

//        base1.add(Box.createVerticalStrut(40));


        add(base1);
        //建立时间的监听关系实现类之间的调用
        Change_Pass.addActionListener(gui);
        Save_Money.addActionListener(gui);
        WithDraw_Money.addActionListener(gui);
        Exit.addActionListener(gui);
        Query.addActionListener(gui);
        Logoff.addActionListener(gui);
        Last.addActionListener(gui);
        

//        addComponentListener(Change_Pass,);
//        icon = new ImageIcon(GUI.class.getResource("123.png"));
//        img = icon.getImage();


        //调用相应的函数
        gui.Change_Pass_Button(Change_Pass);
        gui.Logoff_Button(Logoff);
        gui.Exit_Button(Exit);
        gui.Query_Button(Query);
        gui.WithDraw_Money_Button(WithDraw_Money);
        gui.Save_Money_Button(Save_Money);
        gui.Last_Button(Last);
        gui.Set_JFrame(this);

        



        add(base1);//这个是最后的一个表现形式
        setBounds(200, 200, 500, 400);
        getContentPane().setBackground(Color.gray);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("MENU");


    }

    public static void main(String[] args){
            GUI gui = new GUI();
    }
}
