package _AgentThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import _BeaconMethod.SendBeaconThread;
import _Function.FunctionClass;
import _SQLite.SQLiteInsert;
import _SQLite.SQLiteView;
import __Communication.ClientMain;
import etc.GetAgentType;
import etc.GetDate;
import etc.PrintMainMenu;

public class BrokerAgentThread extends Thread{
	Socket socket;
	PrintWriter writer;
	public BrokerAgentThread(Socket socket){
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
		BufferedReader reader = new BufferedReader ( new InputStreamReader(ClientMain.senderToBrokerIn));
		PrintWriter writerPrintRequester = new PrintWriter(ClientMain.senderToRequesterOut);
		PrintWriter writerPrintProvider = new PrintWriter(ClientMain.senderToProviderOut);

		System.out.println("Broker Thread runs well!!!!!");

		while(true){
			try {
				str=reader.readLine();


				if(!ClientMain.runingBroker)
					System.out.println("This device doesn't run BrokerAgent");

				//manage broker service
				else
				{
					System.out.println("Broker agent Thread: " +str );

					if(str.matches("menu"))
						PrintMainMenu.printBrokerAgentrMenu();

					else if(str.matches(".*requestToSearch_1.*"))
					{
						arrayAll = SQLiteView.viewAllProviderAgentInRPDB();
						for(String provider : arrayAll)
						{
							FunctionClass.sendServiceMessage(Integer.toString(ClientMain.myID_Num), "B", provider.split("@MACaddr=")[1].split("-")[0], "P", "broadcastToSearch_2",str.split("#")[1]+"@"+ str.split("#")[6], socket);
						}
					}

					else if(str.matches(".*applyForBroker_45.*")) //0,B@  applyForBroker_45#1#P#accept/reject
					{
						System.out.println("property of device who applies for me is : " + str.split("#")[6]);
						System.out.println("do you accept "+ str.split("#")[1] + "'s " + str.split("#")[2] + "?  If you want, then type "+ ClientMain.myID_Num + ",B@ackApplication_54#"+str.split("#")[1]+"#"+str.split("#")[2]+"#accept or not then #denial");
						ClientMain.tempReceivedAgentInfor=str;
					}

					else if(str.matches(".*ackApplication_54.*"))
					{
						if(ClientMain.tempReceivedAgentInfor.split("#")[5].matches("applyForBroker_45")&&ClientMain.tempReceivedAgentInfor.split("#")[1].matches(str.split("#")[1])&&ClientMain.tempReceivedAgentInfor.split("#")[2].matches(str.split("#")[2]))
						{
							FunctionClass.sendServiceMessage(Integer.toString(ClientMain.myID_Num), "B", str.split("#")[1], str.split("#")[2] , "ackApplication_54",str.split("#")[3]+"@"+SQLiteView.viewDevicePropertyDB(), socket);
							//Register ClientMain.tempAgentInfor in RPDB SA#DEP#DEP_AGENT#DTN#DTN_AGENT#SERVICE_FIELD#ID=1@UpdateTime=2013-12-23 11:20:30@AgentType=101@UserName=dukki@OwnMACaddr=11-11-11-11-11-11@Status=stable@DeviceName=dukki's@Mobility=stop@BatteryResidual=-77@Location_Altitude=0.0@Location_Latitude=0.0@Location_Longitude=0.0@BeaconTime=1
							SQLiteInsert.insertRPDB(GetDate.getDate(), GetAgentType.stringToInt(ClientMain.tempReceivedAgentInfor.split("#")[2]), ClientMain.tempReceivedAgentInfor.split("OwnMACaddr=")[1].split("@")[0],ClientMain.tempReceivedAgentInfor.split("Status=")[1].split("@")[0], ClientMain.tempReceivedAgentInfor.split("Mobility=")[1].split("@")[0]
									, Integer.parseInt(ClientMain.tempReceivedAgentInfor.split("BatteryResidual=")[1].split("@")[0]), 1, 1);

							System.out.println("TempAgentInfor str is  :"+ClientMain.tempReceivedAgentInfor);
						}
						else
							System.out.println("Check your command!! differ from last received message");
					}
					
					else if(str.matches(".*informExistenceOfContent_5.*"))
					{
						FunctionClass.sendServiceMessage(Integer.toString(ClientMain.myID_Num), "B", str.split("#")[6].split("@")[0], "R", "informExistenceOfContent_5_2", str.split("#")[1]+"@"+str.split("@")[1], socket);
					}

					//add in this field !!!

					else
						System.out.printf("The command has not been developed" + str);
				}



				//manage beacon
				if(str.matches("startBeacon"))  //startBeacon
				{
					if(ClientMain.runingSendBeacon)
						System.out.println("Beacon already has been working");
					else if(SQLiteView.viewBeaconTime()>0)
					{
						ClientMain.runingSendBeacon=true;
						Thread sendBeaconThread = new SendBeaconThread();
						sendBeaconThread.start();
					}
					else 
					{
						System.out.println("Broker Agent needs to set up threadTime");
						System.out.println("Broker Thread closes well!!!!!");
					}
				}
				else if(str.matches("stopBeacon"))  //stopBeacon
				{
					if(ClientMain.runingSendBeacon)
						ClientMain.runingSendBeacon=false;
					else
						System.out.println("Beacon doesn't work yet");
				}
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
