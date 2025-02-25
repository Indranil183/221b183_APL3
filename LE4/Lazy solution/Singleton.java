public class Singleton{
    private static Singleton uniqueInstance{
        private Singleton(){
    }
    public static Singleton getInstance(){
        if(uniqueInstance c==null){
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}