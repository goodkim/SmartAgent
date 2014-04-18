package _SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import etc.GetDate;

import __Communication.ClientMain;

public class SQLiteExample {
	
	
	public static void fillDevicePropertyDB(){
		//attribute print
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			c.setAutoCommit(false);
			System.out.println("Opened DevicePropertyDB of "+ClientMain.myID_Num + "successfully");
			stmt = c.createStatement();
			String sql = "INSERT INTO DevicePropertyDB (UpdateTime, AgentType, UserName, OwnMACaddr, Status, DeviceName, Mobility, BatteryResidual, Location_Altitude, Location_Latitude, Location_Longitude, Beacon_Time) " +
					"VALUES ('2013-12-23 11:20:30',111,'dusan Baek','"+ Integer.toString(ClientMain.myID_Num) +"-00-00-00-00-00','stable','dusan''s','stop',-77,0,0,0,10000 );";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records DevicePropertyDB of "+ClientMain.myID_Num + "successfully");
	}
	
	public static void fillDevicePropertyDB1(String str){
		//attribute print
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			c.setAutoCommit(false);
			System.out.println("Opened DevicePropertyDB of "+ClientMain.myID_Num + "successfully");
			stmt = c.createStatement();
			String sql = "INSERT INTO DevicePropertyDB (UpdateTime, AgentType, UserName, OwnMACaddr, Status, DeviceName, Mobility, BatteryResidual, Location_Altitude, Location_Latitude, Location_Longitude, Beacon_Time) " +
					"VALUES ('2013-12-23 11:20:30',"+str+",'"+Integer.toString(ClientMain.myID_Num)+"-device','" + Integer.toString(ClientMain.myID_Num) + "-11-11-11-11-11','stable','dukki''s','stop',-77,0,0,0,10000 );";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records DevicePropertyDB of "+ClientMain.myID_Num + "successfully");
	}

	/*
	public static void fillContentDB(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			c.setAutoCommit(false);
			System.out.println("Opened fillContentDB of "+ClientMain.myID_Num + "successfully");
			stmt = c.createStatement();
			String sql = "INSERT INTO ContentDB (UpdateTime, ContentName, Price, Runtime, ContentQuality, FileSize, RMACaddr, BMACaddr, PMACaddr, ETA, Status) " +
					"VALUES ('2013-12-23 11:20:30','Gone With the Wind','9000','4800','DivX','1024','NULL','NULL','localhost','0','having'  );";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records fillContentDB of "+ClientMain.myID_Num + "successfully");
	}
	*/
	
	public static void fillCDB(String str){
		SQLiteInsert.insertCDB(GetDate.getDate(), str, 9000, 4800, "DivX", 1024, "NULL", "NULL", "localhost", 0, "having");
	}

	public static void fillBrokerAgentDB(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			c.setAutoCommit(false);
			System.out.println("Opened fillBrokerAgentDB of "+ClientMain.myID_Num + "successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO BrokerAgentDB (UpdateTime, Priority , BMACaddr , Mobility , Trust , BeaconTime) " +
					"VALUES ('2013-12-24 10:20:30','1','localhost','localhost','1','0');" +
					"INSERT INTO BrokerAgentDB (UpdateTime, Priority , BMACaddr , Mobility , Trust , BeaconTime) " +
					"VALUES ('2013-12-24 10:40:12','2','10-BC-50-A2-B4-06','fast','2','1');" +
					"INSERT INTO BrokerAgentDB (UpdateTime, Priority , BMACaddr , Mobility , Trust , BeaconTime) " +
					"VALUES ('2013-12-24 10:42:12','3','10-B2-20-42-24-06','fast','3','0.4');"
					;
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records fillContentDB of "+ClientMain.myID_Num + "successfully");
	}

	public static void fillRPAgentDB(){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			c.setAutoCommit(false);
			System.out.println("Opened fillRPAgentDB of "+ClientMain.myID_Num + "successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO RPAgentDB (UpdateTime, AgentType, MACaddr, Status, Mobility, BatteryResidual, Trust, Acktime ) " +
					"VALUES ('2013-12-24 10:20:30','111','30-62-20-5D-26-E1','receiving','stable','-77','3','3.5');" ;
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records fillContentDB of "+ClientMain.myID_Num + "successfully");
	}
}
