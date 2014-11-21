public class Program {
	public static void main(String[] args) {
		User user = new User("gautier", "la", 100);
		User user2 = new User("solo", "de", 200);
		user.save();
		user2.save();
		System.out.println(new User("solo"));
		
		/*TicketsFactory ticketsFactory = new TicketsFactory();
		
		HashMap<Tickets, Integer> ticketsBuy = new HashMap<Tickets, Integer>();
		ticketsBuy.put(ticketsFactory.getTicketConcert(), 1);
		ticketsBuy.put(ticketsFactory.getTicketTheater(), 4);
		user.setTicketsBuy(ticketsBuy);
		
		System.out.println(user);*/
	}
}
