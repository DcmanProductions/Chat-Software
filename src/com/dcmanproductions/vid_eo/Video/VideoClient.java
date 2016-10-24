package com.dcmanproductions.vid_eo.Video;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class VideoClient {
	public static void main(String[] agrgs) {
		Socket sock = null;

		try{
			System.out.println("Connecting...");
			sock = new Socket("localhost", 8080);
			InputStream is = new FileInputStream(new File("Bear.wmv"));
			byte[] bytes = new byte[1024];

			OutputStream stream = sock.getOutputStream();

			int count = is.read(bytes, 0, 1024);
			while (count != -1){
				stream.write(bytes, 0, 1024);
				count = is.read(bytes, 0, 1024);
			}

			is.close();
			stream.close();
			sock.close();

		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
