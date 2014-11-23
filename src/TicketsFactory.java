public class TicketsFactory {
	public Tickets getTickets(String title, String schedule, int price, int nbTickets) {
		return new Tickets(title,schedule,price,nbTickets);
	}
	
	public Tickets loadTicketsFrom(String title) {
		return new Tickets(title);
	}
}
