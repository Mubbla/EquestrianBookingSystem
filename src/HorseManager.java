import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HorseManager {

    public static void main(String[] args) {

        //Create horse data "Data base"
        Horse horse1 = new Pony("Diabolo", 14, 1.30);
        Horse horse2 = new FullSizeHorse("Blixten", 11, 18);
        Horse horse3 = new PrivateHorse("Hoppla", 7, "Nisse", true);

        //Create a predefined list of all horses available for booking
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        //Console with menu
        Scanner scanner = new Scanner(System.in);
        int menuChoice = 0;
        while (true) {
            System.out.println("\n=== ADMINISTRERA HÄSTAR ===");
            System.out.println("1. Visa alla");
            System.out.println("2. Lista alla bokningskrav");
            System.out.println("3. Lägg till");
            System.out.println("4. Ta bort");
            System.out.println("5. Boka");
            System.out.println("6. Avboka");
            System.out.println("7. Avsluta");
            System.out.print("Vad vill du göra? Mata in siffran: ");
            try {
                menuChoice = scanner.nextInt();
                scanner.nextLine();// consume leftover newline from nextInt()
            } catch (InputMismatchException e) {
                System.out.println("Du måste skriva in siffran för alternativet!");
                scanner.nextLine(); // delete the faulty value
                menuChoice = 0; // keep the loop running
            }
            if (menuChoice != 0) {
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
                        bookHorse(scanner, horses);
                        break;
                    case 6:
                        cancelBooking(scanner, horses);
                        break;
                    case 7:
                        //Quits program
                        System.out.println("Programmet avslutas. Hejdå!");
                        return; //Quits while
                    default:
                        System.out.println("Felaktigt värde!");
                }
            }//if
        }//while menuChoice

    }//main

    //*******************   Horse Manager methods   *******************//

    public static void listAllHorses(ArrayList<Horse> horses) {

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
            String horseName = enterAndValidateName(scanner);
            int age = enterAndValidateAgeInput(scanner);
            int typeChoiceInt = enterAndValidateHorseType(scanner);

            switch (typeChoiceInt) {
                case 1:
                    System.out.print("Ange lägsta tillåtna ålder för ryttaren\n");
                    int ageLimitInt = enterAndValidateAgeInput(scanner);
                    horse = new FullSizeHorse(horseName, age, ageLimitInt);
                    break;
                case 2:
                    System.out.print("Ange minsta tillåtna längd för ryttaren\n");
                    double minRiderLength = enterAndValidateLengthInput(scanner);//
                    horse = new Pony(horseName, age, minRiderLength);
                    break;
                case 3:
                    System.out.println(" == Ägarinfo == ");
                    String owner = enterAndValidateName(scanner);//
                    String contactInput;

                    while (true) {
                        System.out.print("Kan ägaren kontaktas? (ja/nej): ");
                        contactInput = scanner.nextLine().trim().toLowerCase();

                        if (contactInput.equals("ja") || contactInput.equals("nej")) {
                            break;
                        }
                        System.out.println("Fel: Skriv 'ja' eller 'nej'.");
                    }

                    boolean isContactable = contactInput.equals("ja");
                    horse = new PrivateHorse(horseName, age, owner, isContactable);
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

        String horseName = enterAndValidateName(scanner);

        Horse found = findHorse(horseName, horses);

        if (found != null) {
            horses.remove(found);
            System.out.println(found.getName() + " har tagits bort.");
        } else {
            System.out.println("Ingen häst med namnet " + horseName + " kan hittas.");
        }

    }//deleteHorse

    public static void bookHorse(Scanner scanner, ArrayList<Horse> horses) {

        Horse horse = null;
        String horseName;
        String riderLength;
        boolean booked = false;

        //Continues until user ends booking
        while(true) {
            int typeChoice = enterAndValidateHorseType(scanner);

            switch (typeChoice) {

                case 1:
                    System.out.println("== Ryttarinfo ==");
                    int riderAgeInt = enterAndValidateAgeInput(scanner);
                    System.out.println("== Hästinfo ==");
                    horseName = enterAndValidateName(scanner);
                    horse = findHorse(horseName, horses);

                    if (horse instanceof FullSizeHorse) {
                        if (horse.isAvailable())
                            booked = ((FullSizeHorse) horse).book(Integer.toString(riderAgeInt));
                        else
                            System.out.println( horse.getName() + "är tyvärr redan bokad!");
                        if (booked)
                            System.out.println("Bokningen lyckades!");
                        else {
                            System.out.println("Bokningen misslyckades.");
                            System.out.println(horse.getBookingRequirement());
                        }
                    } else
                        System.out.println("Det finns ingen häst med det namnet i systemet!");
                    break;
                case 2:
                    System.out.println("== Ryttarinfo ==");
                    double riderLengthDouble = enterAndValidateLengthInput(scanner);
                    System.out.println("== Hästinfo ==");
                    horseName = enterAndValidateName(scanner);
                    horse = findHorse(horseName, horses);

                    if (horse instanceof Pony) {
                        if (horse.isAvailable())
                            booked = ((Pony) horse).book(Double.toString(riderLengthDouble));
                        else
                           System.out.println( horse.getName() + "är tyvärr redan bokad!");
                        if (booked)
                            System.out.println("Bokningen lyckades!");
                        else {
                            System.out.println("Bokningen misslyckades.");
                            System.out.println(horse.getBookingRequirement());
                        }
                    } else if(horse != null)
                        System.out.println(horseName +" är en " + horse.getType() + "Gör om ditt val!");
                    break;
                case 3:
                    System.out.println("Privata hästar kan inte bokas via systemet. ");
                    break;
            }//switch

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
        String horseName = enterAndValidateName(scanner);
        boolean canceled = false;

        for(Horse horse: horses){
            if(horse.getName().equals(horseName)){
                if(horse instanceof FullSizeHorse)
                    canceled= ((FullSizeHorse)horse).cancelBooking();
                if(horse instanceof Pony)
                    canceled= ((Pony)horse).cancelBooking();
                if(horse instanceof PrivateHorse)
                    System.out.println("Privatägd häst: Kontakta ägaren om du vill avboka!");
            }
        }
        if(canceled)
            System.out.println("Avbokningen lyckades!");
        else
            System.out.println("Du försöker avboka en häst som inte är bokad!");

    }//cancelBooking

    public static int enterAndValidateHorseType(Scanner scanner){

        //Enter horse type and validate input
        int typeChoiceInt = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Typ (1=Stor häst, 2=Ponny, 3=Privat): ");
            String typeChoice = scanner.nextLine();
            try {
                typeChoiceInt = Integer.parseInt(typeChoice);
                if (typeChoiceInt >= 1 && typeChoiceInt <= 3) {
                    validInput = true;
                } else {
                    System.out.println("Fel! Välj mellan 1-3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Försök med ett nummer i listan!");
            }
        }
        return typeChoiceInt;

    }//validate type

    public static int enterAndValidateAgeInput(Scanner scanner){

        int ageInt = -1;
        while (ageInt < 0) {
            try {
                System.out.print("Ålder: ");
                ageInt = Integer.parseInt(scanner.nextLine());

                if (ageInt < 0) {
                    System.out.println("Fel: Ålder måste vara 0 eller högre.");
                    ageInt = -1; // Reset to continue loop
                }
            } catch (NumberFormatException e) {
                System.out.println("Fel: Ålder måste vara ett heltal.");
                ageInt = -1; // Reset to continue loop
            }
        }
        return ageInt;

    }//validate age

    public static double enterAndValidateLengthInput(Scanner scanner){

        double lengthDouble = -1;
        while (lengthDouble < 0) {
            try {
                System.out.print("Längd (meter): ");
                lengthDouble = Double.parseDouble(scanner.nextLine());

                if (lengthDouble <= 0.0) {
                    System.out.println("Längd måste vara större än 0!");
                    lengthDouble = -1; // Reset to continue loop
                }
            } catch (NumberFormatException e) {
                System.out.println("Längd måste vara ett decimaltal!");
                lengthDouble = -1; // Reset to continue loop
            }
        }
        return lengthDouble;

    }//validate length

    public static String enterAndValidateName(Scanner scanner){

        System.out.print("Namn: ");
        String name = scanner.nextLine();
        //Check name only contains letters and spaces, and is not empty
        while (!name.matches("[A-Za-zÅÄÖåäö ]+") || name.trim().isEmpty()) {
            System.out.println("Fel: Skriv in ett giltigt namn: ");
            name = scanner.nextLine();
        }
        return name;

    }//enterAndValidateName

    public static Horse findHorse (String name, ArrayList<Horse> horses) {

        for (Horse horse : horses) {
            if (horse.getName().equalsIgnoreCase(name)) {
                return horse;
            }
        }
        return null;

    }//findHorse

}//class


