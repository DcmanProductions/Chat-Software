/*
 * Decompiled with CFR 0_110.
 */
package com.dcmanproductions.vid_eo.Updater;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class UpdateInfo
extends JFrame {
    private JEditorPane infoPane;
    private JScrollPane scp;
    private JButton ok;
    private JButton cancel;
    private JPanel pan1;
    private JPanel pan2;
    private String updateVersion;

    public UpdateInfo() {
        this.initComponents();
        this.infoPane.setText(this.updateVersion);
    }

    private void initComponents() {
        this.setDefaultCloseOperation(2);
        this.setTitle("New Update Found");
        this.pan1 = new JPanel();
        this.pan1.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setTitle("Update Found");
        this.pan2 = new JPanel();
        this.pan2.setLayout(new FlowLayout());
        this.setAlwaysOnTop(true);
        this.requestFocus();
        this.infoPane = new JEditorPane();
        this.infoPane.setContentType("text/html");
        this.setResizable(false);
        this.updateVersion = "Update Beta 0.3: \n    In this update we just fixed some bugs and worked \n the VideoChat which still does not\nwork";
        this.scp = new JScrollPane();
        this.scp.setViewportView(this.infoPane);
        this.ok = new JButton("Update");
        this.ok.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateInfo.update();
            }
        });
        this.cancel = new JButton("Cancel");
        this.cancel.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateInfo.this.dispose();
            }
        });
        this.pan2.add(this.ok);
        this.pan2.add(this.cancel);
        this.pan1.add((Component)this.pan2, "South");
        this.pan1.add((Component)this.scp, "Center");
        this.add(this.pan1);
        this.pack();
        this.show();
        this.setSize(300, 200);
    }

    public static void update() {
        String[] run = new String[]{"java", "-jar", "update.jar"};
        try {
            Runtime.getRuntime().exec(run);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

}

