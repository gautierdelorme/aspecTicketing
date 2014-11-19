import java.util.Scanner;

public final class Menu {
	public static User currentUser;
	public static Scanner sc = new Scanner(System.in);
	public static void HomePage()
	{
		clearConsole();
		System.out.println ("*****************************************");
		System.out.println ("******* Welcome in GSP-Transaction ******");
		System.out.println ("*****************************************");
		System.out.println ("Please select a number between 1 and 2: ");
		System.out.println ("1 - Log in");
		System.out.println ("2 - Sign up");
		System.out.println ("Choice : ");
		boolean correctchoice = false;
		while (!correctchoice) {
			try {
				switch (Integer.parseInt(sc.nextLine())) {
				case 1 :
					correctchoice = true;
					AuthentificationPage ();
					break;
				case 2 :
					correctchoice = true;
					ProfilCreationPage ();
					break;
				default :
					System.out.println ("Incorrect choice, select a number between 1 and 2 : ");
					break;
				}
			}
			catch (Exception e) {
				System.err.println ("Exception : " + e);
				System.out.println ("Incorrect choice, select a number between 1 and 2 : ");
			}
		}
	}
	
	public static void AuthentificationPage() {
		clearConsole();
		System.out.println ("*******************************");
		System.out.println ("**** Authentification Page ****");
		System.out.println ("*******************************");

		boolean correctLogin = false;
		boolean keepGoing=true;

		while (keepGoing && !correctLogin) {
			System.out.println ("Please enter your login : ");
			string login = Console.ReadLine ();
			System.out.println ("Please enter your password : ");
			string password = Console.ReadLine ();
			try {
				Profil userProfile = db.Query<Profil> ("SELECT * FROM Profil WHERE Login = ? and Password = ?", login, password).First();
				userProfile.Currency = db.Get<Devise>(userProfile.CurrencyId);
				correctLogin = true;
				User.CurrentUser = userProfile;
				User.HomePage ();
			}
			catch (Exception e) {
				Debug.WriteLine ("Exception : " + e);
				System.out.println ("Incorrect informations");
				System.out.println ("Try again ? (y/n)");
				switch (Console.ReadLine ()) {
				case "n":
					keepGoing = false;
					Menu.HomePage ();
					break;
				case "y":
					break;
				default:
					keepGoing = false;
					Menu.HomePage ();
					break;
				}
			}
		}
	}
	
	public static void ProfilCreationPage() {
		
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
	        //  Handle any exceptions.
	    }
	}
}
