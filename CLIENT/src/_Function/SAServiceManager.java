package _Function;

import java.io.PrintWriter;

import _SQLite.SQLiteInsert;
import __Communication.ClientMain;
import etc.GetDate;

public class SAServiceManager {

	public static void sAServiceManager(String str)
	{
		PrintWriter writerPrintRequester = new PrintWriter(ClientMain.senderToRequesterOut);
		PrintWriter writerPrintBroker = new PrintWriter(ClientMain.senderToBrokerOut);
		PrintWriter writerPrintProvider = new PrintWriter(ClientMain.senderToProviderOut);

		System.out.println("sAServiceManager is working well");

		if((str.split("#")[4].matches(".*R.*")&&ClientMain.runingRequester==true)||(str.split("#")[4].matches(".*B.*")&&ClientMain.runingBroker==true)||(str.split("#")[4].matches(".*P.*")&&ClientMain.runingProvider==true))
		{
			switch(str.split("#")[5]){

			case "requestToSearch_1":
				System.out.println("SAServiceManager_requestToSearch_1 receives :"+ str);  //print SA message
				writerPrintBroker.println(str);
				writerPrintBroker.flush();				
				break;

			case "broadcastToSearch_2":
				System.out.println("SAServiceManager_broadcastToSearch_2 receives :"+ str);  //print SA message
				writerPrintProvider.println(str);
				writerPrintProvider.flush();
				break;



			case "applyForBroker_45": // it only be able to receive from broker after Requester or Provider send intention of joining to broker.
				System.out.println("SAServiceManager_applyForBroekr_45 receives :"+ str);  //print SA message
				if(str.split("#")[6].matches("accept.*")) //broker => provider so it's printed in provider side
				{
					System.out.println("application accepted !");
					//register str in BDB. whenever it can change
					SQLiteInsert.insertBDB(GetDate.getDate(), 1, str.split("OwnMACaddr=")[1].split("@")[0], "stable", 1, Integer.parseInt(str.split("Beacon_Time=")[1].split("@")[0]));
				}
				else if(str.split("#")[6].matches("reject.*")) //broker => provider so it's printed in provider side
					System.out.println("applyForBroker_45 is rejected by agent " +  str.split("#")[1]);
				else //provider => broker so it's printed in broker side
				{
					System.out.println("property of device who applies for me is : " + str.split("#")[6]);
					System.out.println("do you accept "+ str.split("#")[1] + "'s " + str.split("#")[2] + "?  If you want, then type "+ ClientMain.myID_Num + ",B@applyForBroker_45#"+str.split("#")[1]+"#"+str.split("#")[2]+"#accept or not then #denial");
					ClientMain.tempReceivedAgentInfor=str;
				}
				break;

			}
		}

		else
		{
			System.out.println("There isn't any properly runing agent!!!!");
		}
	}
}
