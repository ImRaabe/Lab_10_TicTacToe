import java.util.Scanner;
public class SafeInput {

    /**
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */
    public static String getNonZeroLenString(Scanner pipe, String prompt) {

        String retString = "";  // Set this to zero length. Loop runs until it isn’t
        do {
            System.out.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;

    }

    public static int getInt(Scanner pipe, String prompt) {
        int anyInt = 0;
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                anyInt = pipe.nextInt();
                validInput = true;
            } else {
                System.out.println("The input you provided is invalid. Please enter an integer.");
                pipe.next();
            }
        } while (!validInput);

        pipe.nextLine();
        return anyInt;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double anyDouble = 0.0;
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                anyDouble = pipe.nextDouble();
                validInput = true;
            } else {
                System.out.println("The input you provided is invalid. Please enter a valid double value.");
                pipe.next(); // Read and discard the invalid input
            }
        } while (!validInput);

        pipe.nextLine();
        return anyDouble;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int rangedInt = 0;
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                rangedInt = pipe.nextInt();
                if (rangedInt >= low && rangedInt <= high) {
                    validInput = true;
                } else {
                    System.out.println("The input you provided is out of range. Please enter an integer within the range " + low + " - " + high + ".");
                }
            } else {
                System.out.println("The input you provided is invalid. Please enter a valid integer.");
                pipe.next(); // Read and discard the invalid input
            }
        } while (!validInput);

        pipe.nextLine(); // Clear the newline character from the buffer
        return rangedInt;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double rangedDouble = 0.0;
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                rangedDouble = pipe.nextDouble();
                if (rangedDouble >= low && rangedDouble <= high) {
                    validInput = true;
                } else {
                    System.out.println("Input out of range. Please enter a double within the range " + low + " - " + high + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid double.");
                pipe.next(); // Read and discard the invalid input
            }
        } while (!validInput);

        pipe.nextLine(); // Clear the newline character from the buffer
        return rangedDouble;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean confirm = false;
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + ": ");
            String userInput = pipe.nextLine().trim().toUpperCase(); // Convert input to uppercase and trim any leading/trailing whitespace

            if (userInput.equals("Y") || userInput.equals("YES")) {
                confirm = true;
                validInput = true;
            } else if (userInput.equals("N") || userInput.equals("NO")) {
                confirm = false;
                validInput = true;
            } else {
                System.out.println("The input you provided is invalid, Please enter 'Y' for Yes or 'N' for No.");
            }
        } while (!validInput);

        return confirm;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String userInput = null;
        boolean validInput = false;

        do {
            System.out.print("\n" + prompt + ": ");
            userInput = pipe.nextLine().trim(); // Read input and remove leading/trailing whitespace

            if (userInput.matches(regEx)) {
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a string matching the pattern \"" + regEx + "\".");
            }
        } while (!validInput);

        return userInput;
    }
    public static void prettyHeader(String msg) {
        int totalWidth = 60;
        int msgLength = msg.length();
        int starLength = (totalWidth - msgLength - 4) / 2;

        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();

        System.out.print("***");
        for (int i = 0; i < starLength; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < starLength; i++) {
            System.out.print(" ");
        }
        if (msgLength % 2 != 0) {
            System.out.print(" ");
        }
        System.out.println("***");

        for (int i = 0; i < totalWidth; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
}