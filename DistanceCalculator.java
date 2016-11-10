import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;


public class DistanceCalculator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> Map = new HashMap<String,String>();
		String line;
		boolean flag = false;
		String delivery_addr = null;
		delivery_addr = readDeliveryAddr();
		try {
			BufferedReader br = new BufferedReader(new FileReader("fnp_distances.txt"));
			while ((line = br.readLine())!= null)
			{
				String[] parts = line.split("\t", 3);
				if (parts.length >= 3)
				{
					String key = parts[1]+ ".............." + parts[2] ;
					String value = parts[0];
					Map.put(key, value);
				}
				else
				{
					System.out.print("ignoring line: " + line);
				}
			}
			for (String key : Map.keySet())
				{
					//System.out.println(delivery_addr);
					//System.out.println(key);
					if (key.toLowerCase().contains(delivery_addr.toLowerCase()))
					{
						System.out.println(Map.get(key) + " to " + key);
						flag = true;
					}
				}
			
			if (flag == false){
				System.out.println("We do not deliver to that area");
			}
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File Not Found");
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Input Exception");
			e.printStackTrace();
		}
	}

	
	private static String readDeliveryAddr() {
		// TODO Auto-generated method stub
		String[] temp = null;
		String inputAddr = null;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bufr = new BufferedReader(isr);
		System.out.println("Enter the area to which cake has to be delivered");
		try {
			String line = bufr.readLine();
		    temp = line.split(" ", -1);
		    for (int i = 0; i < temp.length; i++)
		    {
		    	temp[i] = temp[i].replace(",", "");
		    	if ( temp[i].contains("Bangalore") || temp[i].contains("bangalore") ){
		    		temp[i] = null;
		    	}
		    }
		    
		   inputAddr = temp[0];
		    for (int k = 1; k < temp.length; k++){
		    	
		    	if (temp[k]!= null){
		    		inputAddr += " " + temp[k];
		   	}
		    	
		    }
	
		    //System.out.println(inputAddr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception");
			e.printStackTrace();
		}
				
		return inputAddr;
	}

}
