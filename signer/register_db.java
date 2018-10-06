package signer;

/**
 * 在这里完成API对用户的响应
 * 实现Button的响应
 * 实现用户的注册信息
 * 由register这个类来调用
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.JarURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class register_db extends conn_db implements ActionListener{
    JTextField Text_Na, Text_Pw;
    JButton okButton, resetButton;
    Statement stmt;
    ResultSet rs;


    // 获取PassWord的内容
    public void Set_Na_Field(JTextField na){
        Text_Na = na;
    }


    // 获取name的界面
    public void Set_Pw_Field(JTextField pw){
        Text_Pw = pw;
    }


    // 确认按钮的响应
    public void setokButton(JButton b1){
        okButton = b1;
    }


    // 重置按钮的响应
    public void setresetButton(JButton b2){
        resetButton = b2;
    }





    public void actionPerformed(ActionEvent e){
        if(e.getSource() == okButton){
            if (Text_Na.getText().equals("")){
                JOptionPane.showMessageDialog(null, "please entry name!", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
            else if (Text_Pw.getText().equals("")){
                JOptionPane.showMessageDialog(null, "please entry password!", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
            else{
                String pw = Text_Pw.getText();
                String name = Text_Na.getText();
                try {
                    connection();
                    writeSql(pw, name);
                    Text_Na.setText("");
                    Text_Pw.setText("");
                } catch (Exception e1){
                    System.out.println("insert is fail!");
                    e1.printStackTrace();
                }
            }
        }
        else if(e.getSource() == resetButton){
            Text_Pw.setText("");
            Text_Na.setText("");
        }
    }


    void writeSql(String pw, String name) throws Exception{
        String sql;
        String sql1;

        Connection con = super.con;
        Statement stmt = con.createStatement();

        sql = "create table if not exists my(account varchar(10),name varchar(20))";

        stmt.executeUpdate(sql);

        System.out.println("create table is successful!");

        sql = "insert into my(password,name,data) values ('"+pw+"','"+name+"',now())";
        sql1 = "SELECT id FROM my WHERE name='" + name +"' AND  password='"+pw+"'"+"ORDER BY id DESC";
//        sql1 = "SELECT id FROM my WHERE name='" + name +"' AND  password='"+acc+"'";
//        String whereClause = "";
//        whereClause += " sex = '" + forSQL(acc) + "' ";
//        System.out.println(sql1);
        int rw = stmt.executeUpdate(sql);
        rs = stmt.executeQuery(sql1);
        if(rw<=0 ){
            JOptionPane.showMessageDialog(null, "register is fail!");
        }
        else{
            rs.next();
//            rs = stmt.executeQuery(sql1);
            JOptionPane.showMessageDialog(null, "register is successful!\n"+ "your id is " + rs.getString(1));

        }
    }
}
