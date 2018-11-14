package signer;


import javax.swing.*;
import java.awt.*;

/**
 * 通过Gui界面的调用  连接Gui和数据库
 * 通过Gui.swing 调用withdrawal_money 在通过withdrawal_money调用withdrawal_db
 */

public class withdrawal_money extends JFrame {
    // 建立给类别所需要的标签
    JLabel Id_Label, Pw_Label, Output_Label;
    JTextField Id_Text, Pw_Text, Output_Text;
    JButton Ok_Button, Cancel_Button;
    Box box1, box2, box3, BaseBox1, BaseBox2;

    // 建立调用数据库的对象
    withdrawal_db wd;

    withdrawal_money(){
        init();
    }

    void init(){
        setLayout(new FlowLayout());
        // 实例化各标签
        Id_Label = new JLabel("Please enter Id");
        Pw_Label = new JLabel("Please enter password");
        Output_Label = new JLabel("please withdrawal cash");
        Id_Text = new JTextField(20);
        Pw_Text = new JPasswordField(20);
        Output_Text = new JTextField(20);
        Ok_Button = new JButton("Confirm");
        Cancel_Button = new JButton("Back");


        // 实例化数据库对象
        wd = new withdrawal_db();


        // 建立窗口框架 Label的布局
        box1 = Box.createVerticalBox();
        box1.add(Id_Label);
        box1.add(Box.createVerticalStrut(8));
        box1.add(Pw_Label);
        box1.add(Box.createVerticalStrut(8));
        box1.add(Output_Label);


        // Text的布局
        box2 = Box.createVerticalBox();
        box2.add(Id_Text);
        box2.add(Box.createVerticalStrut(8));
        box2.add(Pw_Text);
        box2.add(Box.createVerticalStrut(8));
        box2.add(Output_Text);


        // Button的布局
        box3 = Box.createHorizontalBox();
        box3.add(Ok_Button);
        box3.add(Box.createHorizontalStrut(150));
        box3.add(Cancel_Button);


        // 构建Label和Box的合并布局
        BaseBox1 = Box.createHorizontalBox();
        BaseBox1.add(box1);
        BaseBox1.add(Box.createHorizontalStrut(8));
        BaseBox1.add(box2);


        // 构建BaseBox1和box3的合并布局
        BaseBox2 = Box.createVerticalBox();
        BaseBox2.add(BaseBox1);
        BaseBox2.add(Box.createVerticalStrut(10));
        BaseBox2.add(box3);

        // 最后将BaseBox2放到布局中去
        add(BaseBox2);


        // 对两个按钮设置监听
        Ok_Button.addActionListener(wd);
        Cancel_Button.addActionListener(wd);


        // 将获取的输入的内容 对数据库进行响应  完成参数的传递
        wd.Set_JFrame(this);
        wd.Set_User_Id(Id_Text);
        wd.Set_User_Pw(Pw_Text);
        wd.Set_User_Output(Output_Text);
        wd.Set_Ok_Button(Ok_Button);
        wd.Set_Cancel_Button(Cancel_Button);

        // 建立java的图像界面  background放在了label中
        // 在这里修改文件名字  就可以修改背景图像
        ImageIcon image=new ImageIcon("src\\signer\\time16.jpg");
        JLabel logolabel = new JLabel(image);

        logolabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());

        // 设置底层把图片放在最下面的一层
        getLayeredPane().add(logolabel,new Integer(Integer.MIN_VALUE));

        //设置内容面板  getContentPane前面添加  这个JFrame的对象 由于这个图片是继承了JFrame  所以不需要对象 或者使用this
        JPanel jp = (JPanel) this.getContentPane();

        //设置内容面板未透明  true是透明
        jp.setOpaque(false);



        // 设置窗口的大小
        setBounds(200, 200, 400, 300);
        // 不可修改窗体
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("WITHDRAWAL_MONEY");

    }
}
