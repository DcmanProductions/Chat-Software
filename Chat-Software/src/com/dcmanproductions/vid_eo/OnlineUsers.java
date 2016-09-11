/*
 * Decompiled with CFR 0_110.
 */
package com.dcmanproductions.vid_eo;

import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("all")
public class OnlineUsers extends JFrame {
	private static final long serialVersionUID = 1;
	private JPanel contentPane;
	private JList list;

	public OnlineUsers() {
		this.setDefaultCloseOperation(2);
		this.setSize(200, 320);
		this.setTitle("Online Users");
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setContentPane(this.contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[2];
		gbl_contentPane.rowHeights = new int[2];
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		this.contentPane.setLayout(gbl_contentPane);
		this.list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.fill = 1;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		JScrollPane p = new JScrollPane();
		p.setViewportView(this.list);
		this.contentPane.add((Component) p, gbc_list);
		this.list.setFont(new Font("Verdana", 0, 24));
	}

	public void update(String[] users) {
		this.list.setListData(users);
	}
}
