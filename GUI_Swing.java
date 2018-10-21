package signer;


/**
 * GUI.swing 承接了Gui的界面窗口
 * 已完成了对函数的调用操作  这里是所用函数集合的一个接口
 * 在这里可以实现函数的调用
 * 增加Gui的功能  也在此处实现
 */

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Swing implements ActionListener {
    JButton Change_Pass, Save_Money, WithDrawal_Money, Exit, Query, Logoff, Last;
    /**
     * 建立对应的文本对象
     * 方便在函数中进行对象的实例化和引用
     */
    JFrame gui;          // 建立gui界面的JFrame来关闭窗口
    change_password cp;
    query q;
    save_money sm;
    withdrawal_money wm;
    logoff lo;
    login lg;



    //修改密码的Button
    public void Change_Pass_Button(JButton b1){
        Change_Pass = b1;
    }
    
    
    //存钱的button
    public void Save_Money_Button(JButton b1){
        Save_Money = b1;
    }

    
    //取钱的button
    public void WithDraw_Money_Button(JButton b1){
        WithDrawal_Money = b1;
    }
    
    
    //退出的Button
    public void Exit_Button(JButton b1){
        Exit = b1;
    }
    
    
    //查询的Button
    public void Query_Button(JButton b1){
        Query = b1;
    }
    
    
    //打印的Button
    public void Logoff_Button(JButton b1){
        Logoff = b1;
    }


    //获取上面的gui界面
    public void Last_Button(JButton b1) {
        Last = b1;
    }


    //获取退出的JFrame
    public void Set_JFrame(JFrame j1){
        gui = j1;
    }

    
    /**
     * 通过响应按钮调用各类功能的函数
     * @param e  ActionEvent的对象实例
     */
    @Override
    public void actionPerformed (ActionEvent e){
        /**
         * 对象实例化  以及建立它的引用
         * 目前还不会  switch的转化  所以采取else if 的语句来实现这个功能的调用
         * 更具不同的响应  调用不同的函数  这里就是函数响应的接口
         */
        // 先关闭后建立  这样看起来就不会出现父级窗口
        // 0代表是  2 代表不是
        if(e.getSource() == Change_Pass){
            int ju = JOptionPane.showConfirmDialog(null, "Do your want to enter the Change password interface", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if(ju == 0) {
                JOptionPane.showMessageDialog(null, "Will enter Change Password Interface..........");
                gui.dispose();
                cp = new change_password();
            }
        } else if (e.getSource() == Save_Money) {
            int ju = JOptionPane.showConfirmDialog(null, "Do your want to enter the save Money Interface", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if(ju == 0) {
                JOptionPane.showMessageDialog(null, "Will enter Save Money Interface.........");
                gui.dispose();
                sm = new save_money();
            }
        } else if(e.getSource() == WithDrawal_Money){
            int ju = JOptionPane.showConfirmDialog(null, "Do your want to enter the Withdrawal Money interface", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if(ju == 0) {
                JOptionPane.showMessageDialog(null, "Will enter Withdrawal Money Interface.....");
                gui.dispose();
                wm = new withdrawal_money();
            }
        } else if(e.getSource() == Query){
            int ju = JOptionPane.showConfirmDialog(null, "Do your want to enter the Query interface", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if(ju == 0) {
                JOptionPane.showMessageDialog(null, "will enter the Query Interface........");
                gui.dispose();
                q = new query();
            }
        } else if(e.getSource() == Logoff){
            int ju = JOptionPane.showConfirmDialog(null, "Do your want to enter the Logoff interface", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if(ju == 0) {
                JOptionPane.showMessageDialog(null, "Will enter Logoff Interface.......");
                gui.dispose();
                lo = new logoff();
            }
        } else if(e.getSource() == Exit){
            // ju判断是否退出系统
            int ju = JOptionPane.showConfirmDialog(null, "Do your want to Exit The System", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if(ju == 0) {
                JOptionPane.showMessageDialog(null, "The Application will exit........");
                System.exit(0);
            }
        } else if(e.getSource() == Last) {
            // 判断是否返回界面
            int ju = JOptionPane.showConfirmDialog(null, "Do your want to Back The Login interface", "Tip", JOptionPane.OK_CANCEL_OPTION);
            if (ju == 0) {
                JOptionPane.showMessageDialog(null, "Will Backing................");
                gui.dispose();
                lg = new login();
            }
        }

    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
}
