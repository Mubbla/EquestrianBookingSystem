public class PrivateHorse extends Horse{
    String owner;

    public PrivateHorse(String name, int age, String owner) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, false);
        // Initialize subclass-specific field
        this.owner = owner;
    }

    @Override
    public void showDetails(){

        System.out.println();
        System.out.println("/**********************************************/\n");
        System.out.println("Namn: " + this.getName() + "\nÅlder: " + this.getAge()
                + "\nPrivathäst, går ej att boka. " + "\nÄgare: " + this.owner);

    }
}