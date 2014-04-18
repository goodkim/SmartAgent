package etc;

public class GetAgentType {
	public static int stringToInt(String str){
		int agentType=0;
		if(str.matches(".*R.*"))
			agentType = agentType+100;
		if(str.matches(".*B.*"))
			agentType = agentType+10;
		if(str.matches(".*P.*"))
			agentType++;
		return agentType;
	}

}
