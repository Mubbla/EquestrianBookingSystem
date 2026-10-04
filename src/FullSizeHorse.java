public class FullSizeHorse extends Horse{

    int ageLimit;

    public FullSizeHorse(String name, int age, boolean isAvailable, int ageLimit) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, isAvailable);

        // Initialize subclass-specific field
        this.ageLimit = ageLimit;
    }

    //behöver jag denna?
    public int getAgeLimit() {
        return ageLimit;
    }

    // Overrides the base class method to display subclass-specific details
    @Override
    public void showDetails(){

        System.out.println("Namn: " + this.getName() + "\nÅlder: " + this.getAge()
                + "\nLedig för bokning: " + this.isAvailable() + "\nÅldersgräns för bokning: " + this.ageLimit);
        System.out.println("/**********************************************/\n");

    }
}



