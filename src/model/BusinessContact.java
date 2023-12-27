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

    @Override
    public String toString() {
        return "BusinessContact{" +
                "id='" + this.getId() + '\'' +
                ", name='" + this.getName() + '\'' +
                ", jobTitle='" + getJobTitle() + '\'' +
                ", phoneNumbers= \n" + phoneNumbers +
                '}';
    }
}
