import java.util.HashMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.JsonWriter;

public class User {
	private String username;
	private String password;
	private int credit;
	private HashMap<Tickets, Integer> ticketsBuy;
	
	public User(String username, String password, int credit) {
		this.username = username;
		this.password = password;
		this.credit = credit;
		this.ticketsBuy = new HashMap<Tickets, Integer>();
	}
	
	public User(String username) {
		User loaded = load(username);
		this.username = loaded.username;
		this.password = loaded.password;
		this.credit = loaded.credit;
		this.ticketsBuy = loaded.ticketsBuy;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public HashMap<Tickets, Integer> getTicketsBuy() {
		return ticketsBuy;
	}

	public void setTicketsBuy(HashMap<Tickets, Integer> ticketsBuy) {
		this.ticketsBuy = ticketsBuy;
	}

	@Override
	public String toString() {
		String finale = "User [username=" + username + ", password="
				+ password + ", credit=" + credit + ", ticketsBuy=\n";
		for (Tickets key : ticketsBuy.keySet()) {
			finale += "- "+ticketsBuy.get(key)+" "+key+"\n";
		}
		return finale+"]";
	}
	
	public void save() {
		java.io.File f= new File("data/userlist.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			JsonObjectBuilder userJSON = Json.createObjectBuilder();
			JsonArrayBuilder userArrayJSON = Json.createArrayBuilder();
			JsonObjectBuilder finalJSON = Json.createObjectBuilder();
			
			boolean empty = !(f.length() > 0);
			if (!empty) {
				InputStream fis = new FileInputStream("data/userlist.txt");	         
		        JsonReader jsonReader = Json.createReader(fis);
		        JsonObject jsonObject = jsonReader.readObject();
		        jsonReader.close();
		        fis.close();
		        JsonArray jsonArray = jsonObject.getJsonArray("users");
		        for(JsonValue value : jsonArray){
		        	userArrayJSON.add(value);
		        }
			}
			
			userJSON.add("username",username)
					.add("password",password)
					.add("credit",credit);
			userArrayJSON.add(userJSON);
			finalJSON.add("users", userArrayJSON);
			JsonObject empJsonObject = finalJSON.build();
			OutputStream os = new FileOutputStream("data/userlist.txt");
	        JsonWriter jsonWriter = Json.createWriter(os);
	        jsonWriter.writeObject(empJsonObject);
	        jsonWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public User load(String username) {
		User returnUser = null;
		java.io.File f= new File("data/userlist.txt");
		if (f.exists()) {
			try {
				boolean empty = !(f.length() > 0);				
				if (!empty) {
					InputStream fis = new FileInputStream("data/userlist.txt");	         
			        JsonReader jsonReader = Json.createReader(fis);
			        JsonObject jsonObject = jsonReader.readObject();
			        jsonReader.close();
			        JsonArray jsonArray = jsonObject.getJsonArray("users");
			        int i = 0;
			        boolean found=false;
			        while (!found && i < jsonArray.size()) {
			        	JsonObject jso= jsonArray.getJsonObject(i);
						String name = jso.getString("username");
						if (name.equals(username)) {
							System.out.println(jso.getString("username"));
							found=true;
							returnUser = new User(jso.getString("username"), jso.getString("password"), jso.getInt("credit"));
						}
						i++;
			        }
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return returnUser;	
	}
}
