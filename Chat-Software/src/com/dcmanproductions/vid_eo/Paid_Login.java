/*
 * Decompiled with CFR 0_110.
 */
package com.dcmanproductions.vid_eo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dcmanproductions.vid_eo.TransferInfo.TextTransfer;
import com.dcmanproductions.vid_eo.Updater.Updater;

public class Paid_Login extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1;
    private String title = "Vid-Eo | Login";
    private String Name;
    private final Dimension size = new Dimension(316, 344);
    private JLabel lblName;
    private JLabel lblIpAddress;
    private JLabel lblPort;
    public static JTextField txtName;
    public static JTextField txtIpAddress;
    public static JTextField txtPort;
    private JButton login;
    private JButton createServer;
    private JButton update;
    private JPanel contentPane;

    public static void main(String[] args) {
        new com.dcmanproductions.vid_eo.Paid_Login();
    }

    public Paid_Login() {
        this.setTitle(this.title);
        this.setSize(this.size);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout(null);
        this.contentPane.setBackground(Color.DARK_GRAY);
        this.update(this.getGraphics());
        this.init();
    }

    private void init() {
        this.lblName = new JLabel("Please Enter a Username");
        this.lblName.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 280 + 105, 550, 100);
        this.lblName.setForeground(Color.white);
        this.contentPane.add(this.lblName);
        txtName = new JTextField(this.Name);
        txtName.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 210 + 105, 150, 25);
        txtName.requestFocus(true);
        txtName.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    String name = Paid_Login.txtName.getText();
                    int port = Integer.parseInt(Paid_Login.txtPort.getText());
                    String ip = Paid_Login.txtIpAddress.getText();
                    
        			try {
        				TextTransfer.TextWriter(name+"'s Server", name + "\n" + Paid_Login.txtPort.getText() + "\n" + ip);
        				TextTransfer.writer.println(name);
        				TextTransfer.writer.println(ip);
        				TextTransfer.writer.println(Paid_Login.txtPort.getText());
        				TextTransfer.writer.close();					} catch (IOException e1) {
						System.out.println("Having Trouble creating files in KeyPressed Method");
						e1.printStackTrace();
					}
            		
                    
                    Paid_Login.this.login(name, ip, port);
                }
            }
        });
        this.contentPane.add(txtName);
        this.lblIpAddress = new JLabel("Enter Servers IP Address");
        this.lblIpAddress.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 200 + 105, 550, 100);
        this.lblIpAddress.setForeground(Color.white);
        this.contentPane.add(this.lblIpAddress);
        txtIpAddress = new JTextField();
        txtIpAddress.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 130 + 105, 150, 25);
        this.contentPane.add(txtIpAddress);
        this.lblPort = new JLabel("Enter Servers Port #");
        this.lblPort.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 130 + 105, 550, 100);
        this.lblPort.setForeground(Color.white);
        this.contentPane.add(this.lblPort);
        txtPort = new JTextField();
        txtPort.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 60 + 105, 150, 25);
        txtPort.addKeyListener(new KeyListener(){

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    String name = Paid_Login.txtName.getText();
                    int port = Integer.parseInt(Paid_Login.txtPort.getText());
                    String ip = Paid_Login.txtIpAddress.getText();
                    
                    try {
                    	TextTransfer.TextWriter(name+"'s Server", name + "\n" + Paid_Login.txtPort.getText() + "\n" + ip);
        				TextTransfer.writer.println(name);
        				TextTransfer.writer.println(ip);
        				TextTransfer.writer.println(Paid_Login.txtPort.getText());
        				TextTransfer.writer.close();					} catch (IOException e1) {
						System.out.println("Having Trouble creating files in KeyPressed Method");
						e1.printStackTrace();
					}
                    
                    Paid_Login.this.login(name, ip, port);
                }
            }
        });
        this.contentPane.add(txtPort);
        this.login = new JButton("Login");
        this.login.setBounds(this.size.width / 2 - 150 + 12, this.size.height / 2 + 90, 100, 15);
        this.login.addActionListener(this);
        this.login.setForeground(Color.white);
        this.login.setBackground(Color.DARK_GRAY);
        this.login.setBorderPainted(false);
        this.login.setCursor(new Cursor(12));
        this.contentPane.add(this.login);
        this.createServer = new JButton("Create a Server");
        this.createServer.setBounds(this.size.width / 2 - 150 + 120, this.size.height / 2 + 90, 150, 15);
        this.createServer.addActionListener(this);
        this.createServer.setForeground(Color.white);
        this.createServer.setBackground(Color.DARK_GRAY);
        this.createServer.setBorderPainted(false);
        this.createServer.setCursor(new Cursor(12));
        this.contentPane.add(this.createServer);
        this.update = new JButton("Force Update");
        this.update.setBounds(this.size.width / 2 - 150 + 70, this.size.height / 2 + 120, 150, 15);
        this.update.addActionListener(new Updater());
        this.update.setForeground(Color.white);
        this.update.setBackground(Color.DARK_GRAY);
        this.update.setBorderPainted(false);
        this.update.setCursor(new Cursor(12));
        this.contentPane.add(this.update);
        this.update(this.getGraphics());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.login)) {
            String name = txtName.getText();
            int port = Integer.parseInt(txtPort.getText());
            String ip = txtIpAddress.getText();
            
            try {
				TextTransfer.TextWriter(name+"'s Server", name + "\n" + Paid_Login.txtPort.getText() + "\n" + ip);
				TextTransfer.writer.println(name);
				TextTransfer.writer.println(ip);
				TextTransfer.writer.println(Paid_Login.txtPort.getText());
				TextTransfer.writer.close();
				
			} catch (IOException e1) {
				System.out.println("Having Trouble creating files in ActionPreformed Method");
				e1.printStackTrace();
			}
            
            this.login(name, ip, port);
        }
        if (e.getSource().equals(this.createServer)) {
            new com.dcmanproductions.vid_eo.server.ServerWindow();
        }
    }

    private void login(String name, String ip, int port) {
        this.dispose();
        new com.dcmanproductions.vid_eo.ClientWindow(name, ip, port);
    }

}

