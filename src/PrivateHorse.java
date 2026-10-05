public class PrivateHorse extends Horse{
    String owner;
    boolean isContactable;

    public PrivateHorse(String name, int age, String owner, boolean isContactable) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, false);
        // Initialize subclass-specific field
        this.owner = owner;
        this.isContactable = isContactable;
    }

    // Overrides the base class methods to display subclass-specific details
    @Override
    public void showDetails(){

        System.out.println();
        System.out.println("/**********************************************/\n");
        System.out.println("Namn: " + this.getName() + "\nÅlder: " + this.getAge()
                + "\nPrivat. " + "\nÄgare: " + this.owner
                + "\nKan kontaktas; " + this.isContactable);

    }
    // Checks if the horse is available via owner contact
    @Override
    public boolean isAvailableFor(int riderAge, double riderHeight) {
        return this.isContactable;
    }
}