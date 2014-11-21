import java.io.FileWriter;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import javax.json.JsonStructure;

public class User {
	private int id;
	private String username;
	private String password;
	private double credit;
	private HashMap<Tickets, Integer> ticketsBuy;
	
	public User(int id, String username, String password, double credit) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.credit = credit;
		this.ticketsBuy = new HashMap<Tickets, Integer>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setCredit(double credit) {
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
		String finale = "User [id=" + id + ", username=" + username + ", password="
				+ password + ", credit=" + credit + ", ticketsBuy=\n";
		for (Tickets key : ticketsBuy.keySet()) {
			finale += "- "+ticketsBuy.get(key)+" "+key+"\n";
		}
		return finale+"]";
	}
	
	public void Save() {
		java.io.File f= new File("userlist.txt");
		if (!f.exists())
		{
			try {
				f.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
						
		}
		FileWriter writer;
		try {
			writer = new FileWriter("userlist.txt");
			JsonGenerator gen = Json.createGenerator(writer);
			gen.writeStartObject()
			   .write("username",username)
			   .write("password",password)
			   .write("id",id)
			   .write("credit",credit)
			   .writeEnd();
			gen.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public User Load(String username) {
		java.io.File f= new File("userlist.txt");
		if (f.exists())
		{
			try {
				JsonReader reader = Json.createReader(new FileReader("userlist.txt"));
				JsonArray array = reader.readArray();
				reader.close();
				int index=0;
				boolean found=false;
				while (!found) {
					for (int i=0;i < array.size();i++)
					{
						JsonObject jso= array.getJsonObject(i);
						String name = jso.getString("username");
						if (name==username)
						{
							index=i;
							found=true;
						}
					}
					
				}
				if (found)
				{
					JsonObject jso= array.getJsonObject(index);
					String passwd=jso.getString("password");
					int id= Integer.parseInt(jso.getString("id"));
					double cred= Double.parseDouble(jso.getString("credit"));
					User us= new User(id, username, passwd, cred);
					return us;
					
				}
				else 
				{
					return null;
				}
				
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		else {
			return null;
		}
		
	}
}
