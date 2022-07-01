package com.FeeReport.FRGUI.Controller.Admin.CRUDControllers;

import com.FeeReport.DependencyInjector;
import com.FeeReport.FRDatabase.IFRDatabase.IAccountantDB;
import com.FeeReport.FRGUI.Controller.Admin.Accountant;
import com.FeeReport.FRGUI.Controller.Admin.IAdmin.IFRAViewAccountantController;
import com.FeeReport.FRGUI.Controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FRAViewAccountantController extends Controller implements IFRAViewAccountantController {

    private final IAccountantDB database;

    public FRAViewAccountantController() {
        this.database = (IAccountantDB) DependencyInjector.injectDependency(IAccountantDB.class);
    }

    public ObservableList<Accountant> getAccountantsList() throws Exception {
        ObservableList<Accountant> accountants = FXCollections.observableArrayList();
        addAccountantsToList(accountants);
        return accountants;
    }

    private void addAccountantsToList(ObservableList<Accountant> accountants) throws Exception {
        var set = database.getAccountants();
        addAccountantsDBToList(set, 1, accountants);
    }

    private void addAccountantsDBToList(ResultSet set, int id, ObservableList<Accountant> accountants) throws SQLException {
        if (!set.next()) return;
        var name = set.getString("Name");
        var password = set.getString("Password");
        var email = set.getString("Email");
        var number = set.getString("Number");
        var accountant = new Accountant(String.valueOf(id), name, password, email, number);
        accountants.add(accountant);
        addAccountantsDBToList(set, ++id, accountants);
    }
}
