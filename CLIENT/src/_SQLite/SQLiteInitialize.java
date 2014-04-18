package _SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import __Communication.ClientMain;

public class SQLiteInitialize {

	public static void initializeSQLiteDB()
	{
		makeDPDB();
		makeCDB();
		makeBDB();
		makeRPDB();
	}
	
//DevicePropertyDB
	private static void makeDPDB() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "CREATE TABLE DevicePropertyDB " +
					"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
					" UpdateTime TEXT NOT NULL, " +
					" AgentType INT NOT NULL, " +
					" UserName TEXT NOT NULL, " +
					" OwnMACaddr TEXT NOT NULL, " +
					" Status TEXT NOT NULL, " +
					" DeviceName TEXT NOT NULL, " +
					" Mobility TEXT NOT NULL, " +
					" BatteryResidual INT NOT NULL, " +
					" Location_Altitude REAL NOT NULL, " +
					" Location_Latitude REAL NOT NULL, " +
					" Location_Longitude REAL NOT NULL, " + //"YYYY-MM-DD HH:MM:SS.SSS"으로 날짜순, AgentType RBP 순으로 예를들어 111이면 모든 기능 on, 
					" Beacon_Time REAL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			//System.exit(0);
		}
		System.out.println("Table created successfully");
	}

//ContentDB
	private static void makeCDB() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "CREATE TABLE ContentDB " +
					"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
					" UpdateTime TEXT NOT NULL, " +
					" ContentName TEXT NOT NULL, " +					
					" Price INT NOT NULL, " +
					" Runtime INT NOT NULL, " +
					" ContentQuality TEXT NOT NULL, " +
					" FileSize INT NOT NULL, " +
					" RMACaddr TEXT NOT NULL, " +
					" BMACaddr TEXT NOT NULL, " +
					" PMACaddr TEXT NOT NULL, " +
					" ETA INT NOT NULL, " +
					" Status TEXT NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			//System.exit(0);
		}
		System.out.println("Table created successfully");
	}

//BrokerAgentDB
	private static void makeBDB() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "CREATE TABLE BrokerAgentDB " +
					"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
					" UpdateTime TEXT NOT NULL, " +
					" Priority INT NOT NULL, " +
					" BMACaddr TEXT NOT NULL, " +
					" Mobility TEXT NOT NULL, " +
					" Trust INT NOT NULL, " +
					" BeaconTime REAL NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			//System.exit(0);
		}
		System.out.println("Table created successfully");
	}

//RPAgentDB
	private static void makeRPDB() {
		// TODO Auto-generated method stub
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			String sql = "CREATE TABLE RPAgentDB " +
					"(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
					" UpdateTime TEXT NOT NULL, " +
					" AgentType INT NOT NULL, " +
					" MACaddr TEXT NOT NULL, " +
					" Status TEXT NOT NULL, " +
					" Mobility TEXT NOT NULL, " +
					" BatteryResidual INT NOT NULL, " +
					" Trust INT NOT NULL, " +
					" Acktime REAL NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			//System.exit(0);
		}
		System.out.println("Table created successfully");
	}



}
