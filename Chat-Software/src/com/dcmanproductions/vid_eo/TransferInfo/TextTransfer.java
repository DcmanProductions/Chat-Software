package com.dcmanproductions.vid_eo.TransferInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class TextTransfer {
	public static String FileName, fileContent;
	public static File objFile;
	public static PrintWriter writer;
	public static BufferedReader reader;
	
	
    public static void main(String[] args){
        try {
			new TextTransfer(FileName, fileContent);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Something went wrong in The Text Transfer Class");
		}
    }
    public TextTransfer(String FileName, String fileContent) throws IOException {
    	
        TextReader();

        TextWriter(FileName, fileContent);
 
        
    }
    
    public static void TextReader()throws IOException{
        FileReader objFR = null;
		try {
			objFR = new FileReader("MostRecentServer.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        BufferedReader reader=new BufferedReader(objFR);
        
        String strContent = fileContent;
        while((strContent=reader.readLine())!=null)
            System.out.println(reader.readLine());

        reader.close();
    }
    
    public static void TextWriter(String FileName, String fileContent) throws IOException{
    	
    	writer = new PrintWriter("MostRecentServer.txt", "UTF-8");
    	
    }
    
}