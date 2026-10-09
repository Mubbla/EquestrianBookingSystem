public class PrivateHorse extends Horse{
    private String owner;
    private boolean isContactable;

    public PrivateHorse(String name, int age, String owner, boolean isContactable) {
        // Call the superclass constructor to initialize shared Horse fields
        super(name, age, false);
        // Initialize subclass-specific field
        this.owner = owner;
        this.isContactable = isContactable;
    }

    //Getters
    public String getOwner() {
        return owner;
    }

    public boolean isContactable() {
        return isContactable;
    }

    //Setters
    public void setContactable(boolean contactable) {
        isContactable = contactable;
    }

    // Overrides the base class methods to display subclass-specific details
    @Override
    public String getType(){
        return "privat häst";
    }

    @Override
    public String showDetails(){

        return  "Namn: " + getName() + "\n" +
                "Ålder: " + getAge() + "\n" +
                "Typ: " + getType() +"\n" +
                "Ägare: " + getOwner() + "\n" +
                "Tillgänglig för bokning: Nej \n" +

                "/**********************************************/\n";
    }

    @Override
    public String getBookingRequirement(){
        if(this.isContactable)
            return getName() + " är en " + getType() +
                " och kan inte bokas via systemet. Kontakta " + getOwner() +
                " för att boka.";
        else
            return getName() + " är en " + getType() +
                    " och kan inte bokas via systemet.";
    }

}