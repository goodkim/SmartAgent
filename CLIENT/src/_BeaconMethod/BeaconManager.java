package _BeaconMethod;

import java.util.ArrayList;
import java.util.List;

import __Communication.ClientMain;
import __Communication.ReceiverThread;

public class BeaconManager {
	
	
	
	static public void receivedBeaconManager(String str){
		
		if(ClientMain.runingRequester==true || ClientMain.runingProvider==true)
		{
			if(Integer.parseInt(str.split("BEACON DEVICE NUM:")[1]) == ClientMain.myID_Num); //Don't print own beacon
			else if (ReceiverThread.ignoreBrokerList.contains(Integer.parseInt(str.split("BEACON DEVICE NUM:")[1]))); //Don't print beacon ignoring agent
			else
			{
				ReceiverThread.beaconCount++;
				if(ReceiverThread.beaconCount == 10 )
				{
					ReceiverThread.beaconCount=0;
					System.out.println(str);
					System.out.println("Now beacon is printed once during "+10 +"times. If don't want to see beacon, input ignoreBeacon@number !!");
				}
				//send beacon into pipe

			}
		}
	}
	
	static public void ignoreBeaconManager(int ignoreAgentNum){
		if( ignoreAgentNum > -1 )
			ReceiverThread.ignoreBrokerList.add(ignoreAgentNum);
		else
		{
			if(ReceiverThread.ignoreBrokerList.isEmpty())
			{
				System.out.println("List of ignoreBrokerAgent is EMPTY ");
			}
			else
			{
				for(int i : ReceiverThread.ignoreBrokerList)
					System.out.println("ignoreBrokerAgent : "+ i);				
			}
		}
	}
	
	static public void notifyBeaconManager(int notifyAgnetNum){
		ReceiverThread.ignoreBrokerList.remove(ReceiverThread.ignoreBrokerList.indexOf(notifyAgnetNum));
	}
}
