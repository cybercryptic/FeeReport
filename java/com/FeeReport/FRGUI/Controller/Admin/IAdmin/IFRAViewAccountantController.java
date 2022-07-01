package com.FeeReport.FRGUI.Controller.Admin.IAdmin;


import com.FeeReport.FRGUI.Controller.Admin.Accountant;
import javafx.collections.ObservableList;

public interface IFRAViewAccountantController {
    ObservableList<Accountant> getAccountantsList() throws Exception;
}
