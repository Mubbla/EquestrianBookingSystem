import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HorseManager {

    public static void main(String[] args) {

        //Create horse data "Data base"
        Horse horse1 = new Pony("Diabolo", 14, 1.30);
        Horse horse2 = new FullSizeHorse("Blixten", 11,18);
        Horse horse3 = new PrivateHorse("Hoppla", 7, "Klara");

        //Create a predefined list of all horses available for booking
        ArrayList<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);

        //Console with menu
        Scanner scanner = new Scanner(System.in);
        int menuChoice = 0;
        while(menuChoice!= 6) {
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
            }
            catch (InputMismatchException e) {
                System.out.println("Fel: Du måste skriva in siffran för alternativet!");
                scanner.nextLine(); // delete the faulty value
                menuChoice = 0; // keep the loop running
            }
            if(menuChoice!=0) {
                switch (menuChoice) {
                    case 1:
                        listAllHorses(horses);
                        break;
                    case 2:
                        addHorseFromUserInput(horses, scanner);
                        //Välj typ: 1,2,3
                        //Type = input
                        //Mata in övriga data med ; mellan
                        //Konkatenera
                        //Skicka till addHorse
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
        }

    }//main

    //Horse Manager methods

    public static void listAllHorses(ArrayList<Horse> horses) {
        for (Horse horse : horses) {
            horse.showDetails();
        }
    }

    public static void addHorseFromUserInput(ArrayList<Horse> horses, Scanner scanner) {
        Horse horse = null;
        try {
            System.out.print("Namn: ");
            String name = scanner.nextLine();
            //Check name only contains letters and spaces, and is not empty
            while (!name.matches("[A-Za-zÅÄÖåäö ]+")||name.trim().isEmpty()) {
                System.out.println("Fel: Skriv in ett giltigt namn: ");
                name = scanner.nextLine();
            }

            System.out.print("Ålder: ");
            String age = scanner.nextLine();
            int ageInt = Integer.parseInt(age);
            while (ageInt<0) {
                System.out.println("Fel! Skriv in en giltig ålder: ");
                age = scanner.nextLine();
                ageInt = Integer.parseInt(age);
            }

            System.out.print("Typ (1=FullSize, 2=Pony, 3=Private): ");
            int typeChoice = scanner.nextInt();
            scanner.nextLine();
            while(typeChoice < 1 || typeChoice > 3){
                System.out.println("Fel! Välj Typ (1=FullSize, 2=Pony, 3=Private): ");
                typeChoice = scanner.nextInt();
                scanner.nextLine();
            }
//
            switch (typeChoice) {
                case 1:
                System.out.print("Åldersgräns för ryttaren: ");
                String ageLimit = scanner.nextLine();
                int ageLimitInt = Integer.parseInt(ageLimit);
                while (ageLimitInt<0) {
                    System.out.println("Fel! Skriv in en giltig åldersgräns: ");
                    ageLimit = scanner.nextLine();
                }
                horse = new FullSizeHorse(name, ageLimitInt, ageLimitInt);
                break;
                case 2:
                    System.out.print("Minsta tillåtna längd för ryttaren: ");
                    String lengthLimit = scanner.nextLine();
                    double lengthLimitDouble = Double.parseDouble(lengthLimit);
                    horse = new Pony(name, ageInt, lengthLimitDouble);
                    break;
                case 3:
                    System.out.print("Ägare: ");
                    String owner = scanner.nextLine();
                    horse = new PrivateHorse(name, ageInt, owner);
                    break;
            }
        }
        catch (InputMismatchException e) {
            System.out.println("Fel: Ålder eller typ måste vara en siffra.");
            scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Fel: Åldersgräns måste vara ett heltal, längd måste vara ett decimaltal.");
        }

        if (horse != null) {
            horses.add(horse);
            System.out.println("Hästen har registrerats!");
        }
    }

//    public static void addHorseToList( ArrayList<Horse> horses, String horseData){
//
//        int privateIndex = 0; // tracks the next owner in the privateOwners listfor (String row : horseData) {
//        String[] parts = horseData.split(";");
//
//        String name = parts[0];
//        int age = Integer.parseInt(parts[1]);
//        String type = parts[3];
//        switch (type) {
//            case "FullSize":
//                horses.add(new FullSizeHorse(name, age, true, parts[2]));
//                break;
//            case "Pony":
//                horses.add(new Pony(name, age, true, parts[2]));
//                break;
//            case "Private":
//                horses.add(new PrivateHorse(name, age, false, parts[2]));
//                break;
//            default:
//                System.out.println("Unknown type " + type + " entered.");
//                System.out.println("/**********************************************/\n");
//            }
//        }
}


