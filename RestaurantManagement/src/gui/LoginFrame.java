/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author USER
 */
public class LoginFrame extends JFrame
{
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 =new JPanel();
    Label lbLogin =new Label("Đăng Nhập Ứng Dụng");
    Label lbUsername=new Label("Tên người dùng:");
    Label lbPassword=new Label("Mật khẩu:");
    JCheckBox SeepasswdBox =new JCheckBox("Hiện mật khẩu");
    JTextField txtUsername= new JTextField();
    JPasswordField PasswordFd =new JPasswordField();
    JButton btLogin =new JButton("Đăng Nhập");
    JButton btcancel=new JButton("Thoát");
    public LoginFrame()
    {
        init();
    }
    public final void init()
    {
        setLayout(null);
        p1.setBounds(0, 0,400,100);
        p1.setLayout(null);
        p1.setBackground(Color.decode("#0a0623"));
        p2.setBounds(0,100,400,150);
        lbLogin.setBounds(20,20,360,60);
        lbLogin.setFont(new java.awt.Font("Tahoma", 1, 20));
        lbLogin.setForeground(Color.decode("#fcfcfc"));
        lbLogin.setAlignment(1);
        p1.add(lbLogin);
        p2.setLayout(null);
        lbUsername.setBounds(40,40,100,20);
        lbPassword.setBounds(40,90,100,20);
        txtUsername.setBounds(150,30,200,30);
        PasswordFd.setBounds(150, 80,200,30);
        PasswordFd.setEchoChar('*');
        SeepasswdBox.setBounds(148,115,150,20);
        p2.add(txtUsername);
        p2.add(PasswordFd);
        p2.add(lbUsername);
        p2.add(lbPassword);
        p2.add(SeepasswdBox);
        p3.setBounds(0,250,400,50);
        p3.setLayout(null);
        btLogin.setBounds(50,10,100,30);
        btcancel.setBounds(250,10,100,30);
        btcancel.addActionListener(new ActionListener() {
            private Object interger;
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        SeepasswdBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        if(SeepasswdBox.isSelected())
        {
            SeepasswdBox.setText("Ẩn mật khẩu");
            PasswordFd.setEchoChar((char)0);
        }
        else{
            SeepasswdBox.setText("Hiện mật khảu");
            PasswordFd.setEchoChar('*');
        }
        }
        });
        btLogin.addActionListener(new ActionListener() {
            private Object interger;            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String user=txtUsername.getText();
                char[] t={};
                char[] passd= PasswordFd.getPassword();
                if (user.equals("")==true||Arrays.equals(t, passd)==true)//kiểm tra user và pass có trống ko
                {
                    if (user.equals("")==true && Arrays.equals(t, passd)==true)
                        JOptionPane.showMessageDialog(null,"Tên đăng nhập và mật khẩu trống");
                    else
                    {
                        if(user.equals("")==true)JOptionPane.showMessageDialog(null,"Tên đăng nhập trống");
                        else JOptionPane.showMessageDialog(null,"Mật khẩu trống");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Đăng nhập thành công");//cái này có thể xóa
                    //chuyển frame và tắt frame này
                }
            }
        });
        p3.add(btLogin);
        p3.add(btcancel);
        add(p1);
        add(p2);
        add(p3);
        setUndecorated(true);
        setLocation(440,200);
    }
    public static void main(String[] args)
    {
        LoginFrame Login =new LoginFrame();
        Login.setSize(400,300);
        Login.setVisible(true);
    }

}
