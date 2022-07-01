package com.FeeReport.FRGUI.Controller.Accountant.IAccountant;

import com.FeeReport.FRGUI.Controller.Accountant.Student;
import javafx.event.ActionEvent;

public interface IFRADeleteStudentController {
    void deleteStudent(ActionEvent event, Student student) throws Exception;
}
