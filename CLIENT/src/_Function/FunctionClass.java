package _Function;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import __Communication.ClientMain;

public class FunctionClass {
	static PrintWriter writer;
	
	public static void sendServiceMessage(String dEPNum, String dEPType, String dTNNum, String dTNType,String service, String note, Socket socket){
		String str=mergeMessage(dEPNum, dEPType, dTNNum, dTNType, service, note);
		sendSAMessage(str, Integer.parseInt(dTNNum), socket);
	}
	
	
	
	static void sendSAMessage(String str, int dTN,Socket socket)
	{
		try {
			writer = new PrintWriter(socket.getOutputStream());
			str = dTN + ",SA#"+str;
			System.out.println(str);
			writer.println(str);
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String mergeMessage(String dEPNum, String dEPType, String dTNNum, String dTNType,String service, String note)
	{
		return dEPNum+"#"+dEPType+"#"+dTNNum+"#"+dTNType+"#"+service+"#"+note;
	}
}
