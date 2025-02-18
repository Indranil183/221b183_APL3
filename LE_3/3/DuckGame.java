
public class DuckGame {
    public static void main(String[] args) {
        Duck[] ducks = {new RubberDuck(), new WoodenDuck(), new RedHeadDuck(), new LakeDuck()};

        for (Duck duck : ducks) {
            System.out.println("\n" + duck.getClass().getSimpleName() + ":");
            duck.swim();
            if (duck instanceof Flyable) {
                ((Flyable) duck).fly();
            }
            if (duck instanceof Quackable) {
                ((Quackable) duck).quack();
            }
        }
    }
}