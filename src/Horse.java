public class Horse {

    private String name;
    private int age;
    private boolean isBookable;

    public Horse(String name, int age, boolean isBookable) {
        this.name = name;
        this.age = age;
        this.isBookable = isBookable;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isBookable() {
        return isBookable;
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

