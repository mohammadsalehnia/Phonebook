package service;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook implements AutoCloseable {

    Scanner scanner = new Scanner(System.in);
    private ArrayList<Contact> contacts = new ArrayList<>();
    private int nextId = 1;

    public void run() {

        System.out.println("Phonebook Application Started");
        String selectedOption = "";

        while (!selectedOption.equals("0")) {

            printMenu();
            System.out.print("Select a menu item: ");
            selectedOption = scanner.nextLine();

            switch (selectedOption) {
                case "1": {
                    addContact();
                    break;
                }
                case "2": {
                    printAllContacts();
                    break;
                }
                case "3": {
                    searchContact();
                    break;
                }
                case "4": {
                    editContact();
                    break;
                }
                case "5": {
                    deleteContact();
                    break;
                }
                case "0": {
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
        System.out.println("0. Exit");
        System.out.println("---------------------");
    }

    private void printAllContacts() {

        if (contacts.isEmpty()) {
            System.out.println("Phonebook is empty!");
        } else {
            System.out.println("----------------- PhoneBook List -------------");
//            for (Contact contact : contacts.values()) {
            for (Contact contact : contacts) {
                System.out.println(contact.toString());
                System.out.println("-------------------------------------------");
            }
            System.out.println("------------------ End List ------------------");
        }
    }

    private void addContact() {
        System.out.println("Contact Type:");
        System.out.println("1. Personal");
        System.out.println("2. Business (or other numbers)");
        System.out.print("Select a menu item: ");
        int selectedOption = scanner.nextInt();
        scanner.nextLine();
        if (selectedOption == 1) {

            String name = getUserInput("Enter Name: ");
            String lastName = getUserInput("Enter Last Name: ");

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
            int contactId = nextId++;
//            contacts.put(contactId, personalContact);
            contacts.add(personalContact);
        } else {

            String name = getUserInput("Enter Name: ");
            String jobTitle = getUserInput("Enter Job Title: ");

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
            int contactId = nextId++;
//            contacts.put(contactId, businessContact);
            contacts.add(businessContact);

        }

        System.out.println("New contact successfully added to Phonebook");
    }

    private String getUserInput(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private void deleteContact() {
        System.out.print("Enter the ID of the contact you want to delete: ");
        int contactIdToDelete = scanner.nextInt();
        scanner.nextLine();

        if (contacts.removeIf(contact -> contact.getId() == contactIdToDelete)) {
            System.out.println("Contact with ID " + contactIdToDelete + " deleted successfully.");
        } else {
            System.out.println("Contact with ID " + contactIdToDelete + " not found.");
        }
    }

    private void editContact() {
        System.out.print("Enter the ID of the contact you want to edit: ");
        int contactIdToEdit = scanner.nextInt();
        scanner.nextLine();

        Contact contactToEdit = contacts.stream()
                .filter(contact -> contact.getId() == contactIdToEdit)
                .findFirst()
                .orElse(null);

        System.out.println(contactToEdit);

        contactToEdit.update(scanner);
    }

    private void searchContact() {
        System.out.println("Search Options:");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Phone Number");
        System.out.println("4. Search by Last Name");
        System.out.print("Select a search option: ");

        String searchOption = scanner.nextLine();

        switch (searchOption) {
            case "1": {
                searchById();
                break;
            }
            case "2": {
                searchByName();
                break;
            }
            case "3": {
                searchByNumber();
                break;
            }
            case "4": {
                searchByLastName();
                break;
            }
            default: {
                System.out.println("Invalid search option!");
                break;
            }
        }
    }

    private void searchByLastName() {
        System.out.print("Enter Last Name to search: ");
        String searchLastName = scanner.nextLine().trim();

        contacts.stream()
                .filter(contact -> contact instanceof PersonalContact)
                .map(contact -> (PersonalContact) contact)
                .filter(personalContact -> personalContact.getLastName().equalsIgnoreCase(searchLastName))
                .forEach(System.out::println);

        System.out.println("Contact with Last Name " + searchLastName + " not found.");
    }

    private void searchByNumber() {
        System.out.print("Enter Phone Number to search: ");
        String searchNumber = scanner.nextLine().trim();

//        for (Contact contact : contacts.values()) {
        for (Contact contact : contacts) {
            for (PhoneNumber phoneNumber : contact.getPhoneNumbers()) {
                if (phoneNumber.getNumber().equals(searchNumber)) {
                    System.out.println("Contact found:\n" + contact);
                    return;
                }
            }
        }

        System.out.println("Contact with Phone Number " + searchNumber + " not found.");
    }

    private void searchByName() {
        System.out.print("Enter Name to search: ");
        String searchName = scanner.nextLine().trim();

        contacts.stream()
                .filter(contact -> contact.getName().equalsIgnoreCase(searchName))
                .forEach(System.out::println);
    }

    private void searchById() {
        System.out.print("Enter ID to search: ");
        int searchId = scanner.nextInt();
        scanner.nextLine();


        contacts.stream()
                .filter(contact -> contact.getId() == searchId)
                .forEach(System.out::println);
    }

    @Override
    public void close() {
        scanner.close();
        System.out.println("Close");

    }
}
