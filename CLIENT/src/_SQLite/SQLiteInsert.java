package _SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import __Communication.ClientMain;
import etc.GetDate;

public class SQLiteInsert {
	//don't need to make function for insertDevicePropertyDB

	public static void insertDPDB( String UpdateTime, int AgentType, String UserName, String OwnMACaddr, String Status, String DeviceName, String Mobility, int BatteryResidual, float Location_Altitude, float Location_Latitude, float Location_Longitude, float BeaconTime ){
		//attribute print
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			c.setAutoCommit(false);
			System.out.println("Opened DevicePropertyDB of "+ClientMain.myID_Num + "successfully");
			stmt = c.createStatement();
			String sql = "INSERT INTO DevicePropertyDB (UpdateTime, AgentType, UserName, OwnMACaddr, Status, DeviceName, Mobility, BatteryResidual, Location_Altitude, Location_Latitude, Location_Longitude, BeaconTime) " +
					"VALUES ('"+UpdateTime+"','"+AgentType+"','"+UserName +"','"+OwnMACaddr+"','"+Status+"','"+DeviceName+"','"+Mobility+"','"+BatteryResidual+"','"+Location_Altitude+"','"+Location_Latitude+"','"+Location_Longitude+"','"+BeaconTime+"');";
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

	public static void insertBDB(String UpdateTime,int Priority,String BMACaddr,String Mobility,int Trust,float BeaconTime ){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			c.setAutoCommit(false);
			System.out.println("Opened insertBDB of "+ClientMain.myID_Num + " successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO BrokerAgentDB (UpdateTime, Priority , BMACaddr , Mobility , Trust , BeaconTime) " +
					"VALUES ('"+UpdateTime+"','" +Priority + "','"+BMACaddr+"','"+Mobility+"','"+Trust+"','"+BeaconTime+"');";
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records insertBDB of "+ClientMain.myID_Num + "successfully");
	}

	public static void insertRPDB(String UpdateTime, int AgentType, String MACaddr, String Status, String Mobility, int BatteryResidual, int Trust, float Acktime){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			c.setAutoCommit(false);
			System.out.println("Opened insertRPDB of "+ClientMain.myID_Num + "successfully");
			stmt = c.createStatement();

			String sql = "INSERT INTO RPAgentDB (UpdateTime, AgentType, MACaddr, Status, Mobility, BatteryResidual, Trust, Acktime ) " +
					"VALUES ('"+UpdateTime+"','" + AgentType +"','" + MACaddr+"','" + Status+"','" + Mobility+"','" + BatteryResidual+"','" + Trust
					+"','" + Acktime+"');" ;
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records insertRPDB of "+ClientMain.myID_Num + "successfully");
	}

	public static void insertOwnDB()//insert mine agent information into RPDB & BDB 
	{
		String str = SQLiteView.viewDevicePropertyDB();
		SQLiteInsert.insertRPDB(GetDate.getDate(), Integer.parseInt(str.split("AgentType=")[1].split("@")[0]), str.split("OwnMACaddr=")[1].split("@")[0],str.split("Status=")[1].split("@")[0], str.split("Mobility=")[1].split("@")[0]
				, Integer.parseInt(str.split("BatteryResidual=")[1].split("@")[0]), 1, 1);
		SQLiteInsert.insertBDB(GetDate.getDate(), 1,str.split("OwnMACaddr=")[1].split("@")[0], "local host", 1, 0);

	}

	public static void insertCDB( String UpdateTime, String ContentName, int Price, int Runtime, String ContentQuality, int FileSize, String RMACaddr, String BMACaddr, String PMACaddr, int ETA, String Status){
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			c.setAutoCommit(false);
			System.out.println("Opened insertCDB of "+ClientMain.myID_Num + "successfully");
			stmt = c.createStatement();
			String sql = "INSERT INTO ContentDB (UpdateTime, ContentName, Price, Runtime, ContentQuality, FileSize, RMACaddr, BMACaddr, PMACaddr, ETA, Status) " +
					"VALUES ('"+UpdateTime+"','"+ContentName+"','"+Price +"','"+Runtime+"','"+ContentQuality+"','"+FileSize+"','"+RMACaddr+"','"+BMACaddr+"','"+PMACaddr+"','"+ETA+"','"+Status+"');" ;
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
		} catch ( Exception e ) {
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
		System.out.println("Records insertCDB of "+ClientMain.myID_Num + "successfully");
	}


}
