package com.FeeReport.FRGUI.Controller.Accountant.IAccountant;


import com.FeeReport.FRGUI.Controller.Accountant.Student;
import javafx.collections.ObservableList;

public interface IFRAViewStudentController {
    ObservableList<Student> getStudents() throws Exception;
}
