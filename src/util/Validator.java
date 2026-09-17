package util;

import java.util.Scanner;

public class Validator {

    // Método para validar el Email
    // Usamos "static" para poder usar esta herramienta sin tener que hacer "new Validator()"
    public static String readValidEmail(Scanner scanner, String promptMessage) {
        String email = "";
        boolean isValidEmail = false;

        while (!isValidEmail) {
            System.out.print(promptMessage);
            email = scanner.nextLine();

            if (email.matches("^.+@.+\\..+$")) {
                isValidEmail = true;
            } else {
                System.out.println("Error: Invalid email format. Please make sure it includes '@' and a domain.\n");
            }
        }
        return email;
    }

    public static int readValidInt(Scanner scanner, String promptMessage, int min, int max) {
        int choice = -1;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(promptMessage);
            String input = scanner.nextLine();

            try {
                choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) {
                    isValid = true;
                } else {
                    System.out.println("Invalid number. Please select between " + min + " and " + max + ".\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: You cannot enter letters. Please enter a number.\n");
            }
        }
        return choice;
    }
}
