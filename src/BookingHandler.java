public class BookingHandler {

    public boolean tryBooking(Horse horse, String riderInput) {

        if (horse instanceof FullSizeHorse) {
            return ((FullSizeHorse) horse).book(riderInput);
        }

        if (horse instanceof Pony) {
            return ((Pony) horse).book(riderInput);
        }

        return false; // privat häst eller okänd typ
    }
}