public class FullSizeHorse extends Horse implements Bookable {

    private int minRiderAge;

    public FullSizeHorse(String name, int age, int minRiderAge) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, true);

        // Initialize subclass-specific field
        this.minRiderAge = minRiderAge;
    }
    //Getter
    public Integer getMinRiderAge() {
        return minRiderAge;
    }

    // Overrides the base class methods to display subclass-specific details
    @Override
    public String getType() {
        return "stor häst";
    }

    @Override
    public String showDetails() {
        return "=== " + getName() + " (Stor häst) ===\n" +
                "Ålder: " + getAge() + "\n" +
                "Tillgänglig för bokning: " + (isAvailable() ? "Ja" : "Nej") + "\n";
    }

    @Override
    public String getBookingRequirement() {
        return "=== " + getName() + " (Stor häst) ===\n" +
                "Krav: Minst " + getMinRiderAge() + " år för att boka.\n";
    }

    // Interface implementation of book and cancelBooking
    @Override
    public boolean book(String riderAge) {
        int riderAgeInt = Integer.parseInt(riderAge);

        boolean canBook = riderAgeInt >= this.getMinRiderAge();
        if (canBook) {
            this.setAvailable(false); // If booking succeeded – horse no longer available
        }

        return canBook; // true = booked, false = failed booking
    }

    @Override
    public boolean cancelBooking() {
        boolean canceled = false;

        if (!this.isAvailable()) {
            this.setAvailable(true); // If cancelling succeeded, horse is available
            canceled = true;
        }

        return canceled; // true = canceled, false = failed cancelling (already available)
    }
}