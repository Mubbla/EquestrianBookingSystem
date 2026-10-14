public class Pony extends Horse implements Bookable {

    private double minRiderLength;

    public Pony(String name, int age, double lengthLimit) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, true);
        // Initialize subclass-specific field
        this.minRiderLength = lengthLimit;
    }
    //Getter
    public double getMinRiderLength() {
        return minRiderLength;
    }

    // Overrides the base class methods to display subclass-specific details
    @Override
    public String getType() {
        return "ponny";
    }

    @Override
    public String getDetails() {
        return "=== " + getName() + " (Ponny) ===\n" +
                "Ålder: " + getAge() + "\n" +
                "Tillgänglig för bokning: " + (isAvailable() ? "Ja" : "Nej") + "\n";
    }

    @Override
    public String getBookingRequirement() {
        return "=== " + getName() + " (Ponny) ===\n" +
                "Krav: Minst " + getMinRiderLength() + " m för att boka.\n";
    }

    // Interface implementation of book and cancelBooking
    @Override
    public boolean book(String riderLength) {
        double riderLengthDouble = Double.parseDouble(riderLength);

        boolean canBook = riderLengthDouble >= minRiderLength;
        if (canBook) {
            this.setAvailable(false); // If booking succeeded – pony no longer available
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