package com.FeeReport.FRGUI.Controller.Accountant.CRUDControllers;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRDatabase.IFRDatabase.IStudentDB;
import com.FeeReport.FRGUI.Controller.Accountant.IAccountant.IFRAUpdateStudentController;
import com.FeeReport.FRGUI.Controller.Accountant.Student;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FRAUpdateStudentController extends Controller implements IFRAUpdateStudentController {
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

    private Student student;
    private String prevName;

    private final IStudentDB database;

    public FRAUpdateStudentController() {
        this.database = (IStudentDB) DependencyInjector.injectDependency(IStudentDB.class);
    }

    public void init(Student student) {
        this.student = student;
        setFields(student);
        prevName = student.getName();
    }

    private void setFields(Student student) {
        studentNameField.setText(student.getName());
        studentEmailField.setText(student.getEmail());
        studentCourseField.setText(student.getCourse());
        studentFeeField.setText(student.getFee());
        studentPaidField.setText(student.getPaid());
        studentDueField.setText(student.getDue());
        studentAddressArea.setText(student.getAddress());
        studentCityField.setText(student.getCity());
        studentStateField.setText(student.getState());
        studentCountryField.setText(student.getCountry());
        studentNumberField.setText(student.getNumber());
    }

    @FXML
    private void updateStudent(ActionEvent event) throws Exception {
        if (throwErrorsIfExisted()) return;
        var id = getStudentID();
        updateStudentInDB(id);
        backToStudent(event);
    }

    private int getStudentID() throws Exception {
        return database.getStudentID(student.getName());
    }

    private boolean throwErrorsIfExisted() throws Exception {
        if (isNameTaken(studentNameField.getText())) {
            alert(Alert.AlertType.ERROR, "Error", "Name Error",
                    "Name is taken please choose another");
            return true;
        }
        return false;
    }

    private boolean isNameTaken(String name) throws Exception {
        if (prevName.equals(name)) return false;
        else return database.getStudent(name).next();
    }

    private void updateStudentInDB(int id) throws Exception {
        database.updateStudent(id, studentNameField.getText(),
                studentEmailField.getText(), studentCourseField.getText(), studentFeeField.getText(),
                studentPaidField.getText(), studentDueField.getText(), studentAddressArea.getText(),
                studentCityField.getText(), studentStateField.getText(), studentCountryField.getText(),
                studentNumberField.getText());
    }

    @FXML
    private void backToStudent(ActionEvent event) throws IOException {
        changeScene(getClass(), event, "/Scenes/Accountant/FRAStudentScene.fxml");
    }
}
