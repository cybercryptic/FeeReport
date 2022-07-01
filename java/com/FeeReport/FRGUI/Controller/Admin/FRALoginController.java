package com.FeeReport.FRGUI.Controller.Admin;

import com.FeeReport.FRGUI.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.IOException;

public class FRALoginController extends Controller {

    @FXML
    private TextField loginNameField;
    @FXML
    private TextField loginPasswordField;
    public void login(ActionEvent event) throws IOException {

        if (areCredentialsCorrect())
            changeScene(getClass(), event, "/Scenes/Admin/FRAAccountantScene.fxml");
        else
            alert(Alert.AlertType.ERROR, "Error alert", "Wrong credentials",
                    "Name or Password is wrong");

    }

    private boolean areCredentialsCorrect() {
        return (loginNameField.getText().equals("admin")
                && loginPasswordField.getText().equals("pass"));
    }

    public void switchToMainScene(ActionEvent event) throws IOException {
        changeScene(getClass(), event, "/Scenes/FRMainScene.fxml");
    }

}
