package signer;


/**
 * 与register.java的方式类似 同样建立一个合理的GUI的界面  来完成用户对功能的实现
 * 连接 GUI界面和  login_db数据库的连接
 * 同时注册信息在这里实现注册 登录GUi界面实现退出的功能
 */

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

public class login extends JFrame{
    JTextField accountT, PasswordT;                // id和密码的文本框
    JButton okB, registB;                          //确定和创建信用户按钮
    Box baseB1, baseB2, box1, box2, box3;
    login_db log;  //实例化  操作login对象函数
//    change_pw_db cpd;
//    query_db qb;

    login(){
        init();
    }

    void init(){
        // 建立数据库对象
        log = new login_db();
//        cpd = new change_pw_db();
//        qb = new query_db();


        // 建立JTextField的对象标签
        accountT = new JTextField(10);
        PasswordT = new JPasswordField(20);
        okB = new JButton("Login");                 //登录
        registB = new JButton("Register");          //新建


        box1 = Box.createVerticalBox();
        box1.add(new JLabel("id:"));
        box1.add(Box.createVerticalStrut(8));
        box1.add(new JLabel("password:"));

        box2 = Box.createVerticalBox();
        box2.add(accountT);
        box2.add(Box.createVerticalStrut(8));
        box2.add(PasswordT);

        box3 = Box.createHorizontalBox();
        box3.add(okB);
        box3.add(Box.createVerticalStrut(20));
        box3.add(registB);


        baseB1 = Box.createHorizontalBox();
        baseB1.add(box1);
        baseB1.add(Box.createHorizontalStrut(8));
        baseB1.add(box2);


        baseB2 = Box.createHorizontalBox();
        baseB2.add(baseB1);
        baseB2.add(Box.createHorizontalStrut(10));
        baseB2.add(box3);


        /**
         * 在这里建立一个函数接口，可以操作login_db这个类
         * 这里是对long_db事件的一个响应
         */
        okB.addActionListener(log);
        registB.addActionListener(log);
//        okB.addActionListener(qb);
//        okB.addActionListener(cpd);


        //将获取的id和密码传给change_password_db
//        cpd.Set_User_Id(accountT);
//        cpd.Set_User_Pw(PasswordT);
//
//
//        qb.Get_User_id(accountT);
//        qb.Get_User_pw(PasswordT);


        /**
         * 调用接口
         * 调用login_db这个类中间的函数
         */




        /**
         * 在这里将内容传给对应的数据库函数 就可以操作完成一次性输入 直接使用
           这种方式可以一劳永逸  避免了频繁输入账号信息
            缺陷是不能操作其他的账号信息
         */

        log.set_JFrame(this);  // 传入一个参数来控制这个组件
        log.setaccount(accountT);
        log.setNameT(PasswordT);
        log.setButton(okB, registB);


//        cpd.Listern_Button(okB);


        add(baseB2);
        setLayout(new FlowLayout());
        setBounds(200, 150, 500, 300);
        setVisible(true);
        setTitle("Custom Login UI");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


    }

    public static void main(String[] args){
        login lo = new login();
    }
}
