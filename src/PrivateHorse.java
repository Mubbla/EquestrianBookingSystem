public class PrivateHorse extends Horse{
    private String owner;
    private boolean ownerContactable;

    public PrivateHorse(String name, int age, String owner, boolean ownerContactable) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, false);
        // Initialize subclass-specific field
        this.owner = owner;
        this.ownerContactable = ownerContactable;
    }

    //Getters
    public String getOwner() {
        return owner;
    }

    public boolean ownerContactable() {
        return ownerContactable;
    }

    // Overrides the base class methods to display subclass-specific details
    @Override
    public String getType(){
        return "privat häst";
    }

    @Override
    public String showDetails() {
        String availability = ownerContactable ? "Kontakta ägaren" : "Nej";

        return  "=== " + getName() + " (Privat häst) ===\n" +
                "Ålder: " + getAge() + "\n" +
                "Ägare: " + getOwner() + "\n" +
                "Tillgänglig för bokning: " + availability + "\n";
    }

    @Override
    public String getBookingRequirement() {
        if (ownerContactable) {
            return  "=== " + getName() + " (Privat häst) ===\n" +
                    "Kan inte bokas via systemet.\n" +
                    "Kontakt: " + getOwner() + "\n";
        } else {
            return  "=== " + getName() + " (Privat häst) ===\n" +
                    "Kan inte bokas via systemet.\n";
        }
    }

}