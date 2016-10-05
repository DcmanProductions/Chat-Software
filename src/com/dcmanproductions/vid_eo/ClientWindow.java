package com.dcmanproductions.vid_eo;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

@SuppressWarnings("all")
public class ClientWindow extends JFrame implements Runnable {
	private static final long serialVersionUID = 1;
	private JPanel contentPane;
	private JTextField txtMessage;
	public JTextArea history;
	private DefaultCaret caret;
	private Thread run;
	private Thread listen;
	private Client client;
	public String messageGLOBAL;
	private boolean running = false;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOnlineUsers;
	private JMenuItem mntmExit;
	private JMenuItem mntmCommands;
	private JMenuItem mntmBack;
	private OnlineUsers users;
	private boolean isSending = true; 

	public ClientWindow(String name, String address, int port, String serverName) {
		this.setTitle("Vid-Eo | " + serverName + "'s Server");
		this.client = new Client(name, address, port);
		boolean connect = this.client.openConnection(address);
		if (!connect) {
			System.err.println("Connection failed!");
			this.console("Connection failed!");
		}
		isSending = true;
		this.createWindow();
		this.console("Attempting a connection to " + address + ":" + port + ", user: " + name);
		String connection = "/c/" + name + "/e/";
		this.client.send(connection.getBytes());
		this.users = new OnlineUsers();
		this.running = true;
		this.run = new Thread((Runnable) this, "Running");
		this.run.start();
	}

	private void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		this.setDefaultCloseOperation(3);
		this.setSize(880, 550);
		this.setLocationRelativeTo(null);
		this.menuBar = new JMenuBar();
		this.setJMenuBar(this.menuBar);
		this.mnFile = new JMenu("File");
		this.menuBar.add(this.mnFile);
		this.mntmOnlineUsers = new JMenuItem("Online Users");
		this.mntmOnlineUsers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ClientWindow.this.users.setVisible(true);
			}
		});
		this.mnFile.add(this.mntmOnlineUsers);
		this.mntmCommands = new JMenuItem("Commands");
		this.mntmCommands.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CommandsList cmdList = new CommandsList();
				cmdList.setVisible(true);
			}
		});
		this.mnFile.add(this.mntmCommands);

		this.mntmBack = new JMenuItem("Back to Login");
		this.mntmBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// Login backLogin = new Login();
				new com.dcmanproductions.vid_eo.Login();
				// backLogin.setVisible(true);
				ClientWindow.this.setVisible(false);
			}
		});
		// this.mnFile.add(this.mntmBack);

		this.mntmExit = new JMenuItem("Exit");
		this.mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ClientWindow.this.send(ClientWindow.this.client.getName() + " Has Left The Chat", true);
				System.exit(0);
			}
		});
		this.mnFile.add(this.mntmExit);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 28, 815, 30, 7 };
		gbl_contentPane.rowHeights = new int[] { 25, 485, 40 };
		this.contentPane.setLayout(gbl_contentPane);
		this.history = new JTextArea();
		this.history.setEditable(false);
		JScrollPane scroll = new JScrollPane(this.history);
		this.caret = (DefaultCaret) this.history.getCaret();
		this.caret.setUpdatePolicy(2);
		GridBagConstraints scrollConstraints = new GridBagConstraints();
		scrollConstraints.insets = new Insets(0, 0, 5, 5);
		scrollConstraints.fill = 1;
		scrollConstraints.gridx = 0;
		scrollConstraints.gridy = 0;
		scrollConstraints.gridwidth = 3;
		scrollConstraints.gridheight = 2;
		scrollConstraints.weightx = 1.0;
		scrollConstraints.weighty = 1.0;
		scrollConstraints.insets = new Insets(0, 5, 0, 0);
		this.contentPane.add((Component) scroll, scrollConstraints);
		this.txtMessage = new JTextField();
		this.txtMessage.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					ClientWindow.this.send(ClientWindow.this.txtMessage.getText(), true);
				}
			}
		});
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtMessage.fill = 2;
		gbc_txtMessage.gridx = 0;
		gbc_txtMessage.gridy = 2;
		gbc_txtMessage.gridwidth = 2;
		gbc_txtMessage.weightx = 1.0;
		gbc_txtMessage.weighty = 0.0;
		this.contentPane.add((Component) this.txtMessage, gbc_txtMessage);
		this.txtMessage.setColumns(10);
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (ClientWindow.this.messageGLOBAL.contains("/")) {
					ClientWindow.this.send(ClientWindow.this.txtMessage.getText(), true);
				} else {
					ClientWindow.this.send(ClientWindow.this.txtMessage.getText(), false);
				}
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 0, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;
		gbc_btnSend.weightx = 0.0;
		gbc_btnSend.weighty = 0.0;
		this.contentPane.add((Component) btnSend, gbc_btnSend);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				String disconnect = "/d/" + ClientWindow.this.client.getID() + "/e/";
				ClientWindow.this.send(disconnect, false);
				ClientWindow.access$4(ClientWindow.this, false);
				ClientWindow.this.client.close();
			}
		});
		this.setVisible(true);
		this.txtMessage.requestFocusInWindow();
	}

	@Override
	public void run() {
		this.listen();
	}

	private void send(String message, boolean text) {
		isSending = true;
		if (isSending) {
			if (message.startsWith("/")) {
				isSending = false;
			}
			if (message.equals("")) {
				return;
			}

			if (text) {
				message = String.valueOf(this.client.getName()) + ": " + message;
				message = "/m/" + message + "/e/";
				this.txtMessage.setText("");
			}
			

			this.client.send(message.getBytes());

			if (!isSending)
				runCommands(message);

		} else {
			return;
		}
	}

	public void listen() {
		this.listen = new Thread("Listen") {

			@Override
			public void run() {
				while (ClientWindow.this.running) {
					String text;
					String message = ClientWindow.this.client.receive();
					if (message.startsWith("/c/")) {
						ClientWindow.this.client.setID(Integer.parseInt(message.split("/c/|/e/")[1]));
						ClientWindow.this
								.console("Successfully connected to server! ID: " + ClientWindow.this.client.getID());
					} else if (message.startsWith("/m/")) {
						text = message.substring(3);
						text = text.split("/e/")[0];
						ClientWindow.this.console(text);
					} else if (message.startsWith("/i/")) {
						text = "/i/" + ClientWindow.this.client.getID() + "/e/";
						ClientWindow.this.send(text, false);
					} else if (message.startsWith("/u/")) {
						String[] u = message.split("/u/|/n/|/e/");
						ClientWindow.this.users.update(Arrays.copyOfRange(u, 1, u.length - 1));
					}

					ClientWindow.this.messageGLOBAL = message;

				}
			}
		};
		this.listen.start();
	}

	public void console(String message) {
		this.history.append(String.valueOf(message) + "\n\r");
		this.history.setCaretPosition(this.history.getDocument().getLength());
	}

	public void runCommands(String cmds) {
		if (cmds.contains("h") || cmds.contains("help") || cmds.contains("?"))
			ClientWindow.this
					.console("----Heres a List of Commands---- " + "\n /quit -- to Quit the server and return to Login "
							+ "\n/users -- to find out what users are online");

		if (cmds.contains("users") || cmds.contains("list") || cmds.contains("clients"))
			ClientWindow.this.users.setVisible(true);

		if (cmds.contains("stop") || cmds.contains("quit") || cmds.contains("exit"))
			System.exit(0);
	}

	static /* synthetic */ void access$4(ClientWindow clientWindow, boolean bl) {
		clientWindow.running = bl;
	}

}
