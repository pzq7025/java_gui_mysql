package signer;


/**
 * 完成query的mysql的连接查询
 * 由query类调用
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.JarURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class query_db extends conn_db implements ActionListener{
    JTextField Text_Id, Text_PassWord;
    JButton okButton, cancelButton;
    Statement stmt;
    ResultSet rs;
    JFrame qr;
    GUI gui;



    public void Sure_Button(JButton b1){
        okButton = b1;
    }


    public void Cancel_Button(JButton b1){
        cancelButton = b1;
    }


    public void Get_Id(JTextField id){
        Text_Id = id;
    }


    public void Get_PW(JTextField pw){
        Text_PassWord = pw;
    }


    public void Jframe_(JFrame j1){
        qr = j1;
    }


//    public void Exit(int Ex) {
//        Ex;
//    }


//    public void Get_User_id(JTextField id){
//        User_id = id;
//    }
//
//
//    public void Get_User_pw(JTextField pw){
//        User_pw = pw;
//    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == okButton) {
            if (Text_Id.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "please entry id!", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else if (Text_PassWord.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "please entry password!", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else {
                String id = Text_Id.getText();
                String pw = Text_PassWord.getText();
                // 先获取字段在进行判断
                // 用ju判断是否继续进行查询
                // 0确定 2取消
                int ju = JOptionPane.showConfirmDialog(null, "Do you want to Query the id information", "Tip", JOptionPane.OK_CANCEL_OPTION);
                if (ju == 0) {
                    try {
                        connection();
                        boolean QS = QuerySql(id, pw);
                        if (QS) {
//                        JOptionPane.showMessageDialog(null, "Query is successful!");
                            Text_PassWord.setText("");
                            Text_Id.setText("");
                        } else {
//                        JOptionPane.showMessageDialog(null, "Query is fail!\n" + "please again!");
                            Text_Id.setText("");
                            Text_PassWord.setText("");
                        }
                        // 用catch捕捉try产生的错误
                    } catch (Exception e1) {
//                    JOptionPane.showMessageDialog(null,"query is fail!\n please again!");
                        Text_Id.setText("");
                        Text_PassWord.setText("");
                        e1.printStackTrace();
                    }
                // 这是判断是否继续的括号  ju的括号
                }
                // 判断字段不为空后的else的括号
            }
        // else if 是cancel按钮的一个判断
        } else if(e.getSource() == cancelButton) {
//            int disposeOnClose = JFrame.DISPOSE_ON_CLOSE;
//            System.exit(disposeOnClose);
            int ju = JOptionPane.showConfirmDialog(null, "Do you want to Back", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                JOptionPane.showMessageDialog(null, "The Interface Will Exit..........");
                Text_Id.setText("");
                Text_PassWord.setText("");
                // 优化的地方
                /**
                 * 通过传入JFrame的对象  来完成窗口的关闭工作
                 */
                // 关闭当前的界面窗口
                qr.dispose();
//                qr.dispose();
                // 建立新的GUI界面窗口
                gui = new GUI();
            }
        }
    }


    boolean QuerySql(String id, String pw) throws Exception {
        String sql;


        // 建立连接对象
        Connection con = super.con;
        // 建立SQL的游标对象
        Statement stmt = con.createStatement();
        sql = "SELECT money,password FROM my WHERE id=" + id + " AND password='" + pw + "'" + " AND statue=1";
        try {
            rs = stmt.executeQuery(sql);
            // 这里判断用户信息是否正确  只有在正确的情况下才可以  获取到用户的信息  做一个异常的处理判断
            if (rs.next()) {
                // 在这里判断用户信息是否正确 并给用户返回提示
//        JOptionPane.showMessageDialog(null, "Query is successful!\n" +
//                rs.getString(1) + '\n' + rs.getString(2) + '\n' + rs.getString(3)
//        + '\n' + rs.getString(4) + '\n' + rs.getString(5));
                if (rs.getString(2).equals(pw)) {
                    JOptionPane.showMessageDialog(null, "Querying.........");
                    JOptionPane.showMessageDialog(null, "Query is successful!\n" + "Your balance is:" + rs.getString(1));
                }
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Querying.............");
                JOptionPane.showMessageDialog(null, "user info is wrong!\n please check user info!", "WARNING", JOptionPane.WARNING_MESSAGE);
                return false;
            }
        } catch (SQLException e){
            // 捕捉错误    返回false  给出错误的提示信息
            JOptionPane.showMessageDialog(null,"Querying..........");
            JOptionPane.showMessageDialog(null, "User info is wrong!\n please check user info!", "WARNING", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            // 关闭数据库的连接  防止数据的流失
            stmt.close();
//            con.close();
        }
    }
}
