package signer;


/**
 * 完成logoff对数据库的操作
 * 由logoff这个类来调用
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class logoff_db extends conn_db implements ActionListener {
    /**
     * 建立从logoff中传过来的对象
     * @param e
     */
    JTextField Id_Text, Pw_Text;
    JButton Ok_Button, Cancel_Button;
    JFrame Ljk;
    Statement stmt;
    ResultSet rs;
    JFrame lg;
    GUI gui;


    public void Set_Id_Text(JTextField t1){
        Id_Text = t1;
    }

    public void Set_Pw_Text(JTextField t2){
        Pw_Text = t2;
    }

    public void Set_Button_Ok(JButton b1) {
        Ok_Button = b1;
    }

    public void Set_Button_Cancel(JButton b2) {
        Cancel_Button = b2;
    }

    public void Set_JFrame(JFrame p1){
        lg = p1;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Ok_Button){
            if(Id_Text.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please Id", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else if (Pw_Text.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Please enter password", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else {
                // JOption的sure为0  cancel为2
                // JOption的Yes为0   NO为1
                // 确认是否注销用户的判断
                String id = Id_Text.getText();
                String pw = Pw_Text.getText();
                int ju = JOptionPane.showConfirmDialog(null, "Are your want to Logon", "Warning", JOptionPane.OK_CANCEL_OPTION);
                if (ju == 0) {
                    try {
                        connection();
                        boolean Ls = Logoff_Sql(id, pw);
                        // 数据库操作的判断
                        if (Ls) {
                            JOptionPane.showMessageDialog(null, "Successful!");
                            Id_Text.setText("");
                            Pw_Text.setText("");
                        } else {
                            JOptionPane.showMessageDialog(null, "Logoff is fail! Please check information!");
                            Id_Text.setText("");
                            Pw_Text.setText("");
                        }
                    // try的判断 用catch捕捉错误
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "The information is error! please again.......");
                        Id_Text.setText("");
                        Pw_Text.setText("");
                    }
                // 这个是JOptionPane.showConfirmDialog的括号
                }
            // 这个是else的括号,showJOptionDialog的
            }

        } else if(e.getSource()==Cancel_Button) {
            int ju = JOptionPane.showConfirmDialog(null, "Do you want to Back", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                JOptionPane.showMessageDialog(null, "The interface will Exit......");
                Id_Text.setText("");
                Pw_Text.setText("");
                // 关闭当前窗口
                lg.dispose();
                // 创建新GUI界面的窗口
                gui = new GUI();
            }
        }

    }


    boolean Logoff_Sql(String id, String pw) throws SQLException {
        String sql;
        String sql1;


        // 建立连接的对象
        Connection con = super.con;
        // 执行语句游标的建立
        Statement stmt = con.createStatement();


        // SQL语句的书写
        sql = "SELECT password FROM my WHERE id=" + id + " AND password='" + pw + "'" + " AND statue=1";
        try {
            rs = stmt.executeQuery(sql);
            /**
             * 通过excuteQuery来判断rs是否正确
             * 如果正确就继续往下做
             */
            if (rs.next()) {
                if (rs.getString(1).equals(pw)) {
                    JOptionPane.showMessageDialog(null, "Deleting........");
                    sql1 = "update my set statue=0,data=now() where id=" + id + " AND password='" + pw + "'";
                    int rw = stmt.executeUpdate(sql1);
//                return true;
                }
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Deleting.......");
//            JOptionPane.showMessageDialog(null, "user information is wrong!\n Please again!");
                return false;
            }
        } catch (SQLException e){
            // 捕捉错误  返回false
            JOptionPane.showMessageDialog(null, "Deleting.......");
            return false;
        } finally {
            // 关闭数据库的连接   防止数据流失
            stmt.close();
//            con.close();
        }
    }
}
