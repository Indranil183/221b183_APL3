class ConcreteTest extends AbsTest {
    @Override
    public void display() {
        System.out.println("Display method implemented in ConcreteTest.");
    }

    public static void main(String[] args) {
        ConcreteTest obj = new ConcreteTest();
        obj.display();
    }
}