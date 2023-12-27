package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Contact {
    private static final AtomicInteger nextId = new AtomicInteger(1);
    private int id;
    private String name;
    protected ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
    private final ContactType type;

    public Contact(String name, ContactType type) {
        this.id = nextId.getAndIncrement();
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

    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
}
