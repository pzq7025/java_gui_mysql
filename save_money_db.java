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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
                // 还有一个输入字母的bug
                if(cash >= 0) {
                    try {
                        connection();
                        // 设置Boolean来确定值是否可以使用
                        charge(id, pw, cash);
                    boolean com = charge(id, pw, cash);
                    if(com){
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
                } else{
                    JOptionPane.showMessageDialog(null, "Please again Cash......");
                    Input_Text.setText("");
                }
            // 这个是else的括号
            }

        } else if(e.getSource()== Cancel_Button){
            // 这里需要改进 后期再来做优化
            JOptionPane.showMessageDialog(null, "The Interface Will Exit............");
            Input_Text.setText("");
            Id_Text.setText("");
            Pw_Text.setText("");
            sm.dispose();
            gui = new GUI();    // 强对象建立
        }

    }


    // Boolean必须要有一个返回值
    boolean charge(String id, String pw, double cash) throws Exception {
        // 获取原数据库空中金额
        String sql;
        // 刷新数据库中的金额
        String sql1;

        Connection con = super.con;
        Statement stmt = con.createStatement();

        sql = "SELECT money FROM my WHERE id=" + id + " AND password='" + pw + "'";
        rs = stmt.executeQuery(sql);

        // 判断获取的内容是否有效
        // 必须要有rs.next() 才可以rs.getString()  这样获取的内容才能有效
        if(rs.next()){
            double money = rs.getDouble(1);
            double total = money + cash;
            sql1 = "UPDATE my set money =" +  total + ",data=now() WHERE id =" + id + " AND password = '" + pw + "'" + " AND statue=1";
            int rw = stmt.executeUpdate(sql1);
            if(rw <= 0){
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
