package com.FeeReport.FRGUI.Controller.Admin.CRUDControllers;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRDatabase.IFRDatabase.IAccountantDB;
import com.FeeReport.FRGUI.Controller.Admin.Accountant;
import com.FeeReport.FRGUI.Controller.Admin.IAdmin.IFRADeleteAccountantController;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class FRADeleteAccountantController extends Controller implements IFRADeleteAccountantController {

    private final IAccountantDB database;

    public FRADeleteAccountantController() {
        database = (IAccountantDB) DependencyInjector.injectDependency(IAccountantDB.class);
    }

    public void deleteAccountant(ActionEvent event, Accountant accountant) throws Exception {
        var result = getDelete_confirmation(accountant);
        if (result == ButtonType.OK) {
            deleteAccountantInDB(accountant);
            changeScene(getClass(), event, "/Scenes/Admin/FRAAccountantScene.fxml");
        }
    }

    private ButtonType getDelete_confirmation(Accountant accountant) {
        return alert(Alert.AlertType.CONFIRMATION, "Delete Confirmation",
                "Are you sure you want to delete?", "Delete Accountant: " + accountant.getName());
    }

    private void deleteAccountantInDB(Accountant accountant) throws Exception {
        database.deleteAccountant(accountant.getName());
    }
}
