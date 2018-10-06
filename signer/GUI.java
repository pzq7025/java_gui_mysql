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
    JButton Change_Pass, Save_Money, WithDraw_Money, Exit, Query, Print;
    Box box1, box2, box3, base1;
    GUI_Swing gui;


    GUI(){
        init();
    }


    void init(){
        setLayout(new FlowLayout());
        Change_Pass = new JButton("change password");            //修改密码
        Save_Money = new JButton("save money");                  //存钱
        WithDraw_Money = new JButton("withdraw money");          //取钱
        Exit = new JButton("exit system");                       //退出
        Query = new JButton("Query Money");                      //查询
        Print = new JButton("Print Money");                      //打印


        gui = new GUI_Swing();



        box1 = Box.createHorizontalBox();
        box1.add(Box.createVerticalStrut(60));
        box1.add(Save_Money);
        box1.add(Box.createHorizontalStrut(130));
        box1.add(WithDraw_Money);


        box2 = Box.createHorizontalBox();
        box2.add(Box.createVerticalStrut(60));
        box2.add(Query);
        box2.add(Box.createHorizontalStrut(130));
        box2.add(Print);


        box3 = Box.createHorizontalBox();
        box3.add(Box.createVerticalStrut(60));
        box3.add(Change_Pass);
        box3.add(Box.createHorizontalStrut(130));
        box3.add(Exit);



        base1 = Box.createVerticalBox();
        base1.add(box1);
        base1.add(Box.createVerticalStrut(40));
        base1.add(box2);
        base1.add(Box.createVerticalStrut(40));
        base1.add(box3);

//        base1.add(Box.createVerticalStrut(40));


        //建立时间的监听关系实现类之间的调用
        Change_Pass.addActionListener(gui);
        Save_Money.addActionListener(gui);
        WithDraw_Money.addActionListener(gui);
        Exit.addActionListener(gui);
        Query.addActionListener(gui);
        Print.addActionListener(gui);
        

//        addComponentListener(Change_Pass,);
//        icon = new ImageIcon(GUI.class.getResource("123.png"));
//        img = icon.getImage();


        //调用相应的函数
        gui.Change_Pass_Button(Change_Pass);
        gui.Print_Button(Print);
        gui.Exit_Button(Exit);
        gui.Query_Button(Query);
        gui.WithDraw_Money_Button(WithDraw_Money);
        gui.Save_Money_Button(Save_Money);
        



        add(base1);//这个是最后的一个表现形式
        setBounds(200, 200, 500, 300);
        getContentPane().setBackground(Color.gray);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("MENU");


    }

    public static void main(String[] args){
            GUI gui = new GUI();
    }
}
