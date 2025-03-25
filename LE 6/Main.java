
public class Main
{
	public static void main(String[] args) {
	    Offering offering = new Coffee();
	    offering = new Rum(offering);
	    
		System.out.println(offering.getName()+offering.getPrice());
	}
}