abstract class Beverage{
    protected void PourBeverage(int qty){
        System.out.println("Pour " + qty + " ml of beverage into glass ");
    }
    
    protected abstract void addCondiment();
    
    protected void Stir()
    {} // hook
    
    protected void Serve(){
        System.out.println("Serve drink to waiter");
    }
    
    public void templateMethod(int qty){
        PourBeverage(qty);
        addCondiment();
        Stir();
        Serve();
    }
}