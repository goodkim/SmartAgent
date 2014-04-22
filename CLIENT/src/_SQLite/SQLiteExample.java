package _SQLite;

import __Communication.ClientMain;
import etc.GetDate;

public class SQLiteExample {


	public static void fillDPDB(){
		SQLiteInsert.insertDPDB("2013-12-23 11:20:30", 111, Integer.toString(ClientMain.myID_Num)+"-device", Integer.toString(ClientMain.myID_Num) +"-00-00-00-00-00", "stable", "dusan''s", "stop", -77, 0, 0, 0, 10000);		
	}

	public static void fillDPDBByAgentType(String str){
		SQLiteInsert.insertDPDB("2013-12-23 11:20:30", Integer.parseInt(str), "dusan''s", Integer.toString(ClientMain.myID_Num) +"-00-00-00-00-00", "stable", Integer.toString(ClientMain.myID_Num)+"-device", "stop", -77, 0, 0, 0, 10000);
	}

	public static void fillBDB(){
		SQLiteInsert.insertBDB("2013-12-24 10:20:30", 1, "localhost", "localhost", 1, 0);
		SQLiteInsert.insertBDB("2013-12-24 10:20:30", 2, "10-BC-50-A2-B4-06", "fast", 2, 1);
	}

	public static void fillRPDB(){
		SQLiteInsert.insertRPDB("2013-12-24 10:20:30", 111, "30-62-20-5D-26-E1", "receiving", "stable", -77, 3, (float)3.5);
	}

	public static void fillCDB(String str){
		SQLiteInsert.insertCDB(GetDate.getDate(), str, 9000, 4800, "DivX", 1024, "NULL", "NULL", ClientMain.myID_Num + "-localhost", 0, "having");
	}

}
