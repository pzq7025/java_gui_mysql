package signer;


/**
 * 完成修改密码的函数
 * 由Change_password类调用
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.JarURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.print.attribute.standard.JobOriginatingUserName;
import javax.swing.*;

public class change_pw_db extends conn_db implements ActionListener {
    JTextField Pre_Password, New_Password, Sure_Password;
    JTextField Id;
    JButton okButton, resetButton, sureButton;
    JFrame cp;
    Statement stmt;
    ResultSet rs;
    GUI gui;

    //    JPanel pInput = new JPanel();
    // 获取原始id
    public void set_id_Field(JTextField id) {
        Id = id;
    }

    // 获取原密码
    public void set_pre_Field(JTextField p) {
        Pre_Password = p;
    }

    // 获取新密码
    public void set_new_Field(JTextField n) {
        New_Password = n;
    }


    // 确认新密码
    public void set_sure_Field(JTextField s) {
        Sure_Password = s;
    }


    public void set_Jframe(JFrame j1) {
        cp = j1;
    }


    // 获取用户的信息id
//    public void Set_User_Id(JTextField Id){
//        User_Id = Id;
//    }
//
//
//    // 获取用户的密码
//    public void Set_User_Pw(JTextField Pw){
//        User_Pw = Pw;
//    }

    // okbutton函数
    public void setokButton(JButton b1) {
        okButton = b1;
    }


    // reset函数
    public void setresetButton(JButton b2) {
        resetButton = b2;
    }


//    public void Listern_Button(JButton b3){
//        sureButton = b3;
//    }

    // 定义函数的主体
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            if (Pre_Password.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "please entry Pre_Password", "WARNONG", JOptionPane.WARNING_MESSAGE);
            } else if (New_Password.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "please entry New_Password", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else if (Sure_Password.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Entry Sure_Password", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else if (New_Password.getText().equals(Sure_Password.getText())) {
                // 这样写可以更快的进入db中
                String acc = Id.getText();
                String pre = Pre_Password.getText();
                String new_p = New_Password.getText();
                String sure = Sure_Password.getText();
                // 判断是否确认修改密码
                // 0 为确认 2为取消
                int jd = JOptionPane.showConfirmDialog(null, "Do you want change password", "Tip", JOptionPane.OK_CANCEL_OPTION);
                if(jd == 0) {
//                String save = User_Pw.getText();
//            Integer.parseInt(text_save);
                    try {
                        // 连接数据库
                        //                    String sql1;
                        connection();
                        // 判断输入的密码和原始密码是否一致一致才能做修改
                        //                    Connection con = super.con;
                        //                    Statement stmt = con.createStatement();

                        //                    sql1 = "SELECT password FROM  my WHERE id='" + acc + "'";

                        //                    stmt.executeQuery(sql1);
                        //                    boolean judge = Judge()
                        //                    if(rs.getString(1).equals(pre)){
                        // 创建一个com的boolean函数
                        SaveSql(acc, pre, new_p);
//                    boolean com = SaveSql(acc, pre, new_p);
//                    if (com) {
//                        JOptionPane.showMessageDialog(null, "Change is successful!");
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Error has been prompted\n please ");
//                        Pre_Password.setText("");
//                        New_Password.setText("");
//                        Sure_Password.setText("");
//                    }
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Please pre_password!");
//                        Id.setText("");
//                        Pre_Password.setText("");
//                        New_Password.setText("");
//                        Sure_Password.setText("");
//                    }
                        // 用catch捕捉try产生的错误
                    } catch (Exception e1) {
//                    JOptionPane.showMessageDialog(null, "Save is fail!");
                        e1.printStackTrace();
                        }
                    // 这个是确认修改密码提示框的括号
                    }
                // 这个else是新密码和确认新密码不一致所报的错误
                } else {
                    JOptionPane.showMessageDialog(null, "please the new_password and sure_password is same........");
                    Sure_Password.setText("");
                    New_Password.setText("");
                }
            // 这个else if 是点击resetButton造成的响应
        } else if (e.getSource() == resetButton) {
            int ju = JOptionPane.showConfirmDialog(null, "Do you want to Back", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                JOptionPane.showMessageDialog(null, "The Windows Will Exit..........");
                Id.setText("");
                Pre_Password.setText("");
                New_Password.setText("");
                Sure_Password.setText("");
                // 关闭窗口change_pw的窗口界面
                cp.dispose();
                // 建立新的GUI界面
                gui = new GUI();
            }
        }
    }

    // 调用这个Boolean的函数
    void SaveSql(String acc, String saves, String new_p) throws Exception {
        String sql;
        String sql1;

//        String sql_;
//        login_db Lnamet;
//        register rq;
//        Lnamet = new login_db();
//        rq = new register();
//        Lnamet.setNameT(rq.nameText);


        Connection con = super.con;
        Statement stmt = con.createStatement();

        // 查询密码对客户信息进行一个判断
        sql1 = "SELECT password FROM  my WHERE id='" + acc + "'" + " AND statue=1";
////        sql = "SELECT id FROM my WHERE name='" + name +"' AND  password='"+acc+"'";
//
////        int rw = stmt.executeUpdate(sql);
//
////            sql_ = "UPDATE my SET money = " + ;
////            rs = stmt.executeQuery(sql_);
////            String total = money1 + saves;
////            String money1 = rs.getString(1);
////            System.out.println(sql_);
//////            System.exit(0);
////            rs = stmt.executeQuery(sql_);
////            return true;
////            int rw = stmt.executeUpdate(sql_);
        try {
            rs = stmt.executeQuery(sql1);
            // 一定要加rs.next()
            if (rs.next()) {
                String pss = rs.getString(1);

                // 判断输入的密码是否和原密码相同  确保了不乱改密码的可能
                if (saves.equals(pss)) {
                    // 判断原密码和新密码是否一致  一致不做任何修改
                    if (saves.equals(new_p)) {
                        JOptionPane.showMessageDialog(null, "Same as the original password without modification");
                        New_Password.setText("");
                        Sure_Password.setText("");
//                    return false;
                    } else {
//            rs.next();
                        // 输入密码和原密码不相同  修改数据库中的原始密码
                        sql = "update my set password= '" + new_p + "',data=now() where id=" + acc + " AND statue=1";
//            int rw = stmt.executeUpdate(sql.toString());
                        // 此处使用的是excuteUpdate的语句  该语句必须是一个int类型  详细参考fromat
                        int rw = stmt.executeUpdate(sql);
//            String total = rs.getString(1) + saves;
//            sql_ = "UPDATE my SET money= " + total + "where id=" + acc;
//            JOptionPane.showMessageDialog(null, "register is successful!");
//                    return true;
                        /**
                         * 后期的改进在这里做一个标记
                         */
                        JOptionPane.showMessageDialog(null, "It's successful....");
                        // 如果不使用Boolean类型 在这里退出程序
                        Id.setText("");
                        Pre_Password.setText("");
                        New_Password.setText("");
                        Sure_Password.setText("");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Changing Password.........");
                    JOptionPane.showMessageDialog(null, "please check pre_word!");
                    // 后期还需要修改  不完全清空 跟具提示清空
//                return false;
                    Pre_Password.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Changing Password................");
                JOptionPane.showMessageDialog(null, "user info is error!\n please check user info!");
                Pre_Password.setText("");
                Sure_Password.setText("");
                New_Password.setText("");
                Id.setText("");
//            return false;
            }
        } catch (SQLException e){
            // 捕捉错误返回原来的状态
            e.printStackTrace();
        } finally {
            // 关闭数据库
            stmt.close();
//            con.close();
        }
    }
}

