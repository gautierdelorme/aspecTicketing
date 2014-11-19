public class Program {
	public static void main(String[] args) {
		System.out.println("Start");
		
		Transaction s1 = new Transaction();
		Transaction s2 = new Transaction();
		if (s1 == s2) {
			System.out.println("Singleton OK");
		} else {
			System.out.println("Singleton not OK");
		}
	}
}
