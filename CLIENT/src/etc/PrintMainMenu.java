package etc;

import __Communication.ClientMain;

public class PrintMainMenu {
	
	public static void printMainMenu()
	{
		System.out.println("Agnet menu : startR, startB, startP, startAll, runR, runB, runP, runAll, ignoreBeacon@INT (INT>0 ? add : printAllIgnoreList), notifyBeacon@INT, R@message, B@message, P@message ");
		System.out.println("DB menu : init, vRole, vDPDB, fDPDB_INTofAGNETTYPE, fAll, fDPDB, fCDB, fBADB, fRPADB, makeR, makeB, makeP");
		System.out.println("agent status: RequesterAgent: "+ ClientMain.runingRequester + ", BrokerAgent: " + ClientMain.runingBroker+", ProviderAgent: " + ClientMain.runingProvider + ", BeaconThread: " + ClientMain.runingSendBeacon);
	}
	public static void printRequesterAgentMenu()
	{
		System.out.println("printRequesterAgentMenu : requestToSearch_1#ContentName, applyForBroker_45#INT"); //DST is acronym of destination
	}
	public static void printBrokerAgentrMenu()
	{
		System.out.println("BrokerAgentMenu");
	}
	public static void printProviderAgentMenu()
	{
		System.out.println("printProviderAgentMenu : applyForBroker_45#INT"); //DST is acronym of destination
	}
}
