public class Horse {

    private String name;
    private int age;
    private boolean isAvailable;

    public Horse(String name, int age, boolean isBookable) {
        this.name = name;
        this.age = age;
        this.isAvailable = isAvailable;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAge(int age) {
        this.age = age;
    }
    // Base method intended to be overridden by all horse subclasses
    public void showDetails(){}

}//class

//Name:Horse
//- Common fields: name, id, isBookable
//- Common methods: showDetails(), register(), unregister()

