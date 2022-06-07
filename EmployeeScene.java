
//********************Graphics**********************//

package com.company;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.Comparator;

public class EmployeeScene {
    //Stage :
    static Stage window = new Stage();

    //personDeleterScene :
    static Scene employeeScene;

    public static void changingTheProfileOfEmployees(String whoAreYou){
        //GridPane :
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setVgap(8);
        layout.setHgap(10);

        //Buttons :
        Button addingButton = new Button("Adding");
        Button deletingButton = new Button("Deleting");
        Button changingTheInformationButton = new Button("Changing The Information");
        Button searchingButton = new Button("Searching For An Employee");
        Button showingAllButton = new Button("Showing The List Of All Employees");
        Button backButton = new Button("Back");

        //Positioning buttons :
        GridPane.setConstraints(addingButton, 0, 0);
        GridPane.setConstraints(deletingButton, 0, 1);
        GridPane.setConstraints(changingTheInformationButton, 0, 2);
        GridPane.setConstraints(searchingButton, 0, 3);
        GridPane.setConstraints(showingAllButton, 0, 4);
        GridPane.setConstraints(backButton, 0, 5);

        //Adding buttons to the layout :
        layout.getChildren().addAll(addingButton, deletingButton, changingTheInformationButton,
                searchingButton, showingAllButton, backButton);

        //Adding layout to the scene :
        employeeScene = new Scene(layout, 300, 250);
        window.setScene(employeeScene);
        window.setTitle("Farabi Bank");
        window.show();

        //Setting actions on buttons :
        addingButton.setOnAction(e -> {
            PersonScene.addingPersonScene(Accessibility.employee, whoAreYou);

            window.close();
        });

        deletingButton.setOnAction(e -> {
            try {
                if (Accessibility.getEmployees().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Employee Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getEmployees().size() != 0) {
                Accessibility.personDeleterScene(Accessibility.employee, whoAreYou);
                Accessibility.savingInfoOfEmployeesToFile();
            }
        });

        changingTheInformationButton.setOnAction(e -> {
            try {
                if (Accessibility.getEmployees().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Employee Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getEmployees().size() != 0) {
                Accessibility.searchingForChanging(Accessibility.employee, whoAreYou);
            }
        });

        searchingButton.setOnAction(e -> {
            try {
                if (Accessibility.getEmployees().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Employee Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }

            if (Accessibility.getEmployees().size() != 0) {
                Accessibility.identityNumberGetterScene(Accessibility.employee, whoAreYou);
            }
        });

        showingAllButton.setOnAction(e -> {
            try {
                if (Accessibility.getEmployees().size() == 0) {
                    throw new ImproperInputException("");
                }
            } catch (ImproperInputException improperInputException) {
                System.out.println(TextColor.RED + "No Employee Exists Yet !\nChoose Another Option Or Leave !" +
                        TextColor.RESET);
            }
            if (Accessibility.getEmployees().size() != 0) {
                displayTheEmployees(whoAreYou);
            }
        });

        backButton.setOnAction(e -> {
            if (whoAreYou.equals(Accessibility.admin)) {
                MenuScene.adminMenu();
            }
            else if (whoAreYou.equals(Accessibility.manager)) {
                MenuScene.managerMenu();
            }
            window.close();
        });

    }


    //Showing the list of Employees :
    public static void displayTheEmployees (String whoAreyou){
        Collections.sort(Accessibility.getEmployees(), (employee1, employee2) -> {
            int compareValue = employee1.familyName.compareToIgnoreCase(employee2.familyName);

            if (compareValue == 0) {
                return employee1.firstName.compareToIgnoreCase(employee2.firstName);
            }

            return compareValue;
        });

        TableView<Employee> employeeTableView;

        //First name column :
        TableColumn<Employee, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setMinWidth(200);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        //Family name column :
        TableColumn<Employee, String> familyNameColumn = new TableColumn<>("Family Name");
        familyNameColumn.setMinWidth(200);
        familyNameColumn.setCellValueFactory(new PropertyValueFactory<>("familyName"));

        //Identity number column :
        TableColumn<Employee, String> identity_numberColumn = new TableColumn<>("Identity Number");
        identity_numberColumn.setMinWidth(200);
        identity_numberColumn.setCellValueFactory(new PropertyValueFactory<>("identity_number"));

        //Date Of birth column :
        TableColumn<Employee, String> dateOfBirthColumn = new TableColumn<>("Date Of Birth");
        dateOfBirthColumn.setMinWidth(200);
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));

        //Salary column :
        TableColumn<Employee, String> salaryColumn = new TableColumn<>("Salary");
        salaryColumn.setMinWidth(200);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        //Table :
        employeeTableView = new TableView<>();
        employeeTableView.setItems(getObservableEmployees());
        employeeTableView.getColumns().addAll(firstNameColumn, familyNameColumn, identity_numberColumn,
                dateOfBirthColumn, salaryColumn);

        //Button :
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            changingTheProfileOfEmployees(whoAreyou);
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(employeeTableView, closeButton);

        Scene displayTable = new Scene(layout);
        window.setScene(displayTable);
        window.show();
    }

    //Get all of the products
    private static ObservableList<Employee> getObservableEmployees() {
        ObservableList<Employee> employees = FXCollections.observableArrayList();

        employees.addAll(Accessibility.getEmployees());

        return employees;
    }
}
