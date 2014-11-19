public aspect LoggingAspect {
	pointcut mainCall() :
		execution(static void main(String[]));
	before() : mainCall() {
		System.out.println("Here we go !");
	}
}