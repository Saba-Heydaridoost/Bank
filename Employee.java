package com.company;

import java.time.LocalDate;
import java.util.Comparator;

public class Employee extends Person {

    private long salary = 30000000;
    private static final long leastSalary = 28000000;

    public Employee(String identity_number, String firstName, String familyName, LocalDate dateOfBirth,
                    String gender, LocalDate dateOfRegister, String password) {
        super(identity_number, firstName, familyName, dateOfBirth, gender, dateOfRegister, password);
    }

    public long getSalary() {
        return salary;
    }

    public static long getLeastSalary() {
        return leastSalary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString () {
        String info = "[Identity Number : " + this.getIdentity_number() +
                ",\nFirst Name : " + this.getFirstName() +
                ",\nFamily Name : " + this.getFamilyName() +
                ",\nDate Of Birth : " + this.getDateOfBirth() +
                ",\nGender : " + this.getGender() +
                ",\nDate Of Register : " + this.getDateOfRegister() +
                ",\nSalary : " + this.getSalary()+"]";

        return info;
    }
}
