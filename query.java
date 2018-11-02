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
        // 进行Box的布局  后面可以尝试一下 grid的布局方式
        setLayout(new FlowLayout());
        Id_Label = new JLabel("Please entry Id:");
        PassWord_Label = new JLabel("Please entry Password:");
        Id_Text = new JTextField(20);
        PW_Text = new JPasswordField(20);
        Sure_Button = new JButton("Confirm");
        Cancel_Button = new JButton("Back");


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


        // 建立监听器对Button进行监听
        Sure_Button.addActionListener(qb);
        Cancel_Button.addActionListener(qb);



        // 将监听的值传入对应的参数中间 实现对事件的响应操作
        qb.Jframe_(this);
        qb.Sure_Button(Sure_Button);
        qb.Cancel_Button(Cancel_Button);
        qb.Get_Id(Id_Text);
        qb.Get_PW(PW_Text);
//        qb.Exit(this.dispose());


        // 建立java的图像界面  background放在了label中
        // 在这里修改文件的路径就可以修改背景的图片
        ImageIcon image=new ImageIcon("F:\\exploitation\\codes\\java_codes_project\\new_project\\src\\signer\\time13.jpg");
        JLabel logolabel = new JLabel(image);

        logolabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());

        // 设置底层把图片放在最下面的一层
        getLayeredPane().add(logolabel,new Integer(Integer.MIN_VALUE));

        //设置内容面板  getContentPane前面添加  这个JFrame的对象 由于这个图片是继承了JFrame  所以不需要对象 或者使用this
        JPanel jp = (JPanel) this.getContentPane();

        //设置内容面板未透明  true是透明
        jp.setOpaque(false);


        setBounds(200, 200, 400, 300);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("QUERY");
    }

    private JFrame qry;
}
