package com.dcmanproductions.vid_eo.TransferInfo;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("all")
public class TextTransfer {
	public static String FileName, fileContent;
	public static File objFile;
	public static PrintWriter writer;
	public static BufferedWriter bw;
	public static BufferedReader reader;
	public static String serverName;

	public static String text;

	public static String rdIp;
	public static String rdPort;
	public static String rdName;
	public static String rdServerName;

	public static void TextReader(String FileName, String FileLocation, boolean isNonServerList) throws IOException {
		System.out.println("Accessing Text Reader Method...");
		System.out.println("Attempting To Read Designated File...");

		FileReader file = new FileReader(FileLocation + FileName);
		System.out.println("Reading File: " + FileName + " in " + FileLocation);
		BufferedReader reader = new BufferedReader(file);

		BufferedInputStream inStream = new BufferedInputStream(System.in);

		System.out.println("Initializing Buffered Reader... \nInitializing Buffered Input Stream...");

		if (!isNonServerList) {
			try {
				String text = "";

				String line = reader.readLine();
				while (line != null) {

					if (line.startsWith("ip:")) {
						TextTransfer.rdIp = line.substring(3);
						System.out.println("IP Address is: " + TextTransfer.rdIp);
					}
					if (line.startsWith("name:")) {
						TextTransfer.rdName = line.substring(5);
						System.out.println("Username is: " + TextTransfer.rdName);
					}
					if (line.startsWith("port:")) {
						TextTransfer.rdPort = line.substring(5);
						System.out.println("Port # is: " + TextTransfer.rdPort);
					}
					if (line.startsWith("Server_Name:")) {
						TextTransfer.rdServerName = line.substring(12);
						System.out.println("Server Name is: " + TextTransfer.rdServerName);
					}
					// System.out.println(line);
					text += line;
					line = reader.readLine();

				}
			} catch (Exception e) {
				System.out.println(
						"Had A Problem with the while loop in the TextReader Method\n Couldn't proccess line reader");
				e.printStackTrace();
			}
		} else {
			try {
				TextTransfer.text = "";
				String text = TextTransfer.text;

				String line = reader.readLine();
				while (line != null) {
					System.out.println("Line:" + line + " Text" + text);
					text += line;
					line = reader.readLine();

				}
			} catch (Exception e) {
				System.out.println(
						"Had A Problem with the while loop in the TextReader Method\n Couldn't proccess line reader");
				e.printStackTrace();
			}
		}

	}

	public static void TextWriter(String FileName, String fileContent, String FolderName) throws IOException {
		System.out.println("Accessing Text Writer Method...");
		File f = new File(FolderName);
		try {
			bw = new BufferedWriter(new FileWriter(FolderName + FileName, true));
			bw.write(fileContent);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			System.out.println("Had an issue with Appending file " + FileName + " in TextWriter Meathod.  ERROR: "
					+ e.getMessage());
			e.printStackTrace();
		} finally {
			if (bw != null) {
				bw.close();
			}
		}

		try {
			if (f.mkdir()) {
				System.out.println("Directory Created in " + f.getAbsolutePath());
			} else {// Exists
				System.out.println("Directory is not created -- Maybe the File already exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		writer = new PrintWriter("Server-Files/" + FileName, "UTF-8");
//		writer.println(fileContent);
//		writer.close();

	}

	public static void Append(String fileName) {
		try {
			bw = new BufferedWriter(new FileWriter("//Server-Files//" + fileName, true));
		} catch (IOException e) {
			System.out.println("Had an issue in the Append Meathod.  ERROR: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void serverList(String path, String fileName) {
		Scanner s;
		try {
			s = new Scanner(new File(path+"//"+fileName+".txt"));
			ArrayList<String> list = new ArrayList<String>();
			while (s.hasNext()) {
				list.add(s.next());
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}