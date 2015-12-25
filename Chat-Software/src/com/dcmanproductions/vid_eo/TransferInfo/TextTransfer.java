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
    
    public void TextReader()throws IOException{
        FileReader objFR = null;
		try {
			objFR = new FileReader(objFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        BufferedReader objBR=new BufferedReader(objFR);
        
        String strContent = fileContent;
        while((strContent=objBR.readLine())!=null)
            System.out.println(objBR.readLine());

        objBR.close();
    }
    
    public static void TextWriter(String FileName, String fileContent) throws IOException{
    	
    	writer = new PrintWriter(FileName+".txt", "UTF-8");
    	
    }
    
}