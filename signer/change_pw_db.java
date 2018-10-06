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
import java.sql.Statement;
import javax.swing.*;

public class change_pw_db extends conn_db implements ActionListener {
    JTextField Pre_Password, New_Password, Sure_Password;
    JTextField Id;
    JButton okButton, resetButton, sureButton;
    Statement stmt;
    ResultSet rs;

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
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == okButton) {
            if (Pre_Password.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "please entry Pre_Password", "WARNONG", JOptionPane.WARNING_MESSAGE);
            } else if (New_Password.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "please entry New_Password", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else if (Sure_Password.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Entry Sure_Password", "WARNING", JOptionPane.WARNING_MESSAGE);
            } else if (New_Password.getText().equals(Sure_Password.getText())) {
                String acc = Id.getText();
//                String save = User_Pw.getText();
                String pre = Pre_Password.getText();
                String new_p = New_Password.getText();
                String sure = Sure_Password.getText();
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
                    boolean com = SaveSql(acc, pre, new_p);
                    if (com) {
                        JOptionPane.showMessageDialog(null, "register is successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "entry is fail! please again! ");
                        Pre_Password.setText("");
                        New_Password.setText("");
                        Sure_Password.setText("");
                    }
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Please pre_password!");
//                        Id.setText("");
//                        Pre_Password.setText("");
//                        New_Password.setText("");
//                        Sure_Password.setText("");
//                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Save is fail!");
                    e1.printStackTrace();
                }
                // 这个else是新密码和确认新密码不一致所报的错误
            } else {
                JOptionPane.showMessageDialog(null, "please the new_password and sure_password is same!");
            }
            // 这个else if 是点击resetButton造成的响应
        } else if (e.getSource() == resetButton) {
            JOptionPane.showMessageDialog(null, "The Windows Will Exit!");
            Id.setText("");
            Pre_Password.setText("");
            New_Password.setText("");
            Sure_Password.setText("");
        }

    }

    // 调用这个Boolean的函数
    boolean SaveSql(String acc, String saves, String new_p) throws Exception {
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
        sql1 = "SELECT password FROM  my WHERE id='" + acc + "'";
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
        rs = stmt.executeQuery(sql1);
        // 一定要加rs.next()
        if(rs.next()) {
            String pss = rs.getString(1);

            // 判断输入的密码是否和原密码相同  确保了不乱改密码的可能
            if (saves.equals(pss)) {
                // 判断原密码和新密码是否一致  一致不做任何修改
                if (saves.equals(new_p)) {
                    JOptionPane.showMessageDialog(null, "register is fail!" + "the password don't change!");
                    return false;
                } else {
//            rs.next();
                    // 输入密码和原密码不相同  修改数据库中的原始密码
                    sql = "update my set password= '" + new_p + "',data=now() where id=" + acc;
//            int rw = stmt.executeUpdate(sql.toString());
                    // 此处使用的是excuteUpdate的语句  该语句必须是一个int类型  详细参考fromat
                    int rw = stmt.executeUpdate(sql);
//            String total = rs.getString(1) + saves;
//            sql_ = "UPDATE my SET money= " + total + "where id=" + acc;
//            JOptionPane.showMessageDialog(null, "register is successful!");
                    return true;
                }
            } else {
                JOptionPane.showMessageDialog(null, "please check pre_word!");
                // 后期还需要修改  不完全清空 跟具提示清空
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "user info is error!\n please check user info!");
            return false;
        }
    }
}

