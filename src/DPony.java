public class DPony extends Horse
{
    int weightLimit;

    public DPony(String name, int age, boolean isBookable, int weightLimit) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, isBookable);
        // Initialize subclass-specific field
        this.weightLimit = weightLimit;
    }

    public int getWeightLimit() {
        return weightLimit;
    }

    // Overrides the base class method to display subclass-specific details
    @Override
    public void showDetails(){

        System.out.println("Namn: " + this.getName() + "\nÅlder: " + this.getAge()
                + "\nLedig för bokning: " + this.isBookable() + "\nÖvre viktgräns för ryttaren: " + this.weightLimit);
        System.out.println("/**********************************************/\n");

    }
}
