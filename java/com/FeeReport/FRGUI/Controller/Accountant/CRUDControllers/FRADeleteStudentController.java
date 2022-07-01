package com.FeeReport.FRGUI.Controller.Accountant.CRUDControllers;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRDatabase.IFRDatabase.IStudentDB;
import com.FeeReport.FRGUI.Controller.Accountant.IAccountant.IFRADeleteStudentController;
import com.FeeReport.FRGUI.Controller.Accountant.Student;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class FRADeleteStudentController extends Controller implements IFRADeleteStudentController {

    private final IStudentDB database;

    public FRADeleteStudentController() {
        this.database = (IStudentDB) DependencyInjector.injectDependency(IStudentDB.class);
    }

    public void deleteStudent(ActionEvent event, Student student) throws Exception {
        var result = getDelete_confirmation(student);
        if (result == ButtonType.OK) {
            deleteStudentInDB(student);
            changeScene(getClass(), event, "/Scenes/Accountant/FRAStudentScene.fxml");
        }
    }

    private ButtonType getDelete_confirmation(Student student) {
        return alert(Alert.AlertType.CONFIRMATION, "Delete Confirmation",
                "Are you sure you want to delete?", "Delete Student: " + student.getName());
    }

    private void deleteStudentInDB(Student student) throws Exception {
        database.deleteStudent(student.getName());
    }
}
