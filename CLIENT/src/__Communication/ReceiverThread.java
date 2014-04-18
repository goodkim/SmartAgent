package __Communication;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import _BeaconMethod.BeaconManager;
import _Function.SAServiceManager;
import _SQLite.SQLiteView;




public class ReceiverThread extends Thread {
	Socket socket;
	String str;
	List<String> deviceList = new ArrayList<String>();
	public static List<Integer> ignoreBrokerList = new ArrayList<Integer>();
	public static int beaconCount=9;

	//constructor
	ReceiverThread(Socket socket){
		this.socket = socket;
		System.out.println(">>>>>>>>>> Client Receiver Thread is Open~ <<<<<<<<<<");
	}

	public void run(){
		try {
			BufferedReader reader = new BufferedReader ( new InputStreamReader(socket.getInputStream()));

			while (true){				
				str = reader.readLine();


				//추가 - KCSE 2014 논문 위한 시나리오
				/*
				if(str.split(",")[0].matches("bye"))
				{
					System.out.println("지금지금지금지금 내꺼!!! :" + str);
					SenderThread.fileRemain = Long.parseLong(str.split(",")[2]);
					System.out.println("이만큼 남았다!! : "+SenderThread.fileRemain);
					if(Integer.parseInt(str.split(",")[1])!=ClientMain.myID_Num)
						{
						SenderThread.check=false;
						}
					else
						SenderThread.check=true;
				}
				 */
				//추가
				//register device in device list (preprocessor for simulation)
				
				if(str.equals("Client Status Changed!"))
				{
					System.out.println(str);
					manageDeviceIDList(reader);
					System.out.println("My ID:"+ClientMain.myID+"MID_Num:"+ClientMain.myID_Num);
				}
				

				//if broker who send beacon had been registered, don't print beacon message.  
				else if(str.matches("BEACON DEVICE NUM:.*"))
					BeaconManager.receivedBeaconManager(str);
				else if(str.split("#")[0].matches("SA"))
				{
					if(ClientMain.myID_Num==Integer.parseInt(str.split("#")[3]))
						SAServiceManager.sAServiceManager(str);
					else
						System.out.println("The message isn't mine");
				}
				else
					System.out.println("ReceiverThread : "+str);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//--------------------------------------------------------------------------------------------	
	private void manageDeviceIDList(BufferedReader reader) throws ParserConfigurationException, SAXException, IOException, TransformerException{

		if(deviceList.size()==0) //there isn't any my id, initiate my id.
		{
			makeDeviceIDList(reader);
			ClientMain.myID_Num = deviceList.size()-1;
			ClientMain.myID = deviceList.get(deviceList.size()-1);
		}						
		else //there is already deviceList, reinsert into the device ID stack.
		{
			deviceList.clear();
			makeDeviceIDList(reader);						
		}	
	}

	private void makeDeviceIDList(BufferedReader reader) {
		// TODO Auto-generated method stub
		try {
			str = reader.readLine();
			while(!str.equals("end- Client Status Changed!")){
				deviceList.add(str.split("@")[1]); //.split("@")[1]
				System.out.println(str);
				str = reader.readLine();
			}
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public static void readBuffer(Socket socket) throws IOException{
		BufferedReader reader = new BufferedReader ( new InputStreamReader(socket.getInputStream()));
		String str = reader.readLine();
		System.out.println(str);
	}*/
}




/*
public static void readBuffer(Socket socket) throws IOException{
	BufferedReader reader = new BufferedReader ( new InputStreamReader (socket.getInputStream()));
	String str = reader.readLine();
	System.out.println(str);
}*/