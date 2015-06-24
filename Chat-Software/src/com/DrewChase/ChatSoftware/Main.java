package com.DrewChase.ChatSoftware;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main extends JFrame{
	
	public static int width, height;
	public static boolean show;
	public static String title;
	
	public Main(){
		setSize(new Dimension(width, height));
		setVisible(show);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public static void main(String args[]){
		Main m = new Main();
	}

}
