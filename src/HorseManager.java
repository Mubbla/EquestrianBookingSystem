import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HorseManager {

    public static void main(String[] args) {

        //Create horse data "Data base"
        Horse horse1 = new Pony("Diabolo", 14, 1.30);
        Horse horse2 = new FullSizeHorse("Blixten", 11, 18);
        Horse horse3 = new PrivateHorse("Hoppla", 7, "Klara", true);

        //Create a predefined list of all horses available for booking
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        //Console with menu
        Scanner scanner = new Scanner(System.in);
        int menuChoice = 0;
        while (menuChoice != 6) {
            System.out.println("\n=== ADMINISTRERA HÄSTAR ===");
            System.out.println("1. Visa alla");
            System.out.println("2. Lägg till");
            System.out.println("3. Ta bort");
            System.out.println("4. Boka");
            System.out.println("5. Avboka");
            System.out.println("6. Avsluta");
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
                        addHorseFromUserInput(horses, scanner);
                        break;
                    case 3:
                        System.out.println("3");
                        break;
                    case 4:
                        bookHorse(scanner, horses);
                        break;
                    case 5:
                        System.out.println("5");
                        break;
                    case 6:
                        System.out.println("Programmet avslutas. Hejdå!");
                        break;
                    default:
                        System.out.println("Felaktigt värde!");
                }
            }//if
        }//while menuChoice

    }//main

    //Horse Manager methods

    public static void listAllHorses(ArrayList<Horse> horses) {

        for (Horse horse : horses) {
            horse.showDetails();
        }

    }

    public static void addHorseFromUserInput(ArrayList<Horse> horses, Scanner scanner) {

        Horse horse = null;

        String horseName = enterAndValidateName(scanner);
        Integer ageInt = enterAndValidateAgeInput(scanner);
        int typeChoiceInt = enterAndValidateHorseType(scanner);

        switch (typeChoiceInt) {
            case 1:
                Integer ageLimitInt = enterAndValidateAgeInput(scanner);
                horse = new FullSizeHorse(horseName, ageInt, ageLimitInt);
                break;
            case 2:
                //Enter length limit and validate input
                double lengthLimitDouble = -1;
                while (lengthLimitDouble < 0) {
                    try {
                        System.out.print("Minsta tillåtna längd för ryttaren: ");
                        lengthLimitDouble = Double.parseDouble(scanner.nextLine());

                        if (lengthLimitDouble < 0) {
                            System.out.println("Fel: Längd måste vara 0 eller högre.");
                            lengthLimitDouble = -1;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Fel: Längd måste vara ett decimaltal.");
                        lengthLimitDouble = -1;
                    }
                }
                horse = new Pony(horseName, ageInt, lengthLimitDouble);
                break;
            case 3:
                System.out.println(" == Ägarinfo == ");
                String owner = enterAndValidateName(scanner);

                System.out.print("Kan ägaren kontaktas? (ja/nej): ");
                String contactInput = scanner.nextLine().trim().toLowerCase();

                while (!(contactInput.equals("ja") || contactInput.equals("nej"))) {
                    System.out.println("Fel: Skriv 'ja' eller 'nej'.");
                    System.out.print("Kan ägaren kontaktas? (ja/nej): ");
                    contactInput = scanner.nextLine().trim().toLowerCase();
                }

                boolean isContactable = contactInput.equals("ja");
                horse = new PrivateHorse(horseName, ageInt, owner, isContactable);
                break;
        }

        if (horse != null) {
            horses.add(horse);
            System.out.println("Hästen har registrerats!");
        }
        else{
            System.out.println("Registreringen misslyckades. Försök igen!");
        }
    }//addHorse

    public static void bookHorse(Scanner scanner, ArrayList<Horse> horses) {

        Horse horse = null;
        String horseName;
        String riderLength;
        boolean booked = false;
        boolean validInput = false;

        int typeChoice = enterAndValidateHorseType(scanner);

        switch (typeChoice){
            case 1:
                System.out.println("== Ryttarinfo ==");
                Integer riderAgeInt = enterAndValidateAgeInput(scanner);
                System.out.println("== Hästinfo ==");
                horseName = enterAndValidateName(scanner);
                horse = findHorse(horseName, horses);

                if(horse != null){
                    if (horse instanceof FullSizeHorse && horse.isAvailable())
                        booked = ((FullSizeHorse) horse).book(Integer.toString(riderAgeInt));
                     else
                        System.out.println( horseName + " kan tyvärr inte bokas."
                        + "\nFörsök igen!");
                    if(booked)
                        System.out.println("Bokningen lyckades!");
                    else
                        System.out.println("Bokningen misslyckades. \nDu måste vara minst " + horse.getAge() + " år för att boka denna häst.");
                }
                else
                    System.out.println("Det finns ingen häst med det namnet i systemet!");
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    public static int enterAndValidateHorseType(Scanner scanner){

        //Enter horse type and validate input
        int typeChoiceInt = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Typ (1=FullSize, 2=Pony, 3=Private): ");
            String typeChoice = scanner.nextLine();
            try {
                typeChoiceInt = Integer.parseInt(typeChoice);
                if (typeChoiceInt >= 1 && typeChoiceInt <= 3) {
                    validInput = true;
                } else {
                    System.out.println("Fel! Välj mellan 1-3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nu blev det fel! Försök med ett nummer i listan!");
            }
        }

        return typeChoiceInt;

    }//validate type

    public static Integer enterAndValidateAgeInput(Scanner scanner){

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

    public static String enterAndValidateName(Scanner scanner){

        System.out.print("Namn: ");
        String name = scanner.nextLine();
        //Check name only contains letters and spaces, and is not empty
        while (!name.matches("[A-Za-zÅÄÖåäö ]+") || name.trim().isEmpty()) {
            System.out.println("Fel: Skriv in ett giltigt namn: ");
            name = scanner.nextLine();
        }
        return name;
    }

    public static Horse findHorse (String name, ArrayList<Horse> horses) {

        for (Horse horse : horses) {
            if (horse.getName().equals(name)) {
                return horse;
            }
        }
        return null;

    }

}//class


