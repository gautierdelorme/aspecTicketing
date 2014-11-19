import java.util.HashMap;

public class Program {
	public static void main(String[] args) {
		User user = new User(1, "gautier", "la", 100);
		
		TicketsFactory ticketsFactory = new TicketsFactory();
		
		HashMap<Tickets, Integer> ticketsBuy = new HashMap<Tickets, Integer>();
		ticketsBuy.put(ticketsFactory.getTicketConcert(), 1);
		ticketsBuy.put(ticketsFactory.getTicketTheater(), 4);
		user.setTicketsBuy(ticketsBuy);
		System.out.println(user);
	}
}
