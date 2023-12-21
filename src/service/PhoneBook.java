package service;

import model.BusinessContact;
import model.Contact;
import model.PersonalContact;

import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {
    private ArrayList<Contact> contacts = new ArrayList<>();

    public void run() {

        System.out.println("Phonebook Application Started");
        Scanner scanner = new Scanner(System.in);
        String selectedOption = "0";

        while (!selectedOption.equals("3")) {

            printMenu();
            System.out.print("Select a menu item: ");
            selectedOption = scanner.nextLine();

            switch (selectedOption) {
                case "1": {
                    addContact(scanner);
                    break;
                }
                case "2": {
                    printAllContacts();
                    break;
                }
                case "3": {
                    System.out.println("You closed the application");
                    break;
                }
                default: {
                    System.out.println("Invalid Item!");
                    break;
                }
            }
        }

        scanner.close();
        System.out.println("Bye");
    }

    private void printMenu() {
        System.out.println("------- Menu --------");
        System.out.println("1. Add new number");
        System.out.println("2. Print all numbers");
        System.out.println("3. Exit");
        System.out.println("---------------------");
    }

    private void printAllContacts() {

        if (contacts.isEmpty()) {
            System.out.println("Phonebook is empty!");
        } else {
            System.out.println("----------------- PhoneBook List -------------");
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
            System.out.println("------------------ End List ------------------");
        }
    }

    private void addContact(Scanner scanner) {
        System.out.println("Contact Type:");
        System.out.println("1. Personal");
        System.out.println("2. Business (or other numbers)");
        System.out.print("Select a menu item: ");
        int selectedOption = scanner.nextInt();
        scanner.nextLine();

        if (selectedOption == 1) {

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Family: ");
            String family = scanner.nextLine();
            System.out.print("Enter Number: ");
            String number = scanner.nextLine();
            PersonalContact personalContact = new PersonalContact(name, number);
            personalContact.setFamily(family);
            contacts.add(new PersonalContact(name, number));


        } else {
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Fax : ");
            String fax = scanner.nextLine();
            System.out.print("Enter Number: ");
            String number = scanner.nextLine();
            BusinessContact businessContact = new BusinessContact(name, number);
            businessContact.setFax(fax);
            contacts.add(new BusinessContact(name, number));

        }

        System.out.println("New number successfully added to Phonebook");
    }

}
