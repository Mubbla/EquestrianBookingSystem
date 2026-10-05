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
                System.out.println("Fel: Du måste skriva in siffran för alternativet!");
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
                        System.out.println("4");
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

        //Enter name and validate input
        System.out.print("Namn: ");
        String name = scanner.nextLine();
        //Check name only contains letters and spaces, and is not empty
        while (!name.matches("[A-Za-zÅÄÖåäö ]+") || name.trim().isEmpty()) {
            System.out.println("Fel: Skriv in ett giltigt namn: ");
            name = scanner.nextLine();
        }

        //Enter age and validate input
        int ageInt = -1;
        System.out.print("Ålder: ");
        String age = scanner.nextLine();
        try {
            ageInt = Integer.parseInt(age);
            while (ageInt < 0) {
                System.out.println("Fel! Skriv in en giltig ålder: ");
                age = scanner.nextLine();
                ageInt = Integer.parseInt(age);
            }
        } catch(NumberFormatException e){
            System.out.println("Exception: Ålder måste vara ett heltal.");
        }

        //Enter horse type and validate input
        int typeChoiceInt = -1;

        System.out.print("Typ (1=FullSize, 2=Pony, 3=Private): ");
        String typeChoice = scanner.nextLine();
        try {
            typeChoiceInt = Integer.parseInt(typeChoice);
            while (typeChoiceInt < 1 || typeChoiceInt > 3) {
                System.out.println("Fel! Välj Typ (1=FullSize, 2=Pony, 3=Private): ");
                typeChoice = scanner.nextLine();
                typeChoiceInt = Integer.parseInt(typeChoice);
            }
        } catch (InputMismatchException e){
            System.out.println("Fel! Exception! på TYP");
        }

        switch (typeChoiceInt) {
            case 1:
                //Enter age limit and validate input
                int ageLimitInt = -1;
                while (ageLimitInt < 0) {
                    try {
                        System.out.print("Åldersgräns för ryttaren: ");
                        ageLimitInt = Integer.parseInt(scanner.nextLine());

                        if (ageLimitInt < 0) {
                            System.out.println("Fel: Åldersgräns måste vara 0 eller högre.");
                            ageLimitInt = -1; // Reset to continue loop
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Fel: Åldersgräns måste vara ett heltal.");
                        ageLimitInt = -1; // Reset to continue loop
                    }
                }
                horse = new FullSizeHorse(name, ageInt, ageLimitInt);
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
                horse = new Pony(name, ageInt, lengthLimitDouble);
                break;
            case 3:
                System.out.print("Ägare: ");
                String owner = scanner.nextLine();
                //Check owner name only contains letters and spaces, and is not empty
                while (!owner.matches("[A-Za-zÅÄÖåäö ]+") || owner.trim().isEmpty()) {
                    System.out.println("Fel: Skriv in ett giltigt namn: ");
                    owner = scanner.nextLine();
                }
                System.out.print("Kan ägaren kontaktas? (ja/nej): ");
                String contactInput = scanner.nextLine().trim().toLowerCase();

                while (!(contactInput.equals("ja") || contactInput.equals("nej"))) {
                    System.out.println("Fel: Skriv 'ja' eller 'nej'.");
                    System.out.print("Kan ägaren kontaktas? (ja/nej): ");
                    contactInput = scanner.nextLine().trim().toLowerCase();
                }

                boolean isContactable = contactInput.equals("ja");
                horse = new PrivateHorse(name, ageInt, owner, isContactable);
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

}//class


