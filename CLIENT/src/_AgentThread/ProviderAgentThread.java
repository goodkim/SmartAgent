package _AgentThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import _Function.FunctionClass;
import _SQLite.SQLiteView;
import __Communication.ClientMain;
import etc.PrintMainMenu;

public class ProviderAgentThread extends Thread{
	Socket socket;
	PrintWriter writer;
	public ProviderAgentThread(Socket socket){
		this.socket = socket;
		try {
			writer = new PrintWriter(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run(){
		String str;
		ArrayList<String> arrayAll = new ArrayList<>();
		BufferedReader reader = new BufferedReader ( new InputStreamReader(ClientMain.senderToProviderIn));
		PrintWriter writerPrintRequester = new PrintWriter(ClientMain.senderToRequesterOut);
		PrintWriter writerPrintBroker = new PrintWriter(ClientMain.senderToBrokerOut);
		
		System.out.println("Provider Thread run well!!!!!");

		while(true){
			try {

				str=reader.readLine();
				System.out.println("Provider agent Thread: " +str );
				
				if(str.matches("menu"))
					PrintMainMenu.printProviderAgentMenu();
				
				else if(str.matches(".*broadcastToSearch_2.*"))
				{
					arrayAll= SQLiteView.viewAllContentInCDB();
					for(String content : arrayAll)
					{
				//		if(str.split("@")[1]== )
					}
				}
				
				else if(str.matches("applyForBroker_45.*"))
				{
					//in case of applyForBroker_45 the broker agent type should be removed 
					FunctionClass.sendServiceMessage(Integer.toString(ClientMain.myID_Num), SQLiteView.viewRoleString().replace("B", ""), str.split("#")[1], "B", "applyForBroker_45", SQLiteView.viewDevicePropertyDB(), socket);
				}
				
				/*switch(str.split(",")[1]){
				case "menu":
					PrintMainMenu.printProviderAgentMenu();
					break;
				default :
					if(str.split("#")[0].matches("applyForBroker_45"))
						Function.applyForBroker_45(Integer.toString(ClientMain.myID_Num), "P", str.split("#")[1], "B", "applyForBroker_45", "", socket);
				}*/
				
				/*
				writer.println(str);
				writer.flush();*/

				else if(str.matches("break"))
					break;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}
		ClientMain.runingProvider = false;
	}
}
