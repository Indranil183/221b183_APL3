public class Main
{
	public static void main(String[] args) {
		
		
		System.out.println("for Whiskey");
		
		Beverage b= new Whiskey();
		b.templateMethod(30);
		System.out.println("  ");
		System.out.println("for Beer");
		b=new Beer();
		b.templateMethod(100);
	}
}