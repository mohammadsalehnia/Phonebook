package service;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PhoneBook {
//    private ArrayList<Contact> contacts = new ArrayList<>();

    private Map<Integer, Contact> contacts = new HashMap<>();
    private int nextId = 1;

    public void run() {

        System.out.println("Phonebook Application Started");
        Scanner scanner = new Scanner(System.in);
        String selectedOption = "0";

        while (!selectedOption.equals("6")) {

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
                    searchContact(scanner);
                    break;
                }
                case "4": {
                    editContact(scanner);
                    break;
                }
                case "5": {
                    deleteContact(scanner);
                    break;
                }
                case "6": {
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
        System.out.println("3. Search contact");
        System.out.println("4. Edit contact");
        System.out.println("5. Delete contact");
        System.out.println("6. Exit");
        System.out.println("---------------------");
    }

    private void printAllContacts() {

        if (contacts.isEmpty()) {
            System.out.println("Phonebook is empty!");
        } else {
            System.out.println("----------------- PhoneBook List -------------");
            for (Contact contact : contacts.values()) {
                System.out.println(contact.toString());
                System.out.println("-------------------------------------------");
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
            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();

            PersonalContact personalContact = new PersonalContact(name);
            personalContact.setLastName(lastName);

            boolean enterNumber = true;

            while (enterNumber) {

                personalContact.addPhoneNumber(scanner);

                System.out.print("Do you want add more number? (enter y for yes and other wor no): ");
                String addMore = scanner.nextLine();


                if (!addMore.equals("y")) {
                    enterNumber = false;
                }

            }
//            contacts.add(personalContact);

            int contactId = nextId++;
            contacts.put(contactId, personalContact);
        } else {

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Job Title: ");
            String jobTitle = scanner.nextLine();

            BusinessContact businessContact = new BusinessContact(name);
            businessContact.setJobTitle(jobTitle);

            boolean enterNumber = true;

            while (enterNumber) {

                businessContact.addPhoneNumber(scanner);

                System.out.print("Do you want add more number? (enter y for yes and other wor no): ");
                String addMore = scanner.nextLine();

                if (!addMore.equals("y")) {
                    enterNumber = false;
                }

            }

//            contacts.add(businessContact);
            int contactId = nextId++;
            contacts.put(contactId, businessContact);

        }

        System.out.println("New contact successfully added to Phonebook");
    }

    private void deleteContact(Scanner scanner) {
        System.out.print("Enter the ID of the contact you want to delete: ");
        int contactIdToDelete = scanner.nextInt();
        scanner.nextLine();
        Contact removedContact = contacts.remove(contactIdToDelete);
        if (removedContact != null) {
            System.out.println("Contact with ID " + contactIdToDelete + " deleted successfully.");
        } else {
            System.out.println("Contact with ID " + contactIdToDelete + " not found.");
        }
    }

    private void editContact(Scanner scanner) {
        System.out.print("Enter the ID of the contact you want to edit: ");
        int contactIdToEdit = scanner.nextInt();
        scanner.nextLine();
        Contact existingContact = contacts.get(contactIdToEdit);
        if (existingContact != null) {
            if (existingContact.getType() == ContactType.PERSONAL || existingContact.getType() == ContactType.BUSINESS) {
                existingContact.update(scanner);
            } else {
                System.out.println("Invalid ContactType!");
            }
        } else {
            System.out.println("Contact with ID " + contactIdToEdit + " not found.");
        }
    }

    private void searchContact(Scanner scanner) {
        System.out.println("Search Options:");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Phone Number");
        System.out.println("4. Search by Last Name");
        System.out.print("Select a search option: ");

        String searchOption = scanner.nextLine();

        switch (searchOption) {
            case "1": {
                searchById(scanner);
                break;
            }
            case "2": {
                searchByName(scanner);
                break;
            }
            case "3": {
                searchByNumber(scanner);
                break;
            }
            case "4": {
                searchByLastName(scanner);
                break;
            }
            default: {
                System.out.println("Invalid search option!");
                break;
            }
        }
    }

    private void searchByLastName(Scanner scanner) {
        System.out.print("Enter Last Name to search: ");
        String searchLastName = scanner.nextLine().trim();

        for (Contact contact : contacts.values()) {
            if (contact.getType() == ContactType.PERSONAL) {
                PersonalContact personalContact = (PersonalContact) contact;
                if (personalContact.getLastName().equalsIgnoreCase(searchLastName)) {
                    System.out.println("Contact found:\n" + personalContact);
                    return;
                }
            }
        }

        System.out.println("Contact with Last Name " + searchLastName + " not found.");
    }

    private void searchByNumber(Scanner scanner) {
        System.out.print("Enter Phone Number to search: ");
        String searchNumber = scanner.nextLine().trim();

        for (Contact contact : contacts.values()) {
            for (PhoneNumber phoneNumber : contact.getPhoneNumbers().values()) {
                if (phoneNumber.getNumber().equals(searchNumber)) {
                    System.out.println("Contact found:\n" + contact);
                    return;
                }
            }
        }

        System.out.println("Contact with Phone Number " + searchNumber + " not found.");
    }

    private void searchByName(Scanner scanner) {
        System.out.print("Enter Name to search: ");
        String searchName = scanner.nextLine().trim();

        for (Contact contact : contacts.values()) {
            if (contact.getName().equalsIgnoreCase(searchName)) {
                System.out.println("Contact found:\n" + contact);
                return;
            }
        }

        System.out.println("Contact with Name " + searchName + " not found.");
    }

    private void searchById(Scanner scanner) {
        System.out.print("Enter ID to search: ");
        int searchId = scanner.nextInt();
        scanner.nextLine();

        Contact foundContact = contacts.get(searchId);
        if (foundContact != null) {
            System.out.println("Contact found:\n" + foundContact);
        } else {
            System.out.println("Contact with ID " + searchId + " not found.");
        }
    }

}
