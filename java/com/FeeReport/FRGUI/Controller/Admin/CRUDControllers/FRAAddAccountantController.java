package com.FeeReport.FRGUI.Controller.Admin.CRUDControllers;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRDatabase.IFRDatabase.IAccountantDB;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FRAAddAccountantController extends Controller {

    @FXML
    private TextField acntNameField;

    @FXML
    private TextField acntPasswordField;

    @FXML
    private TextField acntEmailField;

    @FXML
    private TextField acntNumberField;

    private final IAccountantDB database;

    public FRAAddAccountantController() {
        this.database = (IAccountantDB) DependencyInjector.injectDependency(IAccountantDB.class);
    }

    public void addAccountant(ActionEvent event) throws Exception {
        if (throwErrorsIfExisted()) return;
        addAccountantToDB();
        backToAccountant(event);
    }

    private boolean throwErrorsIfExisted() throws Exception {
        if (areFieldsEmptyOrBlank()) {
            alert(Alert.AlertType.ERROR, "Error alert",
                    "Cannot add accountant",
                    "Fields cannot be empty or blank");
            return true;
        }
        if (isNameTaken(acntNameField.getText())) {
            alert(Alert.AlertType.ERROR, "Error alert",
                    "Cannot add accountant",
                    "Name is taken please choose another");
            return true;
        }
        return false;
    }

    private boolean isNameTaken(String name) throws Exception {
        return database.getAccountant(name).next();
    }

    private Boolean areFieldsEmptyOrBlank() {
        var name = acntNameField.getText();
        var password = acntPasswordField.getText();
        var email = acntEmailField.getText();
        var number = acntNumberField.getText();
        return name.isEmpty() || name.isBlank() ||
                password.isBlank() ||password.isEmpty() ||
                email.isBlank() || email.isEmpty() ||
                number.isBlank() || number.isEmpty();
    }


    private void addAccountantToDB() throws Exception {
        database.addAccountant(acntNameField.getText(), acntPasswordField.getText(),
                acntEmailField.getText(), acntNumberField.getText());
    }

    public void backToAccountant(ActionEvent event) throws IOException {
        changeScene(getClass(), event, "/Scenes/Admin/FRAAccountantScene.fxml");
    }

}
