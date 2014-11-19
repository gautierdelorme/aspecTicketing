public abstract aspect AbstractSingletonAspect {
	abstract pointcut singletonPointcut();
	
	private Object singletonUniqueInstance;
	
	Object around() : singletonPointcut() {
		if (singletonUniqueInstance == null) {
			singletonUniqueInstance = proceed();
		}
		return singletonUniqueInstance;
	}
}
