package __Communication;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Socket;




public class ClientMain {
	static String myID, public_str = null;
	public static int myID_Num;
	public static Socket socket = null;
	public static String tempReceivedAgentInfor =null;
	
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
			/*while(true){
				//readBuffer
				Receiver.readBuffer(socket);
				//WriteBuffer
				Writer.writeBuffer(socket);
				
				fs
			}*/
			
		/*	while(true){
			
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String str=reader.readLine();

				//System.out.println(str);
				if(str== null){
					System.out.println("\"System terminate\"");
					break;
				}
				else{
					System.out.println(str);
				
				}
				
			}
			*/
			
			
			//InputStream in = socket.getInputStream();
			//OutputStream out = socket.getOutputStream();
			//System.out.println("----getInputStream----- "+ socket.getInputStream() +" ---------");
			//System.out.println("----getOutputStream----- "+ socket.getOutputStream() +" ---------");
			//String str = "Hello, Server";
			
			//out.write(str.getBytes());
			//while(true){
				
			//}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
