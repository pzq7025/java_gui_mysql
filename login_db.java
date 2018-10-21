package signer;


/**
 * 这是对数据库的相关操作 完成增删改查等操作
 * 所有的数据库操作在这里完成后期数据库的响应也是在这里完成的
 * 由login类调用
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.JarURLConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class login_db extends conn_db implements ActionListener{
    JTextField accT, nameT;
    JButton okB, registB;
    JFrame lo;
    register re;
    ResultSet rs;
//    save_cust rt;
    GUI Gui;

    public void setaccount(JTextField a){
        accT = a;
    }


    public void setNameT(JTextField n){
        nameT = n;
    }


    public void setButton(JButton b1, JButton b2){
        okB = b1;
        registB = b2;
    }


    public void set_JFrame(JFrame j1){
        lo = j1;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == okB){
            if(accT.getText().equals("")){
                JOptionPane.showMessageDialog(null, "entry your id:");
            }
            else if(nameT.getText().equals("")){
                JOptionPane.showMessageDialog(null, "entry your password:");
            }
            else{
                String accountT = accT.getText();
                String namesT = nameT.getText();
                try {
                    connection();
                    boolean com = compareWithSql(accountT, namesT);
                    if (com){
                        JOptionPane.showMessageDialog(null, "Logining.............");
                        JOptionPane.showMessageDialog(null, "enter is successful!");
                        /**在这里显示登录后的界面
                         *所有的接口先放在了GUI这个java文件中
                         */
                        // 通过GUI的类来实现其他的功能
                        Gui = new GUI();
                        lo.dispose();  // 控制Login登录的界面
//                        rt = new save_cust();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Logining...................");
                        JOptionPane.showMessageDialog(null, "the error! id is error or pwd is wrong");
                        accT.setText("");
                        nameT.setText("");
                    }
                // 用catch捕捉try产生的错误
                // try的括号
                } catch (Exception e1){
                    e1.printStackTrace();
                }
            // 判断完非空后的else的括号
            }
        }else if(e.getSource() == registB){
            int ju = JOptionPane.showConfirmDialog(null, "Do you want to enter Register Interface", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if(ju == 0) {
                JOptionPane.showMessageDialog(null, "Will inter the register interface..........");
                // 关闭当前的界面
                lo.dispose();
                // 建立新的GUI界面窗口
                re = new register();
            }
            /**
             * 这个地方就是函数接口
             * 通过这个就可以调用其他的类
             */
            //rt = new server_cust();
        }
    }



    boolean compareWithSql(String accountT, String namesT) throws Exception{
        String sql;
        // 建立新的连接
        Connection con = super.con;
        // 建立SQL执行语句的游标
        Statement stmt = con.createStatement();
        sql = "select id,password from my WHERE statue = 1";
        // 执行Result结果的游标
        rs = stmt.executeQuery(sql);
        while (rs.next()){
            String acc = rs.getString(1);
            String names = rs.getString(2);
//            String ids_t = rs.getString(3);
            if(acc.equals(accountT) && names.equals(namesT)){
//                rt = new save_cust();
//                System.out.println(ids_t);
                return true;
            }

        }
        return false;
    }
}
