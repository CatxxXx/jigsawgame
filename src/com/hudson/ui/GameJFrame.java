package com.hudson.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //create Two-dimensional array
    int[][] data = new int[4][4];

    //record white space
    int x = 0;
    int y = 0;

    //creat menu items
    JMenuItem personChange = new JMenuItem("Person");
    JMenuItem animalChange = new JMenuItem("Animal");
    JMenuItem sportChange = new JMenuItem("Sport");
    JMenuItem restartItem = new JMenuItem("Restart");
    JMenuItem reloginItem = new JMenuItem("Relogin");
    JMenuItem closeItem = new JMenuItem("Close");

    JMenuItem introduceItem = new JMenuItem("Introduce");

    //create step count variable
    int stepCount = 0;

    //image path variable
    String path = "image\\person\\person12\\";

    //create correct Two-dimensional array
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //game frame default attribute
    public GameJFrame() {

        initJFrame();

        initJMenuBar();

        initData();

        initImage();

        //visible
        this.setVisible(true);
    }

    /**
     * init data (make image random)
     */
    private void initData() {
        //random image location
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random rd = new Random();

        for (int i = 0; i < tempArr.length; i++) {
            int randomIndex = rd.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[randomIndex];
            tempArr[randomIndex] = temp;
        }

        //add tempArr in 4x4 Two-dimensional array
        /*        for (int i = 0; i < tempArr.length; i++) {
            data[i / 4][i % 4] = tempArr[i];
        }*/

        int index = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tempArr[index] == 0) {
                    x = i;
                    y = j;
                }
                data[i][j] = tempArr[index];
                index++;
            }
        }
    }

    /**
     * init images
     */
    private void initImage() {
        //reset all images after pressed
        this.getContentPane().removeAll();

        //judge win or not
        if (victory()) {
            JLabel win = new JLabel(new ImageIcon("image\\win.png"));
            win.setBounds(203, 283, 197, 73);
            this.getContentPane().add(win);
        }

        //create step count
        JLabel step = new JLabel("STEP: " + stepCount);
        step.setBounds(50, 30, 100, 20);
        this.getContentPane().add(step);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //get image number
                int imageNumber = data[i][j];
                //create jlabel object
                JLabel jLabel = new JLabel(new ImageIcon(path + imageNumber + ".jpg"));
                //Specify this image location
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //set bevelborder
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //add jlabel to imageicon
                this.getContentPane().add(jLabel);
            }
        }

        //create background
        JLabel backGround = new JLabel(new ImageIcon("image\\background.png"));
        backGround.setBounds(40, 40, 508, 560);
        this.getContentPane().add(backGround);

        //repaint content
        this.getContentPane().repaint();

    }


    /**
     * init game  menubar
     */
    private void initJMenuBar() {
        //creat menu bar
        JMenuBar jMenuBar = new JMenuBar();
        //creat menu
        JMenu functionJMenu = new JMenu("Start");
        JMenu aboutJMenu = new JMenu("About Me");
        JMenu changeJMenu = new JMenu("Change gallery");

        //add items to menu
        functionJMenu.add(changeJMenu);
        functionJMenu.add(restartItem);
        functionJMenu.add(reloginItem);
        functionJMenu.add(closeItem);

        changeJMenu.add(personChange);
        changeJMenu.add(animalChange);
        changeJMenu.add(sportChange);

        aboutJMenu.add(introduceItem);

        //add actionListener to menu
        restartItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);

        personChange.addActionListener(this);
        animalChange.addActionListener(this);
        sportChange.addActionListener(this);

        introduceItem.addActionListener(this);

        //add menu to menu bar
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //set menu to frame
        this.setJMenuBar(jMenuBar);
    }

    /**
     * init game frame
     */
    private void initJFrame() {
        //title
        this.setTitle("JIGSAW GAME v1.0");
        //size
        this.setSize(603, 680);
        //location
        this.setLocationRelativeTo(null);
        //on top
        this.setAlwaysOnTop(true);
        //close mode
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //cancel default location(middle)
        this.setLayout(null);

        //create keyboard listen event
        this.addKeyListener(this);
    }

    /**
     * @return
     */
    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //pressed show full image
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();
            //load all image
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            //load background image
            JLabel backGround = new JLabel(new ImageIcon("image\\background.png"));
            backGround.setBounds(40, 40, 508, 560);
            this.getContentPane().add(backGround);
            //repaint
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //if win, stop move
        if (victory()) return;

        int code = e.getKeyCode();
        //control image move
        //left:37 up:38 right:39 down:40
        if (code == 37) {
            if (y == 3) return;
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            stepCount++;
            initImage();
        } else if (code == 38) {
            if (x == 3) return;
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            stepCount++;
            initImage();
        } else if (code == 39) {
            if (y == 0) return;
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            stepCount++;
            initImage();
        } else if (code == 40) {
            if (x == 0) return;
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            stepCount++;
            initImage();
        }
        //show all
        else if (code == 65) {
            initImage();
        }
        //cheat button
        else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == restartItem) {
            //stepcount return to zero
            stepCount = 0;
            //re-scramble image
            initData();
            //reload image
            initImage();

        } else if (obj == reloginItem) {
            //close gameframe
            this.setVisible(false);
            //open loginframe
            new LoginJFrame();

        } else if (obj == closeItem) {
            System.exit(0);

        } else if (obj == introduceItem) {
            //create jdialog
            JDialog jDialog = new JDialog();
            //create jlable
            JLabel jLabel = new JLabel(new ImageIcon("image\\about.png"));
            //set image location and width height
            jLabel.setBounds(0, 0, 258, 258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344, 344);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);

        } else if (obj == personChange) {
            //random pick image
            Random rd = new Random();
            int number = rd.nextInt(13) + 1;
            path = "image\\person\\person"+ number +"\\";
            stepCount = 0;

            initData();
            initImage();
        } else if (obj == animalChange) {
            //random pick image
            Random rd = new Random();
            int number = rd.nextInt(8) + 1;
            path = "image\\animal\\animal"+ number +"\\";
            stepCount = 0;

            initData();
            initImage();
        } else if (obj == sportChange) {
            //random pick image
            Random rd = new Random();
            int number = rd.nextInt(10) + 1;
            path = "image\\sport\\sport"+ number +"\\";
            stepCount = 0;

            initData();
            initImage();
        }
    }
}
