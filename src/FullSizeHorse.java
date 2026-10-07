public class FullSizeHorse extends Horse implements Bookable{

    int minRiderAge;

    public FullSizeHorse(String name, int age, int minRiderAge) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, true);

        // Initialize subclass-specific field
        this.minRiderAge = minRiderAge;    }


    public Integer getMinRiderAge() {
        return minRiderAge;
    }

    // Overrides the base class methods to display subclass-specific details
    @Override
    public void showDetails(){

        System.out.println();
        System.out.println("/**********************************************/\n");
        System.out.println("Namn: " + this.getName() + " (Stor häst)" + "\nÅlder: " + this.getAge()
                + "\nLedig för bokning: " + this.isAvailable()
                + "\nÅldersgräns för bokning: " + this.minRiderAge);

    }

    // Checks if rider is old enough to ride the horse
    @Override
    public boolean isAvailableFor(int riderAge, double riderHeight) {
        return riderAge >= this.minRiderAge;
    }

    public boolean book(String riderAge){

        int riderAgeInt = Integer.parseInt(riderAge);
        if(riderAgeInt>=minRiderAge)
            this.setAvailable(false); //If booking succeeded - pony no longer available

        return riderAgeInt>=minRiderAge; //true = booked, false = failed booking

    }

    public boolean cancelBooking(){

        boolean canceled = false;
        if(!this.isAvailable()) {
            this.setAvailable(true); //If cancelling succeeded, horse is available
            canceled = true;
        }

        return canceled; //true = canceled, false = failed cancelling (already available)

    }
}



