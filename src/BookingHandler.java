import java.util.ArrayList;
// BookingHandler stores all horse and owner data and acts as the system’s booking engine.
// It provides the horse list to the UI and uses polymorphism (Bookable) to perform bookings.

public class BookingHandler {

    private ArrayList<Horse> horses = new ArrayList<>();

    public BookingHandler() {
        Person nisse = new Person("Nisse", true);
        Person janne = new Person("Janne", false);

        horses.add(new Pony("Diabolo", 14, 1.30));
        horses.add(new FullSizeHorse("Blixten", 11, 18));
        horses.add(new PrivateHorse("Hoppla", 7, nisse));
        horses.add(new PrivateHorse("Wille", 3, janne));
    }

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    public boolean tryBooking(Horse horse, String riderInput) {

        // Uses polymorphism via the Bookable interface
        if (horse instanceof Bookable) {
            Bookable bookableHorse = (Bookable) horse;
            return bookableHorse.book(riderInput);
        }

        // PrivateHorse or unknown type – cannot be booked via system
        return false;
    }
}