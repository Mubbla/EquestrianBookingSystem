import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {

    private ArrayList<Horse> horses;
    private BookingHandler bookingHandler;
    private Scanner scanner;

    public ConsoleUI() {
        this.bookingHandler = new BookingHandler();
        this.horses = bookingHandler.getHorses();
        this.scanner = new Scanner(System.in);
    }

    // ============================================================
    // START
    // ============================================================

    public void start() {

        while (true) {
            printMainMenu();
            int menuChoice = Integer.parseInt(enterAndValidateInput("Välj alternativ:", "menu"));

            switch (menuChoice) {
                case 1:
                    listAllHorses();
                    break;

                case 2:
                    listAllBookingRequirements();
                    break;

                case 3:
                    addHorseFromUserInput();
                    break;

                case 4:
                    deleteHorse();
                    break;

                case 5:
                    bookHorse();
                    break;

                case 6:
                    cancelBooking();
                    break;

                case 0:
                    System.out.println("Programmet avslutas.");
                    return;

                default:
                    printError("Felaktigt värde!");
            }
        }
    }

    // ============================================================
    // VALIDATION
    // ============================================================

    public String enterAndValidateInput(String prompt, String type) {

        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();

            if (type.equals("menu")) {
                if (!input.matches("[0-9]+")) {
                    printError("Försök med en siffra från menyn!");
                    continue;
                }
                return input;
            }

            if (type.equals("name")) {
                if (input.matches("[A-Za-zÅÄÖåäö ]+") && !input.trim().isEmpty()) {
                    return input;
                }
                printError("Skriv in ett giltigt namn (endast bokstäver och mellanslag).");
                continue;
            }

            try {
                double value = Double.parseDouble(input);

                if (type.equals("age")) {
                    if (value < 0) {
                        printError("Ålder måste vara 0 eller högre.");
                        continue;
                    }
                    return input;
                }

                if (type.equals("length")) {
                    if (value <= 0) {
                        printError("Orimlig längd.");
                        continue;
                    }
                    return input;
                }

                if (type.equals("horsetype")) {
                    int intValue = (int) value;
                    if (intValue >= 1 && intValue <= 3) {
                        return input;
                    }
                    printError("Försök med ett nummer på listan.");
                }

            } catch (NumberFormatException e) {
                if (type.equals("age")) printError("Ålder måste vara ett heltal.");
                else if (type.equals("length")) printError("Längd måste vara ett decimaltal.");
                else if (type.equals("horsetype")) printError("Försök med ett nummer i listan!");
            }
        }
    }

    // ============================================================
    // PRINTING
    // ============================================================

    private void printMainMenu() {
        System.out.println("\n=== ADMINISTRERA HÄSTAR ===");
        System.out.println("[1] Visa alla");
        System.out.println("[2] Lista alla bokningskrav");
        System.out.println("[3] Lägg till");
        System.out.println("[4] Ta bort");
        System.out.println("[5] Boka");
        System.out.println("[6] Avboka");
        System.out.println("[0] Avsluta programmet");
    }

    private void printHeader(String title) {
        System.out.println("\n== " + title + " ==");
    }

    private void printHorseTypeMenu() {
        //printHeader("Hästtyp");
        System.out.println("1. Stor häst");
        System.out.println("2. Ponny");
        System.out.println("3. Privat häst");
    }

    private void printHorseDetails(Horse horse) {
        System.out.println(horse.showDetails());
    }

    private void printBookingRequirement(Horse horse) {
        System.out.println(horse.getBookingRequirement());
    }

    // ============================================================
    // ERROR PRINTING
    // ============================================================

    private void printInvalidInput() {
        printError("Ogiltig input.");
    }


    private void printError(String message) {
        System.out.println("Fel: " + message);
    }

    private void printHorseNotFound(String name) {
        printError("Det finns ingen häst med namnet " + name + " i systemet!");
    }

    private void printWrongHorseType(Horse horse) {
        printError(horse.getName() + " är en " + horse.getType() + ". Gör om ditt val!");
    }

    private void printHorseAlreadyBooked(Horse horse) {
        printError(horse.getName() + " är redan bokad!");
    }

    private void printBookingFailed(Horse horse) {
        printError("Bokningen misslyckades.");
        System.out.println(horse.getBookingRequirement());
    }

    // ============================================================
    // YES/NO
    // ============================================================

    private boolean askYesNo(String question) {
        while (true) {
            System.out.print(question + " (ja/nej): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("ja")) return true;
            if (input.equals("nej")) return false;

            System.out.println("Skriv 'ja' eller 'nej'.");
        }
    }

    private boolean askAbort() {
        return askYesNo("Vill du avbryta?");
    }

    // ============================================================
    // HORSE MANAGEMENT
    // ============================================================

    public void listAllHorses() {
        printHeader("Alla hästar");
        for (Horse horse : horses) {
            printHorseDetails(horse);
        }
    }

    public void listAllBookingRequirements() {
        printHeader("Bokningskrav");
        for (Horse horse : horses) {
            printBookingRequirement(horse);
        }
    }

    public void addHorseFromUserInput() {

        while (true) {
            printHeader("Hästinfo");
            String horseName = enterAndValidateInput("Namn:", "name");
            int age = Integer.parseInt(enterAndValidateInput("Ålder:", "age"));

            printHorseTypeMenu();
            int typeChoice = Integer.parseInt(enterAndValidateInput("Välj typ:", "horsetype"));

            Horse horse = null;

            switch (typeChoice) {
                case 1:
                    printHeader("Ryttarinfo");
                    int ageLimitInt = Integer.parseInt(
                            enterAndValidateInput("Lägsta tillåtna ålder:", "age"));
                    horse = new FullSizeHorse(horseName, age, ageLimitInt);
                    break;

                case 2:
                    printHeader("Ryttarinfo");
                    double minRiderLength = Double.parseDouble(
                            enterAndValidateInput("Minsta längd (meter):", "length"));
                    horse = new Pony(horseName, age, minRiderLength);
                    break;

                case 3:
                    printHeader("Ägarinfo");
                    String ownerName = enterAndValidateInput("Ägarens namn:", "name");
                    boolean contactable = askYesNo("Kan ägaren kontaktas?");
                    Person owner = new Person(ownerName, contactable);
                    horse = new PrivateHorse(horseName, age, owner);
                    break;
            }

            if (horse != null) {
                horses.add(horse);
                System.out.println("Hästen har registrerats!");
            } else {
                printError("Registreringen misslyckades.");
            }

            if (askAbort()) return;
        }
    }

    public void deleteHorse() {

        while (true) {
            printHeader("Ta bort häst");
            String horseName = enterAndValidateInput("Namn:", "name");
            Horse found = findHorse(horseName);

            if (found != null) {
                horses.remove(found);
                System.out.println(found.getName() + " har tagits bort.");
            } else {
                printHorseNotFound(horseName);
            }

            if (askAbort()) return;
        }
    }

    public void bookHorse() {

        while (true) {
            printHorseTypeMenu();
            int typeChoice = Integer.parseInt(enterAndValidateInput("Välj typ:", "horsetype"));

            Horse horse;
            String horseName;
            boolean booked;

            switch (typeChoice) {
                case 1:
                    printHeader("Ryttarinfo");
                    int riderAgeInt = Integer.parseInt(enterAndValidateInput("Ålder:", "age"));

                    printHeader("Hästinfo");
                    horseName = enterAndValidateInput("Hästens namn:", "name");
                    horse = findHorse(horseName);

                    if (horse == null) {
                        printHorseNotFound(horseName);
                        break;
                    }

                    if (!(horse instanceof FullSizeHorse)) {
                        printWrongHorseType(horse);
                        break;
                    }

                    if (!horse.isAvailable()) {
                        printHorseAlreadyBooked(horse);
                        break;
                    }

                    booked = bookingHandler.tryBooking(horse, Integer.toString(riderAgeInt));

                    if (booked) System.out.println("Bokningen lyckades!");
                    else printBookingFailed(horse);

                    break;

                case 2:
                    printHeader("Ryttarinfo");
                    double riderLengthDouble = Double.parseDouble(
                            enterAndValidateInput("Längd (meter):", "length"));

                    printHeader("Hästinfo");
                    horseName = enterAndValidateInput("Hästens namn:", "name");
                    horse = findHorse(horseName);

                    if (horse == null) {
                        printHorseNotFound(horseName);
                        break;
                    }

                    if (!(horse instanceof Pony)) {
                        printWrongHorseType(horse);
                        break;
                    }

                    if (!horse.isAvailable()) {
                        printHorseAlreadyBooked(horse);
                        break;
                    }

                    booked = bookingHandler.tryBooking(horse, Double.toString(riderLengthDouble));

                    if (booked) System.out.println("Bokningen lyckades!");
                    else printBookingFailed(horse);

                    break;

                case 3:
                    System.out.println("Visa bokningskrav för att se om du kan kontakta ägaren!");
                    break;
            }

            if (askAbort()) return;
        }
    }

    public void cancelBooking() {

        while (true) {
            printHeader("Avboka häst");
            String horseName = enterAndValidateInput("Hästens namn:", "name");

            boolean canceled = false;
            boolean found = false;

            for (Horse horse : horses) {

                if (horse.getName().equalsIgnoreCase(horseName)) {
                    found = true;

                    if (horse instanceof Bookable && !(horse instanceof PrivateHorse)) {
                        canceled = ((Bookable) horse).cancelBooking();
                    } else if (horse instanceof PrivateHorse p) {

                        if (p.getOwner().isContactable()) {
                            System.out.println("Privatägd häst: Kontakta ägaren om du vill avboka!");
                        } else {
                            System.out.println("Privatägd häst kan inte avbokas via systemet.");
                        }
                    }
                    break;
                }
            }

            if (!found) {
                printHorseNotFound(horseName);
            } else if (!canceled && !(findHorse(horseName) instanceof PrivateHorse)) {
                printError("Du försöker avboka en häst som inte är bokad!");
            } else if (canceled) {
                System.out.println("Avbokningen lyckades!");
            }

            if (askAbort()) return;
        }
    }

    // ============================================================
    // FIND HORSE
    // ============================================================

    public Horse findHorse(String name) {
        for (Horse horse : horses) {
            if (horse.getName().equalsIgnoreCase(name)) return horse;
        }
        return null;
    }
}
