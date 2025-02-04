class Voice{
    private Animal[] animals= new Animal[5];
    
    void makevoice(){
        animals[0]= new Cow();
        animals[1]= new dog();
        animals[2]= new Pig();
        animals[3]= new Goat();
        animals[4]= new Lion();
    }
    
    void hear(){
        for (Animal animals : animals){
            if(animals != null){
                animals.makevoice();
            }
            
        }
    }
}