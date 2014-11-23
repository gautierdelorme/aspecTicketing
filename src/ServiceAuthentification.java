import java.util.Scanner;

public class ServiceAuthentification {
	public static User currentUser;
	public static Scanner sc = new Scanner(System.in);
	
	public User authenticate() {
		checkUser();
		if (currentUser != null) {
			return currentUser;
		} else {
			return null;
		}
	}
		
	public void checkUser() {
		clearConsole();
		System.out.println ("*******************************");
		System.out.println ("**** Authentification Page ****");
		System.out.println ("*******************************");
		System.out.println ("login : gautier\npassword : uqtr");
		System.out.println ("*******************************");

		boolean keepGoing=true;
		while (keepGoing) {
			try {
				System.out.println ("Please enter your login : ");
				String login = sc.nextLine();
				System.out.println ("Please enter your password : ");
				String password = sc.nextLine();
				if (logUser(login, password) != null) {
					keepGoing = false;
				} else {
					throw new Exception();
				}
			}catch (Exception e) {
				System.out.println ("Incorrect informations");
				System.out.println ("Try again ? (y/n)");
				switch (sc.nextLine()) {
				case "n":
					keepGoing = false;
					break;
				case "y":
					break;
				default:
					keepGoing = false;
					break;
				}
			}
		}
	}
	
	public User logUser(String login,String password) {
		User returnValue = null;
		try {
			currentUser = new User(login);
			if (currentUser != null && currentUser.getPassword().equals(password)) {
				returnValue = currentUser;
			}
		} catch (Exception e) {
			System.out.println("Cannot connect...");
		}
		return returnValue;
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
