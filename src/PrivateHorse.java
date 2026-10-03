public class PrivateHorse extends Horse{
    String owner;

    public PrivateHorse(String name, int age, boolean isAvailable, String owner) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, isAvailable);
        // Initialize subclass-specific field
        this.owner = owner;
    }

    @Override
    public void showDetails(){

        System.out.println("Namn: " + this.getName() + "\nÅlder: " + this.getAge()
                + "\nLedig för bokning: " + this.isAvailable() + "\nÄgare: " + this.owner);
        System.out.println("/**********************************************/\n");

    }
}