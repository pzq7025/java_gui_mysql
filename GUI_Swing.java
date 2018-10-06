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
    JButton Change_Pass, Save_Money, WithDrawal_Money, Exit, Query, Print;
    /**
     * 建立对应的文本对象
     * 方便在函数中进行对象的实例化和引用
     */
    change_password cp;
    query q;
    save_money sm;
    withdrawal_money wm;



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
    public void Print_Button(JButton b1){
        Print = b1;
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
        if(e.getSource() == Change_Pass){
            JOptionPane.showMessageDialog(null, "Will enter Change_Password Interface..........");
            cp = new change_password();
        } else if (e.getSource() == Save_Money) {
            JOptionPane.showMessageDialog(null, "Will enter Save_Money Interface.........");
            sm = new save_money();
        } else if(e.getSource() == WithDrawal_Money){
            JOptionPane.showMessageDialog(null, "Will enter Withdrawal_Money Interface.....");
            wm = new withdrawal_money();
        } else if(e.getSource() ==Query){
            JOptionPane.showMessageDialog(null, "will enter the Query Interface........");
            q = new query();
        } else if(e.getSource() ==Print){

        } else if(e.getSource() == Exit){
            JOptionPane.showMessageDialog(null,"The Application will exit........");
            System.exit(0);
        }

    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
}
