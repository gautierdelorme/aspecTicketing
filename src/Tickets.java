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

public class Tickets {
	private String title;
	private String schedule;
	private int price;
	private int nbTickets;
	private int nbTicketsSold;
	
	public Tickets(String title, String schedule, int price, int nbTickets) {
		this.title = title;
		this.schedule = schedule;
		this.price = price;
		this.nbTickets = nbTickets;
		this.nbTicketsSold = 0;
	}
	
	public Tickets(String title, String schedule, int price, int nbTickets,int nbTicketsSold) {
		this.title = title;
		this.schedule = schedule;
		this.price = price;
		this.nbTickets = nbTickets;
		this.nbTicketsSold = nbTicketsSold;
	}
	
	public Tickets(String title) {
		Tickets loaded = load(title);
		this.title = loaded.title;
		this.schedule = loaded.schedule;
		this.price = loaded.price;
		this.nbTickets = loaded.nbTickets;
		this.nbTicketsSold = loaded.nbTicketsSold;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNbTickets() {
		return nbTickets;
	}

	public void setNbTickets(int nbTickets) {
		this.nbTickets = nbTickets;
	}

	public int getNbTicketsSold() {
		return nbTicketsSold;
	}

	public void setNbTicketsSold(int nbTicketsSold) {
		this.nbTicketsSold = nbTicketsSold;
	}

	@Override
	public String toString() {
		return "Tickets [title=" + title + ", schedule=" + schedule
				+ ", price=" + price + ", nbTickets=" + nbTickets
				+ ", nbTicketsSold=" + nbTicketsSold + "]";
	}
	
	public void save() {
		java.io.File f= new File("data/ticketslist.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			JsonObjectBuilder ticketJSON = Json.createObjectBuilder();
			JsonArrayBuilder ticketsArrayJSON = Json.createArrayBuilder();
			JsonObjectBuilder finalJSON = Json.createObjectBuilder();
			
			boolean empty = !(f.length() > 0);
			if (!empty) {
				InputStream fis = new FileInputStream("data/ticketslist.txt");	         
		        JsonReader jsonReader = Json.createReader(fis);
		        JsonObject jsonObject = jsonReader.readObject();
		        jsonReader.close();
		        fis.close();
		        JsonArray jsonArray = jsonObject.getJsonArray("tickets");
		        
		        int i = 0;
		        for(JsonValue value : jsonArray){
		        	if (!jsonArray.getJsonObject(i).getString("title").equals(this.title)) {
		        		ticketsArrayJSON.add(value);
		        	}
		        	i++;
		        }
			}
			
			ticketJSON.add("title",title)
					.add("schedule",schedule)
					.add("price",price)
					.add("nbTickets",nbTickets)
					.add("nbTicketsSold",nbTicketsSold);
			ticketsArrayJSON.add(ticketJSON);
			finalJSON.add("tickets", ticketsArrayJSON);
			JsonObject empJsonObject = finalJSON.build();
			OutputStream os = new FileOutputStream("data/ticketslist.txt");
	        JsonWriter jsonWriter = Json.createWriter(os);
	        jsonWriter.writeObject(empJsonObject);
	        jsonWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Tickets load(String title) {
		Tickets returnTickets = null;
		java.io.File f= new File("data/ticketslist.txt");
		if (f.exists()) {
			try {
				boolean empty = !(f.length() > 0);				
				if (!empty) {
					InputStream fis = new FileInputStream("data/ticketslist.txt");	         
			        JsonReader jsonReader = Json.createReader(fis);
			        JsonObject jsonObject = jsonReader.readObject();
			        jsonReader.close();
			        JsonArray jsonArray = jsonObject.getJsonArray("tickets");
			        int i = 0;
			        boolean found=false;
			        while (!found && i < jsonArray.size()) {
			        	JsonObject jso= jsonArray.getJsonObject(i);
						String name = jso.getString("title");
						if (name.equals(title)) {
							found=true;
							returnTickets = new Tickets(jso.getString("title"), jso.getString("schedule"), jso.getInt("price"), jso.getInt("nbTickets"), jso.getInt("nbTicketsSold"));
						}
						i++;
			        }
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return returnTickets;	
	}
}
