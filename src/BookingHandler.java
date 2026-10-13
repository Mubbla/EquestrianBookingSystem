public class BookingHandler {

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