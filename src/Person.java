public class Person {

    private String name;
    private boolean contactable;

    public Person(String name, boolean contactable) {
        this.name = name;
        this.contactable = contactable;
    }

    public String getName() {
        return name;
    }

    public boolean isContactable() {
        return contactable;
    }

}

