import java.util.ArrayList;

public class Main {
    static void main(String[] args) {

        String[] horseData =
               {
                "Mr Project;24;false;CPony",
                "Frans;8;true;CPony",
                "Blixten;12;false;CPony",
                "Dex;22;false;DPony",
                "Bosse;24;true;DPony",
                "Diabolo;14;true;DPony",
                "Hoppla;11;true;DPony",
                "Allan;15;true;FullSize",
                "Gini;18;true;FullSize",
                "Fingal;23;true;FullSize",
                "Sigge;23;true;FullSize",
                "Gareth;22;true;Private",
                "Sture;18;true;Private",
                "Tullah;14;true;Private",
                "Stellan;2;true;Mini"
                };

        String[] privateOwners = {
                "Bodil Olsson",
                "Klara Berg",
                "Magnus Svensson"
        };
        int privateIndex = 0; // tracks the next owner in the privateOwners list

        //A predefined list of all horses available for booking
        ArrayList<Horse> horses = new ArrayList<>();
        for (String row : horseData) {
            String[] parts = row.split(";");

            String name = parts[0];
            int age = Integer.parseInt(parts[1]);
            boolean isAvailable = Boolean.parseBoolean(parts[2]);
            String type = parts[3];
            switch (type) {
                case "FullSize":
                    horses.add(new FullSizeHorse(name, age, isAvailable, 18));
                    break;
                case "DPony":
                    horses.add(new DPony(name, age, isAvailable, 45));
                    break;
                case "CPony":
                    horses.add(new CPony(name, age, isAvailable,  120));
                    break;
                case "Private":
                    String owner = privateOwners[privateIndex];
                    horses.add(new PrivateHorse(name, age, isAvailable, owner));
                    privateIndex++;
                    break;
                default:
                    System.out.println("Unknown type "+ type + " entered.");
                    System.out.println("/**********************************************/\n");
            }
        }
        for (Horse horse : horses) {
            // Polymorphism: each subclass provides its own implementation of showDetails()
            horse.showDetails();
        }

    }

    //Validate data entered by user
    private boolean ValidateInput(){
            return true;
    }
}//class
