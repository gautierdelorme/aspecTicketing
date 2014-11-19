public class Tickets {
	private String title;
	private String schedule;
	private double price;
	private int nbTickets;
	private int nbTicketsSold;
	
	public Tickets(String title, String schedule, double price, int nbTickets) {
		this.title = title;
		this.schedule = schedule;
		this.price = price;
		this.nbTickets = nbTickets;
		this.nbTicketsSold = 0;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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
}
