/*
 * Decompiled with CFR 0_110.
 */
package com.dcmanproductions.vid_eo;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("all")
public class CommandsList
extends JFrame {
    private static final long serialVersionUID = 1;
    private JPanel contentPane;
    private JTextArea area;

    public CommandsList() {
        this.setDefaultCloseOperation(2);
        this.setSize(400, 120);
        this.setTitle("Commands");
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.contentPane = new JPanel();
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.area = new JTextArea("--- List of Avalible Commands --- \n /quit -- to Exit the Program \n/users -- to find out what users are online");
        this.area.setBounds(100, 60, 380, 300);
        this.area.setEditable(false);
        this.contentPane.add(this.area);
    }
}

