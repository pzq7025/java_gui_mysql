package signer;

/**
 * 在这里完成API对用户的响应
 * 实现Button的响应
 * 实现用户的注册信息
 * 由register这个类来调用
 */

import java.awt.*;
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
    JFrame rd; //接收传入register的框架模式
    Statement stmt;
    ResultSet rs;
    login ld;

//    register rt;


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


    public  void Set_Jframe(JFrame j1){
        rd = j1;
    }





    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == okButton){
            if (Text_Na.getText().equals("")){
                JOptionPane.showMessageDialog(null, "please entry name!", "WARNING", JOptionPane.WARNING_MESSAGE);
            }
            else if (Text_Pw.getText().equals("")){
                JOptionPane.showMessageDialog(null, "please entry password!", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else {
                String pw = Text_Pw.getText();
                String name = Text_Na.getText();
                // 先获取字段在 判断是够继续建立用户
                // 判断是否继续继续创建用户
                // 0建立 2 不建立
                int ju = JOptionPane.showConfirmDialog(null, "Do you want to create the user", "Tip", JOptionPane.OK_CANCEL_OPTION);
                if (ju == 0) {
                    try {
                        connection();
                        writeSql(pw, name);
                        Text_Na.setText("");
                        Text_Pw.setText("");
                    // 用catch捕捉try产生的错误
                    } catch (Exception e1) {
                        Text_Pw.setText("");
                        Text_Na.setText("");
                        e1.printStackTrace();
                    }
                // 这是判断是否继续的ju的括号
                }
            // 判断字段不为空之后的else的括号
            }
        // okButton的括号
        }
        else if(e.getSource() == resetButton) {
            int ju = JOptionPane.showConfirmDialog(null, "Do you want to Back", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                Text_Pw.setText("");
                Text_Na.setText("");
                JOptionPane.showMessageDialog(null, "Willing Back..............");
                // 关闭当前的界面窗口
                rd.dispose();
                // 建立新的GUI的界面
                ld = new login();
            }
        }
    }


    void writeSql(String pw, String name) throws Exception{
        String sql;
        String sql1;

        // 建立数据库的连接对象
        Connection con = super.con;
        // 建立执行SQL语句的游标
        Statement stmt = con.createStatement();

        sql = "create table if not exists my(account varchar(10),name varchar(20))";

        stmt.executeUpdate(sql);

        System.out.println("create table is successful!");

        sql = "insert into my(password,name,data,statue) values ('"+pw+"','"+name+"',now(),1)";
        sql1 = "SELECT id FROM my WHERE name='" + name + "' AND  password='" + pw + "'" + " AND statue=1 " + " ORDER BY id DESC";
//        sql1 = "SELECT id FROM my WHERE name='" + name +"' AND  password='"+acc+"'";
//        String whereClause = "";
//        whereClause += " sex = '" + forSQL(acc) + "' ";
//        System.out.println(sql1);
        int rw = stmt.executeUpdate(sql);
        rs = stmt.executeQuery(sql1);
        if(rw<=0 ){
            JOptionPane.showMessageDialog(null, "Creating...........");
            JOptionPane.showMessageDialog(null, "register is fail!");
        }
        else{
            rs.next();
//            rs = stmt.executeQuery(sql1);
            JOptionPane.showMessageDialog(null, "Creating..........");
            JOptionPane.showMessageDialog(null, "register is successful!\n"+ "your id is " + rs.getString(1));

        }
        // 建立关闭数据库的好习惯  防止数据流失
        stmt.close();
        con.close();
        rs.close();
    }
}
