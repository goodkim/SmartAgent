package _SQLite;

import etc.PrintMainMenu;


public class _SQLiteMain {

	public static void menuSQLite(String str){
		String dPDB_str;

		switch(str.split(",")[0]){

		
		case "init" :
			SQLiteInitialize.initializeSQLiteDB();
			//InitializeXMLFile.initializeXMLfile();
			break;
		case "vRole" :
			System.out.println("Agent type of this is : " + SQLiteView.viewRoleString());
			break;
		case "vDPDB" :
			dPDB_str=SQLiteView.viewDevicePropertyDB();
			System.out.println(dPDB_str);
			break;
		case "fAll" :
			SQLiteExample.fillDevicePropertyDB();
			SQLiteExample.fillCDB("example.mp4");
			SQLiteExample.fillBrokerAgentDB();
			SQLiteExample.fillRPAgentDB();
			break;
		case "fDPDB" : //fillDevicePropertyDB
			SQLiteExample.fillDevicePropertyDB();
			//MakeExampleXMLFile.makeXMLfile();
			break;
		
		case "fBADB" : //fillBrokerAgentDB
			SQLiteExample.fillBrokerAgentDB();
			//MakeExampleXMLFile.makeXMLfile();
			break;
		case "fRPADB" : //fillRPAgentDB
			SQLiteExample.fillRPAgentDB();
			//MakeExampleXMLFile.makeXMLfile();
			break;
			
			//14.03.18 add
		case "fRPDB_myself" : //fillRPAgentDB
			SQLiteInsert.insertRPDB();
			//MakeExampleXMLFile.makeXMLfile();
			break;
			
			
		case "makeR" :
			//BrokerAgentExample.makeExample();
			break;
		case "makeB" :
			//ProviderAgentExample.makeExample();
			break;
		case "makeP" :
			//RequesterAgentExample.makeExample();
			break;
		default :
			PrintMainMenu.printMainMenu();
			
			break;
		}
	}

	
}