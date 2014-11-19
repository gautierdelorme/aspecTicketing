public class TicketsFactory {
	public Tickets getTicketConcert() {
		return new Tickets("1995 posse", "21h00", 23.0, 200);
	}
	public Tickets getTicketTheater() {
		return new Tickets("Le splendide", "19h00", 15.0, 170);
	}
}
