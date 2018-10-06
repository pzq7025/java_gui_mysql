package signer;


/**
 * 通过withdrawal_db 连接数据库 使得Gui界面可以实现对数据库的操作
 * 通过withdraw_money调用这个类调用
 * 取款
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class withdrawal_db extends conn_db implements ActionListener {
    JTextField Id_Text, Pw_Text, Output_Text;
    JButton Ok_Button, Cancel_Button;
    Statement stmt;
    ResultSet rs;


    // 获取用户的id
    public void Set_User_Id(JTextField id){
        Id_Text = id;
    }

    // 获取用户的Pw
    public void Set_User_Pw(JTextField pw){
        Pw_Text = pw;
    }

    // 取款的金额
    public void Set_User_Output(JTextField op){
        Output_Text = op;
    }

    // 获取Ok_Button的响应
    public void Set_Ok_Button(JButton ok) {
        Ok_Button = ok;
    }

    // 获取Cancel_Button的响应
    public void Set_Cancel_Button(JButton cancel) {
        Cancel_Button = cancel;
    }


    // 为了提供准确的报错信息所以在Boolean给用户提示
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Ok_Button){
            if(Id_Text.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter your id", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else if(Pw_Text.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please Enter your password", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else if(Output_Text.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please Cash", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else {
                // 将获取的内容做对应的转化 传入数据库的操作中
                String id = Id_Text.getText();
                String pw = Pw_Text.getText();
                double cash = Double.parseDouble(Output_Text.getText());
                if (cash >= 0) {
                    try {
                        // 连接数据库
                        connection();
                        boolean com = withdrawal(id, pw, cash);
                        if (com) {
                            // 正确了就将取款金额重置
                            Output_Text.setText("");
                        } else {
                            // 错误了 重置不可见的密码
//                            Output_Text.setText("");
                            Pw_Text.setText("");
                            Id_Text.setText("");
                        }
                        // 进行错误的捕捉 错误的捕捉在Boolean函数中会提示这里只完成重置的功能
                    } catch (Exception e1) {
                        Id_Text.setText("");
                        Pw_Text.setText("");
                        Output_Text.setText("");
                        e1.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please again cash....");
                    Output_Text.setText("");
                }
            }

        } else if(e.getSource() == Cancel_Button){
            // 后期还需要做一定的改进
            JOptionPane.showMessageDialog(null, "The interface will Exit.......");
            Id_Text.setText("");
            Pw_Text.setText("");
            Output_Text.setText("");
        }


    }


    boolean withdrawal(String id, String pw, double cash) throws Exception{
        // 获取原来数据库中的金额
        String sql;
        // 刷新余额
        String sql1;


        Connection con = super.con;
        Statement stmt = con.createStatement();

        sql = "SELECT money FROM my WHERE id=" + id + " AND password='" + pw + "'";
        rs = stmt.executeQuery(sql);
        // 判断获取的内容是否有效
        if(rs.next()){
            double money = rs.getDouble(1);
            if(money > cash){
                double remain = money - cash;
                sql1 = "UPDATE my set money =" +  remain + ",data=now() WHERE id =" + id + " AND password = '" + pw + "'";
                int rw = stmt.executeUpdate(sql1);
                if(rw <= 0){
                    JOptionPane.showMessageDialog(null, "Please check your info!\n Please again");
                    return false;
                } else {
                    JOptionPane.showMessageDialog(null, "It's Successful!");
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Insufficient Balance\n Please again Cash....");
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Please check your info1\n Please again......");
            return false;
        }
        // 这个是Boolean函数的括号
    }



    // 这个是withdrawal_db的括号
}
