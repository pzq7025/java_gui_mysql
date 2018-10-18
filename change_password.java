package signer;


/**
 *完成用户的存款函数
 * 连接了GUI界面和数据库的操作
 * 通过GUI的响应传到change_password在传到change_pw_db类中
 */

import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.JarURLConnection;
import javax.swing.*;
/**
 * java的swing采取的是拼接的方式
 * 通过对box的操作
 * 调用box中的类首先是建立一个GUI的界面
 * 其次是通过带Struct的参数来对这个内容进行操作确定它的位置
 */

public class change_password extends JFrame{
    /**
     * baseBox1  baseBox2 处理合并
     * save_Label 用于存款的标签
     * acc_Label  输入id的标签
     */
    JLabel Id_Label, New_Label, Pre_Label, Sure_New_Label;                             //三个密码的标签和id标签
    JButton cancel_Button, sure_Button;                                                //取消和确定的button
    JTextField Id_Text, New_Password_Text, Pre_Password_Text, Sure_New_Text;           //获取密码和id的文本框
    Box baseBox1, baseBox2, box1, box2, box3;

    /**
     * 建立一个操作mysql数据库的regist对象
     * 以完成对数据库的操作
     * 一个功能一个函数库
     */
    change_pw_db regist;


    change_password(){
        init();
    }




    void init(){
        setLayout(new FlowLayout());
        Id_Label = new JLabel("please Id");                                 // id的标签
        Pre_Label = new JLabel("please Pre_password");                      // 原密码的标签
        New_Label = new JLabel("please new entry password");                // 新密码的标签
        Sure_New_Label = new JLabel("please again password");               // 确定密码的标签
        Pre_Password_Text = new JPasswordField(20);                     // 原密码的文本框
        New_Password_Text = new JPasswordField(20);                     // 新密码的文本框
        Sure_New_Text = new JPasswordField(20)   ;                      // 确认新密码的文本框
        Id_Text = new JTextField(20);                                   // 获取Id的文本框
        sure_Button = new JButton("Confirm");
        cancel_Button = new JButton("Back");


        regist = new change_pw_db();


        box1 = Box.createVerticalBox();
        box1.add(Id_Label);
        box1.add(Box.createVerticalStrut(8));
        box1.add(Pre_Label);
        box1.add(Box.createVerticalStrut(8));
        box1.add(New_Label);
        box1.add(Box.createVerticalStrut(8));
        box1.add(Sure_New_Label);


        box2 = Box.createVerticalBox();
        box2.add(Id_Text);
        box2.add(Box.createVerticalStrut(8));
        box2.add(Pre_Password_Text);
        box2.add(Box.createVerticalStrut(8));
        box2.add(New_Password_Text);
        box2.add(Box.createVerticalStrut(8));
        box2.add(Sure_New_Text);


        box3 = Box.createHorizontalBox();
        box3.add(sure_Button);
        box3.add(Box.createVerticalStrut(15));
        box3.add(cancel_Button);


        baseBox1 = Box.createHorizontalBox();
        baseBox1.add(box1);
        baseBox1.add(Box.createHorizontalStrut(8));
        baseBox1.add(box2);


        baseBox2 = Box.createVerticalBox();
        baseBox2.add(baseBox1);
        baseBox2.add(Box.createVerticalStrut(10));
        baseBox2.add(box3);
        add(baseBox2);

        sure_Button.addActionListener(regist);
        cancel_Button.addActionListener(regist);


//        regist.setaccountField(save_Money_Text);
        regist.set_Jframe(this);

        regist.set_id_Field(Id_Text);
        regist.set_pre_Field(Pre_Password_Text);
        regist.set_new_Field(New_Password_Text);
        regist.set_sure_Field(Sure_New_Text);
        regist.setokButton(sure_Button);
        regist.setresetButton(cancel_Button);


        setBounds(200, 200, 400, 300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("CHANGE_PASSWORD");
    }

//    public static void main(String[] args){
//        change_password rs = new change_password();
//    }
}
