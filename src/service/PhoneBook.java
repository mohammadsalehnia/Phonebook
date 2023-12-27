package service;

import model.*;

import java.util.*;

public class PhoneBook implements AutoCloseable {

    Scanner scanner = new Scanner(System.in);
    private ArrayList<Contact> contacts = new ArrayList<>();

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
            addPersonaContact();
        } else {
            addBusinessContact();
        }

        System.out.println("New contact successfully added to Phonebook");
    }

    private void addBusinessContact() {
        String name = getUserInput("Enter Name: ");
        String jobTitle = getUserInput("Enter Job Title: ");

        BusinessContact businessContact = new BusinessContact(name);
        businessContact.setJobTitle(jobTitle);

        boolean enterNumber = true;

        while (enterNumber) {

            addPhoneNumberTo(businessContact);

            System.out.print("Do you want add more number? (enter y for yes and other wor no): ");
            String addMore = scanner.nextLine();

            if (!addMore.equals("y")) {
                enterNumber = false;
            }

        }
        contacts.add(businessContact);
    }

    private void addPersonaContact() {
        String name = getUserInput("Enter Name: ");
        String lastName = getUserInput("Enter Last Name: ");
        PersonalContact personalContact = new PersonalContact(name);
        personalContact.setLastName(lastName);
        boolean enterNumber = true;
        while (enterNumber) {
            addPhoneNumberTo(personalContact);
            System.out.print("Do you want add more number? (enter y for yes and other wor no): ");
            String addMore = scanner.nextLine();
            if (!addMore.equals("y")) {
                enterNumber = false;
            }
        }
        contacts.add(personalContact);
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

        if (contactToEdit instanceof PersonalContact) {
            editPersonalContact((PersonalContact) contactToEdit);
        } else {
            editBusinessContact((BusinessContact) contactToEdit);
        }
    }

    private void editBusinessContact(BusinessContact businessContact) {
        System.out.println("It's a BUSINESS contact");
        System.out.println("What do you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Job Title");
        System.out.println("3. Numbers");
        System.out.print("Select a menu item: ");
        String selectedOption = scanner.nextLine();


        switch (selectedOption) {
            case "1": {
                System.out.print("Enter new Name: ");
                businessContact.setName(scanner.nextLine());
                System.out.println("Name updated");
                break;
            }
            case "2": {
                System.out.print("Enter new Job Title: ");
                businessContact.setJobTitle(scanner.nextLine());
                System.out.println("Job Title updated");
                break;
            }
            case "3": {
                editPhoneNumber(businessContact);
                break;
            }
            default: {
                System.out.println("Invalid Item!");
                break;
            }
        }

        System.out.println("Contact with ID " + businessContact.getId() + " edited successfully.");
    }

    private void editPersonalContact(PersonalContact personalContact) {
        System.out.println("It's a PERSONAL contact");
        System.out.println("What do you want to edit?");
        System.out.println("1. Name");
        System.out.println("2. Last Name");
        System.out.println("3. Numbers");
        System.out.print("Select a menu item: ");
        String selectedOption = scanner.nextLine();


        switch (selectedOption) {
            case "1": {
                System.out.print("Enter new Name: ");
                personalContact.setName(scanner.nextLine());
                System.out.println("Name updated");
                break;
            }
            case "2": {
                System.out.print("Enter new Last Name: ");
                personalContact.setLastName(scanner.nextLine());
                System.out.println("Last Name updated");
                break;
            }
            case "3": {
                editPhoneNumber(personalContact);
                break;
            }
            default: {
                System.out.println("Invalid Item!");
                break;
            }
        }

        System.out.println("Contact with ID " + personalContact.getId() + " edited successfully.");
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

    private void addPhoneNumberTo(Contact contact) {

        if (contact instanceof PersonalContact) {
            String phoneNumberType;
            System.out.println("Phone Number types:");
            System.out.println("1. HOME");
            System.out.println("2. WORK");
            System.out.println("3. MOBILE");
            System.out.print("Select Phone Number Type: ");

            phoneNumberType = scanner.nextLine();
            System.out.println("select: " + phoneNumberType);

            PhoneNumberType type;
            switch (phoneNumberType) {
                case "1":
                    type = PhoneNumberType.HOME;
                    break;
                case "2":
                    type = PhoneNumberType.WORK;
                    break;
                case "3":
                    type = PhoneNumberType.MOBILE;
                    break;
                default:
                    System.out.println("Invalid Item!");
                    return;
            }

            System.out.print("Enter phone number: ");
            String inputPhoneNumber = scanner.nextLine();
            PhoneNumber phoneNumber = new PhoneNumber(inputPhoneNumber, type);
            contact.getPhoneNumbers().add(phoneNumber);

        } else {

            String phoneNumberType;
            System.out.println("Phone Number types:");
            System.out.println("1. WORK");
            System.out.println("2. MOBILE");
            System.out.println("3. FAX");
            System.out.print("Select Phone Number Type: ");

            phoneNumberType = scanner.nextLine();
            System.out.println("select: " + phoneNumberType);

            PhoneNumberType type;
            switch (phoneNumberType) {
                case "1":
                    type = PhoneNumberType.WORK;
                    break;
                case "2":
                    type = PhoneNumberType.MOBILE;
                    break;
                case "3":
                    type = PhoneNumberType.FAX;
                    break;
                default:
                    System.out.println("Invalid Item!");
                    return;
            }

            System.out.print("Enter phone number: ");
            String inputPhoneNumber = scanner.nextLine();
            PhoneNumber phoneNumber = new PhoneNumber(inputPhoneNumber, type);

            contact.getPhoneNumbers().add(phoneNumber);
        }

        System.out.println("Phone Number added successfully");
    }

    private void editPhoneNumber(Contact contact) {
        System.out.println("List of this contact numbers: ");
        System.out.println(contact.getPhoneNumbers());
        System.out.println("End of numbers list");

        System.out.print("Enter the ID of the number you want to edit: ");
        int phoneNumberIdToEdit = scanner.nextInt();
        scanner.nextLine();

        PhoneNumber phoneNumberToEdit = contact.getPhoneNumbers().stream()
                .filter(phoneNumber -> phoneNumber.getId() == phoneNumberIdToEdit)
                .findFirst()
                .orElse(null);

        System.out.println(phoneNumberIdToEdit);


        if (phoneNumberToEdit != null) {

            System.out.println("Update number options: ");
            System.out.println("1. Edit number value");
            System.out.println("2. Delete number");
            System.out.print("Enter update option: ");
            String updateNumberOption = scanner.nextLine();

            switch (updateNumberOption) {
                case "1":
                    updatePhoneNumber(phoneNumberToEdit);
                    break;
                case "2":
                    if (contact.getPhoneNumbers().removeIf(phoneNumber -> phoneNumber.getId() == phoneNumberIdToEdit)) {
                        System.out.println("Number with ID " + phoneNumberIdToEdit + " deleted successfully.");
                    } else {
                        System.out.println("Number with ID " + phoneNumberIdToEdit + " not found.");
                    }
                    break;
                default:
                    System.out.println("Invalid Item!");
                    return;
            }
        } else {
            System.out.println("Number with ID " + phoneNumberIdToEdit + " not found.");
        }
    }


    private void updatePhoneNumber(PhoneNumber phoneNumber) {
        System.out.print("Enter new number: ");
        String newNumber = scanner.nextLine();
        phoneNumber.setNumber(newNumber);
        System.out.println("Number updated");
    }


    @Override
    public void close() {
        scanner.close();
        System.out.println("Close");
    }
}
