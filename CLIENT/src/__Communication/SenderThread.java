package __Communication;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import org.omg.CORBA.portable.RemarshalException;

import _AgentThread.BrokerAgentThread;
import _AgentThread.ProviderAgentThread;
import _AgentThread.RequesterAgentThread;
import _BeaconMethod.BeaconManager;
import _SQLite.SQLiteExample;
import _SQLite.SQLiteView;
import _SQLite._SQLiteMain;



public class SenderThread extends Thread{
	String str;
	Socket socket;
	PrintWriter writer;
	static public boolean check=false;

	//�߰�
	DataOutputStream dos;
	FileInputStream fis;
	BufferedInputStream bis;
	static public long fileRemain;
	//�߰�

	//constructor
	SenderThread(Socket socket){
		this.socket = socket;
		try {
			writer = new PrintWriter(socket.getOutputStream());

			//�߰� data stream
			dos = new DataOutputStream(socket.getOutputStream());
			//�߰�

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(">>>>>>>>>> Client Sender Thread is Open~ <<<<<<<<<<");
	}


	public synchronized void run(){
		try{
			ClientMain.senderToRequesterOut = new PipedOutputStream();
			ClientMain.senderToRequesterIn = new PipedInputStream(ClientMain.senderToRequesterOut);
			PrintWriter writerPrintRequester = new PrintWriter(ClientMain.senderToRequesterOut);
			ClientMain.senderToBrokerOut = new PipedOutputStream();
			ClientMain.senderToBrokerIn = new PipedInputStream(ClientMain.senderToBrokerOut);
			PrintWriter writerPrintBroker = new PrintWriter(ClientMain.senderToBrokerOut);
			ClientMain.senderToProviderOut = new PipedOutputStream();
			ClientMain.senderToProviderIn = new PipedInputStream(ClientMain.senderToProviderOut);
			PrintWriter writerPrintProvider = new PrintWriter(ClientMain.senderToProviderOut);


			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			Thread requesterAgentThread = new RequesterAgentThread(socket);
			Thread brokerAgentThread = new BrokerAgentThread(socket);
			Thread providerAgentThread = new ProviderAgentThread(socket);
			brokerAgentThread.start();
			requesterAgentThread.start();
			providerAgentThread.start();




			while(true)
			{
				str = reader.readLine();
				//exit
				if(str.equals("exit"))break;


				//test
				else if(str.equals("test"))
					System.out.print(SQLiteView.viewDevicePropertyDB());



				else if(str.equals("who am i?"))
					System.out.println("i am "+ ClientMain.myID_Num);

				//act my own
				else if(str.split(",")[0].equals(Integer.toString(ClientMain.myID_Num)))
				{
					//startR
					if(str.split(",")[1].matches("strtR"))
					{
						if(SQLiteView.viewRoleInt()>99)
						{
							if(ClientMain.runingRequester)
								System.out.println("RequesterAgent already has been working");
							else
								ClientMain.runingRequester = true;
						}
						else
							System.out.println("This device doesn't run RequesterAgent");
					}
					else if(str.split(",")[1].matches("stopR"))
						ClientMain.runingRequester = false;

					//startB
					else if(str.split(",")[1].matches("startB"))
					{
						if(SQLiteView.viewRoleInt()%100>9)
						{
							if(ClientMain.runingBroker)
								System.out.println("BrokerAgent already has been working");
							else
							{								
								ClientMain.runingBroker = true;
								writerPrintBroker.println("startBeacon"); //beacon start
								writerPrintBroker.flush();
							}
						}						
						else
							System.out.println("This device doesn't run BrokerAgent");
					}
					else if(str.split(",")[1].matches("startB"))
					{
						ClientMain.runingBroker = false;
						writerPrintBroker.println("stopBeacon"); //beacon stop
						writerPrintBroker.flush();
					}

					//startP
					else if(str.split(",")[1].matches("startP"))
					{	
						if(SQLiteView.viewRoleInt()%10>0)
						{
							if(ClientMain.runingProvider)
								System.out.println("ProviderAgent already has been working");
							else
								ClientMain.runingProvider = true;
						}
						else
							System.out.println("This device doesn't run ProviderAgent");
					}
					else if(str.split(",")[1].matches("stopP"))
						ClientMain.runingProvider = false;

					//startAll
					else if(str.split(",")[1].matches("startAll"))
					{
						int agent_type = SQLiteView.viewRoleInt();
						if(agent_type>99)
						{
							if(ClientMain.runingRequester)
								System.out.println("RequesterAgent already has been working");
							else
								ClientMain.runingRequester = true;
						}
						else
							System.out.println("This device doesn't run RequesterAgent");

						if(agent_type%100>9)
						{
							if(ClientMain.runingBroker)
								System.out.println("BrokerAgent already has been working");
							else
							{								
								ClientMain.runingBroker = true;
								writerPrintBroker.println("startBeacon"); //beacon start
								writerPrintBroker.flush();
							}
						}						
						else
							System.out.println("This device doesn't run BrokerAgent");

						if(agent_type%10>0)
						{
							if(ClientMain.runingProvider)
								System.out.println("ProviderAgent already has been working");
							else
								ClientMain.runingProvider = true;
						}
						else
							System.out.println("This device doesn't run ProviderAgent");
					}

					//beacon Manager
					else if(str.split(",")[1].matches("ignoreBeacon.*"))  // .* is not supported by switch-case
						BeaconManager.ignoreBeaconManager(Integer.parseInt(str.split("@")[1]));
					else if(str.split(",")[1].matches("notifyBeacon.*"))
						BeaconManager.notifyBeaconManager(Integer.parseInt(str.split("@")[1]));

					//transfer message to agent
					else if(str.split(",")[1].matches("R.*"))
					{
						if(ClientMain.runingRequester == true)
						{
							writerPrintRequester.println(str.split("@")[1]);
							writerPrintRequester.flush();
						}
						else
							System.out.println("This device doesn't run RequesterAgent");
					}
					else if(str.split(",")[1].matches("B.*"))
					{
						if(ClientMain.runingBroker == true)
						{
							writerPrintBroker.println(str.split("@")[1]);
							writerPrintBroker.flush();
						}
						else
							System.out.println("This device doesn't run BrokerAgent");
					}
					else if(str.split(",")[1].matches("P.*"))
					{
						if(ClientMain.runingProvider == true)
						{
							writerPrintProvider.println(str.split("@")[1]);
							writerPrintProvider.flush();
						}
						else
							System.out.println("This device doesn't run ProviderAgent");
					}

					//go to SQLite menu
					else
						_SQLiteMain.menuSQLite(str.split(str.split(",")[0]+",")[1]);
				}


				//�߰� KCSE 2014 �� �غ�
				/*
				else if(str.matches("hello"))
				{
					writer.println(str);
					writer.flush();
					System.out.println("���� ���� �۾��� �����մϴ�.");

		            // ���� �̸� ����
		            //String fName = "snowpiercer_h.mp4";
					String fName = "test2.avi";
		            System.out.printf("���� �̸�(%s)�� �����Ͽ����ϴ�.\n", fName);

		            // ���� ������ �����鼭 ����
		            File f = new File(fName);
		            System.out.println("file size is "+f.length());
		            fis = new FileInputStream(f);
		            bis = new BufferedInputStream(fis);
		            int len;
		            int size = 1028;
		            byte[] data = new byte[size];
		            while ((len = bis.read(data)) != -1) {
		                dos.write(data, 0, len);
		            }

		            dos.flush();
		           // dos.close();
		            //bis.close();
		            fis.close();
		            System.out.println("���� ���� �۾��� �Ϸ��Ͽ����ϴ�.");
		            System.out.println("���� ������ ������ : " + f.length());
				}

				else if(str.matches("bye"))
				{
					writer.println(str);
					writer.flush();
					System.out.println("���� ���� �۾��� �����մϴ�.");

		            // ���� �̸� ����
		            // String fName = "�۾���a.txt";
		            // String fName = "��Ƽa.ppt";
		            // String fName = "�۾���a.jpg";
		            //String fName = "snowpiercer_h.mp4";
		            //dos.writeUTF(fName);
					String fName = "test2.avi";
		            System.out.printf("���� �̸�(%s)�� �����Ͽ����ϴ�.\n", fName);

		            // ���� ������ �����鼭 ����
		            File f = new File(fName);
		            fis = new FileInputStream(f);

		            bis = new BufferedInputStream(fis);
		            int len;
		            int size = 1028;
		            byte[] data = new byte[size];
		            System.out.println("f.length"+f.length() + " \n fileRemain"+fileRemain);
		            bis.skip(fileRemain);

		            while (fileRemain!=f.length()) {
		            	//System.out.println("�ǳ�?");

		            	if(check==true)
		            	{
		            		bis.skip(fileRemain);
		            		while(fileRemain!=f.length())
		            		{
		            		len = bis.read(data);
		            		dos.write(data, 0, len);
		            		}
		            	}
		            	//System.out.println("��������"+" \n fileRemain"+fileRemain);
		            }

		            dos.flush();
		           // dos.close();
		            //bis.close();
		            fis.close();
		            System.out.println("���� ���� �۾��� �Ϸ��Ͽ����ϴ�.");
		            //System.out.println("���� ������ ������ : " + f.length());
				}
				 */
				//�߰� KCSE 2014 �� �غ�


				//send message to server
				else{
					writer.println(str);
					writer.flush();
				}
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

	}


}
