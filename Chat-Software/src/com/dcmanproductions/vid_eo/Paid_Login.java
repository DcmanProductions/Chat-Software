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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dcmanproductions.vid_eo.TransferInfo.TextTransfer;
import com.dcmanproductions.vid_eo.Updater.Download;

@SuppressWarnings("all")
public class Paid_Login extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1;
	private String title = "Vid-Eo | Login";
	private String Name;
	private final Dimension size = new Dimension(350, 500);
	private JLabel lblName;
	private JLabel lblIpAddress;
	private JLabel lblPort;
	private JLabel lblServerName;
	private JLabel lblCopyright;
	private JLabel lblError;
	private JButton login;
	private JButton createServer;
	private JButton update;
	private JButton mapper;
	private JButton back;
	private JPanel contentPane;

	public static JTextField txtName;
	public static JTextField txtIpAddress;
	public static JTextField txtPort;
	public static JTextField txtServerName;

	public static JCheckBox cbAdmin;
	public static boolean isAdmin;

	public Paid_Login() {

		System.out.println("Starting Login Application...\nCreating GUI...");

		setTitle(title);
		setSize(size);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(false);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);
		update(getGraphics());
		init();
	}

	private void init() {

		lblName = new JLabel("Please Enter a Username");
		lblName.setBounds(size.width / 2 - 150 + 65, size.height / 2 - 143 - 30, 550, 100);
		lblName.setForeground(Color.white);
		contentPane.add(lblName);
		txtName = new JTextField(Name);

		txtName.setBounds(size.width / 2 - 150 + 65, size.height / 2 - 73 - 30, 150, 25);
		txtName.requestFocus(true);
		txtName.addKeyListener(new KeyListener() {

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

					WriteFile(name, ip, port, serverName);
					Paid_Login.this.login(name, ip, port, serverName);
				}
			}
		});
		lblServerName = new JLabel("Please Enter a Server Name");
		lblServerName.setBounds(size.width / 2 - 150 + 65, size.height / 2 - 230 - 30, 550, 100);
		lblServerName.setForeground(Color.white);
		contentPane.add(lblServerName);

		txtServerName = new JTextField(Name);
		txtServerName.setBounds(size.width / 2 - 150 + 65, size.height / 2 - 160 - 30, 150, 25);
		txtServerName.requestFocus(true);


		txtServerName.addKeyListener(new KeyListener() {

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

					WriteFile(name, ip, port, serverName);

					Paid_Login.this.login(name, ip, port, serverName);
				}
			}
		});

		lblCopyright = new JLabel("A Drew Chase Project!");
		lblCopyright.setBounds(size.width / 2 - 150 + 50, size.height / 2 + 32 + 145, 250, 15);
		lblCopyright.setForeground(Color.gray);
		lblCopyright.setFont(new Font("Verdana", 0, 15));
		contentPane.add(lblCopyright);

		contentPane.add(txtServerName);
		contentPane.add(txtName);
		lblIpAddress = new JLabel("Enter Servers IP Address");
		lblIpAddress.setBounds(size.width / 2 - 150 + 65, size.height / 2 - 63 - 30, 550, 100);
		lblIpAddress.setForeground(Color.white);
		contentPane.add(lblIpAddress);
		txtIpAddress = new JTextField();
		txtIpAddress.setBounds(size.width / 2 - 150 + 65, size.height / 2 + 7 - 30, 150, 25);
		txtIpAddress.addKeyListener(new KeyListener() {

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

					WriteFile(name, ip, port, serverName);

					Paid_Login.this.login(name, ip, port, serverName);
				}
			}
		});

		contentPane.add(txtIpAddress);
		lblPort = new JLabel("Enter Servers Port #");
		lblPort.setBounds(size.width / 2 - 150 + 65, size.height / 2 + 7 - 30, 550, 100);
		lblPort.setForeground(Color.white);
		contentPane.add(lblPort);
		txtPort = new JTextField();
		txtPort.setBounds(size.width / 2 - 150 + 65, size.height / 2 + 77 - 30, 150, 25);
		txtPort.addKeyListener(new KeyListener() {

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

					WriteFile(name, ip, port, serverName);

					Paid_Login.this.login(name, ip, port, serverName);
				}
			}
		});

		contentPane.add(txtPort);
		login = new JButton("Login");
		login.setBounds(size.width / 2 - 150 + 12, size.height / 2 + 122 - 30, 100, 15);
		login.addActionListener(this);
		login.setForeground(Color.white);
		login.setBackground(Color.DARK_GRAY);
		login.setBorderPainted(false);
		login.setCursor(new Cursor(12));
		contentPane.add(login);

		createServer = new JButton("Create a Server");
		createServer.setBounds(size.width / 2 - 150 + 120, size.height / 2 + 90 + 32 - 30, 150, 15);
		createServer.addActionListener(this);
		createServer.setForeground(Color.white);
		createServer.setBackground(Color.DARK_GRAY);
		createServer.setBorderPainted(false);
		createServer.setCursor(new Cursor(12));
		contentPane.add(createServer);

		mapper = new JButton("Make This Public?");
		mapper.setBounds(size.width / 2 - 150 + 12, size.height / 2 + 90 + 60 - 30, 145, 15);
		mapper.addActionListener(this);
		mapper.setForeground(Color.white);
		mapper.setBackground(Color.DARK_GRAY);
		mapper.setBorderPainted(false);
		mapper.setCursor(new Cursor(12));
		contentPane.add(mapper);

		// Action Listener for the PortMapper Button

		mapper.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				startPortMapper();
			}
		});

		// Adds Admin commands
		if (txtIpAddress.getText() == "localhost")
			isAdmin = true;
		else
			isAdmin = false;

		cbAdmin = new JCheckBox("Are You The ADMINISTRATOR");
		cbAdmin.setBounds(size.width / 2 - 150 + 20, size.height / 2 + 32 + 145 - 30, 250, 15);
		cbAdmin.setBackground(Color.DARK_GRAY);
		cbAdmin.setForeground(Color.WHITE);
		cbAdmin.setEnabled(false);
		contentPane.add(cbAdmin);
		if (isAdmin) {
			cbAdmin.setSelected(true);
		} else {
			cbAdmin.setSelected(false);
		}

		// update.addActionListener(new Updater());
		update = new JButton("Force Update");
		update.setBounds(size.width / 2 - 150 + 140, size.height / 2 + 32 + 120 - 30, 150, 15);
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Download();
			}
		});
		update.setForeground(Color.white);
		update.setBackground(Color.DARK_GRAY);
		update.setBorderPainted(false);
		update.setCursor(new Cursor(12));

		contentPane.add(update);
		
		back = new JButton("Back");
		back.setBounds(size.width / 2 - 150, size.height / 2 - 230 - 30, 65, 75);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Paid_Login.this.setVisible(false);
				ServerList sl = new ServerList();
				sl.setVisible(true);
			}
		});
		back.setForeground(Color.white);
		back.setBackground(Color.DARK_GRAY);
		back.setBorderPainted(false);
		back.setCursor(new Cursor(12));

		contentPane.add(back);
		

		txtIpAddress.setText(ServerList.ip);
		txtPort.setText(ServerList.port);
		txtName.setText(ServerList.username);
		txtServerName.setText(ServerList.serverName);

		update(getGraphics());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(login)) {
			String name = txtName.getText();
			int port = Integer.parseInt(txtPort.getText());
			String ip = txtIpAddress.getText();
			String serverName = txtServerName.getText();

			WriteFile(name, ip, port, serverName);

			login(name, ip, port, serverName);
		}
		if (e.getSource().equals(createServer)) {
			new com.dcmanproductions.vid_eo.server.ServerWindow();
		}
	}

	public static void WriteFile(String name, String ip, int port, String serverName) {
		try {
			// Creating Server Info
			TextTransfer.TextWriter("Server_" + serverName + ".txt",
					"Server_Name:" + serverName + "\n" + "ip:" + ip + "\n" + "port:" + port + "\n" + "name:" + name,
					serverName);
			// Creating Last Used
			TextTransfer.TextWriter("Server_LastUsed.txt",
					"Server_Name:" + serverName + "\n" + "ip:" + ip + "\n" + "port:" + port + "\n" + "name:" + name,
					serverName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFile() {
		try {
			TextTransfer.TextReader("//Server_" + txtServerName.getText() + ".txt",
					"C://Program Files//Vid-Eo_ServerFiles", false);
			txtIpAddress.setText(TextTransfer.rdIp);
			txtPort.setText(TextTransfer.rdPort);
			txtName.setText(TextTransfer.rdName);
		} catch (IOException e) {
			System.out.println("Had an issue with The ReadFile Method");
			e.printStackTrace();
		}

	}

	private void startPortMapper() {
		getClass();
		// Protocol protocol = ;
		// InitializePortMapper ipm = new
		// InitializePortMapper(Integer.parseInt(txtPort.getText()),
		// Integer.parseInt(txtPort.getText()), protocol);
		String[] run = { "java", "-jar", "Vid-Eo_Server//PortMapping//portmapper.jar" };
		try {
			Runtime.getRuntime().exec(run);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void login(String name, String ip, int port, String serverName) {
		dispose();
		new com.dcmanproductions.vid_eo.ClientWindow(name, ip, port, serverName);
	}

}
