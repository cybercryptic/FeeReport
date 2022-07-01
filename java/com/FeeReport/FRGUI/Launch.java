package com.FeeReport.FRGUI;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRDatabase.AccountantDB;
import com.FeeReport.FRDatabase.DerbyDB;
import com.FeeReport.FRDatabase.FeeReportDB;
import com.FeeReport.FRDatabase.IFRDatabase.IAccountantDB;
import com.FeeReport.FRDatabase.IFRDatabase.IFeeReportDB;
import com.FeeReport.FRDatabase.IFRDatabase.IDerbyDB;
import com.FeeReport.FRDatabase.IFRDatabase.IStudentDB;
import com.FeeReport.FRDatabase.StudentDB;
import com.FeeReport.FRGUI.Controller.Accountant.CRUDControllers.FRADeleteStudentController;
import com.FeeReport.FRGUI.Controller.Accountant.CRUDControllers.FRAViewStudentController;
import com.FeeReport.FRGUI.Controller.Accountant.IAccountant.IFRADeleteStudentController;
import com.FeeReport.FRGUI.Controller.Accountant.IAccountant.IFRAViewStudentController;
import com.FeeReport.FRGUI.Controller.Admin.CRUDControllers.FRADeleteAccountantController;
import com.FeeReport.FRGUI.Controller.Admin.CRUDControllers.FRAViewAccountantController;
import com.FeeReport.FRGUI.Controller.Admin.IAdmin.IFRADeleteAccountantController;
import com.FeeReport.FRGUI.Controller.Admin.IAdmin.IFRAViewAccountantController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Launch extends Application {

    private final IFeeReportDB database;
    private final IDerbyDB derbyDB;

    public Launch() throws Exception {
        var dbLocation = "E:\\Projects\\Java\\Intellij_Idea\\Projects\\FeeReport\\src\\main\\resources\\Database\\FeeReportDB";
        this.derbyDB = new DerbyDB(dbLocation);
        this.database = new FeeReportDB(derbyDB);
    }

    @Override
    public void start(Stage stage) throws Exception {
        loadDependencies();
        loadGUI(stage);
    }


    private void loadDependencies() {
        DependencyInjector.addDependency(IAccountantDB.class, new AccountantDB(derbyDB));
        DependencyInjector.addDependency(IStudentDB.class, new StudentDB(derbyDB));
        DependencyInjector.addDependency(IFRAViewAccountantController.class, new FRAViewAccountantController());
        DependencyInjector.addDependency(IFRADeleteAccountantController.class, new FRADeleteAccountantController());
        DependencyInjector.addDependency(IFRADeleteStudentController.class, new FRADeleteStudentController());
        DependencyInjector.addDependency(IFRAViewStudentController.class, new FRAViewStudentController());
    }

    private void loadGUI(Stage stage) throws IOException {
        var loader = new FXMLLoader(getClass().getResource("/Scenes/FRMainScene.fxml"));
        var scene = new Scene(loader.load());
        stage.setTitle("Fee Report");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(WEvent -> closeDB());
    }

    private void closeDB() {
        try {
            database.closeAndShutdown();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
