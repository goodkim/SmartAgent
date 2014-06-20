package _FileTransport;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import __Communication.ClientMain;

public class FileTransportMain {

	public static int fileServerPortIndex[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

	public static int getServerSocketPort(){
		for(int i=0; i<10 ; i++ )
		{
			if(fileServerPortIndex[i] == 0)
			{
				fileServerPortIndex[i] = 1;
				return 7000 + ClientMain.myID_Num*10 + i;
			}
		}
		return -1; //-1 means full of available socketPort
	}
}
