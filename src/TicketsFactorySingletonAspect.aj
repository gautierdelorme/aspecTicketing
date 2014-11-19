public aspect TicketsFactorySingletonAspect extends AbstractSingletonAspect {
	pointcut singletonPointcut() :
		call(TicketsFactory.new(..));
}
