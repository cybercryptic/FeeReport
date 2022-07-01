package com.FeeReport.FRGUI.Controller.Admin.CRUDControllers;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRDatabase.IFRDatabase.IAccountantDB;
import com.FeeReport.FRGUI.Controller.Admin.Accountant;
import com.FeeReport.FRGUI.Controller.Admin.IAdmin.IFRAUpdateAccountantController;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FRAUpdateAccountantController extends Controller implements IFRAUpdateAccountantController {

    @FXML
    private TextField acntNameField;

    @FXML
    private TextField acntPasswordField;

    @FXML
    private TextField acntEmailField;

    @FXML
    private TextField acntNumberField;

    private Accountant accountant;
    private String prevName;

    private final IAccountantDB database;

    public FRAUpdateAccountantController() {
        this.database = (IAccountantDB) DependencyInjector.injectDependency(IAccountantDB.class);
    }

    public void init(Accountant accountant) {
        this.accountant = accountant;
        setFields(accountant);
        prevName = accountant.getName();
    }

    private void setFields(Accountant accountant) {
        acntNameField.setText(accountant.getName());
        acntPasswordField.setText(accountant.getPassword());
        acntEmailField.setText(accountant.getEmail());
        acntNumberField.setText(accountant.getNumber());
    }

    @FXML
    private void updateAccountant(ActionEvent event) throws Exception {
        if (showAlertsIfExisted()) return;
        var id = getAccountantID(accountant.getName());
        updateAccountantInDB(id);
        backToAccountant(event);
    }

    private int getAccountantID(String name) throws Exception {
        return database.getAccountantID(name);
    }

    private boolean showAlertsIfExisted() throws Exception {
        if (isNameTaken(acntNameField.getText())) {
            alert(Alert.AlertType.ERROR, "Error alert",
                    "Cannot update accountant",
                    "Name is taken please choose another");
            return true;
        }
        return false;
    }

    private boolean isNameTaken(String name) throws Exception {
        if (prevName.equals(name)) return false;
        else return database.getAccountant(name).next();
    }

    private void updateAccountantInDB(int id) throws Exception {
        database.updateAccountant(id, acntNameField.getText(), acntPasswordField.getText(),
                acntEmailField.getText(), acntNumberField.getText());
    }

    @FXML
    private void backToAccountant(ActionEvent event) throws IOException {
        changeScene(getClass(), event, "/Scenes/Admin/FRAAccountantScene.fxml");
    }
}
