package model;

public class PersonalContact extends Contact {
    private String family;
    public PersonalContact(String name, String number) {
        super(name, number, ContactType.PERSONAL);
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
