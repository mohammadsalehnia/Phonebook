package model;

import java.util.Scanner;

public class PhoneNumber {
    private static int nextId = 1;
    private int id;

    private String number;
    private final PhoneNumberType type;

    public int getId() {
        return id;
    }

    public PhoneNumber(String number, PhoneNumberType type) {
        this.id = nextId++;
        this.setNumber(number);
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PhoneNumberType getType() {
        return type;
    }

    public void update(Scanner scanner) {
        System.out.print("Enter new number: ");
        String newNumber = scanner.nextLine();
        this.setNumber(newNumber);
        System.out.println("Number updated");
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id='" + this.getId() + '\'' +
                ", number='" + number + '\'' +
                ", type=" + type +
                '}';
    }
}
