import java.util.Scanner;

public final class Menu {
	public static Scanner sc = new Scanner(System.in);
	public static void HomePage()
	{
		clearConsole();
		System.out.println ("*****************************************");
		System.out.println ("******* Welcome in AspectTicketing ******");
		System.out.println ("*****************************************");
		System.out.println ("Please select a number between 1 and 2: ");
		System.out.println ("1 - Buy tickets");
		System.out.println ("2 - Personnal Account");
		System.out.println ("Choice : ");
		boolean correctchoice = false;
		while (!correctchoice) {
			try {
				switch (Integer.parseInt(sc.nextLine())) {
				case 1 :
					correctchoice = true;
					buyTicketsConnect (null);
					break;
				case 2 :
					correctchoice = true;
					personnalAccountConnect (null);
					break;
				default :
					throw new Exception();
				}
			}
			catch (Exception e) {
				System.err.println (e);
				System.out.println ("Incorrect choice");
				HomePage();
			}
		}
	}
	
	public static void buyTicketsConnect(User usr) {
		clearConsole();
		System.out.println ("*******************************");
		System.out.println ("**** Tickets Shop ****");
		System.out.println ("*******************************");
		System.out.println ("Hi "+usr.getUsername()+" ! Choose an event :");
		TicketsFactory ticketsFactory = new TicketsFactory();
		System.out.println ("1 - "+ticketsFactory.loadTicketsFrom("1995 posse"));
		System.out.println ("2 - "+ticketsFactory.loadTicketsFrom("Le splendide"));
		System.out.println ("3 - back to home page");
		System.out.println ("Choice number : ");
		boolean correctchoice = false;
		while (!correctchoice) {
			try {
				switch (Integer.parseInt(sc.nextLine())) {
				case 1 :
					transactionWithTicketsAndUsername(ticketsFactory.loadTicketsFrom("1995 posse"),usr.getUsername());
					correctchoice = true;
					break;
				case 2 :
					transactionWithTicketsAndUsername(ticketsFactory.loadTicketsFrom("Le splendide"),usr.getUsername());
					correctchoice = true;
					break;
				case 3 :
					correctchoice = true;
					HomePage();
					break;
				default :
					throw new Exception();
				}
			}
			catch (Exception e) {
				System.out.println ("Incorrect choice");
				buyTicketsConnect(usr);
			}
		}
		System.out.println ("back to homepage ? (y/n)");
		switch (sc.nextLine()) {
		case "n":
			buyTicketsConnect(usr);
			break;
		case "y":
			HomePage();
			break;
		default:
			HomePage();
			break;
		}
	}
	
	public static void transactionWithTicketsAndUsername(Tickets tickets, String username) {
		System.out.println ("*******************************");
		boolean correctchoice = false;
		int quantity = 0;
		int maxQuantity = tickets.getNbTickets()-tickets.getNbTicketsSold();
		while (!correctchoice) {
			System.out.println ("Quantity (max "+maxQuantity+") :");
			quantity = Integer.parseInt(sc.nextLine());
			if (quantity <= maxQuantity) {
				correctchoice = true;
			}
		}
		Transaction transaction = new Transaction();
		transaction.doTransaction(username, tickets, quantity);
	}
	
	public static void personnalAccountConnect(User usr) {
		clearConsole();
		System.out.println ("*******************************");
		System.out.println ("**** Personnal Account ****");
		System.out.println ("*******************************");
		usr = new User(usr.getUsername());
		System.out.println("Credits : "+usr.getCredit());
		System.out.println("Transactions : ");
		Transaction transaction= new Transaction();
		transaction.Printall(usr.getUsername());

		System.out.println ("\nback to homepage ? (y/n)");
		switch (sc.nextLine()) {
		case "n":
			personnalAccountConnect(usr);
			break;
		case "y":
			HomePage();
			break;
		default:
			HomePage();
			break;
		}
	}
	
	public static void clearConsole()
	{
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	    	System.err.println(e);
	    }
	}
}
