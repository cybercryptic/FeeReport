package com.FeeReport.FRGUI.Controller.Accountant;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRDatabase.IFRDatabase.IAccountantDB;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.ResultSet;

public class FRALoginController extends Controller {

    @FXML
    private TextField loginNameField;

    @FXML
    private TextField loginPasswordField;

    private final IAccountantDB database;

    public FRALoginController() {
        this.database = (IAccountantDB) DependencyInjector.injectDependency(IAccountantDB.class);
    }

    public void login(ActionEvent event) throws Exception {
        var name = loginNameField.getText();
        var password = loginPasswordField.getText();
        if (areCredentialsCorrect(name, password))
            changeScene(getClass(), event, "/Scenes/Accountant/FRAStudentScene.fxml");
        else
            alert(Alert.AlertType.ERROR, "Error", "Wrong credentials",
                    "Name or Password is wrong");
    }

    private boolean areCredentialsCorrect(String loginName, String loginPassword) throws Exception {
        var resultSet = getAccountantResultSet(loginName);
        while (resultSet.next()) {
            var dbLoginName = resultSet.getString("Name");
            var dbLoginPassword = resultSet.getString("Password");
            if (dbLoginName.equals(loginName) && dbLoginPassword.equals(loginPassword))
                return true;
        }
        return false;
    }

    private ResultSet getAccountantResultSet(String loginName) throws Exception {
        return database.getAccountant(loginName);
    }

    public void backToMain(ActionEvent event) throws IOException {
        changeScene(getClass(), event, "/Scenes/FRMainScene.fxml");
    }
}
