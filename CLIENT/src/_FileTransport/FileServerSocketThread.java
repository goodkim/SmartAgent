package _FileTransport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import __Communication.ClientMain;

public class FileServerSocketThread extends Thread{
	int socketNum;
	String contentName;

	//constructor
	public FileServerSocketThread(int socketNum, String contentName)
	{
		this.socketNum = socketNum;
		this.contentName = contentName;
	}

	public void run(){
		try {
			/*if(socketNum!=-1)
			{*/
			while(true){
				ServerSocket serverSocket = new ServerSocket(socketNum); //first, each device can open a socket!!
				//serverSocket.setReuseAddress(true);
				System.out.println("file server socket has been opened !!!!! socket port NUM : "+ socketNum);
				Socket incoming = serverSocket.accept();
				Thread fileReceiverThread = new FileReceiverServerThread(incoming,contentName,socketNum);
				fileReceiverThread.start();
				System.out.println("file server socket Thread closed !!!!! socket port NUM : "+ socketNum);
				/*}
			else
				System.out.println("There doesn't remain any available socketport!");*/
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
