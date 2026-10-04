import java.util.ArrayList;
import java.util.Scanner;

public class HorseManager {

    public static void main(String[] args) {

        //Create horse data "data base"
        String[] horseData =
                {
                        "Diabolo;14;1.30;Pony",
                        "Hoppla;11;1.35;Pony",
                        "Blixten;15;18;FullSize",
                        "Fingal;23;14;FullSize",
                        "Gareth;22;Bodil;Private",
                        "Tullah;14;Klara;Private"
                };
        //A predefined list of all horses available for booking
        ArrayList<Horse> horses = new ArrayList<>();
        for (String row : horseData) {
            addHorseToList(horses, row);
        }

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
            menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    listAllHorses(horses);
                    break;
                case 2:
                    System.out.println("2");
                    //Välj typ: 1,2,3
                    //Type = input
                    //Mata in övriga data med ; mellan
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
        }

    }//main

    //Manager methods

    public static void listAllHorses(ArrayList<Horse> horses) {
        for (Horse horse : horses) {
            horse.showDetails();
        }
    }

    public static void addHorseToList( ArrayList<Horse> horses, String horseData){

        int privateIndex = 0; // tracks the next owner in the privateOwners listfor (String row : horseData) {
        String[] parts = horseData.split(";");

        String name = parts[0];
        int age = Integer.parseInt(parts[1]);
        //boolean isAvailable = Boolean.parseBoolean(parts[2]);
        String type = parts[3];
        switch (type) {
            case "FullSize":
                horses.add(new FullSizeHorse(name, age, true, 18));
                break;
            case "Pony":
                horses.add(new Pony(name, age, true, 45));
                break;
            case "Private":
                horses.add(new PrivateHorse(name, age, false, parts[3]));

                break;
            default:
                System.out.println("Unknown type " + type + " entered.");
                System.out.println("/**********************************************/\n");
            }
        }
}


