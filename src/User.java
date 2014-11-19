import com.sun.javafx.collections.MappingChange.Map;

public class User {
	private int id;
	private String username;
	private String password;
	private double credit;
	private Map<Tickets, Integer> ticketsBuy;
	
	public User(int id, String username, String password, double credit,
			Map<Tickets, Integer> ticketsBuy) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.credit = credit;
		this.ticketsBuy = ticketsBuy;
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

	public Map<Tickets, Integer> getTicketsBuy() {
		return ticketsBuy;
	}

	public void setTicketsBuy(Map<Tickets, Integer> ticketsBuy) {
		this.ticketsBuy = ticketsBuy;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", credit=" + credit + ", ticketsBuy="
				+ ticketsBuy + "]";
	}
}
