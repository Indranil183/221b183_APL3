class JUET{
  String name;
  int age;
  public void setName(String n){
      this.name=n;
      
  }
  public void setAge(int m){
      this.age=m;
      
  }
  public String getName(){
      return name;
      
  }
  
  public int getAge(){
      return age;
  }
}
public class LE_0
{
	public static void main(String[] args) {
	   JUET obj= new JUET();
	   obj.setName("Indu");
	   obj.setAge(21);
	   
	   System.out.println("your name "+ obj.getName());
	   System.out.println("your Age "+ obj.getAge());
	   
	   
	   
	}
}
