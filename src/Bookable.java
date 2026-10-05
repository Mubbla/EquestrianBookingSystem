public interface Bookable {
    boolean book(int riderAge, double riderHeight); // true = booking succeeded, false = booking failed
    boolean cancelBooking();  // true = cancellation succeeded , false = cancellation failed
}
