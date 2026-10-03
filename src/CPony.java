public class CPony extends Horse{
    int lengthLimit;

    public CPony(String name, int age, boolean isBookable, int lengthLimit) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, isBookable);
        // Initialize subclass-specific field
        this.lengthLimit = lengthLimit;
    }

    // Overrides the base class method to display subclass-specific details
    @Override
    public void showDetails(){

        System.out.println("Namn: " + this.getName() + "\nÅlder: " + this.getAge()
                + "\nLedig för bokning: " + this.isBookable() + "\nMaxlängd för ryttaren: " + this.lengthLimit + " cm");
        System.out.println("/**********************************************/\n");

    }
}
