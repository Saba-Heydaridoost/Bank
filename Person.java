package com.company;

import java.time.LocalDate;

public class Person {

    protected String identity_number;
    protected String firstName;
    protected String familyName;
    protected LocalDate dateOfBirth;
    protected String gender;
    protected LocalDate dateOfRegister;
    protected String password;

    public Person(String identity_number, String firstName, String familyName, LocalDate dateOfBirth,
                  String gender, LocalDate dateOfRegister, String password){
        this.identity_number = identity_number;
        this.firstName = firstName;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.dateOfRegister = dateOfRegister;
        this.password = password;
    }

    public Person(String identity_number, String firstName, String familyName,
                  String password, LocalDate dateOfBirth, String gender){
        this.identity_number = identity_number;
        this.firstName = firstName;
        this.familyName = familyName;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
        this.gender = gender;
    }


    public String getIdentity_number() {
        return identity_number;
    }

    public void setIdentity_number(String identity_number) {
        this.identity_number = identity_number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfRegister() {
        return dateOfRegister;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
