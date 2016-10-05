package com.dcmanproductions.vid_eo;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dcmanproductions.vid_eo.TransferInfo.TextTransfer;
import com.dcmanproductions.vid_eo.Updater.Download;
import com.dcmanproductions.vid_eo.Updater.UpdateInfo;
import com.dcmanproductions.vid_eo.Updater.Updater;

@SuppressWarnings("all")
public class ServerList extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static Dimension size = new Dimension(677, 249);
	private static String title = "Vid-Eo Server Initialization";
	private static JPanel contentPane;
	private static JButton create;
	private static JButton startServer;
	private static JButton useLast;
	private static JTextArea info;
	private static JTextField name;
	private static JLabel error;
	public static String ip;
	public static String port;
	public static String username;
	public static String serverName;

	public ServerList() {
		System.out.println("Server List Class Initialized...\nBuilding Screen...");

		setDefaultCloseOperation(3);
		setSize(size);
		setTitle(title);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		System.out.println("Screen Built...\nBuilding GUI...");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.DARK_GRAY);

		info = new JTextArea();
		info.setEditable(false);
		info.setFont(new Font("Arial", 0, 12));
		info.setText("---If you haven't already created or joined a server then click 'Open Login Window'."
				+ "\n---If you have created or connected to a server before, type the server name in the text box below,\n then press enter or click 'Open Login Window'."
				+ "\n---If you have connected to a server and you want to connect to the last server click 'Connect With Last Used Server!'   "
				+ "\nOther wise... WHY ARE YOU USEING THIS PROGRAM!?!?!\n");
		contentPane.add(info);

		name = new JTextField(50);

		name.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}

			public void keyReleased(KeyEvent e) {
			}

			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					try {
						TextTransfer tt = new TextTransfer();
						TextTransfer.TextReader("Server_" + ServerList.name.getText() + ".txt",
								"C:Server-Files/", false);

						ServerList.serverName = TextTransfer.rdServerName;
						ServerList.port = TextTransfer.rdPort;
						ServerList.username = TextTransfer.rdName;
						ServerList.ip = TextTransfer.rdIp;

						ServerList.this.setVisible(false);
						new Login();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(name);
		error = new JLabel();
		error.setForeground(Color.RED);
		contentPane.add(error);

		System.out.println("Creating the 'Open Login Window' Button Functions...");
		create = new JButton("Open Login Window!");
		create.setBackground(Color.DARK_GRAY);
		create.setForeground(Color.WHITE);
		create.setBounds(size.width / 2 - 10 + 65, size.height / 2 - 700, 150, 50);
		create.setBorderPainted(false);
		create.setCursor(new Cursor(12));
		create.addActionListener(this);
		create.addActionListener(new ActionListener()
	    {
	      public void actionPerformed(ActionEvent arg0)
	      {
	        TextTransfer tt = new TextTransfer();
	        try
	        {
	          TextTransfer.TextReader("Server_" + ServerList.name.getText() + ".txt", "C:Server-Files/", false);
	          
	          ServerList.serverName = TextTransfer.rdServerName;
	          ServerList.port = TextTransfer.rdPort;
	          ServerList.username = TextTransfer.rdName;
	          ServerList.ip = TextTransfer.rdIp;
	        }
	        catch (IOException e)
	        {
	        System.out.println("One of Two things has happened"+"\n(1)You haven't entered anything into the 'Server Name' TextField"+"\n(2)An Issue has ");
	        }
	        ServerList.this.setVisible(false);
	        new Login();
	      }
	    });
		contentPane.add(create);

		useLast = new JButton("Connect With Last Used Server!");
		useLast.setBackground(Color.DARK_GRAY);
		useLast.setForeground(Color.WHITE);
		useLast.setBounds(size.width / 2 - 10 + 65, size.height / 2 - 700, 150, 50);
		useLast.addActionListener(this);
		useLast.setBorderPainted(false);
		useLast.setCursor(new Cursor(12));
		contentPane.add(useLast);

		System.out.println("Creating the 'Last Used Server' Button Functions...");
		create.addActionListener(this);

		update(getGraphics());
		setSize(680, 250);
	}

	public static void main(String[] args) {
		try {
//			TextTransfer.TextWriter("currentVersion.txt", "1", "Server-Files/");
			TextTransfer.TextReader("currentVersion.txt/", "Server-Files/", true);
			if(TextTransfer.text == "1"){
				System.out.println("Current Version is 1");
			}
//            if (Integer.parseInt(Updater.getLatestVersion()) > Integer.parseInt(TextTransfer.text)) {
//                new UpdateInfo(Updater.getWhatsNew());
//            
        } catch (Exception ex) {
        	System.out.println("Had Issues with the Version Verification. ERROR: "+ex.getMessage());
        }
		new ServerList();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(useLast)) {
			TextTransfer tt = new TextTransfer();
			try {
				TextTransfer.TextReader("Server_LastUsed.txt", "Server-Files/", false);

				serverName = TextTransfer.rdServerName;
				port = TextTransfer.rdPort;
				username = TextTransfer.rdName;
				ip = TextTransfer.rdIp;

				System.out.println("Found File Server_LastUsed");
				setVisible(false);
				new Login();
			} catch (IOException er) {
				System.out.println("Had Issues Reading the 'Last Used' Server File");
				error.setText("Sorry Had Issues Reading the 'Last Used' Server File");
				er.printStackTrace();
			}
		}
	}
}
