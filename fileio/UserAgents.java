package first.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class UserAgents {
	private static ArrayList<String> agentsList = new ArrayList<String>();
	static{
        File file = new File("src/main/resources/userAgents.txt");
        BufferedReader in = null;
        
        try {
            in = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = in.readLine()) !=  null && !line.equals("") ) {
                agentsList.add(line);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
	public static String getRandomAgent() {
		return agentsList.get((int)Math.random()*agentsList.size());
	}
}
