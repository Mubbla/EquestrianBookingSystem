public class FullSizeHorse extends Horse{

    int minAgeRider;

    public FullSizeHorse(String name, int age, int ageLimit) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, true);

        // Initialize subclass-specific field
        this.minAgeRider = ageLimit;
    }

    //behöver jag denna?
    public int getAgeLimit() {
        return minAgeRider;
    }

    // Overrides the base class methods to display subclass-specific details
    @Override
    public void showDetails(){

        System.out.println();
        System.out.println("/**********************************************/\n");
        System.out.println("Namn: " + this.getName() + " (Stor häst)" + "\nÅlder: " + this.getAge()
                + "\nLedig för bokning: " + this.isAvailable()
                + "\nÅldersgräns för bokning: " + this.minAgeRider);

    }

    // Checks if rider is old enough to ride the horse
    @Override
    public boolean isAvailableFor(int riderAge, double riderHeight) {
        return riderAge >= this.minAgeRider;
    }
}



