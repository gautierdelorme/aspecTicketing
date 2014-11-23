import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public aspect LoggingAspect {
	pointcut logIn() :
		execution(User ServiceAuthentification.logUser(..));
	
	pointcut goingDeal() :
		call(void Transaction.doTransaction(..));
	
	pointcut launchProgram() :
		execution(static void main(String[]));
	
	before() : launchProgram() {
		File f= new File("data/logs.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			FileWriter fw = new FileWriter("data/logs.txt",true);
			fw.write("- "+new Date()+" Program launch\n");
			fw.close();
		}catch(IOException ioe){
			System.err.println("IOException: " + ioe.getMessage());
		}
	}
	
	before() : goingDeal() {
		File f= new File("data/logs.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Object[] paramValues = thisJoinPoint.getArgs();
		User usr = new User((String) paramValues[0]);
		Tickets tickets = (Tickets) paramValues[1];
		int quantity = (int) paramValues[2];
		if (usr.getCredit() >= tickets.getPrice()*quantity) {
			try {
    			FileWriter fw = new FileWriter("data/logs.txt",true);
    			fw.write("- "+new Date()+" : transaction success (login : "+usr.getUsername()+" / password : "+usr.getPassword()+")\n"
    					+ "   "+quantity+" tickets for "+tickets.getTitle()+"\n"
    					+ "   "+(usr.getCredit()-tickets.getPrice()*quantity)+" credits left\n");
    			fw.close();
    		}catch(IOException ioe){
    			System.err.println("IOException: " + ioe.getMessage());
    		}
		} else {
			try {
    			FileWriter fw = new FileWriter("data/logs.txt",true);
    			fw.write("- "+new Date()+" : transaction failed (login : "+usr.getUsername()+" / password : "+usr.getPassword()+")\n"
    					+ "   "+quantity+" tickets for "+tickets.getTitle()+"\n"
    					+ "   No credits enough ("+usr.getCredit()+" credits left)\n");
    			fw.close();
    		}catch(IOException ioe){
    			System.err.println("IOException: " + ioe.getMessage());
    		}
		}
	}
	
	Object around() : logIn() {
		File f= new File("data/logs.txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Object returnValue = proceed();
		Object[] paramValues = thisJoinPoint.getArgs();
    	if (returnValue != null) {
    		try {
    			FileWriter fw = new FileWriter("data/logs.txt",true);
    			fw.write("- "+new Date()+" : Connexion success (login : "+(String) paramValues[0]+" / password : "+(String) paramValues[1]+")\n");
    			fw.close();
    		}catch(IOException ioe){
    			System.err.println("IOException: " + ioe.getMessage());
    		}
    	} else {
    		try {
    			FileWriter fw = new FileWriter("data/logs.txt",true);
    			fw.write("- "+new Date()+" : Connexion failed (login : "+(String) paramValues[0]+" / password : "+(String) paramValues[1]+")\n");
    			fw.close();
    		}catch(IOException ioe){
    			System.err.println("IOException: " + ioe.getMessage());
    		}
    	}
		return returnValue;
	}
}