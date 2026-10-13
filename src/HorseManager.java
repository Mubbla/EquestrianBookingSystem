import java.util.ArrayList;
import java.util.Scanner;

public class HorseManager {

    public static void main(String[] args) {
        // Creates a bookingHandler for booking logic only
        BookingHandler bookingHandler = new BookingHandler();

        // Creates horse data "database"
        Horse horse1 = new Pony("Diabolo", 14, 1.30);
        Horse horse2 = new FullSizeHorse("Blixten", 11, 18);
        Horse horse3 = new PrivateHorse("Hoppla", 7, "Nisse", true);
        Horse horse4 = new PrivateHorse("Wille", 3, "Janne", false);

        // Creates a predefined list of all horses available for booking
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        horses.add(horse4);

        // Console with menu
        Scanner scanner = new Scanner(System.in);
        int menuChoice = 0;

        while (true) {
            System.out.println("\n=== ADMINISTRERA HÄSTAR ===");
            System.out.println("[1] Visa alla");
            System.out.println("[2] Lista alla bokningskrav");
            System.out.println("[3] Lägg till");
            System.out.println("[4] Ta bort");
            System.out.println("[5] Boka");
            System.out.println("[6] Avboka");
            System.out.println("[0] Avsluta programmet");

            menuChoice = Integer.parseInt(enterAndValidateInput(scanner, "Välj alternativ:", "menu"));

            if (menuChoice >= 0) {
                switch (menuChoice) {
                    case 1:
                        listAllHorses(horses);
                        break;
                    case 2:
                        listAllBookingRequirements(horses);
                        break;
                    case 3:
                        addHorseFromUserInput(horses, scanner);
                        break;
                    case 4:
                        deleteHorse(horses, scanner);
                        break;
                    case 5:
                        bookHorse(scanner, horses, bookingHandler);
                        break;
                    case 6:
                        cancelBooking(scanner, horses);
                        break;
                    default:
                        if (menuChoice == 0) {
                            System.out.println("Programmet avslutas.");
                            return;
                        }
                        System.out.println("Felaktigt värde!");
                }
            }
        }
    }

    // *******************   Horse Manager methods   ******************* //

    public static void listAllHorses(ArrayList<Horse> horses) {
        System.out.println("\n/**********************************************/\n");
        for (Horse horse : horses) {
            System.out.println(horse.showDetails());
        }
    }

    public static void listAllBookingRequirements(ArrayList<Horse> horses) {
        System.out.println("\n/************************************************************************************/\n");
        for (Horse horse : horses) {
            System.out.println(horse.getBookingRequirement());
        }
    }

    public static void addHorseFromUserInput(ArrayList<Horse> horses, Scanner scanner) {

        Horse horse = null;

        while (true) {
            printHeader("Hästinfo");
            String horseName = enterAndValidateInput(scanner, "Namn:", "name");
            int age = Integer.parseInt(enterAndValidateInput(scanner, "Ålder:", "age"));
            int typeChoiceInt = Integer.parseInt(
                    enterAndValidateInput(scanner, "Typ (1=Stor häst, 2=Ponny, 3=Privat):", "horsetype")
            );

            switch (typeChoiceInt) {
                case 1:
                    printHeader("Ryttarinfo");
                    int ageLimitInt = Integer.parseInt(
                            enterAndValidateInput(scanner, "Ange lägsta tillåtna ålder för ryttaren:", "age")
                    );
                    horse = new FullSizeHorse(horseName, age, ageLimitInt);
                    break;

                case 2:
                    printHeader("Ryttarinfo");
                    double minRiderLength = Double.parseDouble(
                            enterAndValidateInput(scanner, "Ange minsta tillåtna längd för ryttaren:", "length")
                    );
                    horse = new Pony(horseName, age, minRiderLength);
                    break;

                case 3:
                    printHeader("Ägarinfo");
                    String owner = enterAndValidateInput(scanner, "Ägarens namn:", "name");
                    boolean contactable;

                    while (true) {
                        System.out.print("Kan ägaren kontaktas? (ja/nej): ");
                        String input = scanner.nextLine().trim().toLowerCase();

                        if (input.equals("ja")) {
                            contactable = true;
                            break;
                        }

                        if (input.equals("nej")) {
                            contactable = false;
                            break;
                        }

                        System.out.println("Fel: Skriv 'ja' eller 'nej'.");
                    }

                    horse = new PrivateHorse(horseName, age, owner, contactable);
                    break;
            }

            if (horse != null) {
                horses.add(horse);
                System.out.println("Hästen har registrerats!");
            } else {
                System.out.println("Registreringen misslyckades. Försök igen!");
            }

            while (true) {
                System.out.print("Vill du fortsätta registrera? (ja/nej): ");
                String yesNo = scanner.nextLine().trim().toLowerCase();

                if (yesNo.equals("ja"))
                    break;

                if (yesNo.equals("nej"))
                    return;

                System.out.println("Fel: Skriv 'ja' eller 'nej'.");
            }
        }
    }

    public static void deleteHorse(ArrayList<Horse> horses, Scanner scanner) {

        while (true) {
            printHeader("Ta bort häst");
            String horseName = enterAndValidateInput(scanner,"Namn: ", "name");
            Horse found = findHorse(horseName, horses);

            if (found != null) {
                horses.remove(found);
                System.out.println(found.getName() + " har tagits bort.");
            } else {
                System.out.println("Ingen häst med namnet " + horseName + " kan hittas.");
            }

            while (true) {
                System.out.print("Vill du ta bort en till? (ja/nej): ");
                String yesNo = scanner.nextLine().trim().toLowerCase();

                if (yesNo.equals("ja")) break;
                if (yesNo.equals("nej")) return;

                System.out.println("Fel: Skriv 'ja' eller 'nej'.");
            }
        }
    }


    public static void bookHorse(Scanner scanner, ArrayList<Horse> horses, BookingHandler bookingHandler) {

        Horse horse;
        String horseName;
        boolean booked;

        while (true) {
            int typeChoice = Integer.parseInt(
                    enterAndValidateInput(scanner, "Typ (1=Stor häst, 2=Ponny, 3=Privat):", "horsetype")
            );

            switch (typeChoice) {
                case 1:
                    printHeader("Ryttarinfo");
                    int riderAgeInt = Integer.parseInt(
                            enterAndValidateInput(scanner, "Ålder:", "age")
                    );
                    printHeader("Hästinfo");
                    horseName = enterAndValidateInput(scanner, "Hästens namn:", "name");
                    horse = findHorse(horseName, horses);

                    if (horse == null) {
                        System.out.println("Det finns ingen häst med det namnet i systemet!");
                        break;
                    }

                    if (!(horse instanceof FullSizeHorse)) {
                        System.out.println(horse.getName() + " är en " + horse.getType() + ". Gör om ditt val!");
                        break;
                    }

                    if (!horse.isAvailable()) {
                        System.out.println(horse.getName() + " är redan bokad!");
                        break;
                    }

                    booked = bookingHandler.tryBooking(horse, Integer.toString(riderAgeInt));

                    if (booked) {
                        System.out.println("Bokningen lyckades!");
                    } else {
                        System.out.println("Bokningen misslyckades.");
                        System.out.println(horse.getBookingRequirement());
                    }

                    break;

                case 2:
                    printHeader("Ryttarinfo");
                    double riderLengthDouble = Double.parseDouble(
                            enterAndValidateInput(scanner, "Längd (meter):", "length")
                    );
                    printHeader("Hästinfo");
                    horseName = enterAndValidateInput(scanner, "Hästens namn:", "name");
                    horse = findHorse(horseName, horses);

                    if (horse == null) {
                        System.out.println("Det finns ingen häst med det namnet i systemet!");
                        break;
                    }

                    if (!(horse instanceof Pony)) {
                        System.out.println(horse.getName() + " är en " + horse.getType() + ". Gör om ditt val!");
                        break;
                    }

                    if (!horse.isAvailable()) {
                        System.out.println(horse.getName() + " är redan bokad!");
                        break;
                    }

                    booked = bookingHandler.tryBooking(horse, Double.toString(riderLengthDouble));

                    if (booked) {
                        System.out.println("Bokningen lyckades!");
                    } else {
                        System.out.println("Bokningen misslyckades.");
                        System.out.println(horse.getBookingRequirement());
                    }

                    break;

                case 3:
                    System.out.println("Visa bokningskrav för att se om du kan kan kontakta ägaren!");
                    break;
            }

            while (true) {
                System.out.print("Vill du fortsätta boka? (ja/nej): ");
                String yesNo = scanner.nextLine().trim().toLowerCase();

                if (yesNo.equals("ja"))
                    break;

                if (yesNo.equals("nej"))
                    return;

                System.out.println("Fel: Skriv 'ja' eller 'nej'.");
            }
        }
    }

    public static void cancelBooking(Scanner scanner, ArrayList<Horse> horses){

        while (true) {
            printHeader("Avboka häst");
            String horseName = enterAndValidateInput(scanner, "Hästens namn:", "name");
            boolean canceled = false;
            boolean found = false;

            for (Horse horse : horses) {

                if (horse.getName().equalsIgnoreCase(horseName)) {
                    found = true;

                    if (horse instanceof Bookable && !(horse instanceof PrivateHorse)) {
                        canceled = ((Bookable) horse).cancelBooking();
                    }
                    else if (horse instanceof PrivateHorse) {
                        PrivateHorse p = (PrivateHorse) horse;

                        if (p.ownerContactable()) {
                            System.out.println("Privatägd häst: Kontakta ägaren om du vill avboka!");
                        } else {
                            System.out.println("Privatägd häst kan inte avbokas via systemet.");
                        }

                        // hoppa över resten men fortsätt loopen
                        break;
                    }

                    break;
                }
            }

            if (!found) {
                System.out.println("Det finns ingen häst med det namnet i systemet!");
            } else if (!canceled && !(findHorse(horseName, horses) instanceof PrivateHorse)) {
                System.out.println("Du försöker avboka en häst som inte är bokad!");
            } else if (canceled) {
                System.out.println("Avbokningen lyckades!");
            }

            while (true) {
                System.out.print("Vill du avboka en till? (ja/nej): ");
                String yesNo = scanner.nextLine().trim().toLowerCase();

                if (yesNo.equals("ja")) break;
                if (yesNo.equals("nej")) return;

                System.out.println("Fel: Skriv 'ja' eller 'nej'.");
            }
        }
    }

    public static String enterAndValidateInput(Scanner scanner, String prompt, String type) {

        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();

            if (type.equals("menu")) {
                if (!input.matches("[0-9]+")) {
                    System.out.println("Fel: Försök med en siffra från menyn!");
                    continue;
                }
                return input;
            }

            if (type.equals("name")) {
                if (input.matches("[A-Za-zÅÄÖåäö ]+") && !input.trim().isEmpty()) {
                    return input;
                }
                System.out.println("Fel: Skriv in ett giltigt namn (endast bokstäver och mellanslag).");
                continue;
            }

            try {
                double value = Double.parseDouble(input);

                if (type.equals("age")) {
                    if (value < 0) {
                        System.out.println("Fel: Ålder måste vara 0 eller högre.");
                        continue;
                    }
                    return input;
                }

                if (type.equals("length")) {
                    if (value <= 0) {
                        System.out.println("Fel: Orimlig längd.");
                        continue;
                    }
                    return input;
                }

                if (type.equals("horsetype")) {
                    int intValue = (int) value;
                    if (intValue >= 1 && intValue <= 3) {
                        return input;
                    }
                    System.out.println("Fel: Försök med ett nummer på listan.");
                    continue;
                }

            } catch (NumberFormatException e) {

                if (type.equals("age"))
                    System.out.println("Fel: Ålder måste vara ett heltal.");
                else if (type.equals("length"))
                    System.out.println("Fel: Längd måste vara ett decimaltal.");
                else if (type.equals("horsetype"))
                    System.out.println("Fel: Försök med ett nummer i listan!");
            }
        }
    }

    public static Horse findHorse(String name, ArrayList<Horse> horses) {
        for (Horse horse : horses) {
            if (horse.getName().equalsIgnoreCase(name)) {
                return horse;
            }
        }
        return null;
    }

    public static void printHeader(String title) {
        System.out.println("== " + title + " ==");
    }
}