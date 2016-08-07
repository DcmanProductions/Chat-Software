
package com.dcmanproductions.vid_eo.server;

import com.dcmanproductions.vid_eo.Paid_Login;
import com.dcmanproductions.vid_eo.server.ServerMain;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

public class ServerPrivateStarter extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1;
    public JButton start;
    Dimension size;
    private JPanel content;
    public JTextField txtPort;
    
    public static void main(String[] args){
    	ServerPrivateStarter sw = new ServerPrivateStarter();
    }
    
    public ServerPrivateStarter() {
        this.setTitle("Vid-Eo | Server Starter");
        this.size = new Dimension(220, 204);
        this.setSize(this.size);
        this.setVisible(true);
        this.setDefaultCloseOperation(2);
        this.setLocationRelativeTo(null);
        this.content = new JPanel();
        this.content.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.content);
        this.content.setLayout(null);
        this.setResizable(false);
        this.init();
    }

    private void init() {
        JLabel lblPort = new JLabel("This is your Port #");
        lblPort.setBounds(this.size.width / 2 - 80, this.size.height / 2 - 80, 150, 25);
        this.content.add(lblPort);
        this.txtPort = new JTextField();
        this.txtPort.setBounds(this.size.width / 2 - 80, this.size.height / 2 - 50, 150, 25);
        this.txtPort.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent arg0) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    ServerPrivateStarter.this.dispose();
                    new com.dcmanproductions.vid_eo.server.ServerMain(Integer.parseInt(ServerPrivateStarter.this.txtPort.getText()));
                    ServerPrivateStarter.this.dispose();
                }
            }

            @Override
            public void keyPressed(KeyEvent arg0) {
            }
        });
        this.content.add(this.txtPort);
        this.start = new JButton("Start Server");
        this.start.setBounds(this.size.width / 2 - 80, this.size.height / 2, 150, 25);
        this.content.add(this.start);
        this.start.setForeground(Color.white);
        this.start.setBackground(Color.DARK_GRAY);
        this.start.addActionListener(this);
        this.update(this.getGraphics());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.start)) {
            this.dispose();
            new com.dcmanproductions.vid_eo.server.ServerMain(Integer.parseInt(this.txtPort.getText()));
            this.dispose();
        }
    }

}

