package model;

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

    @Override
    public String toString() {
        return "PersonalContact{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", lastName='" + this.getLastName() + '\'' +
                ", phoneNumbers=\n" + phoneNumbers.toString() +
                '}';
    }
}
