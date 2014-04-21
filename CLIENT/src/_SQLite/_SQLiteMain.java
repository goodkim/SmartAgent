package _SQLite;

import java.util.ArrayList;
import java.util.List;

import etc.PrintMainMenu;


public class _SQLiteMain {

	public static void menuSQLite(String str){

		if(str.matches("initDB"))
			SQLiteInitialization.initializeSQLiteDB();

		else if(str.matches("viewRole"))
			System.out.println("Agent type of this is : " + SQLiteView.viewRoleString());

		else if(str.matches("viewDPDB"))
			System.out.println(SQLiteView.viewDevicePropertyDB());

		else if(str.matches("fillAll"))
		{
			SQLiteExample.fillDPDB();
			SQLiteExample.fillCDB("example.mp4");
			SQLiteExample.fillBDB();
			SQLiteExample.fillRPDB();			
		}

		else if(str.matches("fillDPDB"))
			SQLiteExample.fillDPDB();

		else if(str.matches("fillBDB"))
			SQLiteExample.fillBDB();

		else if(str.matches("fillRPDB"))
			SQLiteExample.fillRPDB();

		else if(str.matches("fillOwnRPDB"))
			SQLiteInsert.insertOwnRPDB();
		
		else if(str.matches("fDPDB@.*"))
			SQLiteExample.fillDPDBByAgentType(str.split("@")[1]);
		
		else if(str.matches("fCDB@.*"))
			SQLiteExample.fillCDB(str.split("@")[1]);

		else
			PrintMainMenu.printMainMenu();

	}
}


