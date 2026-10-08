import java.util.ArrayList;

public abstract class Horse {

    //Common fields for all horses
    private String name;
    private int age;
    private boolean isAvailable;

    //Constructor
    public Horse(String name, int age, boolean isAvailable) {
        this.name = name;
        this.age = age;
        this.isAvailable = isAvailable;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    //Setters
    public void setAge(int age) {
        this.age = age;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Base method intended to be overridden by all horse subclasses
    public abstract String showDetails();

    public abstract String getType();

    // Base method intended to be overridden by all horse subclasses
    public abstract String getBookingRequirement();


    // Base method intended to be overridden by all horse subclasses
    public abstract boolean isAvailableFor(String requirement);

}



