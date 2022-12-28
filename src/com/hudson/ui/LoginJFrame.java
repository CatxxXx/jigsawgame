package com.hudson.ui;

import com.hudson.javabean.User;
import com.hudson.util.CodeUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements MouseListener {

    static ArrayList<User> allUsers = new ArrayList<>();
    static {
        allUsers.add(new User("admin","admin"));
        allUsers.add(new User("lisi","1234"));
    }

    JButton login = new JButton();
    JButton register = new JButton();
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField code = new JTextField();

    JLabel rightCode = new JLabel();


    //login frame default attribute
    public LoginJFrame() {
        initJFrame();
        initView();
        this.setVisible(true);
    }


    /**
     * init login frame
     */
    public void initView() {
        //add username image
        JLabel usernameText = new JLabel(new ImageIcon("image\\login\\username.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //add username textfield
        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //add password image
        JLabel passwordText = new JLabel(new ImageIcon("image\\login\\password.png"));
        passwordText.setBounds(110, 195, 90, 26);
        this.getContentPane().add(passwordText);

        //add password textfield
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //verificationcode
        JLabel codeText = new JLabel(new ImageIcon("image\\login\\verificationCode.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //add verificationcode textfield

        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        String codeStr = CodeUtil.getCode();
        //set text
        rightCode.setText(codeStr);
        rightCode.addMouseListener(this);
        rightCode.setBounds(300, 256, 50, 30);
        this.getContentPane().add(rightCode);

        //add login button
        login.setBounds(123, 310, 128, 47);
        login.setIcon(new ImageIcon("image\\login\\loginButton.png"));

        login.setBorderPainted(false);

        login.setContentAreaFilled(false);
        login.addMouseListener(this);
        this.getContentPane().add(login);

        //add regist button
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\registButton.png"));

        register.setBorderPainted(false);

        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.getContentPane().add(register);

        //add background image
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }


    public void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("JIGSAW GAME v1.0");
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
    }


    /**
     * show dialog
     * @param content
     */
    public void showJDialog(String content) {

        JDialog jDialog = new JDialog();

        jDialog.setSize(300, 150);

        jDialog.setAlwaysOnTop(true);

        jDialog.setLocationRelativeTo(null);

        jDialog.setModal(true);


        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 300, 150);
        jDialog.getContentPane().add(warning);


        jDialog.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();

        //press login button
        if(obj == login) {
            //get input username, password, verification code
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            String codeInput = code.getText();

            //create user
            User user = new User(usernameInput, passwordInput);

            //check verification code, username, password
            if(codeInput.length() == 0) {
                showJDialog("please enter verification code");
            } else if(usernameInput.length() == 0 || passwordInput.length() == 0) {
                showJDialog("please username/password");
            } else if(!codeInput.equalsIgnoreCase(rightCode.getText())) {
                showJDialog("wrong verification code");
            } else if(contains(user)) {
                this.setVisible(false);
                new GameJFrame();
            } else {
                showJDialog("username/password is wrong, please try again");
            }
        } else if(obj == register) {
            //
        } else if(obj == rightCode) {
            //change verification code
            String code = CodeUtil.getCode();
            rightCode.setText(code);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\pressLogin.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\pressRegist.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image\\login\\loginButton.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\login\\registButton.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * contain username & password
     * @param userInput
     * @return
     */
    public boolean contains(User userInput){
        for (int i = 0; i < allUsers.size(); i++) {
            User rightUser = allUsers.get(i);
            if(userInput.getUserName().equals(rightUser.getUserName()) && userInput.getPassword().equals(rightUser.getPassword())){
                return true;
            }
        }
        return false;
    }
}
