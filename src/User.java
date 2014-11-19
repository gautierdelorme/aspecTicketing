import java.util.HashMap;

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
		
	}
	
	public User Load(String username) {
		return null;
	}
}
