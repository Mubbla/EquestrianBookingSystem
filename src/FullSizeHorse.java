import javax.print.attribute.standard.MediaSize;

public class FullSizeHorse extends Horse implements Bookable{

    private int minRiderAge;

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
    public String getType(){
        return "stor häst";
    }
    @Override
    public String showDetails(){

        return  "Namn: " + getName() +  "\n" +
                "Ålder: " + getAge() + "\n" +
                "Typ: " + getType() +"\n" +
                "Tillgänglig för bokning: " + (isAvailable() ? "Ja" : "Nej") + "\n" +

                "/**********************************************/";

    }

    @Override
    public String getBookingRequirement(){
        return getName() + " är en " + getType() +
                " och du måste vara minst " + getMinRiderAge() +
                " år för att boka.";
    }

    //Interface implementation of book and cancelBooking
    @Override
    public boolean book(String riderAge){

        int riderAgeInt = Integer.parseInt(riderAge);
        if(riderAgeInt>= this.getMinRiderAge())
            this.setAvailable(false); //If booking succeeded - pony no longer available

        return riderAgeInt>=this.getMinRiderAge(); //true = booked, false = failed booking

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



