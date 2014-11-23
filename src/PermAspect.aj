public aspect PermAspect {
	pointcut doDeal(String username, Tickets tickets, int quantity) :
		call(void Transaction.doTransaction(String, Tickets, int)) && args(username, tickets, quantity);
	
	Object around(String username, Tickets tickets, int quantity) : doDeal(username, tickets, quantity) {
		Object[] paramValues = thisJoinPoint.getArgs();
		User usr = new User((String) paramValues[0]);
		Tickets tickets2 = (Tickets) paramValues[1];
		int quantity2 = (int) paramValues[2];
		if (usr.getCredit() >= tickets2.getPrice()*quantity2) {
			return proceed(usr.getUsername(), tickets2, quantity2);
		} else {
			System.out.println("No credits enough...");
			return proceed(null, tickets2, quantity2);
		}
	}
}
