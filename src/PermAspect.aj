public aspect PermAspect {
	pointcut doDeal() :
		call(void Transaction.doTransaction(..));
	
	Object around() : doDeal() {
		Object[] paramValues = thisJoinPoint.getArgs();
		User usr = new User((String) paramValues[0]);
		Tickets tickets = (Tickets) paramValues[1];
		int quantity = (int) paramValues[2];
		if (usr.getCredit() >= tickets.getPrice()*quantity) {
			return proceed();
		} else {
			System.out.println("No credits enough...");
			return null;
		}
	}
}
