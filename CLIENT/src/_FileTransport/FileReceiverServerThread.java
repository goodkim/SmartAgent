package _FileTransport;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import __Communication.ClientMain;

public class FileReceiverServerThread extends Thread {

	String contentName,str;
	int socketNum;

	DataInputStream dis;
	FileOutputStream fos;
	BufferedOutputStream bos;
	Socket socket;
	
	FileReceiverServerThread(Socket socket,String contentName,int socketNum){
		this.socket = socket;
		this.contentName = contentName;
		this.socketNum = socketNum;
		System.out.println(ClientMain.myID_Num + "'s FileReceiverThread has been opened!");		
	}
	
	
	public void run(){
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			dis = new DataInputStream(socket.getInputStream());
			while (true){ //Have to add additional code to exit
				str = reader.readLine();
				if(str == null){
					System.out.println("\"System terminate\"");
					break;
				}
				
				else if(str.matches("test"))  //47 means checkTheThroughput_47
				{
					contentName = "test" + contentName;  //debugging
					
					System.out.println("Content : " + contentName + " 's transfer has been started");
					System.out.println(contentName + "file has been made");

					int len;
					int size = 1024*100;
					byte[] data = new byte[size];
					long start = System.nanoTime();
					System.out.println("data size : "+ data.length +"bytes");
					dis.read(data);
					
					long end = System.nanoTime(); 
					System.out.println("run time : " + (end-start) + "nanosec");
					System.out.println("Throughput : " + ((double)data.length)*8*1000000000/1024/1024/(end-start) + "Mbps");
					
					dis.close();
					System.out.println("Content : " + contentName + " 's tranfer has been completed");
					break;
				}
				
				else if(str.matches("start")) //receive file
				{
					contentName = "test" + contentName;  //debugging
					
					System.out.println("Content : " + contentName + " 's transfer has been started");
					File f = new File(contentName);
					fos = new FileOutputStream(f);
					bos = new BufferedOutputStream(fos);
					System.out.println(contentName + "file has been made");

					int len;
					int size = 1024;
					byte[] data = new byte[size];
					long start = System.nanoTime();
					while(true){
						if((len = dis.read(data)) == -1)
							break;
						bos.write(data,0,len);
					}
					long end = System.nanoTime(); 
					System.out.println("run time : " + (end-start) + "nanosec");
					System.out.println("Throughput : " + ((double)f.length())*8*1000000000/1024/1024/(end-start) + "Mbps");
					bos.flush();
					bos.close();
					fos.close();
					dis.close();
					System.out.println("Content : " + contentName + " 's tranfer has been completed");
					System.out.println("Recevied Content file size : " + f.length());
				}
				
				else
					System.out.println("File Receiver Thread recevied : "+str);

			}
			FileTransportMain.fileServerPortIndex[socketNum%10]=0;
			socket.close();
			System.out.println("socket release : "+ socket.isClosed());
			System.out.println("Content : " + contentName + " 's tranfer has been completed  && FileReceiverThread has been terminated");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
