package com.company;

import java.time.LocalDate;

public class Manager extends Person {


    public Manager(String identity_number, String firstName, String familyName,
                   String password, LocalDate dateOfBirth, String gender) {
        super(identity_number, firstName, familyName, password, dateOfBirth, gender);
    }

    @Override
    public String toString () {
        String info = String.format( "First Name : %s\nFamily Name : %s\nIdentity Number : %s\n" +
                        "Gender : %s\nPassword : %s\nDate Of Birth : " + this.dateOfBirth + "\n",
                this.firstName, this.familyName, this.identity_number,
                this.gender, this.password);
        return info;
    }

}