package _BeaconMethod;

import java.io.IOException;
import java.io.PrintWriter;

import _SQLite.SQLiteView;
import __Communication.ClientMain;

public class SendBeaconThread extends Thread{
	public void run(){
	
		while(ClientMain.runingSendBeacon){
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(ClientMain.socket.getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated method stub
			System.out.println("Send Beacon!!");
			String beaconMessage = "-1,BEACON DEVICE NUM:"+ClientMain.myID_Num ;
			//System.out.println("I am sending beacon message to everyone");
			writer.println(beaconMessage);
			writer.flush();
			try {
				
				Thread.sleep(SQLiteView.viewBeaconTime() * 1000); //milli sec
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				break;
			}
		}
	}	
}
