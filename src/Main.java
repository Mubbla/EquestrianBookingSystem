import java.util.ArrayList;

public class Main {
    static void main(String[] args) {

        String[] horseData =
               {"Tullah;14;true;Private",
                "Frans;8;true;Mini",
                "Dex;22;false;DPony",
                "Sture;18;true;Private",
                "Alen;15;true;FullSize",
                "Gareth;22;true;Private",
                "Gini;18;true;FullSize",
                "Mr Project;24;false;CPony",
                "Bosse;24;true;DPony",
                "Diabolo;14;true;DPony",
                "Mr Project;24;false;CPony",
                "Sigge;23;true;DPony",
                "Loppan;11;true;DPony",
                };
        //A predefined list of all horses available for booking
        ArrayList<Horse> horses = new ArrayList<>();
        for (String row : horseData) {
            String[] parts = row.split(";");

            String name = parts[0];
            int age = Integer.parseInt(parts[1]);
            boolean isBookable = Boolean.parseBoolean(parts[2]);
            String type = parts[3];
            switch (type) {
                case "FullSize":
                    horses.add(new FullSizeHorse(name, age, isBookable, 18));
                    break;

//                case "DPony":
//                    horses.add(new DPony(name, age, isBookable, 45));
//                    break;
//
//                case "CPony":
//                    horses.add(new CPony(name, age, isBookable, 45, 120));
//                    break;
//
//                case "Private":
//                    horses.add(ne


        }

            for (Horse horse : horses) {
                horse.ShowDetails();
            }


        }



    }//main

    //Validate data entered by user
    private boolean ValidateInput(){
            return true;
    }
}//class
