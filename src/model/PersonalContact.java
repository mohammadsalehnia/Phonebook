package model;

import java.util.Scanner;

public class PersonalContact extends Contact {
    private String lastName;

    public PersonalContact(String name) {
        super(name, ContactType.PERSONAL);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void addPhoneNumber(Scanner scanner) {
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
        this.getPhoneNumbers().put(phoneNumber.getId(), phoneNumber);

        System.out.println(type + " number added successfully");
    }

    @Override
    public String toString() {
        return "PersonalContact{" +
                "id='" + this.getId() + '\'' +
                "name='" + this.getName() + '\'' +
                "lastName='" + this.getLastName() + '\'' +
                ", phoneNumbers=\n" + phoneNumbers.toString() +
                '}';
    }


    @Override
    public void update(Scanner scanner) {

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
                this.setName(scanner.nextLine());
                System.out.println("Name updated");
                break;
            }
            case "2": {
                System.out.print("Enter new Last Name: ");
                this.setLastName(scanner.nextLine());
                System.out.println("Last Name updated");
                break;
            }
            case "3": {
                editPhoneNumber(scanner);
                break;
            }
            default: {
                System.out.println("Invalid Item!");
                break;
            }
        }

        System.out.println("Contact with ID " + this + " edited successfully.");
    }

    private void editPhoneNumber(Scanner scanner) {
        System.out.println("List of this contact numbers: ");
        System.out.println(phoneNumbers.toString());
        System.out.println("End of numbers list");

        System.out.print("Enter the ID of the number you want to edit: ");
        int phoneNumberIdToEdit = scanner.nextInt();
        scanner.nextLine();
        PhoneNumber existingPhoneNumber = phoneNumbers.get(phoneNumberIdToEdit);
        if (existingPhoneNumber != null) {

            System.out.println("Update number options: ");
            System.out.println("1. Edit number value");
            System.out.println("2. Delete number");
            System.out.print("Enter update option: ");
            String updateNumberOption = scanner.nextLine();

            switch (updateNumberOption) {
                case "1":
                    existingPhoneNumber.update(scanner);
                    break;
                case "2":

                    PhoneNumber removedPhoneNumber = phoneNumbers.remove(phoneNumberIdToEdit);

                    if (removedPhoneNumber != null) {
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


}
