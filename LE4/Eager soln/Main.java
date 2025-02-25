

public class Main
{
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		System.out.println(s1.hashCode());
		
		Singleton s2 = Singleton.getInstance();
		System.out.println(s2.hashCode());
		
	}
}