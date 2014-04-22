package _AgentThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import _Function.FunctionClass;
import _SQLite.SQLiteExample;
import _SQLite.SQLiteInsert;
import _SQLite.SQLiteView;
import __Communication.ClientMain;
import etc.GetDate;
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

				else if(str.matches(".*ackApplication_54.*"))
				{
					if(str.split("#")[6].matches("accept.*")) //broker => provider so it's printed in provider side
					{
						System.out.println("application is accepted !");
						//register str in BDB. whenever it can change
						SQLiteInsert.insertBDB(GetDate.getDate(), 1, str.split("OwnMACaddr=")[1].split("@")[0], "stable", 1, Integer.parseInt(str.split("BeaconTime=")[1].split("@")[0]));
					}
					else if(str.split("#")[6].matches("reject.*")) //broker => provider so it's printed in provider side
						System.out.println("applyForBroker_45 is rejected by agent " +  str.split("#")[1]);
				}

				else if(str.matches("applyForMyBroker_45_2"))
				{
					SQLiteInsert.insertOwnDB();
				}

				else if(str.matches("addContent_25_3.*"))
				{
					if(str.split("#").length == 2)
						SQLiteExample.fillCDB(str.split("#")[1]);
					else
						System.out.println("length is : " + str.split("#").length + " So, it donen't work!");
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
