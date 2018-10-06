package signer;


/**
 * 通过Gui界面的调用  连接Gui和数据库
 * 通过Gui的组件调用save_money 在通过save_money调用 save_money_db完成数据库的操作
 */

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class save_money extends JFrame {
    // 建立对应的标签实现对应的功能
    JLabel Id_Label, Pw_Label, Input_Label;
    JTextField Id_Text, Pw_Text, Input_Text;
    JButton Ok_Button, Cancel_Button;
    Box box1, box2, box3, BaseBox1, BaseBox2;

    save_money_db smd;

    save_money() {
        init();
    }

    void init(){
        setLayout(new FlowLayout());
        // 建立对应的标签对象 实现功能
        Id_Label = new JLabel("please Save Id");
        Pw_Label = new JLabel("please password");
        Input_Label = new JLabel("please save cash");
        Id_Text = new JTextField(20);
        Pw_Text = new JPasswordField(20);
        Input_Text = new JTextField(20);
        Ok_Button = new JButton("Sure");
        Cancel_Button = new JButton("Cancel");


        // 建立数据库对象
        smd = new save_money_db();


        // 构建窗口框架 Label标签的布局
        box1 = Box.createVerticalBox();
        box1.add(Id_Label);
        box1.add(Box.createVerticalStrut(8));
        box1.add(Pw_Label);
        box1.add(Box.createVerticalStrut(8));
        box1.add(Input_Label);


        // 构建JTextField的标签的布局
        box2 = Box.createVerticalBox();
        box2.add(Id_Text);
        box2.add(Box.createVerticalStrut(8));
        box2.add(Pw_Text);
        box2.add(Box.createVerticalStrut(8));
        box2.add(Input_Text);


        // 构建JButton的标签布局
        box3 = Box.createHorizontalBox();
        box3.add(Ok_Button);
        box3.add(Box.createHorizontalStrut(150));
        box3.add(Cancel_Button);


        // 构建Label和Textfield的和合并布局
        BaseBox1 = Box.createHorizontalBox();
        BaseBox1.add(box1);
        BaseBox1.add(Box.createHorizontalStrut(8));
        BaseBox1.add(box2);


        // 将所有布局进行合并
        BaseBox2 = Box.createVerticalBox();
        BaseBox2.add(BaseBox1);
        BaseBox2.add(Box.createVerticalStrut(10));
        BaseBox2.add(box3);

        // 将最后的整体放入布局中
        add(BaseBox2);


        // 设置监听
        Ok_Button.addActionListener(smd);
        Cancel_Button.addActionListener(smd);


        // 进行参数的传递
        smd.Set_Id_Text(Id_Text);
        smd.Set_Pw_Text(Pw_Text);
        smd.Set_Input_Text(Input_Text);
        smd.Set_Ok_Button(Ok_Button);
        smd.Set_Cancel_Button(Cancel_Button);


        // 设置窗口的大小以及窗口的位置
        setBounds(200, 200, 400, 300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("SAVE_MONEY");


    }
}