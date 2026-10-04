public class Pony extends Horse
{
    double lengthLimit;

    public Pony(String name, int age, boolean isAvailable, int lengthLimit) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, isAvailable);
        // Initialize subclass-specific field
        this.lengthLimit = lengthLimit;
    }

    public double getWeightLimit() {
        return lengthLimit;
    }

    // Overrides the base class method to display subclass-specific details
    @Override
    public void showDetails(){

        System.out.println("Namn: " + this.getName() + "\nÅlder: " + this.getAge()
                + "\nLedig för bokning: " + this.isAvailable() + "\nRyttaren måste vara minst: "
                + this.lengthLimit + " m");
        System.out.println("/**********************************************/\n");

    }
}
