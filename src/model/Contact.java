package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Contact {
    private static int nextId = 1;
    private int id;
    private String name;
    protected Map<Integer, PhoneNumber> phoneNumbers = new HashMap<>();

    private final ContactType type;

    public abstract void update(Scanner scanner);

    public Contact(String name, ContactType type) {
        this.id = nextId++;
        this.setName(name);
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public ContactType getType() {
        return type;
    }

    private String capitalizeFirstCharacter(String str) {
        if (str != null && !str.isEmpty()) {
            return Character.toUpperCase(str.charAt(0)) + str.substring(1);
        }
        return str;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = capitalizeFirstCharacter(name);
    }

    public Map<Integer, PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
}
