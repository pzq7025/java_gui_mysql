package signer;

/**
 * 在java中直接使用  JpasswordText这个文本窗口就可以实现对密码的演示处理
 */

import javax.swing.*;

public class tool {
    /**
     * 对字符串处理:将指定位置到指定位置的字符以星号代替
     *
     * @param content 传入的字符串
     * @param begin   开始位置
     * @param end     结束位置
     * @return
     */
    private static String getStarString(String content, int begin, int end) {

        if (begin >= content.length() || begin < 0) {
            return content;
        }
        if (end >= content.length() || end < 0) {
            return content;
        }
        if (begin >= end) {
            return content;
        }
        String starStr = "";
        for (int i = begin; i < end; i++) {
            starStr = starStr + "*";
        }
        return content.substring(0, begin) + starStr + content.substring(end, content.length());

    }
}

//
//    ImageIcon img = new ImageIcon("image\\1.jpg");//这是背景图片，这种方式可以获取图片相对路径
//    JLabel imgLabel = new JLabel(img);//将背景图放在标签里。
//
//    JButton bReturn=new JButton("");  //添加返回按钮
//        bReturn.addActionListener(new java.awt.event.ActionListener() {
//            @Override
//        public void actionPerformed(ActionEvent e) {bReturnActionPerformd(e);}});
//        panAbout.add(bReturn);
//        panAbout.add(imgLabel);//注意这里是关键，将背景标签添加到jfram的LayeredPane面板里。
//        imgLabel.setBounds(0,0,img.getIconWidth(), img.getIconHeight());//设置背景标签的位置

