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

public class Transaction {
	private String username;
	private String title;
	private int quantity;
	private int total;
	
	public Transaction() {}
	
	public Transaction(String username, String title, int quantity, int total) {
		this.username = username;
		this.title = title;
		this.quantity = quantity;
		this.total = total;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	public void doTransaction(String username, Tickets tickets, int quantity) {
		if (username != null) {
			int total = tickets.getPrice()*quantity;
			Transaction transaction = new Transaction();
			transaction.setQuantity(quantity);
			transaction.setTitle(tickets.getTitle());
			transaction.setTotal(total);
			transaction.setUsername(username);
			transaction.save();
			tickets.setNbTicketsSold(tickets.getNbTicketsSold()+quantity);
			tickets.save();
			User user = new User(username);
			user.setCredit(user.getCredit() - total);
			user.save();
			System.out.println ("Transaction saved !");
		}
	}
	
	public void save() {
		java.io.File f= new File("data/transactionslist.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			JsonObjectBuilder transactionJSON = Json.createObjectBuilder();
			JsonArrayBuilder transactionsJSON = Json.createArrayBuilder();
			JsonObjectBuilder finalJSON = Json.createObjectBuilder();
			
			boolean empty = !(f.length() > 0);
			int oldQuantity = 0;
			int oldTotal = 0;
			if (!empty) {
				InputStream fis = new FileInputStream("data/transactionslist.txt");	         
		        JsonReader jsonReader = Json.createReader(fis);
		        JsonObject jsonObject = jsonReader.readObject();
		        jsonReader.close();
		        fis.close();
		        JsonArray jsonArray = jsonObject.getJsonArray("transactions");
		        
		        int i = 0;
		        
		        for(JsonValue value : jsonArray){
		        	if (!(jsonArray.getJsonObject(i).getString("username").equals(this.username) &&
		        			jsonArray.getJsonObject(i).getString("title").equals(this.title))) {
		        		transactionsJSON.add(value);
		        	} else {
		        		oldQuantity += jsonArray.getJsonObject(i).getInt("quantity");
		        		oldTotal += jsonArray.getJsonObject(i).getInt("total");
		        	}
		        	i++;
		        }
			}
			transactionJSON.add("username",username)
					.add("title",title)
					.add("quantity",quantity+oldQuantity)
					.add("total",total+oldTotal);
			transactionsJSON.add(transactionJSON);
			finalJSON.add("transactions", transactionsJSON);
			JsonObject empJsonObject = finalJSON.build();
			OutputStream os = new FileOutputStream("data/transactionslist.txt");
	        JsonWriter jsonWriter = Json.createWriter(os);
	        jsonWriter.writeObject(empJsonObject);
	        jsonWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Transaction load(String username) {
		Transaction returnTransaction = null;
		java.io.File f= new File("data/transactionslist.txt");
		if (f.exists()) {
			try {
				boolean empty = !(f.length() > 0);				
				if (!empty) {
					InputStream fis = new FileInputStream("data/transactionslist.txt");	         
			        JsonReader jsonReader = Json.createReader(fis);
			        JsonObject jsonObject = jsonReader.readObject();
			        jsonReader.close();
			        JsonArray jsonArray = jsonObject.getJsonArray("transactions");
			        int i = 0;
			        boolean found=false;
			        while (!found && i < jsonArray.size()) {
			        	JsonObject jso= jsonArray.getJsonObject(i);
						String name = jso.getString("username");
						if (name.equals(username)) {
							found=true;
							returnTransaction = new Transaction ();
							returnTransaction.setQuantity(jso.getInt("quantity"));
							returnTransaction.setTitle(jso.getString("title"));
							returnTransaction.setTotal(jso.getInt("total"));
							returnTransaction.setUsername(jso.getString("username"));
						}
						i++;
			        }
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return returnTransaction;
	}
	
	public void Printall(String username) {
		java.io.File f= new File("data/transactionslist.txt");
		if (f.exists()) {
			try {
				boolean empty = !(f.length() > 0);				
				if (!empty) {
					InputStream fis = new FileInputStream("data/transactionslist.txt");	         
			        JsonReader jsonReader = Json.createReader(fis);
			        JsonObject jsonObject = jsonReader.readObject();
			        jsonReader.close();
			        JsonArray jsonArray = jsonObject.getJsonArray("transactions");
			        int i = 0;
			        boolean found=false;
			        while (!found && i < jsonArray.size()) {
			        	JsonObject jso= jsonArray.getJsonObject(i);
						String name = jso.getString("username");
						if (name.equals(username)) {
							Transaction returnTransaction = new Transaction ();
							returnTransaction.setQuantity(jso.getInt("quantity"));
							returnTransaction.setTitle(jso.getString("title"));
							returnTransaction.setTotal(jso.getInt("total"));
							returnTransaction.setUsername(jso.getString("username"));
							System.out.println(returnTransaction);
						}
						i++;
			        }
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return "Transaction [username=" + username + ", title=" + title
				+ ", quantity=" + quantity + ", total=" + total + "]";
	}
}
