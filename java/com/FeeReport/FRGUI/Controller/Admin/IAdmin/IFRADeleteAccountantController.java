package com.FeeReport.FRGUI.Controller.Admin.IAdmin;

import com.FeeReport.FRGUI.Controller.Admin.Accountant;
import javafx.event.ActionEvent;

public interface IFRADeleteAccountantController {
    void deleteAccountant(ActionEvent event, Accountant accountant) throws Exception;
}
