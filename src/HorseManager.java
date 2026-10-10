import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HorseManager {

    public static void main(String[] args) {
        //Creates a bookingEngine that handles booking logic
        BookingHandling bookingEngine = new BookingHandling();

        //Creates horse data "Data base"
        Horse horse1 = new Pony("Diabolo", 14, 1.30);
        Horse horse2 = new FullSizeHorse("Blixten", 11, 18);
        Horse horse3 = new PrivateHorse("Hoppla", 7, "Nisse", true);

        //Creates a predefined list of all horses available for booking
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        //Console with menu
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
            menuChoice = Integer.parseInt(enterAndValidateInput(scanner, "Välj alternativ: ", "menu"));
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
                            bookHorse(scanner, horses, bookingEngine);
                            break;
                        case 6:
                            cancelBooking(scanner, horses);
                            break;
                        default:
                            if(menuChoice==0) {
                                System.out.println("Programmet avslutas. Hejdå!");
                                return; //Quits while
                            }
                            System.out.println("Felaktigt värde!");
                    }
                }//if
        }//while menuChoice

    }//main

    //*******************   Horse Manager methods   *******************//

    public static void listAllHorses(ArrayList<Horse> horses) {

        System.out.println("\n/**********************************************/\n");
        for (Horse horse : horses) {
            System.out.println(horse.showDetails());
        }

    }

    public static void listAllBookingRequirements(ArrayList<Horse> horses) {

        for (Horse horse : horses) {
            System.out.println(horse.getBookingRequirement());
        }

    }

    public static void addHorseFromUserInput(ArrayList<Horse> horses, Scanner scanner) {

        Horse horse = null;
        while(true) {
//            String horseName = enterAndValidateName(scanner);
//            int age = enterAndValidateAgeInput(scanner);
//            int typeChoiceInt = enterAndValidateHorseType(scanner);
            System.out.println("== Hästinfo ==");
            String horseName = enterAndValidateInput(scanner, "Namn:", "name");
            int age = Integer.parseInt(enterAndValidateInput(scanner, "Ålder:", "age"));
            int typeChoiceInt = Integer.parseInt(enterAndValidateInput(scanner,
                    "Typ (1=Stor häst, 2=Ponny, 3=Privat):", "horsetype"));


            switch (typeChoiceInt) {
                case 1:
                    System.out.println("== Ryttarinfo ==");
                    int ageLimitInt = Integer.parseInt(
                            enterAndValidateInput(scanner, "Ange lägsta tillåtna ålder för ryttaren:", "age")
                    );

                    horse = new FullSizeHorse(horseName, age, ageLimitInt);
                    break;
                case 2:
                    System.out.println("== Ryttarinfo ==");
                    double minRiderLength = Double.parseDouble(
                            enterAndValidateInput(scanner, "Ange minsta tillåtna längd för ryttaren:", "length")
                    );

                    horse = new Pony(horseName, age, minRiderLength);
                    break;

                case 3:
                    System.out.println("== Ägarinfo ==");

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
                    break;      // breaks current while
                if (yesNo.equals("nej"))
                    return;     // breaks the method
                System.out.println("Fel: Skriv 'ja' eller 'nej'.");
            }
        }
    }//addHorse

    public static void deleteHorse(ArrayList<Horse> horses, Scanner scanner) {

        String horseName = enterAndValidateInput(scanner,"Namn: ", "name");
                //enterAndValidateName(scanner);

        Horse found = findHorse(horseName, horses);

        if (found != null) {
            horses.remove(found);
            System.out.println(found.getName() + " har tagits bort.");
        } else {
            System.out.println("Ingen häst med namnet " + horseName + " kan hittas.");
        }

    }//deleteHorse

    public static void bookHorse(Scanner scanner, ArrayList<Horse> horses, BookingHandling bookingEngine) {

        Horse horse = null;
        String horseName;
        boolean booked = false;

        //Continues until user ends booking
        while(true) {
            int typeChoice = Integer.parseInt(
                    enterAndValidateInput(scanner, "Typ (1=Stor häst, 2=Ponny, 3=Privat):", "horsetype"));

            switch (typeChoice) {

                case 1: // FullSizeHorse
                    System.out.println("== Ryttarinfo ==");
                    int riderAgeInt = Integer.parseInt(
                            enterAndValidateInput(scanner, "Ålder:", "age")
                    );

                    System.out.println("== Hästinfo ==");
                    horseName = enterAndValidateInput(scanner, "Hästens namn:", "name");
                    horse = findHorse(horseName, horses);

                    if (horse != null) {
                        booked = bookingEngine.tryBooking(horse, Integer.toString(riderAgeInt));
                    } else {
                        System.out.println("Det finns ingen häst med det namnet i systemet!");
                    }
                    break;


                case 2: // Pony
                    System.out.println("== Ryttarinfo ==");
                    double riderLengthDouble = Double.parseDouble(
                            enterAndValidateInput(scanner, "Längd (meter):", "length")
                    );

                    System.out.println("== Hästinfo ==");
                    horseName = enterAndValidateInput(scanner, "Hästens namn:", "name");
                    horse = findHorse(horseName, horses);

                    if (horse instanceof Pony) {
                        booked = bookingEngine.tryBooking(horse, Double.toString(riderLengthDouble));
                    } else if (horse != null) {
                        System.out.println(horseName + " är en " + horse.getType() + ". Gör om ditt val!");
                    } else {
                        System.out.println("Det finns ingen häst med det namnet i systemet!");
                    }
                    break;


                case 3: // PrivateHorse
                    System.out.println("Privata hästar kan inte bokas via systemet.");
                    break;
            }
            while (true) {
                System.out.print("Vill du fortsätta boka? (ja/nej): ");
                String yesNo = scanner.nextLine().trim().toLowerCase();
                if (yesNo.equals("ja"))
                    break;      // breaks current while
                if (yesNo.equals("nej"))
                    return;     // breaks the method
                System.out.println("Fel: Skriv 'ja' eller 'nej'.");
            }//while 2
        }//while 1
    }//bookHorse

    public static void cancelBooking(Scanner scanner, ArrayList<Horse> horses){

        System.out.println("== Hästinfo ==");
        String horseName = enterAndValidateInput(scanner, "Hästens namn:", "name");
        boolean canceled = false;

        for (Horse horse : horses) {

            if (horse.getName().equalsIgnoreCase(horseName)) {

                if (horse instanceof FullSizeHorse)
                    canceled = ((FullSizeHorse) horse).cancelBooking();

                else if (horse instanceof Pony)
                    canceled = ((Pony) horse).cancelBooking();

                else if (horse instanceof PrivateHorse)
                    System.out.println("Privatägd häst: Kontakta ägaren om du vill avboka!");
            }
        }

        if (canceled)
            System.out.println("Avbokningen lyckades!");
        else
            System.out.println("Du försöker avboka en häst som inte är bokad!");

    }//cancelBooking

    public static String enterAndValidateInput(Scanner scanner, String prompt, String type) {

        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();

            //menu
            if (type.equals("menu")) {
                if (!input.matches("[0-9]+")) {
                    System.out.println("Fel: Försök med en siffra från menyn!");
                    continue; // ask again
                }
                return input; // valid input
            }

            // name
            if (type.equals("name")) {
                if (input.matches("[A-Za-zÅÄÖåäö ]+") && !input.trim().isEmpty()) {
                    return input;
                }
                System.out.println("Fel: Skriv in ett giltigt namn (endast bokstäver och mellanslag).");
                continue;
            }

            // numbers (age, length, horsetype)
            try {
                double value = Double.parseDouble(input);

                //age
                if (type.equals("age")) {
                    if (value < 0) {
                        System.out.println("Fel: Ålder måste vara 0 eller högre.");
                        continue;
                    }
                    return input;
                }

                //length
                if (type.equals("length")) {
                    if (value <= 0) {
                        System.out.println("Fel: Längd ska vara större än 0!");
                        continue;
                    }
                    return input;
                }

                // Horse type (1–3)
                if (type.equals("horsetype")) {
                    int intValue = (int) value;
                    if (intValue >= 1 && intValue <= 3) {
                        return input;
                    }
                    System.out.println("Fel: Välj mellan 1–3.");
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
    }//enterAndValidateInput

    public static Horse findHorse (String name, ArrayList<Horse> horses) {

        for (Horse horse : horses) {
            if (horse.getName().equalsIgnoreCase(name)) {
                return horse;
            }
        }
        return null;

    }//findHorse

    public static void printHeader(String title) {
        System.out.println("== " + title + " ==");
    }

}//class


