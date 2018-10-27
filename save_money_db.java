package signer;


/**
 * 实现Gui界面对数据库的操作
 * 通过save_money调用这个类
 * 存款
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.*;

/**设置监听  监听save_money传来的参数
 * 通过public void actionPerformed来监听响应
 * 建立函数接收传来的参数
 */

public class save_money_db extends conn_db implements ActionListener {
    JTextField Id_Text, Pw_Text, Input_Text;
    JButton Ok_Button, Cancel_Button;
    Statement stmt;
    ResultSet rs;
    JFrame sm;
    GUI gui;


    // 接收Id_Text的内容
    public void Set_Id_Text(JTextField id){
        Id_Text = id;
    }

    // 接收Pw_Text的内容
    public void Set_Pw_Text(JTextField pw){
        Pw_Text = pw;
    }

    // 接收Input的内容
    public void Set_Input_Text(JTextField input){
        Input_Text = input;
        // 注册键盘事件监听器
        Input_Text.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key = e.getKeyChar();
                if(!(key >= KeyEvent.VK_0 && key <= KeyEvent.VK_9)){
                    int su = JOptionPane.showConfirmDialog(null, "Please enter number", "WARNING", JOptionPane.OK_CANCEL_OPTION);
                    if(su == 0){
                        Input_Text.setText("");
                    }
                    e.consume();
                }
            }
        });
    }

    // 设置Ok的响应
    public void Set_Ok_Button(JButton b1){
        Ok_Button = b1;
    }

    // 设置Cancel的响应
    public void Set_Cancel_Button(JButton b2) {
        Cancel_Button = b2;
    }


    // 设置JFrame的响应
    public void Set_JFrame(JFrame j1){
        sm = j1;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // 对输入信息是否为空进行判断

        if(e.getSource() == Ok_Button){
            if (Id_Text.getText().equals("")){
                JOptionPane.showMessageDialog(null, "please enter your id!", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else if(Pw_Text.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter your password", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else if(Input_Text.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter Cash!", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else {
                String id = Id_Text.getText();
                String pw = Pw_Text.getText();
                double cash = Double.parseDouble(Input_Text.getText());
                // 先获取字段内容
                // 判断是否继续进行存钱的操作
                // 0继续  2取消
//                try {
                int ju = JOptionPane.showConfirmDialog(null, "Do you want to save money", "Tip", JOptionPane.OK_CANCEL_OPTION);
                if (ju == 0) {
//                    if(cash.Class){
//                        JOptionPane.showMessageDialog(null, "please Inter", "WARNING", JOptionPane.WARNING_MESSAGE);
//                        Input_Text.setText("");
//                    // 判断cash是否是数字
//                    } else {
                    // 还有一个输入字母的bug
                    if (cash >= 0) {
                        try {
                            connection();
                            // 设置Boolean来确定值是否可以使用
                            charge(id, pw, cash);
                            boolean com = charge(id, pw, cash);
                            if (com) {
                                JOptionPane.showMessageDialog(null, "Charging.............");
                                JOptionPane.showMessageDialog(null, "Charge is successful.......");
                                Input_Text.setText("");
                            } else {
                                JOptionPane.showMessageDialog(null, "Charging...............");
                                JOptionPane.showMessageDialog(null, "Charge is fail!\n" + "Please check info....");
                                Id_Text.setText("");
                                Pw_Text.setText("");
                            }

                            // 异常的捕捉
                        } catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, "Recharge failed!\n Please again.....");
                            Id_Text.setText("");
                            Pw_Text.setText("");
                            Input_Text.setText("");
                            e1.printStackTrace();
                        }
                        // 判断金额大于0之后的else
                    } else {
                        JOptionPane.showMessageDialog(null, "Cash enter is error! Please again Cash......");
                        Input_Text.setText("");
                    }
                    // 判断Input不是字符后 继续执行的括号
//                    }
                    // 这个是ju判断的括号  判断是否继续进行操作
                }
//                } catch (NumberFormatException e1){
//                    System.out.println("klkjlk");
//                    e1.getStackTrace();
//                    e1.getCause();
//                    throw new IOException();
//                throw new
//                }
            // 这个是else的括号 判断字段不为空之后的一个条件语句
            }
        //  else if 是判断Cancel_Button按钮的
        } else if(e.getSource() == Cancel_Button) {
            // 这里需要改进 后期再来做优化
            int ju = JOptionPane.showConfirmDialog(null, "Do you want to Back", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                JOptionPane.showMessageDialog(null, "The Interface Will Exit............");
                Input_Text.setText("");
                Id_Text.setText("");
                Pw_Text.setText("");
                // 关闭当前窗口
                sm.dispose();
                // 建立新的GUI界面
                gui = new GUI();    // 强对象建立
            }
        }

    }


    // Boolean必须要有一个返回值
    boolean charge(String id, String pw, double cash) throws Exception {
        // 获取原数据库空中金额
        String sql;
        // 刷新数据库中的金额
        String sql1;

        // 建立连接的对象
        Connection con = super.con;
        // 建立执行SQL语句的游标
        Statement stmt = con.createStatement();

        // 执行SQL语句
        sql = "SELECT money FROM my WHERE id=" + id + " AND password='" + pw + "'";
        rs = stmt.executeQuery(sql);

        // 判断获取的内容是否有效
        // 必须要有rs.next() 才可以rs.getString()  这样获取的内容才能有效
        if(rs.next()){
            double money = rs.getDouble(1);
            double total = money + cash;
            sql1 = "UPDATE my set money =" +  total + ",data=now() WHERE id =" + id + " AND password = '" + pw + "'" + " AND statue=1";
            int rw = stmt.executeUpdate(sql1);
            if (rw <= 0){
//                JOptionPane.showMessageDialog(null, "Info is wrong\n Please check again...");
                return false;
            } else {
//                JOptionPane.showMessageDialog(null, "It's successful!");
                return true;
            }
        } else {
//            JOptionPane.showMessageDialog(null, "Info is wrong\n Please check again...");
            return false;
        }

    }
    // 这是整个主类的括号
}
