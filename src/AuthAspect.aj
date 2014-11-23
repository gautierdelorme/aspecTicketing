public aspect AuthAspect {
	private ServiceAuthentification authenticator = new ServiceAuthentification();
	private User currentUser;
	
	pointcut connectCall(User usr) :
		execution(static void Menu.*Connect(User)) && args(usr);
	
	Object around(User usr) : connectCall(usr) {
		if (currentUser == null) {
			currentUser = authenticator.authenticate();
		}
		return proceed(currentUser);
	}
}
