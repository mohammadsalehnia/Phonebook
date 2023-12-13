import java.util.ArrayList;
import java.util.Scanner;

public class Phonebook {
    public static void main(String[] args) {
        System.out.println("Phonebook Application Started");

        ArrayList<String> names = new ArrayList<>();
        ArrayList<String> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String selectedOption = "0";

        while (!selectedOption.equals("3")) {

            System.out.println("------- Menu --------");
            System.out.println("1. Add new number");
            System.out.println("2. Print all numbers");
            System.out.println("3. Exit");
            System.out.println("---------------------");
            System.out.println("Select a menu item:");
            selectedOption = scanner.nextLine();

            switch (selectedOption) {
                case "1": {
                    System.out.println("Enter Name:");
                    String newName = scanner.nextLine();
                    names.add(newName);

                    System.out.println("Enter Number:");
                    String newNumber = scanner.nextLine();
                    numbers.add(newNumber);
                    System.out.println("New number successfully added to Phonebook");
                }
                break;
                case "2": {
                    System.out.println("----------------- PhoneBook List -------------");
                    for (int i = 0; i < numbers.size(); i++) {
                        System.out.println("Name: " + names.get(i) + " - Number: " + numbers.get(i));
                    }
                    System.out.println("------------------ End List ------------------");
                    break;
                }
                case "3": {
                    System.out.println("You closed the application");
                    break;
                }
                default:
                {
                    System.out.println("Invalid Item!");
                    break;
                }
            }
        }
        scanner.close();
        System.out.println("Bye");
    }
}