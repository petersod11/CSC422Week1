package com.mycompany.petapp;
//Derik Peterson
//CSC422
//May 16 2021
//Week 1 Pet names
//Milestone 21 add pet and view table

//Import all nessecarry extentions

import java.util.ArrayList;
import java.util.Scanner;
//start of main class
public class MyyApp {
//instance variables
private String _name;
private int _age;

//constructor
public MyyApp(String name, int age) { 
    this._name = name; 
    this._age = age; 
} 

//getters and setters
public String getName() {
    return _name;
}
public void setName(String newName) {
    this._name = newName;
}

public int getAge() {
    return _age;
}
public void setAge(int newAge) {
    this._age = newAge;
}

public static void displayChoices(ArrayList<MyyApp> petRecords) {
    Scanner reader = new Scanner(System.in);
    System.out.println();
    System.out.println("What would you like to do?");
    System.out.println("1) View all pets");
    System.out.println("2) Add more pets");
    System.out.println("3) Update an existing pet");
    System.out.println("4) Remove an existing pet");
    System.out.println("5) Search pets by name");
    System.out.println("6) Search pets by age");
    System.out.println("7) Exit Program");
    
    int choice = reader.nextInt();
    System.out.println("Your choice: " + choice);
    
    launchChoice(choice, petRecords);
}

public static void viewPets(ArrayList<MyyApp> petRecords, int nextStep) {
    System.out.println();
    //Header
    String divider = "+----------------------+";
    System.out.println(divider);
    System.out.println("| ID | NAME      | AGE |");
    System.out.println(divider);
    //List
    for (MyyApp pr : petRecords) {
        System.out.printf("| %2d | %9s | %3d |\n", petRecords.indexOf(pr), pr.getName(), pr.getAge());
    }
    //Footer
    System.out.println(divider + "\n");
    System.out.println(petRecords.size() + " rows in set.\n");
    switch(nextStep) {
        case 0:
            addPets(petRecords);

        default:
            displayChoices(petRecords);
    }
}

public static void viewPetsFilteredByName(ArrayList<MyyApp> petRecords, String petName) {
    int filteredResultCount = 0;
    System.out.println();
    //Header
    String divider = "+----------------------+";
    System.out.println(divider);
    System.out.println("| ID | NAME      | AGE |");
    System.out.println(divider);
    //List
    for (MyyApp pr : petRecords) {
        if(pr.getName().equals(petName)) {
            filteredResultCount ++;
            System.out.printf("| %2d | %9s | %3d |\n", petRecords.indexOf(pr), pr.getName(), pr.getAge());
        }
    }
    //Footer
    System.out.println(divider + "\n");
    System.out.println(filteredResultCount + " rows in set.\n");
    displayChoices(petRecords);
}

public static void viewPetsFilteredByAge(ArrayList<MyyApp> petRecords, int petAge) {
    int filteredResultCount = 0;
    System.out.println();
    //Header
    String divider = "+----------------------+";
    System.out.println(divider);
    System.out.println("| ID | NAME      | AGE |");
    System.out.println(divider);
    //List
    for (MyyApp pr : petRecords) {
        if(pr.getAge() == petAge) {
            filteredResultCount ++;
            System.out.printf("| %2d | %9s | %3d |\n", petRecords.indexOf(pr), pr.getName(), pr.getAge());                
        }
    }
    //Footer
    System.out.println(divider + "\n");
    System.out.println(filteredResultCount + " rows in set.\n");
            displayChoices(petRecords);
}

public static void addPets(ArrayList<MyyApp> petRecords) {
    if(petRecords.size()<5) {
        Scanner reader = new Scanner(System.in);
        boolean valid = false;
        String petName = "";
        while(!valid) {
            System.out.println("Type the pet's name and press ENTER: ");
            petName = reader.next();
            if(!petName.isEmpty()) {
                valid = true;
            }
        }
        boolean tooOld = true;
        int petAge = 0;
        while(tooOld) {
            System.out.println("Type the pet's age (as a number in range 1-20) and press ENTER: ");
            try {
                String strPetAge = reader.next();
                petAge = Integer.parseInt(strPetAge);
                if(petAge <= 20) {
                    tooOld = false;
                } else {
                    System.out.println("Must be a number in range 1-20. Please try again.");
                }
            } catch (NumberFormatException e){
                System.out.println("Must be a number in range 1-20. Please try again. (CATCH)");
            }
        }
        petRecords.add( new MyyApp(petName, petAge) );

        System.out.println("Please choose one option.");
        System.out.println("1) Add another pet");
        System.out.println("0) Return to main menu");
        int answer = reader.nextInt();
        if(answer == 1) {
            viewPets(petRecords, 0);
        } else {
            displayChoices(petRecords);
        }
    } else {
        System.out.println("Limit of 5 pets has been reached. Please remove one or more pets.");
        displayChoices(petRecords);
    }
}


public static void launchChoice(int choice, ArrayList<MyyApp> petRecords) {
    switch(choice) {
        case 1:
            viewPets(petRecords, 5);
            break;
        case 2:
            addPets(petRecords);
            break;
        case 3:
            System.exit(0);
            break;       
    }
}
public static void main(String[] args) {
    ArrayList<MyyApp> petRecords = new ArrayList<>();
    //petRecords.add( new PetRecord("Spot",10) );
    //petRecords.add( new PetRecord("Bear",6) );
    System.out.println("Pet Database Program.");

    displayChoices(petRecords);
}

}