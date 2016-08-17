package com.dcmanproductions.vid_eo.TransferInfo;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextTransfer {
	public static String FileName, fileContent;
	public static File objFile;
	public static PrintWriter writer;
	public static BufferedReader reader;
	public static String serverName;

	public static String rdIp;
	public static String rdPort;
	public static String rdName;

	/*
	 * public static void main(String[] args){ try { new TextTransfer(FileName,
	 * fileContent); } catch (IOException e) { e.printStackTrace();
	 * System.out.println("Something went wrong in The Text Transfer Class"); }
	 * } public TextTransfer(String FileName, String fileContent) throws
	 * IOException {
	 * 
	 * TextReader(FileName);
	 * 
	 * TextWriter(FileName, fileContent);
	 * 
	 * 
	 * }
	 */

	public static void TextReader(String FileName, String FileLocation) throws IOException {
		System.out.println("Accessing Text Reader Method...");
		System.out.println("Attempting To Read Designated File...");

		FileReader file = new FileReader(FileLocation + FileName);
		System.out.println("Reading File: " + FileName + " in " + FileLocation);
		BufferedReader reader = new BufferedReader(file);

		BufferedInputStream inStream = new BufferedInputStream(System.in);

		System.out.println("Initializing Buffered Reader... \nInitializing Buffered Input Stream...");

		// Scanner scan = new Scanner(inStream);
		// String info = scan.nextLine();

		/*
		 * while (info != null) { System.out.println("Info Print " + info); if
		 * (info.startsWith("ip:")) { System.out.println("Server Ip is:" +
		 * info.length()); } if (info.startsWith("port:")) {
		 * 
		 * } if (info.startsWith("name:")) {
		 * 
		 * } }
		 */
		try {
			String text = "";

			String line = reader.readLine();
			while (line != null) {
				if (reader.toString().startsWith("ip:")) {
					text = TextTransfer.rdIp;
				}
				if (reader.toString().startsWith("port:")) {
					text = TextTransfer.rdPort;
				}
				if (reader.toString().startsWith("name:")) {
					text = TextTransfer.rdName;
				}
				System.out.println(line);
				text += line;
				line = reader.readLine();
			}
		} catch (Exception e) {
			System.out.println(
					"Had A Problem with the while loop in the TextReader Method\n  Couldn't proccess line reader");
			e.printStackTrace();
		}

	}

	public static void TextWriter(String FileName, String fileContent, String FolderName) throws IOException {
		System.out.println("Accessing Text Writer Method...");
		File f = new File("/Vid-Eo_ServerFiles/");
		try {
			if (f.mkdir()) {
				System.out.println("Directory Created in " + f.getAbsolutePath());
			} else {// Exists
				System.out.println("Directory is not created -- Maybe the File already exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		writer = new PrintWriter("C:/Vid-Eo_ServerFiles/" + FileName, "UTF-8");
		writer.println(fileContent);
		writer.close();

	}

}