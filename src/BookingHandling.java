public class BookingHandling {

    public boolean tryBooking(Horse horse, String riderInput) {

        if (!horse.isAvailable()) {
            System.out.println(horse.getName() + " är tyvärr redan bokad!");
            return false;
        }

        boolean booked = false;

        if (horse instanceof FullSizeHorse) {
            booked = ((FullSizeHorse) horse).book(riderInput);
        }

        if (horse instanceof Pony) {
            booked = ((Pony) horse).book(riderInput);
        }

        if (booked) {
            System.out.println("Bokningen lyckades!");
        } else {
            System.out.println("Bokningen misslyckades.");
            System.out.println(horse.getBookingRequirement());
        }

        return booked;
    }
}