package com.dcmanproductions.vid_eo;

import java.awt.Color;
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

@SuppressWarnings("all")
public class ServerList extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private static Dimension size = new Dimension(677, 249);
	private static String title = "Vid-Eo Server Initialization";
	private static JPanel contentPane;
	private static JButton create, startServer, useLast;

	private static JTextArea info;
	private static JTextField name;
	private static JLabel error;
	public static String ip, port, username, serverName;

	public ServerList() {
		System.out.println("Server List Class Initialized...\nBuilding Screen...");

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(size);
		this.setTitle(title);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		setResizable(false);
		System.out.println("Screen Built...\nBuilding GUI...");
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		contentPane.setBackground(Color.DARK_GRAY);

		info = new JTextArea();
		info.setEditable(false);
		info.setFont(new Font("Arial", 0, 12));
		info.setText(
				"If you haven't already created or joined a server then click 'Create Your Own'.\nIf you have created or connected to a server before, type the server name in the text box below.\nIf you have connected to a server and you want to connect to the last server click 'Connect With Last Used Server!'   \nOther wise... WHY ARE YOU USEING THIS PROGRAM!?!?!\n");
		contentPane.add(info);

		name = new JTextField(50);
		// name.setBounds(this.size.width / 2 - 150 + 65, this.size.height / 2 +
		// 77 - 30, 150, 25);
		name.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {
					try {
						TextTransfer tt = new TextTransfer();
						tt.TextReader("Server_"+name.getText()+".txt", "C://Vid-Eo_Server Files//", false);
						
						serverName = tt.rdServerName;
						port = tt.rdPort;
						username = tt.rdName;
						ip = tt.rdIp;
						
						ServerList.this.setVisible(false);
						new com.dcmanproductions.vid_eo.Paid_Login();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(name);
		error = new JLabel();
		error.setForeground(Color.RED);
		contentPane.add(error);

		this.create = new JButton("Open Login Window!");
		this.create.setBackground(Color.DARK_GRAY);
		this.create.setForeground(Color.WHITE);
		create.setBounds(this.size.width / 2 - 10 + 65, this.size.height / 2 - 700, 150, 50);
		this.contentPane.add(this.create);
		// Adding Functions to The Create Button
		System.out.println("Creating the 'Create Your Own' Button Functions...");
		create.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				TextTransfer tt = new TextTransfer();
				try {
					tt.TextReader("Server_"+name.getText()+".txt", "C://Vid-Eo_Server Files//", false);
					
					serverName = tt.rdServerName;
					port = tt.rdPort;
					username = tt.rdName;
					ip = tt.rdIp;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ServerList.this.setVisible(false);
				new com.dcmanproductions.vid_eo.Paid_Login();
			}

		});

		this.useLast = new JButton("Connect With Last Used Server!");
		this.useLast.setBackground(Color.DARK_GRAY);
		this.useLast.setForeground(Color.WHITE);
		useLast.setBounds(this.size.width / 2 - 10 + 65, this.size.height / 2 - 700, 150, 50);
		useLast.addActionListener(this);
		this.contentPane.add(this.useLast);
		// Adding Functions to The Create Button
		System.out.println("Creating the 'Last Used Server' Button Functions...");
		create.addActionListener(this);

		this.update(getGraphics());
		setSize(680, 250);

	}

	public static void main(String[] args) {

		new com.dcmanproductions.vid_eo.ServerList();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(useLast)) {

			TextTransfer tt = new TextTransfer();
			try {
				tt.TextReader("Server_LastUsed.txt", "C://Vid-Eo_Server Files//", false);

				serverName = tt.rdServerName;
				port = tt.rdPort;
				username = tt.rdName;
				ip = tt.rdIp;

				System.out.println("Found File Server_LastUsed");
				ServerList.this.setVisible(false);
				new com.dcmanproductions.vid_eo.Paid_Login();
			} catch (IOException er) {
				System.out.println("Had Issues Reading the 'Last Used' Server File");
				error.setText("Sorry Had Issues Reading the 'Last Used' Server File");
				er.printStackTrace();
			}

		}
	}

}
