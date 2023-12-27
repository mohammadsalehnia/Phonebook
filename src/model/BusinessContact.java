package model;

import java.util.Scanner;

public class BusinessContact extends Contact {
    private String jobTitle;

    public BusinessContact(String name) {
        super(name, ContactType.BUSINESS);
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    public void addPhoneNumber(Scanner scanner) {
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

        this.phoneNumbers.add(phoneNumber);

    }

    @Override
    public String toString() {
        return "BusinessContact{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", jobTitle='" + getJobTitle() + '\'' +
                ", phoneNumbers= \n" + phoneNumbers +
                '}';
    }

    @Override
    public void update(Scanner scanner) {

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
                this.setName(scanner.nextLine());
                System.out.println("Name updated");
                break;
            }
            case "2": {
                System.out.print("Enter new Job Title: ");
                this.setJobTitle(scanner.nextLine());
                System.out.println("Job Title updated");
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

        PhoneNumber phoneNumberToEdit = phoneNumbers.stream()
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
                    phoneNumberToEdit.update(scanner);
                    break;
                case "2":
                    if (phoneNumbers.removeIf(phoneNumber -> phoneNumber.getId() == phoneNumberIdToEdit)) {
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
