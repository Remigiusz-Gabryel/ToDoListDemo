package com.codeFirstProject.util;

import java.util.Scanner;

public class ScannerHelperMethods {
    public static int getInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {

            scanner.next();
        }
        return scanner.nextInt();
    }

    public static int getInt(Scanner scanner, int from, int to) {
        int number = getInt(scanner);

        while (!checkRange(number, from, to)) {
            number = getInt(scanner);
        }
        return number;
    }

    private static boolean checkRange(int number, int lowerBound, int upperBound) {
        return (number >= lowerBound && number <= upperBound) ? true : false;
    }

    public static String allowOnlyAlphabetValues(Scanner scanner) {
        String myString = scanner.nextLine();
        while (!(myString.matches("(?i)[a-zA-z ]*") && !myString.isBlank())) {
            myString = scanner.nextLine();
        }
        myString.trim();
        return myString;
    }
    public static String allowOnlyAlphabetValuesAndNumbers(Scanner scanner) {
        String myString;
        scanner.nextLine();
        do {
            myString = scanner.nextLine();
        } while (!(myString.matches("(?i)[a-zA-z0-9 ]*")));

        myString.trim();
        return myString;
    }
}
