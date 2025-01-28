
class mother{
    public void show(){
        System.out.println("hii i am mother");
        
    }
}
class child extends mother{
    public void show(){
        System.out.println("hello juet");
    }
}

public class Q2
{
	public static void main(String[] args) {
	//	System.out.println("Hello World");
		mother obj = new mother();
		obj.show();
		mother ch = new child();
		ch.show();
		
	}
}