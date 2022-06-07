
//******************Graphics******************//

package com.company;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class PersonScene {
    //Scenes :
    static Scene addingPerson;
    static Scene changingPersonInfo;
    //Stage :
    static Stage window = new Stage();

    //Entering first name :
    static Label firstNameLabel = new Label("First Name :");
    static TextField firstNameInput = new TextField();

    //Entering family name :
    static Label familyNameLabel = new Label("Family Name :");
    static TextField familyNameInput = new TextField();

    //Entering identity number :
    static Label identityNumberLabel = new Label("Identity Number :");
    static TextField identityNumberInput = new TextField();

    //Choosing gender :
    static Label genderLabel = new Label("Gender :");
    static ComboBox<String> genderCombo = new ComboBox<>();

    //Entering salary :
    static Label salaryLabel = new Label("Salary :");
    static ComboBox <String> salaryCombo = new ComboBox<>();
    static TextField salaryInput = new TextField();


    //Entering date of birth :
    static Label dateOfBirthLabel = new Label("Date Of Birth...");
    static Label yearLabel = new Label("Year :");
    static TextField yearInput = new TextField();

    static Label monthLabel = new Label("Month :");
    static TextField monthInput = new TextField();

    static Label dayLabel = new Label("Day :");
    static TextField dayInput = new TextField();

    //Entering password :
    static Label passwordLabel = new Label("Password :");
    static PasswordField passwordInput = new PasswordField();


    //Adding person :
    public static void addingPersonScene(String whoIsIt, String whoAreYou) {
        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        Label title = new Label("Adding " + whoIsIt);

        //Adding choices :
        if (genderCombo.getItems().size() == 0) {
            genderCombo.getItems().addAll(
                    "Male",
                    "Female"
            );
        }

        genderCombo.getSelectionModel().selectFirst();

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button( " Cancel ");

        //Positioning labels, textFields and buttons :
        GridPane.setConstraints(title, 0, 0);
        GridPane.setConstraints(firstNameLabel, 0, 2);
        GridPane.setConstraints(familyNameLabel, 0, 3);
        GridPane.setConstraints(identityNumberLabel, 0, 4);
        GridPane.setConstraints(dateOfBirthLabel, 0, 5);
        GridPane.setConstraints(yearLabel, 0, 6);
        GridPane.setConstraints(monthLabel, 0, 7);
        GridPane.setConstraints(dayLabel, 0, 8);
        GridPane.setConstraints(genderLabel, 0, 9);
        GridPane.setConstraints(passwordLabel, 0, 10);

        GridPane.setConstraints(firstNameInput, 1, 2);
        GridPane.setConstraints(familyNameInput, 1, 3);
        GridPane.setConstraints(identityNumberInput, 1, 4);
        GridPane.setConstraints(yearInput, 1, 6);
        GridPane.setConstraints(monthInput, 1, 7);
        GridPane.setConstraints(dayInput, 1, 8);
        GridPane.setConstraints(genderCombo, 1, 9);
        GridPane.setConstraints(passwordInput, 1, 10);

        GridPane.setConstraints(confirmButton, 1, 11);
        GridPane.setConstraints(cancelButton, 1, 12);

        //Adding all things to the layout :
        layout.getChildren().addAll(identityNumberLabel, identityNumberInput,
                passwordLabel, passwordInput, firstNameInput, firstNameLabel,
                familyNameInput, familyNameLabel, genderCombo, title, dateOfBirthLabel,
                yearInput, yearLabel, monthInput, monthLabel, dayInput, dayLabel,
                confirmButton, cancelButton, genderLabel);


        //Creating scene :
        addingPerson = new Scene(layout, 300, 400);
        window.setScene(addingPerson);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting buttons on action :
        confirmButton.setOnAction(e -> {
            if (noExceptions(whoIsIt) && dateMaker(whoIsIt)) {
                if (whoIsIt.equals(Accessibility.manager)) {
                    LogInActions.manager = new Manager(identityNumberInput.getText(), firstNameInput.getText(),
                            familyNameInput.getText(), passwordInput.getText(),
                            LocalDate.of(Integer.parseInt(yearInput.getText()),
                                    Integer.parseInt(monthInput.getText()), Integer.parseInt(dayInput.getText())),
                            genderCombo.getValue());

                    MessageScene.message("Manager Is Added Successfully !", whoAreYou);
                    LogInActions.managerCreator(identityNumberInput.getText(), passwordInput.getText(),
                            firstNameInput.getText(), familyNameInput.getText(),
                            LocalDate.of(Integer.parseInt(yearInput.getText()),
                                    Integer.parseInt(monthInput.getText()), Integer.parseInt(dayInput.getText())),
                            genderCombo.getValue());

                    MenuScene.adminMenu();
                }

                else if (whoIsIt.equals(Accessibility.employee)) {
                    Employee employee = new Employee(identityNumberInput.getText(),
                            firstNameInput.getText(), familyNameInput.getText(),
                            LocalDate.of(Integer.parseInt(yearInput.getText()),
                                    Integer.parseInt(monthInput.getText()), Integer.parseInt(dayInput.getText())),
                            genderCombo.getValue(), DateAndTime.getToday(), passwordInput.getText());

                    Accessibility.getEmployees().add(employee);
                    Accessibility.savingInfoOfEmployeesToFile();

                    MessageScene.message("Employee Is Added Successfully !", whoAreYou);

                    EmployeeScene.changingTheProfileOfEmployees(whoAreYou);
                }

                else if (whoIsIt.equals(Accessibility.client)) {
                    Client client = new Client(identityNumberInput.getText(),
                            firstNameInput.getText(), familyNameInput.getText(),
                            LocalDate.of(Integer.parseInt(yearInput.getText()),
                                    Integer.parseInt(monthInput.getText()), Integer.parseInt(dayInput.getText())),
                            genderCombo.getValue(), DateAndTime.getToday(), passwordInput.getText());

                    Accessibility.getClients().add(client);
                    Accessibility.savingInfoOfClientsToFile();
                    Accessibility.savingInfoOfAccountToFile();
                    Accessibility.savingInfoOfTransactionsToFile();

                    MessageScene.message("Client Is Added Successfully !", whoAreYou);

                    ClientScene.clientProfile(whoAreYou);
                }
                window.close();

                identityNumberInput.clear();
                firstNameInput.clear();
                familyNameInput.clear();
                yearInput.clear();
                monthInput.clear();
                dayInput.clear();
                passwordInput.clear();
                salaryInput.clear();
            }
        });

        cancelButton.setOnAction(e -> {
            if (whoIsIt.equals(Accessibility.manager) ||
                    Accessibility.getClients().size() == 0 ||
                    Accessibility.getEmployees().size() == 0) {
                LogInScene.logIn(Accessibility.admin);
            }
            else {
                if (whoAreYou.equals(Accessibility.admin)) {
                    MenuScene.adminMenu();
                }

                else if (whoAreYou.equals(Accessibility.manager)) {
                    MenuScene.managerMenu();
                }

                else if (whoAreYou.equals(Accessibility.employee)) {
                    MenuScene.employeeMenu();
                }
            }
            window.close();

            identityNumberInput.clear();
            firstNameInput.clear();
            familyNameInput.clear();
            yearInput.clear();
            monthInput.clear();
            dayInput.clear();
            passwordInput.clear();
            salaryInput.clear();
        });
    }

    public static void changingPersonInfo (String whoIsIt, String whoAreYou, int index) {

        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        Label title = new Label("Changing Info");

        //Entering first name :
        firstNameLabel = new Label("First Name :");
        firstNameInput = new TextField();

        //Entering family name :
        familyNameLabel = new Label("Family Name :");
        familyNameInput = new TextField();

        //Choosing gender :
        genderLabel = new Label("Gender :");
        genderCombo = new ComboBox<>();
        //Adding choices :
        genderCombo.getItems().addAll(
                "Male",
                "Female"
        );

        genderCombo.getSelectionModel().selectFirst();

        //Entering password :
        passwordLabel = new Label("Password :");
        passwordInput = new PasswordField();

        //Entering salary :
        salaryLabel = new Label("Salary :");
        salaryCombo.getItems().addAll(
                "Decrease",
                "Increase"
        );
        salaryCombo.getSelectionModel().selectFirst();
        salaryInput = new TextField();
        Label amountLabel = new Label("Amount :");

        //Buttons :
        Button confirmButton = new Button("Confirm");
        Button cancelButton = new Button( " Cancel ");

        //Positioning labels, textFields and buttons :
        GridPane.setConstraints(title, 0, 0);
        GridPane.setConstraints(firstNameLabel, 0, 2);
        GridPane.setConstraints(familyNameLabel, 0, 3);
        GridPane.setConstraints(genderLabel, 0, 4);
        GridPane.setConstraints(passwordLabel, 0, 5);

        GridPane.setConstraints(firstNameInput, 1, 2);
        GridPane.setConstraints(familyNameInput, 1, 3);
        GridPane.setConstraints(genderCombo, 1, 4);
        GridPane.setConstraints(passwordInput, 1, 5);

        if (whoIsIt.equals(Accessibility.employee)) {
            GridPane.setConstraints(salaryLabel, 0, 6);
            GridPane.setConstraints(salaryCombo, 1, 6);
            GridPane.setConstraints(amountLabel, 0, 7);
            GridPane.setConstraints(salaryInput, 1, 7);
            GridPane.setConstraints(confirmButton, 1, 8);
            GridPane.setConstraints(cancelButton, 1, 9);

            //Adding all things to the layout :
            layout.getChildren().addAll(passwordLabel, passwordInput, firstNameInput,
                    firstNameLabel, familyNameInput, familyNameLabel, genderCombo, title,
                    confirmButton, cancelButton, genderLabel, salaryInput, salaryLabel, salaryCombo,
                    amountLabel);
        }

        else {
            GridPane.setConstraints(confirmButton, 1, 6);
            GridPane.setConstraints(cancelButton, 1, 7);

            //Adding all things to the layout :
            layout.getChildren().addAll(passwordLabel, passwordInput, firstNameInput,
                    firstNameLabel, familyNameInput, familyNameLabel, genderCombo, title,
                    confirmButton, cancelButton, genderLabel);
        }

        //Creating scene :
        changingPersonInfo = new Scene(layout, 350, 350);
        window.setScene(changingPersonInfo);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting buttons on action :
        confirmButton.setOnAction(e -> {

            if (noExceptions(index, whoIsIt)) {
                if (whoIsIt.equals(Accessibility.manager)) {
                    LogInActions.manager.setFamilyName(familyNameInput.getText());
                    LogInActions.manager.setFirstName(firstNameInput.getText());
                    LogInActions.manager.setGender(genderCombo.getValue());
                    LogInActions.manager.setPassword(passwordInput.getText());

                    String[] data = {LogInActions.manager.getIdentity_number(), LogInActions.manager.getPassword(),
                            LogInActions.manager.getFirstName(), LogInActions.manager.getFamilyName(),
                            String.valueOf(LogInActions.manager.getDateOfBirth()), LogInActions.manager.getGender()};

                    try {
                        Files.writeToFile("manager", data);
                    } catch (IOException ioexception) {
                        ioexception.printStackTrace();
                    }

                    MenuScene.adminMenu();
                }

                else if (whoIsIt.equals(Accessibility.employee)) {
                    Accessibility.getEmployees().get(index).setFirstName(firstNameInput.getText());
                    Accessibility.getEmployees().get(index).setFamilyName(familyNameInput.getText());
                    Accessibility.getEmployees().get(index).setPassword(passwordInput.getText());
                    Accessibility.getEmployees().get(index).setGender(genderCombo.getValue());

                    if (salaryCombo.getValue().equals("Decrease")) {
                        Accessibility.getEmployees().get(index).setSalary(
                                Accessibility.getEmployees().get(index).getSalary() -
                                        Long.parseLong(salaryInput.getText())
                        );
                    }

                    if (salaryCombo.getValue().equals("Increase")) {
                        Accessibility.getEmployees().get(index).setSalary(
                                Accessibility.getEmployees().get(index).getSalary() +
                                        Long.parseLong(salaryInput.getText())
                        );
                    }

                    //Getting back to the previous scene :
                    if (whoAreYou.equals(Accessibility.admin)) {
                        MenuScene.adminMenu();
                    }

                    else if (whoAreYou.equals(Accessibility.manager)) {
                        MenuScene.managerMenu();
                    }

                    Accessibility.savingInfoOfEmployeesToFile();
                }

                else if (whoIsIt.equals(Accessibility.client)) {
                    Accessibility.getClients().get(index).setFirstName(firstNameInput.getText());
                    Accessibility.getClients().get(index).setFamilyName(familyNameInput.getText());
                    Accessibility.getClients().get(index).setPassword(passwordInput.getText());
                    Accessibility.getClients().get(index).setGender(genderCombo.getValue());

                    //Getting back to previous scene :
                    if (whoAreYou.equals(Accessibility.admin)) {
                        MenuScene.adminMenu();
                    }

                    else if (whoAreYou.equals(Accessibility.manager)) {
                        MenuScene.managerMenu();
                    }

                    else if (whoAreYou.equals(Accessibility.employee)) {
                        MenuScene.employeeMenu();
                    }
                    Accessibility.savingInfoOfClientsToFile();
                    Accessibility.savingInfoOfAccountToFile();
                    Accessibility.savingInfoOfTransactionsToFile();
                }
                MessageScene.message("Changes Are Applied !", whoAreYou);
                window.close();

                identityNumberInput.clear();
                firstNameInput.clear();
                familyNameInput.clear();
                yearInput.clear();
                monthInput.clear();
                dayInput.clear();
                genderCombo.getSelectionModel().selectFirst();
                passwordInput.clear();
                salaryInput.clear();
            }
        });

        cancelButton.setOnAction(e -> {
            if (whoAreYou.equals(Accessibility.admin)) {
                MenuScene.adminMenu();
            }

            identityNumberInput.clear();
            firstNameInput.clear();
            familyNameInput.clear();
            yearInput.clear();
            monthInput.clear();
            dayInput.clear();
            genderCombo.getSelectionModel().selectFirst();
            passwordInput.clear();
            salaryInput.clear();

            window.close();
        });
    }

    private static boolean noExceptions (int index, String whoIsIt) {
        boolean properInput = true;

        try {
            if (!firstNameInput.getText().matches("[a-z|A-Z]+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            properInput = false;
            System.out.println(TextColor.RED + "First Name Should Not Include Numbers !" + TextColor.RESET);
            firstNameInput.clear();
        }

        try {
            if (!familyNameInput.getText().matches("[a-z|A-Z]+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Family Name Should Not Include Numbers !" + TextColor.RESET);
            properInput = false;
            familyNameInput.clear();
        }

        try {
            if (!passwordInput.getText().matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Password Should Only Include Numbers !" + TextColor.RESET);
            properInput = false;
            passwordInput.clear();
        }

        if (whoIsIt.equals(Accessibility.employee)) {
            try {
                if (!salaryInput.getText().matches("[\\d]+")) {
                    throw new ImproperInputException("");
                }
                try {
                    if (salaryCombo.getValue().equals("Decrease")) {
                        if (Accessibility.getEmployees().get(index).getSalary() - Integer.parseInt(salaryInput.getText())
                                < Employee.getLeastSalary()) {
                            throw new ImproperInputException("");
                        }
                    }
                } catch (Exception e) {
                    properInput = false;
                    System.out.println(TextColor.RED + "Based On Last Enactment Of Government," +
                            " Employees' Salary Can Not Be Less Than 28000000 Rials !" + TextColor.RESET);
                    salaryInput.clear();
                }
            } catch (Exception e) {
                System.out.println(TextColor.RED + "Amount Should Only Include Numbers !" + TextColor.RESET);
                properInput = false;
                salaryInput.clear();
            }
        }

        return properInput;
    }

    private static boolean noExceptions (String whoIsIt) {
        boolean properInput = true;

        try {
            if (!firstNameInput.getText().matches("[a-z|A-Z]+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            properInput = false;
            System.out.println(TextColor.RED + "First Name Should Not Include Numbers !" + TextColor.RESET);
            firstNameInput.clear();
        }

        try {
            if (!familyNameInput.getText().matches("[a-z|A-Z]+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Family Name Should Not Include Numbers !" + TextColor.RESET);
            properInput = false;
            familyNameInput.clear();
        }

        try {
            if (!identityNumberInput.getText().matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
            try {
                if (whoIsIt.equals(Accessibility.employee)) {
                    for (int i = 0; i < Accessibility.getEmployees().size(); i++) {
                        if (Accessibility.getEmployees().get(i).getIdentity_number()
                                .equals(identityNumberInput.getText())) {
                            throw new ImproperInputException("");
                        }
                    }

                    File myFile = new File(".\\manager.txt");
                    String identityNumber = "";
                    Scanner myReader = new Scanner(myFile);
                        while (myReader.hasNextLine()) {
                          String[] identityNumberArr = myReader.nextLine().split(":");
                          identityNumber = identityNumberArr[1];
                          myReader.close();
                          break;
                        }

                    if (identityNumber.equals(identityNumberInput.getText())){
                      throw new ImproperInputException("");
                    }
                }

                else if (whoIsIt.equals(Accessibility.client)) {
                    for (int i = 0; i < Accessibility.getClients().size(); i++) {
                        if (Accessibility.getClients().get(i).getIdentity_number()
                                .equals(identityNumberInput.getText())) {
                            throw new ImproperInputException("");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println(TextColor.RED + "Identity Number Belongs To Someone Else In This Bank !"
                        + TextColor.RESET);
                properInput = false;
                identityNumberInput.clear();
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Identity Number Should Only Include Numbers !" + TextColor.RESET);
            properInput = false;
            identityNumberInput.clear();
        }

        try {
            if (!passwordInput.getText().matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Password Should Only Include Numbers !" + TextColor.RESET);
            properInput = false;
            passwordInput.clear();
        }

        return properInput;
    }


    //It checks day, month and year of Person's date of birth :
    public static boolean dateMaker (String whoIsIt) {
        boolean validYear = false, validMonth = false, validDay = false;

       try {
           if (!yearInput.getText().matches("[\\d]+")) {
               throw new ImproperInputException("");
           }
           try {
               int year = Integer.parseInt(yearInput.getText());

               if (whoIsIt.equals(Accessibility.client) && year > 2002) {
                   throw new DateException("");
               }
               try {
                   if (year < 1880 || year > 2020) {
                       throw new DateException("");
                   }
                   try {
                       if ((whoIsIt.equals(Accessibility.manager) || whoIsIt.equals(Accessibility.employee)) &&
                               (year < 1965 || year > 1995)) {
                           System.out.println(year);
                           throw new DateException("");
                       }
                       validYear = true;
                   } catch (Exception e) {
                       System.out.println(TextColor.RED + "Only People Between 25-55 Years Old" +
                               " Are Allowed To Be Manager Or Employee !" + TextColor.RESET);
                       yearInput.clear();
                   }
               } catch (Exception e) {
                   System.out.println(TextColor.RED + "Year Out Of Bound !" + TextColor.RESET);
                   yearInput.clear();
               }
           } catch (Exception e) {
               System.out.println(TextColor.RED + "Under-18-Aged People Can Not Have An Account On Their Own !"
                       + TextColor.RESET);
               yearInput.clear();
           }
       } catch (Exception e) {
           System.out.println(TextColor.RED + "Year Should Only Include Numbers !" + TextColor.RESET);
           yearInput.clear();
       }

        try {
            if (!monthInput.getText().matches("[\\d]+")) {
                throw new ImproperInputException("");
            }
            try {
                int month = Integer.parseInt(monthInput.getText());

                if (month > 12 || month < 1) {
                    throw new DateException("");
                }
                validMonth = true;

                //Day :
                try {
                    if (!dayInput.getText().matches("[\\d]+")) {
                        throw new ImproperInputException("");
                    }
                    try {
                        int day = Integer.parseInt(dayInput.getText());

                        if (day > DateAndTime.daysOfMonth(month) || day < 1) {
                            throw new DateException("");
                        }
                        validDay = true;
                    } catch (Exception e) {
                        System.out.println(TextColor.RED + "Day Not Valid !" + TextColor.RESET);
                        dayInput.clear();
                    }
                } catch (Exception e) {
                    System.out.println(TextColor.RED + "Day Should Only Include Numbers !" + TextColor.RESET);
                    dayInput.clear();
                }

            } catch (Exception e) {
                System.out.println(TextColor.RED + "Month Not Valid !" + TextColor.RESET);

                monthInput.clear();
            }
        } catch (Exception e) {
            System.out.println(TextColor.RED + "Month Should Only Include Numbers !" + TextColor.RESET);
            monthInput.clear();
        }
        return validDay && validMonth && validYear;
    }
}
