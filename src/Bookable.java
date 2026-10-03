public interface Bookable {
    boolean book();           // true = bokning lyckades, false = misslyckades
    boolean cancelBooking();  // true = avbokning lyckades, false = misslyckades
}
