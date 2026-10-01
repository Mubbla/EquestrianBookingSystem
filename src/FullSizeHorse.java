public class FullSizeHorse extends Horse{

    int ageLimit;

    public FullSizeHorse(String name, int age, boolean isBookable, int ageLimit) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, isBookable);

        // Initialize subclass-specific field
        this.ageLimit = ageLimit;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void ShowDetails(){

        System.out.println("Namn: " + this.getName() + "\nÅlder: " + this.getAge()
                + "\nLedig för bokning: " + this.isBookable() + "\nÅldersgräns för bokning: " + this.ageLimit);
        System.out.println("/**********************************************/\n");

    }
}



