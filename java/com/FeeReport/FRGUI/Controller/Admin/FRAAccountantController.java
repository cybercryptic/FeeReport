package com.FeeReport.FRGUI.Controller.Admin;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRGUI.Controller.Admin.IAdmin.IFRADeleteAccountantController;
import com.FeeReport.FRGUI.Controller.Admin.IAdmin.IFRAUpdateAccountantController;
import com.FeeReport.FRGUI.Controller.Admin.IAdmin.IFRAViewAccountantController;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class FRAAccountantController extends Controller {

    @FXML
    private TableView<Accountant> acntTable;

    @FXML
    private TableColumn<Accountant, String> acntIdClmn;

    @FXML
    private TableColumn<Accountant, String> acntNameClmn;

    @FXML
    private TableColumn<Accountant, String> acntPasswordClmn;

    @FXML
    private TableColumn<Accountant, String> acntEmailClmn;

    @FXML
    private TableColumn<Accountant, String> acntNumberClmn;

    private final IFRAViewAccountantController viewController;
    private final IFRADeleteAccountantController deleteController;

    public FRAAccountantController() {
        this.viewController = (IFRAViewAccountantController) DependencyInjector.injectDependency(IFRAViewAccountantController.class);
        this.deleteController = (IFRADeleteAccountantController) DependencyInjector.injectDependency(IFRADeleteAccountantController.class);
    }

    public void initialize() throws Exception {
        loadAccountantsToTable();
    }

    private void loadAccountantsToTable() throws Exception {
        acntIdClmn.setCellValueFactory(new PropertyValueFactory<>("id"));
        acntNameClmn.setCellValueFactory(new PropertyValueFactory<>("name"));
        acntPasswordClmn.setCellValueFactory(new PropertyValueFactory<>("password"));
        acntEmailClmn.setCellValueFactory(new PropertyValueFactory<>("email"));
        acntNumberClmn.setCellValueFactory(new PropertyValueFactory<>("number"));
        acntTable.setItems(viewController.getAccountantsList());
    }

    @FXML
    private void addAccountant(ActionEvent event) throws IOException {
        changeScene(getClass(), event, "/Scenes/Admin/FRAAddAccountantScene.fxml");
    }

    @FXML
    private void deleteAccountant(ActionEvent event) throws Exception {
        var accountant = acntTable.getSelectionModel().getSelectedItem();
        if (isAccountantNull(accountant)) {
            showNoRowSelectedAlert();
            return;
        }
        deleteController.deleteAccountant(event, accountant);
    }

    @FXML
    private void updateAccountant(ActionEvent event) throws Exception {
        var accountant = acntTable.getSelectionModel().getSelectedItem();
        if (isAccountantNull(accountant)) {
            showNoRowSelectedAlert();
            return;
        }
        var loader = new FXMLLoader(getClass().getResource("/Scenes/Admin/FRAUpdateAccountantScene.fxml"));
        var stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        var scene = new Scene(loader.load());
        stage.setScene(scene);
        IFRAUpdateAccountantController controller = loader.getController();
        controller.init(accountant);
        CompletableFuture.runAsync(stage::show);
    }

    private boolean isAccountantNull(Accountant accountant) {
        return accountant == null;
    }

    private void showNoRowSelectedAlert() {
        alert(Alert.AlertType.INFORMATION, "Information alert",
                "No row selected",
                "Select a row to continue...");
    }

    public void logout(ActionEvent event) throws IOException {
        var alert = alert(Alert.AlertType.CONFIRMATION, "Confirmation alert",
                "Log out", "Are you sure you want to log out?");
        if (isAlertResponseOk(alert))
            changeScene(getClass(), event, "/Scenes/FRMainScene.fxml");
    }

    private boolean isAlertResponseOk(ButtonType alert) {
        return alert == ButtonType.OK;
    }
}
