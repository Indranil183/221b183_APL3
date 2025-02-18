class RubberDuck extends Duck implements Quackable {
    @Override
    public void quack() {
        System.out.println("Rubber Duck squeaks.");
    }
}