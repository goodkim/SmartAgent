package _FileTransport;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import __Communication.ClientMain;

public class FileSenderClientThread extends Thread{

	String contentName, str;
	Socket socket;
	PrintWriter writer;
	int menuFlag;

	DataOutputStream dos;
	FileInputStream fis;
	BufferedInputStream bis;
	static public long fileRemain;

	public FileSenderClientThread(Socket socket, String contentName, int menuFlag)
	{
		this.socket = socket;
		this.contentName = contentName;
		this.menuFlag = menuFlag;
	}

	public void run()
	{
		try {
			writer = new PrintWriter(socket.getOutputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//this thread.sleep function is made to self-simulate, so it must be deleted in using Wi-Fi 
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (menuFlag == 47) //47 means checkTheThroughput_47
		{
			writer.println("test");
			writer.flush();
			System.out.println("Content : " + contentName + " 's transfer has been started");
			File f = new File(contentName);
			System.out.println("file size is "+f.length());
			try {
				fis = new FileInputStream(f);
				bis = new BufferedInputStream(fis);
				int size = 1024*100;
				byte[] data = new byte[size];

				dos.write(data, 0, bis.read(data));

				dos.flush();
				dos.close();
				bis.close();
				fis.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Content : " + contentName + " 's tranfer has been completed && FileSenderThread has been terminated");
		}
		
		else{ //send file
			writer.println("start");
			writer.flush();
			System.out.println("Content : " + contentName + " 's transfer has been started");
			File f = new File(contentName);
			System.out.println("file size is "+f.length());
			try {
				fis = new FileInputStream(f);
				bis = new BufferedInputStream(fis);
				int len;
				int size = 1024;
				byte[] data = new byte[size];
				while ((len = bis.read(data)) != -1) {
					dos.write(data, 0, len);
				}

				dos.flush();
				dos.close();
				bis.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Content : " + contentName + " 's tranfer has been completed && FileSenderThread has been terminated");
		}
		//			}
		//		}
	}


}
