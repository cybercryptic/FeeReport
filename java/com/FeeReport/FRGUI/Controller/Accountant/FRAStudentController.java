package com.FeeReport.FRGUI.Controller.Accountant;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRGUI.Controller.Accountant.IAccountant.IFRADeleteStudentController;
import com.FeeReport.FRGUI.Controller.Accountant.IAccountant.IFRAUpdateStudentController;
import com.FeeReport.FRGUI.Controller.Accountant.IAccountant.IFRAViewStudentController;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class FRAStudentController extends Controller {

    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> rollNo;
    @FXML
    private TableColumn<Student, String> name;
    @FXML
    private TableColumn<Student, String> email;
    @FXML
    private TableColumn<Student, String> course;
    @FXML
    private TableColumn<Student, String> fee;
    @FXML
    private TableColumn<Student, String> paid;
    @FXML
    private TableColumn<Student, String> due;
    @FXML
    private TableColumn<Student, String> address;
    @FXML
    private TableColumn<Student, String> city;
    @FXML
    private TableColumn<Student, String> state;
    @FXML
    private TableColumn<Student, String> country;
    @FXML
    private TableColumn<Student, String> contactNumber;
    private final IFRAViewStudentController viewController;
    private final IFRADeleteStudentController deleteController;

    public FRAStudentController() {
        this.viewController = (IFRAViewStudentController) DependencyInjector.injectDependency(IFRAViewStudentController.class);
        this.deleteController = (IFRADeleteStudentController) DependencyInjector.injectDependency(IFRADeleteStudentController.class);
    }

    public void initialize() throws Exception {
        addItemsToTable();
    }

    public void addItemsToTable() throws Exception {
        rollNo.setCellValueFactory(new PropertyValueFactory<>("rollNo"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        course.setCellValueFactory(new PropertyValueFactory<>("course"));
        fee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        paid.setCellValueFactory(new PropertyValueFactory<>("paid"));
        due.setCellValueFactory(new PropertyValueFactory<>("due"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        state.setCellValueFactory(new PropertyValueFactory<>("state"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        contactNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        studentTable.setItems(viewController.getStudents());
    }

    @FXML
    private void addStudent(ActionEvent event) throws IOException {
        changeScene(getClass(), event, "/Scenes/Accountant/FRAAddStudentScene.fxml");
    }

    @FXML
    private void deleteStudent(ActionEvent event) throws Exception {
        var student = getSelectedStudentFromTable();
        if (isStudentNull(student)) {
            showNoRowSelectedAlert();
            return;
        }
        deleteController.deleteStudent(event, student);
    }

    @FXML
    private void updateStudent(ActionEvent event) throws Exception {
        var student = getSelectedStudentFromTable();
        if (isStudentNull(student)) {
            showNoRowSelectedAlert();
            return;
        }
        var loader = new FXMLLoader(getClass().getResource("/Scenes/Accountant/FRAUpdateStudentScene.fxml"));
        var stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        var scene = new Scene(loader.load());
        IFRAUpdateStudentController controller = loader.getController();
        controller.init(student);
        stage.setScene(scene);
        CompletableFuture.runAsync(stage::show);
    }

    private Student getSelectedStudentFromTable() {
        return studentTable.getSelectionModel().getSelectedItem();
    }

    private void showNoRowSelectedAlert() {
        alert(Alert.AlertType.INFORMATION, "Information alert","No row selected",
                "Select a row to continue...");
    }

    private boolean isStudentNull(Student student) {
        return student == null;
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        var alert = alert(Alert.AlertType.CONFIRMATION, "Confirmation alert",
                "Log out", "Are you sure you want to log out?");
        if (isAlertResponseOk(alert))
            changeScene(getClass(), event, "/Scenes/FRMainScene.fxml");
    }

    private boolean isAlertResponseOk(ButtonType alert) {
        return alert == ButtonType.OK;
    }
}
