package signer;


/**
 * 完成注销函数
 * 连接 Gui界面和logoff_db  完成Gui界面对数据库的响应内容
 */

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

public class logoff extends JFrame{

    /**
     * 建立结构框架体的对象
     */
    JTextField Id_Text, Pw_Text;
    JLabel Id_Label, Pw_Label;
    JButton Ok_Button, Cancel_Button;
    Box box1, box2, box3, baseBox1, baseBox2;

    /**
     * 建立数据库对象
     */
    logoff_db lb;

    /**
     * 建立自己对象的函数
     */
    logoff() {
        init();
    }


    void init() {
        /**
         * 建立游标对象
         */
        setLayout(new FlowLayout());
        Id_Label = new JLabel("Please entry Id:");
        Pw_Label = new JLabel("Please entry Password:");
        Id_Text = new JTextField(20);
        Pw_Text = new JPasswordField(20);
        Ok_Button = new JButton("Confirm");
        Cancel_Button = new JButton("Back");


        /**
         * 建立数据库对象
         */
        lb = new logoff_db();


        /**
         * 建立模块的布局
         */
        box1 = Box.createVerticalBox();
        box1.add(Id_Label);
        box1.add(Box.createVerticalStrut(8));
        box1.add(Pw_Label);


        box2 = Box.createVerticalBox();
        box2.add(Id_Text);
        box2.add(Box.createVerticalStrut(8));
        box2.add(Pw_Text);


        box3 = Box.createHorizontalBox();
        box3.add(Ok_Button);
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


        /**
         *建立监听对象
          */
        Ok_Button.addActionListener(lb);
        Cancel_Button.addActionListener(lb);


        /**
         * 传入参数
         */
        lb.Set_Id_Text(Id_Text);
        lb.Set_Pw_Text(Pw_Text);
        lb.Set_Button_Ok(Ok_Button);
        lb.Set_Button_Cancel(Cancel_Button);
        lb.Set_JFrame(this);



        // 建立java的图像界面  background放在了label中
        // 这里修改文件的路径
        ImageIcon image=new ImageIcon("src\\signer\\time12.jpg");
        JLabel logolabel = new JLabel(image);

        logolabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());


        // 设置底层把图片放在最下面的一层
        getLayeredPane().add(logolabel,new Integer(Integer.MIN_VALUE));

        //设置内容面板  getContentPane前面添加  这个JFrame的对象 由于这个图片是继承了JFrame  所以不需要对象 或者使用this
        JPanel jp = (JPanel) this.getContentPane();

        //设置内容面板未透明  true是透明
        jp.setOpaque(false);


        /**
         * 建立框架大小
         */
        setBounds(200, 200, 400, 300);
        // 不可修改窗体
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("LOGOFF");

    }
}
