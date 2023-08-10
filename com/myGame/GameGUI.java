package com.myGame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javafx.scene.chart.PieChart.Data;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.sql.Date;
import java.util.Date;

public class GameGUI extends JFrame  implements ActionListener {
    JLabel heading,clock;
    Font font = new Font("", Font.BOLD,40);
    JButton [] btns = new JButton[9];
    JPanel panel;

    //Creating Games Instance

    int GameChances[] = {2,2,2,2,2,2,2,2,2};
    int ActivePlayer = 0;

    int wpp[][]={
        {0,1,2},
        {3,4,5},
        {6,7,8},
        {0,3,6},
        {1,4,7},
        {2,5,8},
        {0,4,8},
        {2,4,6}
    };

    int winner =2;
    boolean GameOver=false;

     ImageIcon zero = new ImageIcon("zero.png");
     ImageIcon one = new ImageIcon("one.png");

    
    GameGUI(){
        // System.out.println("Create Game GUI");
        // setSize(850,850);
        setBounds(500,0 , 850, 850);
        setTitle("Tic Tac Toe Game");
        ImageIcon img = new ImageIcon("icon.png");
        setIconImage(img.getImage());
        CrateGUI();




        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void CrateGUI(){
        this.setLayout(new BorderLayout());
        //north side we have to set Heading label
        this.getContentPane().setBackground(Color.decode("#2196f3"));
        heading = new JLabel("Tic Tac Toe Game");
        heading.setFont(font);
        ImageIcon img = new ImageIcon("icon.png");
        heading.setIcon(img);
        this.add(heading,BorderLayout.NORTH);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setForeground(Color.white);
        //south side we have to set Clock Label...
        clock = new JLabel("Clock");
        clock.setFont(font);
        clock.setHorizontalAlignment(SwingConstants.CENTER);
        clock.setForeground(Color.white);
        this.add(clock,BorderLayout.SOUTH);

        //Creating a Thread 
        Thread t = new Thread(){

            public void run(){
            try {
                while(true){
                    String datetime = new Date().toLocaleString();
                    clock.setText(datetime);

                    Thread.sleep(1000);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    };
    t.start();

    //Time to add panel in window

    panel = new JPanel();
    panel.setLayout(new GridLayout(3,3));

    for(int i=1;i<=9;i++){
        JButton btn = new JButton();
        //ImageIcon zero = new ImageIcon("zero1.png");
        //ImageIcon one = new ImageIcon("one.png");
       // btn.setIcon(zero);
        btn.setFont(font);
        btns[i-1]=btn;
        btn.setBackground(Color.decode("#90caf9"));
        btn.addActionListener(this);
        btn.setName(i-1+"");
        
        panel.add(btn);
    }
    this.add(panel,BorderLayout.CENTER);
    
       

    }

    @Override
    public void actionPerformed(ActionEvent e) {
    //    System.out.println("Clicked");
    JButton CurrentBtn = (JButton)e.getSource();
    String Namestr = CurrentBtn.getName();
    // System.out.println(Namestr);
    int name = Integer.parseInt(Namestr);

    //If Game is over Show yhid mesage...
    if(GameOver){
        JOptionPane.showMessageDialog(this,"Game Already Over ! ! !");
        return;
    }

    if(GameChances[name]==2){
        if(ActivePlayer==1){
            CurrentBtn.setIcon(one);
            GameChances[name]=ActivePlayer;
            ActivePlayer=0;
        }else{
            CurrentBtn.setIcon(zero);
            GameChances[name]=ActivePlayer;
            ActivePlayer=1;
        }
        System.out.println(GameChances[name]);

        //winning logic...
    for (int []temp : wpp) {
        if((GameChances[temp[0]]==GameChances[temp[1]]) && (GameChances[temp[1]]==GameChances[temp[2]]) && GameChances[temp[2]]!=2){
            winner=GameChances[temp[0]];
            GameOver = true;
            JOptionPane.showMessageDialog(this,"Player "+winner+" is won");
            int i = JOptionPane.showConfirmDialog(this,"Are you want to continue ?");
            if(i==0){
                this.setVisible(false);
                new GameGUI();
            }else if(i==1){
                System.exit(404);
            }else{

            }
            System.out.println(i);
            break;
        }        
    }
      //Draw Logic...
        int count = 0;
        for(int x:GameChances){
            if(x==2){
                count++;
                break;
            }
        }
        if(count==0 && GameOver==false){
            JOptionPane.showMessageDialog(this,"The Game is Draw....");
            int i = JOptionPane.showConfirmDialog(this,"Are you want to continue ?");
            if(i==0){
                this.setVisible(false);
                new GameGUI();
            }else if(i==1){
                System.exit(404);
            }else{

            }
            GameOver=true;
        }
    }else{
        JOptionPane.showMessageDialog(this,"This Position is Already Required!!!");
    }    
    }
}

