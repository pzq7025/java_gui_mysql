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
    // 创建一个layeredPane用于分层
//    JLayeredPane layeredPane;
    // 创建一个Pane和一个Label用于存放图片  作为背景


    GUI(){
        init();
    }


    void init(){
        setLayout(new FlowLayout());
        // 建立对应的标签对象
        Change_Pass = new JButton("改密");                        // 修改密码
        Save_Money = new JButton("存钱");                         // 存钱
        WithDraw_Money = new JButton("取钱");                     // 取钱
        Exit = new JButton("退出");                               // 退出
        Query = new JButton("查询");                              // 查询
        Logoff = new JButton("注销");                             // 注销用户
        Last = new JButton("返回");                               // 返回上一层
        Next = new JButton("继续");                               // 下一层

        // 设置一下窗口的大小
        Change_Pass.setPreferredSize(new Dimension(85, 25));
        Save_Money.setPreferredSize(new Dimension(85, 25));
        WithDraw_Money.setPreferredSize(new Dimension(85, 25));
        Exit.setPreferredSize(new Dimension(85, 25));
        Query.setPreferredSize(new Dimension(85, 25));
        Logoff.setPreferredSize(new Dimension(85, 25));
        Last.setPreferredSize(new Dimension(85, 25));
        Next.setPreferredSize(new Dimension(85,25));


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
        base1.add(Box.createHorizontalStrut(75));
        base1.add(box1);
        base1.add(Box.createHorizontalStrut(75));
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





        // 开始添加面板里面的内容
        // 不能设置成null  设置成null之后没有内容了
//        jp.setLayout(null);
//        jp.add(base1);
//        jp.add(Change_Pass);
//        jp.add(Save_Money);
//        jp.add(WithDraw_Money);
//        jp.add(Exit);
//        jp.add(Query);
//        jp.add(Logoff);
//        jp.add(Last);
//        jp.add(Next);


        // 前面已经添加了 jp的内容这里不需要在添加jp的内容
        add(base1);
        //建立时间的监听关系实现类之间的调用
        Change_Pass.addActionListener(gui);
        Save_Money.addActionListener(gui);
        WithDraw_Money.addActionListener(gui);
        Exit.addActionListener(gui);
        Query.addActionListener(gui);
        Logoff.addActionListener(gui);
        Last.addActionListener(gui);
//        add(base1);//这个是最后的一个表现形式
        

//        addComponentListener(Change_Pass,);
//        icon = new ImageIcon(GUI.class.getResource("123.jpg"));
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


        // 处理背景
//        jl = new JLabel(image);
////        layeredPane = new JLayeredPane();
////        image = new ImageIcon("123.jpg");
////        jp = new JPanel();
////
////
////        layeredPane.add(jp, JLayeredPane.DEFAULT_LAYER);
//        layeredPane.add(base1, JLayeredPane.MODAL_LAYER);
//
//
//
//
//        setLayeredPane(layeredPane);
//        setSize(image.getIconWidth(), image.getIconHeight());
//        setLocation(image.getIconHeight(), image.getIconHeight());

        // 建立java的图像界面  background放在了label中
        // 文件的路径
        ImageIcon image=new ImageIcon("F:\\exploitation\\codes\\java_codes_project\\new_project\\src\\signer\\time6.jpg");
        JLabel logolabel = new JLabel(image);

        logolabel.setBounds(0, 0, image.getIconWidth(), image.getIconHeight());


        // 设置底层把图片放在最下面的一层
        getLayeredPane().add(logolabel,new Integer(Integer.MIN_VALUE));

        //设置内容面板  getContentPane前面添加  这个JFrame的对象 由于这个图片是继承了JFrame  所以不需要对象 或者使用this
        JPanel jp = (JPanel) this.getContentPane();

        //设置内容面板未透明  true代表透明  透明之后的gui界面是看不到背景图像的
        jp.setOpaque(false);


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
