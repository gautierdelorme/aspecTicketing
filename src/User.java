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
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
		this.credit = 1000;
	}
	
	public User(String username, String password, int credit) {
		this.username = username;
		this.password = password;
		this.credit = credit;
	}
	
	public User(String username) {
		User loaded = load(username);
		this.username = loaded.username;
		this.password = loaded.password;
		this.credit = loaded.credit;
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

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password="
				+ password + ", credit=" + credit + "]";
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

		        int i = 0;
		        for(JsonValue value : jsonArray){
		        	if (!jsonArray.getJsonObject(i).getString("username").equals(this.username)) {
		        		userArrayJSON.add(value);
		        	}
		        	i++;
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
