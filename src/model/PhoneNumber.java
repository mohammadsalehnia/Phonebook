package model;

import java.util.concurrent.atomic.AtomicInteger;

public class PhoneNumber {
    private static final AtomicInteger nextId = new AtomicInteger(1);
    private int id;

    private String number;
    private final PhoneNumberType type;

    public int getId() {
        return id;
    }

    public PhoneNumber(String number, PhoneNumberType type) {
        this.id = nextId.getAndIncrement();
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

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "id='" + this.getId() + '\'' +
                ", number='" + number + '\'' +
                ", type=" + type +
                '}';
    }
}
