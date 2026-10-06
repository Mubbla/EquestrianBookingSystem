public interface Bookable {
    boolean book(String requirement); // true = booking succeeded, false = booking failed
    boolean cancelBooking();  // true = cancellation succeeded , false = cancellation failed
}
