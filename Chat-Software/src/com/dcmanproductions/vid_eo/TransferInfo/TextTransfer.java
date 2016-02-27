package com.dcmanproductions.vid_eo.TransferInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
	
	
//    public static void main(String[] args){
//        try {
//			new TextTransfer(FileName, fileContent);
//		} catch (IOException e) {
//			e.printStackTrace();
//			System.out.println("Something went wrong in The Text Transfer Class");
//		}
//    }
//    public TextTransfer(String FileName, String fileContent) throws IOException {
//    	
//        TextReader(FileName);
//
//        TextWriter(FileName, fileContent);
// 
//        
//    }
    
    public static void TextReader(String FileName, String FileLocation)throws IOException{
    	
    	FileReader file = new FileReader(FileLocation+FileName);
    	BufferedReader reader = new BufferedReader(file);
    	
    	String text = "";
    	String line = reader.readLine();
    	while(line != null){
    		text += line;
    		line = reader.readLine();
    	}
    	
    	System.out.println(text);
    	
    }
    
    public static void TextWriter(String FileName, String fileContent, String FolderName) throws IOException{
    	writer = new PrintWriter(FileName, "UTF-8");
    	writer.println(fileContent);
    	writer.close();
    	
    }
    
}