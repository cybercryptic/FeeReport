package com.FeeReport.FRGUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public abstract class Controller {

    protected void changeScene(Class<? extends Controller> OClass, ActionEvent event, String location) throws IOException {
        var loader = new FXMLLoader(OClass.getResource(location));
        var stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        var scene = new Scene(loader.load());
        stage.setScene(scene);
        CompletableFuture.runAsync(stage::show);
    }

    protected ButtonType alert(Alert.AlertType alertType, String title, String header, String content) {
        var alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
        return alert.getResult();
    }
}
