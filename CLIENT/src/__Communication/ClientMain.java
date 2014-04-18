package __Communication;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Socket;




public class ClientMain {
	static String myID, public_str = null;
	public static int myID_Num;
	public static Socket socket = null;
	public static String tempReceivedAgentInfor =null;
	
	//to make pipeline between a Thread and another Thread
	public static PipedOutputStream senderToRequesterOut;
	public static PipedInputStream senderToRequesterIn;
	public static PipedOutputStream senderToBrokerOut;
	public static PipedInputStream senderToBrokerIn;
	public static PipedOutputStream senderToProviderOut;
	public static PipedInputStream senderToProviderIn;
	
	public static boolean runingRequester=false, runingBroker=false, runingProvider=false, runingSendBeacon=false;
	
	public static void main(String[] args){
		try{
			socket = new Socket("127.0.0.1", 9090);
			
			Thread clientReceiverThread = new ReceiverThread(socket);
			Thread clientSenderThread = new SenderThread(socket);
			
			clientReceiverThread.start();
			clientSenderThread.start();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
