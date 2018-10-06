package signer;


/**
 * 完成查询的query的函数
 * 连接GUI界面 和 query_db 数据库的查询以及连接工作
 */

import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;

public class query extends JFrame {
    JLabel Id_Label, PassWord_Label;
    JTextField Id_Text, PW_Text;
    JButton Sure_Button, Cancel_Button;
    Box box1, box2, box3, baseBox1, baseBox2;

    // 建立query的数据库操作对象
    query_db qb;


    query(){
        init();
    }


    void init(){
        setLayout(new FlowLayout());
        Id_Label = new JLabel("Please entry id:");
        PassWord_Label = new JLabel("Please entry password:");
        Id_Text = new JTextField(20);
        PW_Text = new JPasswordField(20);
        Sure_Button = new JButton("Sure");
        Cancel_Button = new JButton("Cancel");


        qb = new query_db();


        box1 = Box.createVerticalBox();
        box1.add(Id_Label);
        box1.add(Box.createVerticalStrut(8));
        box1.add(PassWord_Label);


        box2 = Box.createVerticalBox();
        box2.add(Id_Text);
        box2.add(Box.createVerticalStrut(8));
        box2.add(PW_Text);


        box3 = Box.createHorizontalBox();
        box3.add(Sure_Button);
        box3.add(Box.createVerticalStrut(15));
        box3.add(Cancel_Button);


        baseBox1 = Box.createHorizontalBox();
        baseBox1.add(box1);
        baseBox1.add(Box.createHorizontalStrut(8));
        baseBox1.add(box2);


        baseBox2 = Box.createVerticalBox();
        baseBox2.add(baseBox1);
        baseBox2.add(Box.createVerticalStrut(10));
        baseBox2.add(box3);
        add(baseBox2);


        Sure_Button.addActionListener(qb);
        Cancel_Button.addActionListener(qb);



        qb.Sure_Button(Sure_Button);
        qb.Cancel_Button(Cancel_Button);
        qb.Get_Id(Id_Text);
        qb.Get_PW(PW_Text);


        setBounds(200, 200, 400, 300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("QUERY");
    }
    
}
