package _SQLite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import __Communication.ClientMain;

public class SQLiteView {

	public static String viewDevicePropertyDB() {
		// TODO Auto-generated method stub
		String str = null;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM DevicePropertyDB;");
			while ( rs.next() ) {
				int ID = rs.getInt("ID");
				String UpdateTime = rs.getString("UpdateTime");
				int AgentType  = rs.getInt("AgentType");
				String UserName = rs.getString("UserName");
				String OwnMACaddr = rs.getString("OwnMACaddr");
				String Status = rs.getString("Status");
				String DeviceName = rs.getString("DeviceName");
				String Mobility = rs.getString("Mobility");
				int BatteryResidual = rs.getInt("BatteryResidual");
				float Location_Altitude = rs.getFloat("Location_Altitude");
				float Location_Latitude = rs.getFloat("Location_Latitude");
				float Location_Longitude = rs.getFloat("Location_Longitude");
				int Beacon_Time = rs.getInt("Beacon_Time");

				str =  "ID=" + ID + "@UpdateTime=" + UpdateTime + "@AgentType=" + AgentType + "@UserName=" + UserName + "@OwnMACaddr=" + OwnMACaddr
						+ "@Status=" + Status + "@DeviceName=" + DeviceName + "@Mobility=" + Mobility + "@BatteryResidual=" + BatteryResidual
						+ "@Location_Altitude=" + Location_Altitude + "@Location_Latitude=" + Location_Latitude + "@Location_Longitude=" + Location_Longitude
						+ "@Beacon_Time=" + Beacon_Time ;

			}
			rs.close();
			stmt.close();
			c.close();
			System.out.println("Closed database successfully");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	public static ArrayList<String> viewAllBrokerAgentInBDB() {
		// TODO Auto-generated method stub
		String str = null;
		ArrayList <String> arrayAll = new ArrayList<> ();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM BrokerAgentDB;");
			while ( rs.next() ) {
				int ID = rs.getInt("ID");
				String UpdateTime = rs.getString("UpdateTime");
				int Priority = rs.getInt("Priority");
				String BMACaddr = rs.getString("BMACaddr");
				String Mobility = rs.getString("Mobility");
				int Trust = rs.getInt("Trust");
				int Beacon_Time = rs.getInt("BeaconTime");

				str =  "ID=" + ID + "@UpdateTime=" + UpdateTime + "@Priority=" + Priority + "@BMACaddr=" + BMACaddr + "@Mobility=" + Mobility+ "@Trust=" + Trust+ "@Beacon_Time=" + Beacon_Time;
				arrayAll.add(str);
			}
			rs.close();
			stmt.close();
			c.close();
			System.out.println("Closed database successfully");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayAll;
	}
	
	public static ArrayList<String> viewAllProviderAgentInRPDB() {
		String str = null;
		ArrayList <String> arrayAll = new ArrayList<> ();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM RPAgentDB where AgentType % 10 > 0;");
			while ( rs.next() ){
				int ID = rs.getInt("ID");
				String UpdateTime = rs.getString("UpdateTime");
				int AgentType = rs.getInt("AgentType");
				String MACaddr = rs.getString("MACaddr");
				String Status = rs.getString("Status");
				String Mobility = rs.getString("Mobility");
				int BatteryResidual = rs.getInt("BatteryResidual");
				int Trust = rs.getInt("Trust");
				float Acktime = rs.getFloat("Acktime");
				
				str =  "ID=" + ID + "@UpdateTime=" + UpdateTime + "@AgentType=" + AgentType + "@MACaddr=" + MACaddr + "@Status=" + Status+ "@Mobility=" + Mobility+ "@BatteryResidual=" + BatteryResidual+ "@Trust=" + Trust+ "@Acktime=" + Acktime;
				arrayAll.add(str);
			}
			rs.close();
			stmt.close();
			c.close();
			System.out.println("Closed database successfully");
			
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayAll;
	}
	
	
	public static ArrayList<String> viewAllRequesterAgentInRPDB() {
		String str = null;
		ArrayList <String> arrayAll = new ArrayList<> ();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM RPAgentDB where AgentType > 99;");
			while ( rs.next() ){
				int ID = rs.getInt("ID");
				String UpdateTime = rs.getString("UpdateTime");
				int AgentType = rs.getInt("AgentType");
				String MACaddr = rs.getString("MACaddr");
				String Status = rs.getString("Status");
				String Mobility = rs.getString("Mobility");
				int BatteryResidual = rs.getInt("BatteryResidual");
				int Trust = rs.getInt("Trust");
				float Acktime = rs.getFloat("Acktime");
				
				str =  "ID=" + ID + "@UpdateTime=" + UpdateTime + "@AgentType=" + AgentType + "@MACaddr=" + MACaddr + "@Status=" + Status+ "@Mobility=" + Mobility+ "@BatteryResidual=" + BatteryResidual+ "@Trust=" + Trust+ "@Acktime=" + Acktime;
				arrayAll.add(str);
			}
			rs.close();
			stmt.close();
			c.close();
			System.out.println("Closed database successfully");
			
		}catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayAll;
	}
	
	public static ArrayList<String> viewAllContentInCDB() {
		// TODO Auto-generated method stub
		String str = null;
		ArrayList <String> arrayAll = new ArrayList<> ();
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			System.out.println("Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ContentDB;");
			while ( rs.next() ) {
				int ID = rs.getInt("ID");
				String UpdateTime = rs.getString("UpdateTime");
				String ContentName = rs.getString("ContentName");
				int Price = rs.getInt("Price");
				int Runtime = rs.getInt("Runtime");
				String ContentQuality = rs.getString("ContentQuality");
				int FileSize = rs.getInt("FileSize");
				String RMACaddr = rs.getString("RMACaddr");
				String BMACaddr = rs.getString("BMACaddr");
				String PMACaddr = rs.getString("PMACaddr");
				int ETA = rs.getInt("ETA");
				String Status = rs.getString("Status");

				str =  "ID=" + ID + "@UpdateTime=" + UpdateTime + "@ContentName=" + ContentName + "@Price=" + Price + "@Runtime=" + Runtime+ "@ContentQuality=" + ContentQuality+ "@FileSize=" + FileSize
						+ "@RMACaddr=" + RMACaddr+ "@BMACaddr=" + BMACaddr+ "@PMACaddr=" + PMACaddr+ "@ETA=" + ETA+ "@Status=" + Status;
				arrayAll.add(str);
			}
			rs.close();
			stmt.close();
			c.close();
			System.out.println("Closed database successfully");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrayAll;
	}
	
	public static int viewRoleInt(){
		// TODO Auto-generated method stub
		int agent_type=0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			//System.out.println("viewRole() _Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT AgentType FROM DevicePropertyDB;");

			agent_type  = rs.getInt("AgentType");

			rs.close();
			stmt.close();
			c.close();
			//System.out.println("viewRole() _Closed database successfully");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return agent_type;
	}
	
	public static String viewRoleString(){
		String str="";
		int agent_type = viewRoleInt();
		if (agent_type>99)
			str=str.concat("R");
		if (agent_type %100 > 9)
			str=str.concat("B");
		if (agent_type%10>0)
			str=str.concat("P");
		return str;
	}

	public static int viewBeaconTime(){
		// TODO Auto-generated method stub
		int Beacon_Time=0;
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+ClientMain.myID_Num+".db");
			//System.out.println("viewRole() _Opened database successfully");
			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT Beacon_Time FROM DevicePropertyDB;");

			Beacon_Time  = rs.getInt("Beacon_Time");

			rs.close();
			stmt.close();
			c.close();
			//System.out.println("viewRole() _Closed database successfully");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Beacon_Time;
	}
}
