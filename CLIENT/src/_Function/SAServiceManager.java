package _Function;

import java.io.PrintWriter;

import __Communication.ClientMain;

public class SAServiceManager {

	public static void sAServiceManager(String str)
	{
		PrintWriter writerPrintRequester = new PrintWriter(ClientMain.senderToRequesterOut);
		PrintWriter writerPrintBroker = new PrintWriter(ClientMain.senderToBrokerOut);
		PrintWriter writerPrintProvider = new PrintWriter(ClientMain.senderToProviderOut);

		System.out.println("sAServiceManager recevie : " + str);

		if((str.split("#")[4].matches(".*R.*")&&ClientMain.runingRequester==true)||(str.split("#")[4].matches(".*B.*")&&ClientMain.runingBroker==true)||(str.split("#")[4].matches(".*P.*")&&ClientMain.runingProvider==true))
		{
			if(str.split("#")[5].matches("requestToSearch_1"))
			{
				System.out.println("SAServiceManager requestToSearch_1 receives :"+ str);  //print SA message
				writerPrintBroker.println(str);
				writerPrintBroker.flush();	
			}

			else if(str.split("#")[5].matches("broadcastToSearch_2"))
			{
				System.out.println("SAServiceManager broadcastToSearch_2 receives :"+ str);  //print SA message
				writerPrintProvider.println(str);
				writerPrintProvider.flush();
			}

			else if(str.split("#")[5].matches("applyForBroker_45"))
			{
				System.out.println("SAServiceManager applyForBroekr_45 receives :"+ str);  //print SA message
				writerPrintBroker.println(str);
				writerPrintBroker.flush();
			}

			else if(str.split("#")[5].matches("ackApplication_54"))
			{
				System.out.println("SAServiceManager ackApplication_54 receives :"+ str);  //print SA message
				//AckApplication message is transferred to One of the requester or provider agent
				if(ClientMain.runingRequester)
				{
					writerPrintRequester.println(str);
					writerPrintRequester.flush();
				}
				else if(ClientMain.runingProvider)
				{
					writerPrintProvider.println(str);
					writerPrintProvider.flush();
				}
				else
					System.out.println("The device doesn't have proper agent");
			}
			
			else if(str.split("#")[5].matches("informExistenceOfContent_5"))
			{
				System.out.println("SAServiceManager informExistenceOfContent_5 receives :"+ str);  //print SA message
				writerPrintBroker.println(str);
				writerPrintBroker.flush();
			}
			
			else if(str.split("#")[5].matches("checkTheThroughput_47"))
			{
				writerPrintProvider.println(str);
				writerPrintProvider.flush();
			}
			
			
		}

		else
		{
			System.out.println("There isn't any properly runing agent!!!!");
		}
	}
}
