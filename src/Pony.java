public class Pony extends Horse implements Bookable
{
    double minRiderLength;

    public Pony(String name, int age, double lengthLimit) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, true);
        // Initialize subclass-specific field
        this.minRiderLength = lengthLimit;
    }

    public double getlengthLimit() {
        return minRiderLength;
    }

    // Overrides the base class methods to display subclass-specific details
    @Override
    public void showDetails(){

        System.out.println();
        System.out.println("/**********************************************/\n");
        System.out.println("Namn: " + this.getName() + " (Ponny)" + "\nÅlder: " + this.getAge()
                + "\nLedig för bokning: " + this.isAvailable() + "\nRyttaren måste vara minst: "
                + this.minRiderLength + " m");

    }
    // Checks if rider is tall enough to ride the pony
    @Override
    public boolean isAvailableFor(int riderAge, double riderHeight) {
        return riderHeight >= this.minRiderLength;
    }

    public boolean book(String riderLength){

        double riderLengthDouble = Double.parseDouble(riderLength);
        if(riderLengthDouble>=minRiderLength)
            this.setAvailable(false); //If booking succeeded - pony no longer available

        return riderLengthDouble>=minRiderLength; //true = booked, false = failed booking

    }

    public boolean cancelBooking(){

        if(!this.isAvailable())
            this.setAvailable(true); //If cancelling succeeded, pony is available

        return this.isAvailable(); //true = cancelled, false = failed cancelling (already available)

    }
}
