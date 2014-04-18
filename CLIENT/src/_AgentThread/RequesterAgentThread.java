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

public class RequesterAgentThread extends Thread{
	Socket socket;
	PrintWriter writer;
	public RequesterAgentThread(Socket socket){
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
		BufferedReader reader = new BufferedReader ( new InputStreamReader(ClientMain.senderToRequesterIn));
		PrintWriter writerPrintBroker = new PrintWriter(ClientMain.senderToBrokerOut);
		PrintWriter writerPrintProvider = new PrintWriter(ClientMain.senderToProviderOut);

		System.out.println("Requester Thread run well!!!!!");
		
		while(true){
			try {
				
				str=reader.readLine();
				System.out.println("Requester agent Thread: " +str );
				
				if(str.matches("menu"))
					PrintMainMenu.printRequesterAgentMenu();
				
				else if(str.matches("applyForBroker_45.*"))
				{
					FunctionClass.sendServiceMessage(Integer.toString(ClientMain.myID_Num), SQLiteView.viewRoleString(), str.split("#")[1], "B", "applyForBroker_45", SQLiteView.viewDevicePropertyDB(), socket);
				}
				
				else if(str.matches("requestToSearch_1.*")) //03.14.14 implement
				{
					System.out.println("Testing");
					arrayAll=SQLiteView.viewAllBrokerAgentInBDB();
					for(String broker : arrayAll)
					{
						FunctionClass.sendServiceMessage(Integer.toString(ClientMain.myID_Num), "R", broker.split("@BMACaddr=")[1].split("-")[0], "B", "requestToSearch_1", str.split("#")[1], socket);
					}					
				}
				
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
