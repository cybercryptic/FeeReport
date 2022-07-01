module com.FeeReport.FRGUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.FeeReport.FRGUI.Controller;
    opens com.FeeReport.FRGUI.Controller to javafx.fxml;
    exports com.FeeReport.FRGUI.Controller.Admin;
    opens com.FeeReport.FRGUI.Controller.Admin to javafx.fxml;
    exports com.FeeReport.FRGUI.Controller.Accountant;
    opens com.FeeReport.FRGUI.Controller.Accountant to javafx.fxml;
    exports com.FeeReport.FRGUI.Controller.Admin.CRUDControllers;
    opens com.FeeReport.FRGUI.Controller.Admin.CRUDControllers to javafx.fxml;
    exports com.FeeReport.FRGUI.Controller.Accountant.CRUDControllers;
    opens com.FeeReport.FRGUI.Controller.Accountant.CRUDControllers to javafx.fxml;
    exports com.FeeReport;
    opens com.FeeReport to javafx.fxml;
    exports com.FeeReport.FRGUI;
    opens com.FeeReport.FRGUI to javafx.fxml;
    exports com;
    opens com to javafx.fxml;
}