package signer;


/**
 * 这个功能单独放在了登录界面  以确保登陆之后确实有信息的存在
 * 连接的登录界面和数据库的操作关系
 * login调用register这个类  在通过这个类调用register_db 完成对数据库的操作
 */

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.net.JarURLConnection;
import javax.swing.*;

/**
 * 继承与java的Frame框架 构建出gui的操作界面
 * 设置一个accountLabel  对应数据库中的account的字段
 * 设置nameLabel 对应数据库中的name字段
 * JButton 是操作按钮通过这种响应完成对客户的操作内容
 * JtextField 输入文本框架 通过客户的操作完成可视化的操作
 * Box 布局操作是对GUI用户界面的一个设置大小 来呈现这种操作
 */

public class register extends JFrame{
    JLabel PassWord_Label, Name_Label;
    JButton okButton, resetButton;
    JTextField PassWord_Text, Name_Text;
    Box baseBox1, baseBox2, box1, box2, box3;


    register_db regist;

    register(){
        init();
    }


    void init(){
        // 标签的设计采取Box  可以尝试一下grid
        setLayout(new FlowLayout());
        PassWord_Label = new JLabel("password");
        Name_Label = new JLabel("name");
        PassWord_Text = new JPasswordField(10);
        Name_Text = new JTextField(20);
        okButton = new JButton("sure");
        resetButton = new JButton("reset");


        regist = new register_db();


        box1 = Box.createVerticalBox();
        box1.add(Name_Label);
        box1.add(Box.createVerticalStrut(8));
        box1.add(PassWord_Label);


        box2 = Box.createVerticalBox();
        box2.add(Name_Text);
        box2.add(Box.createVerticalStrut(8));
        box2.add(PassWord_Text);


        box3 = Box.createHorizontalBox();
        box3.add(okButton);
        box3.add(Box.createVerticalStrut(15));
        box3.add(resetButton);


        baseBox1 = Box.createHorizontalBox();
        baseBox1.add(box1);
        baseBox1.add(Box.createHorizontalStrut(8));
        baseBox1.add(box2);


        baseBox2 = Box.createHorizontalBox();
        baseBox2.add(baseBox1);
        baseBox2.add(Box.createHorizontalStrut(10));
        baseBox2.add(box3);
        add(baseBox2);


        // 建立监听的设置  目前还只能夸一个函数  后期在做修改
        okButton.addActionListener(regist);
        resetButton.addActionListener(regist);


        // 通过监听 调用对应的函数 传入对应的参数  实现相应的功能
        regist.Set_Na_Field(Name_Text);
        regist.Set_Pw_Field(PassWord_Text);
        regist.setokButton(okButton);
        regist.setresetButton(resetButton);


        setBounds(200, 200, 500, 300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("use register UI");
    }
}
