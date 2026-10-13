
// Bookable defines the contract for booking and cancellation behavior.
// Each horse type implements its own rules for booking and cancellation,
// so the actual logic is handled inside the horse classes rather than in BookingHandler.

public interface Bookable {
    boolean book(String requirement); // true = booking succeeded, false = booking failed
    boolean cancelBooking();  // true = cancellation succeeded , false = cancellation failed
}
