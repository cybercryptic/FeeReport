package com.FeeReport.FRGUI.Controller.Accountant.CRUDControllers;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRDatabase.IFRDatabase.IStudentDB;
import com.FeeReport.FRGUI.Controller.Accountant.IAccountant.IFRAViewStudentController;
import com.FeeReport.FRGUI.Controller.Accountant.Student;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FRAViewStudentController extends Controller implements IFRAViewStudentController {

    private final IStudentDB database;

    public FRAViewStudentController() {
        this.database = (IStudentDB) DependencyInjector.injectDependency(IStudentDB.class);
    }

    public ObservableList<Student> getStudents() throws Exception {
        ObservableList<Student> students = FXCollections.observableArrayList();
        addStudentsToList(students);
        return students;
    }

    private void addStudentsToList(ObservableList<Student> students) throws Exception {
        var set = database.getStudents();
        addStudentToList(set,1, students);
    }

    private void addStudentToList(ResultSet set, int id, ObservableList<Student> students) throws SQLException {
        if (!set.next()) return;
        var name = set.getString("Name");
        var email = set.getString("Email");
        var course = set.getString("Course");
        var fee = set.getString("Fee");
        var paid = set.getString("Paid");
        var due = set.getString("Due");
        var address = set.getString("Address");
        var city = set.getString("City");
        var state = set.getString("State");
        var country = set.getString("Country");
        var number = set.getString("Number");
        var student = new Student();
        student.setRollNo(String.valueOf(id));
        student.setName(name);
        student.setEmail(email);
        student.setCourse(course);
        student.setFee(fee);
        student.setPaid(paid);
        student.setDue(due);
        student.setAddress(address);
        student.setCity(city);
        student.setState(state);
        student.setCountry(country);
        student.setNumber(number);
        students.add(student);
        addStudentToList(set, ++id, students);
    }
}
