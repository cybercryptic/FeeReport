package com.FeeReport.FRGUI.Controller.Accountant.CRUDControllers;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRDatabase.IFRDatabase.IStudentDB;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

public class FRAAddStudentController extends Controller {

    @FXML
    private TextField studentNameField;
    @FXML
    private TextField studentEmailField;
    @FXML
    private TextField studentCourseField;
    @FXML
    private TextField studentFeeField;
    @FXML
    private TextField studentPaidField;
    @FXML
    private TextField studentDueField;
    @FXML
    private TextArea studentAddressArea;
    @FXML
    private TextField studentCityField;
    @FXML
    private TextField studentStateField;
    @FXML
    private TextField studentCountryField;
    @FXML
    private TextField studentNumberField;

    private final IStudentDB database;

    public FRAAddStudentController() {
        this.database = (IStudentDB) DependencyInjector.injectDependency(IStudentDB.class);
    }

//    public void initialize() {
//        setFields();
//        // TODO: This will set temp data in fields
//    }
    @FXML
    private void addStudent(ActionEvent event) throws Exception {
        if (throwErrorsIfExisted()) return;
        addStudentToDB();
        backToStudent(event);
    }

    private boolean throwErrorsIfExisted() throws Exception {
        if (areFieldsEmptyOrBlank()) {
            alert(Alert.AlertType.ERROR, "Error alert",
                    "Cannot add student",
                    "Fields cannot be empty or blank");
            return true;
        }
        if (isNameTaken(studentNameField.getText())) {
            alert(Alert.AlertType.ERROR, "Error alert",
                    "Cannot add student",
                    "Name is taken please choose another");
            return true;
        }
        return false;
    }

    private boolean areFieldsEmptyOrBlank() {
        var name = studentNameField.getText();
        var email = studentEmailField.getText();
        var course = studentCourseField.getText();
        var fee = studentFeeField.getText();
        var paid = studentPaidField.getText();
        var due = studentDueField.getText();
        var address = studentAddressArea.getText();
        var city = studentCityField.getText();
        var state = studentStateField.getText();
        var country = studentCountryField.getText();
        var number = studentNumberField.getText();
        return name.isEmpty() || name.isBlank() ||
                email.isEmpty() || name.isBlank() ||
                course.isEmpty() || course.isBlank() ||
                fee.isEmpty() || fee.isBlank() ||
                paid.isEmpty() || paid.isBlank() ||
                due.isEmpty() || due.isBlank() ||
                address.isEmpty() || address.isBlank() ||
                city.isEmpty() || city.isBlank() ||
                state.isEmpty() || state.isBlank() ||
                country.isEmpty() || country.isBlank() ||
                number.isEmpty() || number.isBlank();
    }

    private boolean isNameTaken(String name) throws Exception {
        return database.getStudent(name).next();
    }

    private void addStudentToDB() throws Exception {
        database.addStudent(studentNameField.getText(), studentEmailField.getText(),
            studentCourseField.getText(), studentFeeField.getText(), studentPaidField.getText(),
            studentDueField.getText(), studentAddressArea.getText(), studentCityField.getText(),
            studentStateField.getText(), studentCountryField.getText(), studentNumberField.getText());

    }

    private void setFields() {
        studentNameField.setText("Vijay");
        studentEmailField.setText("vijayk23@gmail.com");
        studentCourseField.setText("Java");
        studentFeeField.setText("10000");
        studentPaidField.setText("6000");
        studentDueField.setText("4000");
        studentAddressArea.setText("Ap, india, 593493");
        studentCityField.setText("vizag");
        studentStateField.setText("Ap");
        studentCountryField.setText("India");
        studentNumberField.setText("9258204146");
    }

    @FXML
    private void backToStudent(ActionEvent event) throws IOException {
        changeScene(getClass(), event, "/Scenes/Accountant/FRAStudentScene.fxml");
    }

}
