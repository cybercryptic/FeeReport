package com.FeeReport.FRGUI.Controller;

import javafx.event.ActionEvent;

import java.io.IOException;

public class FRMainController extends Controller {

    public void switchToAdminScene(ActionEvent event) throws IOException {
        changeScene(getClass(), event, "/Scenes/Admin/FRALoginScene.fxml");
    }

    public void switchToAccountantScene(ActionEvent event) throws IOException {
        changeScene(getClass(), event, "/Scenes/Accountant/FRALoginScene.fxml");
    }
}
