public aspect TransactionSingletonAspect extends AbstractSingletonAspect {
	pointcut singletonPointcut() :
		call(Transaction.new(..));
}
