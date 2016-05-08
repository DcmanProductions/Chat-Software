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
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dcmanproductions.vid_eo.TransferInfo.TextTransfer;
import com.dcmanproductions.vid_eo.Updater.Download;

public class Paid_Login extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1;
    private String title = "Vid-Eo | Login";
    private String Name;
    private final Dimension size = new Dimension(316, 450);
    private JLabel lblName;
    private JLabel lblIpAddress;
    private JLabel lblPort;
    private JLabel lblServerName;
    public static JTextField txtName;
    public static JTextField txtIpAddress;
    public static JTextField txtPort;
    public static JTextField txtServerName;
    private JButton login;
    private JButton createServer;
    private JButton update;
    private JPanel contentPane;
    public static JCheckBox cbAdmin;
    public static boolean isAdmin;

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
        this.lblName.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 280 + 105 + 32, 550, 100);
        this.lblName.setForeground(Color.white);
        this.contentPane.add(this.lblName);
		txtName = new JTextField(this.Name);
		
        txtName.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 210 + 105 + 32, 150, 25);
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
                    String serverName = Paid_Login.txtServerName.getText();
                    
//                    try {
                    	WriteFile(name, ip, port, serverName);
/*                    	TextTransfer.TextWriter(serverName+"'s Server", name + "\n" + Paid_Login.txtPort.getText() + "\n" + ip,ser);
        				TextTransfer.writer.println(serverName+",");
        				TextTransfer.writer.println(name+",");
        				TextTransfer.writer.println(ip+",");
        				TextTransfer.writer.println(Paid_Login.txtPort.getText());
        				TextTransfer.writer.close();
   				} catch (IOException e1) {
						System.out.println("Having Trouble creating files in KeyPressed Method");
						e1.printStackTrace();
					}
*/                    
                    Paid_Login.this.login(name, ip, port, serverName);
                }
            }
        });
        this.lblServerName = new JLabel("Please Enter a Server Name");
        this.lblServerName.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 280 + 50, 550, 100);
        this.lblServerName.setForeground(Color.white);
        this.contentPane.add(this.lblServerName);
        txtServerName = new JTextField(this.Name);
        txtServerName.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 210 + 50, 150, 25);
        txtServerName.requestFocus(true);
        if(txtServerName.requestFocus(false)){
        	readFile();
        }
        
        
        
        txtServerName.addKeyListener(new KeyListener(){

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
                    String serverName = Paid_Login.txtServerName.getText();
                    
                	WriteFile(name,ip,port,serverName);
                	
                    
                    Paid_Login.this.login(name, ip, port, serverName);
                }
            }
        });
        this.contentPane.add(txtServerName);
        this.contentPane.add(txtName);
        this.lblIpAddress = new JLabel("Enter Servers IP Address");
        this.lblIpAddress.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 200 + 105 + 32, 550, 100);
        this.lblIpAddress.setForeground(Color.white);
        this.contentPane.add(this.lblIpAddress);
        txtIpAddress = new JTextField();
        txtIpAddress.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 130 + 105 + 32, 150, 25);
        this.contentPane.add(txtIpAddress);
        this.lblPort = new JLabel("Enter Servers Port #");
        this.lblPort.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 130 + 105 + 32, 550, 100);
        this.lblPort.setForeground(Color.white);
        this.contentPane.add(this.lblPort);
        txtPort = new JTextField();
        txtPort.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 - 60 + 105 + 32, 150, 25);
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
                    String serverName = Paid_Login.txtServerName.getText();
                    
    				WriteFile(name,ip,port,serverName);
                    
                    Paid_Login.this.login(name, ip, port, serverName);
                }
            }
        });
        
        this.contentPane.add(txtPort);
        this.login = new JButton("Login");
        this.login.setBounds(this.size.width / 2 - 150 + 12, this.size.height / 2 + 90 + 32, 100, 15);
        this.login.addActionListener(this);
        this.login.setForeground(Color.white);
        this.login.setBackground(Color.DARK_GRAY);
        this.login.setBorderPainted(false);
        this.login.setCursor(new Cursor(12));
        this.contentPane.add(this.login);
        this.createServer = new JButton("Create a Server");
        this.createServer.setBounds(this.size.width / 2 - 150 + 120, this.size.height / 2 + 90 + 32, 150, 15);
        this.createServer.addActionListener(this);
        this.createServer.setForeground(Color.white);
        this.createServer.setBackground(Color.DARK_GRAY);
        this.createServer.setBorderPainted(false);
        this.createServer.setCursor(new Cursor(12));
        this.contentPane.add(this.createServer);
        this.update = new JButton("Force Update");
        this.update.setBounds(this.size.width / 2 - 150 + 140, this.size.height / 2 + 32 + 120, 150, 15);
        
      //Adds Admin commands
        if(this.txtIpAddress.getText() == "localhost")
        	this.isAdmin = true;
        else
        	this.isAdmin = false;
        
        cbAdmin = new JCheckBox("Are You The ADMINISTRATOR");
        cbAdmin.setBounds(this.size.width / 2 - 150 + 20, this.size.height / 2 + 32 + 145, 250, 15);
        cbAdmin.setBackground(Color.DARK_GRAY);
        cbAdmin.setForeground(Color.WHITE);
        cbAdmin.setEnabled(false);
        this.contentPane.add(cbAdmin);
        if(isAdmin){
        	cbAdmin.setSelected(true);
        }else{
        	cbAdmin.setSelected(false);
        }
        
//        this.update.addActionListener(new Updater());
        this.update.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Download();
			}});
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
            String serverName = txtServerName.getText();
            
				WriteFile(name,ip,port,serverName);
            
            this.login(name, ip, port, serverName);
        }
        if (e.getSource().equals(this.createServer)) {
            new com.dcmanproductions.vid_eo.server.ServerWindow();
        }
    }
    
    public static void WriteFile(String name, String ip, int port, String serverName){
    	try {
    		TextTransfer.TextWriter("Server_"+serverName+".txt", "ip:"+ip+"\n"+"port:"+port+"\n"+"name"+name, serverName);
    		
//    		new File("/"+serverName+"/").createNewFile();
//			TextTransfer.TextWriter(serverName+"'s Server"+" server name.txt", serverName,serverName);
//			TextTransfer.TextWriter(serverName+"'s Server"+" name.txt", name,serverName);
//			TextTransfer.TextWriter(serverName+"'s Server"+" ip.txt", ip,serverName);
//			TextTransfer.TextWriter(serverName+"'s Server"+" port.txt", Paid_Login.txtPort.getText(),serverName);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void readFile(){
    	try {
			TextTransfer.TextReader(this.txtServerName.getText() +"'s Server information.txt", this.txtServerName.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    } 


    private void login(String name, String ip, int port, String serverName) {
        this.dispose();
        new com.dcmanproductions.vid_eo.ClientWindow(name, ip, port, serverName);
    }

}

