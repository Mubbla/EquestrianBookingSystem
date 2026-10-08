public class Pony extends Horse implements Bookable
{
    private double minRiderLength;

    public Pony(String name, int age, double lengthLimit) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, true);
        // Initialize subclass-specific field
        this.minRiderLength = lengthLimit;
    }

    public double getMinRiderLength() {
        return minRiderLength;
    }

    // Overrides the base class methods to display subclass-specific details
    @Override
    public String getType(){
        return "ponny";
    }

    @Override
    public String showDetails(){

        return "\n/**********************************************/\n" +
                "Namn: " + getName() + " (Ponny)\n" +
                "Ålder: " + getAge() + "\n" +
                "Ledig för bokning: " + (isAvailable() ? "Ja" : "Nej") + "\n" +
                "Ryttaren måste vara minst: " + getMinRiderLength() + " m\n" +
                "/**********************************************/";

    }

    @Override
    public String getBookingRequirement(){
        return getName() + " är en " + getType() +
                " och du måste vara minst " + getMinRiderLength() +
                " m för att boka.";
    }

    // Checks if rider is tall enough to ride the pony
    @Override
    public boolean isAvailableFor(String requirement) {

        return Integer.parseInt(requirement) >= this.minRiderLength;
    }

    //Interface implementation of book and cancelBooking
    @Override
    public boolean book(String riderLength){

        double riderLengthDouble = Double.parseDouble(riderLength);
        if(riderLengthDouble>=minRiderLength)
            this.setAvailable(false); //If booking succeeded - pony no longer available

        return riderLengthDouble>=minRiderLength; //true = booked, false = failed booking

    }
    @Override
    public boolean cancelBooking(){

        boolean canceled = false;
        if(!this.isAvailable()) {
            this.setAvailable(true); //If cancelling succeeded, horse is available
            canceled = true;
        }

        return canceled; //true = canceled, false = failed cancelling (already available)

    }
}
