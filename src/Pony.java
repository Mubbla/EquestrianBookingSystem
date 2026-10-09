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

        return  "Namn: " + getName() + "\n" +
                "Ålder: " + getAge() + "\n" +
                "Typ: " + getType() +"\n" +
                "Tillgänglig för bokning: " + (isAvailable() ? "Ja" : "Nej") + "\n" +

                "/**********************************************/\n";

    }

    @Override
    public String getBookingRequirement(){
        return getName() + " är en " + getType() +
                " och du måste vara minst " + getMinRiderLength() +
                " m för att boka.";
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
