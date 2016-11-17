import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Delivery_Info {

	public static void main(String[] args) {
		
		StringBuilder sbuild = new StringBuilder();
		URL url = null;
		try(FileWriter fw = new FileWriter("delivery_status.csv", true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter out = new PrintWriter(bw))
		{
			out.print("AREA" + "," + "METHOD" + "," + "ORDER_ID" + "," + "PREPARED" +  "," + "DELIVERY MAN" + "," + "DELIVERED" +  "," + "SOURCE" + "," + "CATEGORY NAME" + "," + "CREATED AT"  + "," + "ITEM ID" + "," + "DELIVERY TIME" + "," + "STATION" + "," + "SLOT DATE" + "," + "INCREMENT ID" + "," + "PACKED" + "," + "OUT FOR DELIVERY" + "," + "STATUS" );
			out.println();
		}catch (IOException e) {
		    System.out.println("Error in file handling");
		}
		try {
			url = new URL("//PASTE LINK HERE");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Malformed URL");
			e1.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			String line = null;
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			while ((line = reader.readLine()) != null) {
				sbuild.append(line);
			}
	    
			String json = sbuild.toString();
			System.out.println(json);
	    
			JsonParser parser = new JsonParser();
			Object obj = parser.parse(json);
			JsonArray jb = (JsonArray) obj;
			
			for (int i=0;i < jb.size(); i++ ){
				JsonObject jsonObject1 = (JsonObject) jb.get(i);
				System.out.println(jsonObject1);
				
				try(FileWriter fw = new FileWriter("delivery_status.csv", true);
						BufferedWriter bw = new BufferedWriter(fw);
						PrintWriter out = new PrintWriter(bw))
						{
							try{
								out.print(jsonObject1.get("area"));
								out.print(",");
								out.print(jsonObject1.get("method"));
								out.print(",");
								out.print(jsonObject1.get("orderId"));
								out.print(",");
								out.print(jsonObject1.get("prepared"));
								out.print(",");
								out.print(jsonObject1.get("deliveryman"));
								out.print(",");
								out.print(jsonObject1.get("delivered"));
								out.print(",");
								out.print(jsonObject1.get("source"));
								out.print(",");
								out.print(jsonObject1.get("categoryName"));
								out.print(",");
								out.print(jsonObject1.get("createdAt"));
								out.print(",");
								out.print(jsonObject1.get("itemId"));
								out.print(",");
								out.print(jsonObject1.get("deliverytime"));
								out.print(",");
								out.print(jsonObject1.get("station"));
								out.print(",");
								out.print(jsonObject1.get("slotDate"));
								out.print(",");
								out.print(jsonObject1.get("incrementId"));
								out.print(",");
								out.print(jsonObject1.get("packed"));
								out.print(",");
								out.print(jsonObject1.get("outfordelivery"));
								out.print(",");
								out.print(jsonObject1.get("status"));
								out.println();
							}catch(NullPointerException e1)	{
								out.print("Null");
								
							
							}
						}catch (IOException e) {
						    System.out.println("Error in file handling");
						}
				//System.out.println(distance);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception");
			e.printStackTrace();
		}
		
	}
}
