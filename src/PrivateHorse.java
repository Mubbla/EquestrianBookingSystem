public class PrivateHorse extends Horse {

    private Person owner;

    public PrivateHorse(String name, int age, Person owner) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, false);
        // Initialize subclass-specific fields
        this.owner = owner;
    }

    // Getters
    public Person getOwner() {
        return owner;
    }

    // Overrides the base class methods to display subclass-specific details
    @Override
    public String getType() {
        return "privat häst";
    }

    @Override
    public String showDetails() {
        String availability = owner.isContactable() ? "Kontakta ägaren" : "Nej";

        return "=== " + getName() + " (Privat häst) ===\n" +
                "Ålder: " + getAge() + "\n" +
                "Ägare: " + owner.getName() + "\n" +
                "Tillgänglig för bokning: " + availability + "\n";
    }


    @Override
    public String getBookingRequirement() {
        if (owner.isContactable()) {
            return "=== " + getName() + " (Privat häst) ===\n" +
                    "Kan inte bokas via systemet.\n" +
                    "Kontakt: " + getOwner().getName() + "\n";
        } else {
            return "=== " + getName() + " (Privat häst) ===\n" +
                    "Kan inte bokas via systemet.\n";
        }
    }
}