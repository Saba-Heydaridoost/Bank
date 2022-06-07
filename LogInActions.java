package com.company;

import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class LogInActions {

    //Stage
    static Stage window = new Stage();

    public static Manager manager;

    public static void enter (String whoAreYou) {

        infoGetter();     //Getting all information from files

        if (whoAreYou.equals(Accessibility.admin)) {
            adminsMainPage(whoAreYou);
        }
        else if (whoAreYou.equals(Accessibility.manager)) {
            //Checks if manager exists or not :
            File myFile = new File(".\\manager.txt");

            try {
                if (!myFile.exists() || myFile.length() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException e) {
                System.out.println(TextColor.RED + "Manager Does Not Exist Yet !"
                        + TextColor.RESET);

                LogInScene.window.close();
                System.exit(0);
            }

            boolean correct = adminOrManagerAbleToAccess("manager", Accessibility.manager);
            if (correct) {
                MenuScene.managerMenu();
            }
        } else if (whoAreYou.equals(Accessibility.employee)) {
            //Checks if employee exists or not :
            try {
                File myFile = new File(".\\employee.csv");
                if (!myFile.exists() || myFile.length() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException e) {
                System.out.println(TextColor.RED + "No Employee Exists Yet !"
                        + TextColor.RESET);
                LogInScene.window.close();
                System.exit(0);
            }

            String identity = able_To_Access(Accessibility.employee);
            if (!identity.equals("")) {
                MenuScene.employeeMenu();
            }
        }
        else if (whoAreYou.equals(Accessibility.client)) {
            //Checks if client exists or not :
            try {
                File myFile = new File(".\\clients.csv");
                if (!myFile.exists() || myFile.length() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException e) {
                System.out.println(TextColor.RED + "No Client Exists Yet !"
                        + TextColor.RESET);
                LogInScene.window.close();
                System.exit(0);
            }
            String identity = able_To_Access(Accessibility.client);
            if (!identity.equals("")) {
                MenuScene.clientMenu(identity);
            }
        }
    }

    public static void infoGetter () {
        if (Accessibility.getEmployees().size() == 0) {  //Gets info of employees from employee.csv
            Accessibility.gettingInfoOfEmployees();      // if they exist
        }

        if (Accessibility.getClients().size() == 0) {    //Gets info of clients from employee.csv
            Accessibility.gettingInfoOfClients();        // if they exist

            //Gets info of accounts and transactions from accounts.csv and transactions.csv if they exist :
            Accessibility.gettingInfoOfAccounts();
            Accessibility.gettingInfoOfTransactions();
        }
    }

    public static boolean adminOrManagerAbleToAccess(String name, String whoAreYou) {
        String identityNumber = "", password = "";
        File myFile = new File(".\\" + name + ".txt");

        try {
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                String[] id = myReader.nextLine().split(":");
                identityNumber = id[1];
                String[] pass = myReader.nextLine().split(":");
                password = pass[1];
                break;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println(TextColor.RED + "There Is Not Any File To Check The Info Of " + name + " !\n" +
                    "May be The Pathname Is Incorrect !" +TextColor.RESET);
        }

        boolean correct = ableToAccess(identityNumber, password, whoAreYou);

        return correct;
    }

    public static boolean checkIsCorrect(String identityNumber, String password, ArrayList<Person> person){
        boolean IsCorrect = false;
        int index = 0;

        //check Identity_number Is Correct
        for (int i = 0; i < person.size(); ++i){
            if (person.get(i).getIdentity_number().equals(identityNumber)){
                index += i ;
                IsCorrect = true;
                break;
            }
        }
        if (IsCorrect) {
            IsCorrect = PasswordIsCorrect(password, person, index);
        }

        return IsCorrect;
    }

    public static boolean PasswordIsCorrect(String password, ArrayList<Person> person, int index){
        boolean IsCorrect = false;

        if (person.get(index).getPassword().equals(password)){
            IsCorrect = true;
        }

        return IsCorrect;
    }

    public static boolean ableToAccess(String identity_number, String password, String whoAreYou) {

        String inputIdentityNumber = "", inputPassword = "";

        inputIdentityNumber = LogInScene.getIdentityNumberInput().getText();
        inputPassword = LogInScene.getPasswordInput().getText();

        if (!(inputIdentityNumber.equals(identity_number)) || !(inputPassword.equals(password))) {
            System.out.println(TextColor.RED + "\u2716 The Entered Identity Number Or Password Is Wrong \u2716" +
                    "\nPlease Try Again..." + TextColor.RESET);
        }

        if (inputIdentityNumber.equals(identity_number) && inputPassword.equals(password)) {
            return true;
        }
        else {
            return false;
        }
    }

    //This method checks the information to get the access of employee & client : ///******Debugggggg
    public static String able_To_Access (String whoAreYou) {
        Scanner input = new Scanner(System.in);
        ArrayList <Person> person = new ArrayList<>();
        //////////////////////////////////////////////////////////////////////////
        if (whoAreYou.equals(Accessibility.employee)){
            person = (ArrayList<Person>) Accessibility.getEmployees().clone();
        }
        else if (whoAreYou.equals(Accessibility.client)){
            person = (ArrayList<Person>) Accessibility.getClients().clone();
        }

        String inputIdentityNumber = LogInScene.getIdentityNumberInput().getText();
        String inputPassword = LogInScene.getPasswordInput().getText();


        if (!checkIsCorrect(inputIdentityNumber, inputPassword, person)) {
            System.out.println(TextColor.RED + "\u2716 The Entered Identity Number Or Password Is Wrong" +
                            "\u2716\nPlease Try Again..." + TextColor.RESET);

            return "";
        }
        else {
            return inputIdentityNumber;
        }
    }

    public static void adminsMainPage (String whoAreYou) {
        boolean correct = adminOrManagerAbleToAccess("admin", Accessibility.admin);

        if (correct) {
            File myFile = new File(".\\manager.txt");

            if (!myFile.exists() || myFile.length() == 0) {     //Checks if manager file exists or not
                PersonScene.addingPersonScene(Accessibility.manager, Accessibility.admin);
            } else {  //If manager file exists, manager is built in this block
                String identityNumber = "", password = "", firstName = "", familyName = "", gender = "";
                LocalDate dateOfBirth = LocalDate.of(1, 1, 1);

                try {
                    Scanner myReader = new Scanner(myFile);

                    while (myReader.hasNextLine()) {
                        String[] identityNumberArr = myReader.nextLine().split(":");
                        identityNumber = identityNumberArr[1];

                        String[] firstNameArr = myReader.nextLine().split(":");
                        firstName = firstNameArr[1];

                        String[] familyNameArr = myReader.nextLine().split(":");
                        familyName = familyNameArr[1];

                        String[] passwordArr = myReader.nextLine().split(":");
                        password = passwordArr[1];

                        String[] dateOfBirthArr = myReader.nextLine().split(":");
                        String[] partsOfDate = dateOfBirthArr[1].split("-");
                        dateOfBirth = LocalDate.of(Integer.parseInt(partsOfDate[0]),
                                Integer.parseInt(partsOfDate[1]), Integer.parseInt(partsOfDate[2]));

                        String[] genderArr = myReader.nextLine().split(":");
                        gender = genderArr[1];
                    }

                    manager = new Manager(identityNumber, firstName, familyName,
                            password, dateOfBirth, gender);

                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println(TextColor.RED + "There Is Not Any File To Check The Info Of Manger !\n" +
                            "May be The Pathname Is Incorrect !" + TextColor.RESET);
                }
                MenuScene.adminMenu();
                LogInScene.window.close();
            }

        }
        else {
            LogInScene.logIn(whoAreYou);
        }
    }

    //This method creates a manager by admin :
    public static void managerCreator (String identityNumber, String password, String firstName,
                                       String familyName, LocalDate dateOfBirth, String gender) {

        String[] data ={identityNumber, password, firstName,
                familyName,String.valueOf(dateOfBirth), gender};

        try {
            Files.writeToFile("manager", data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
