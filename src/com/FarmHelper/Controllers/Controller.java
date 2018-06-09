package com.FarmHelper.Controllers;

import com.FarmHelper.Repository.DataEntry;
import com.FarmHelper.Services.TransactionWithSql;
import com.FarmHelper.Services.UserDataTransaction;
import com.FarmHelper.User;
import javafx.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.*;


public class Controller {

    private List<DataEntry> list = new ArrayList<>();

    public void onClick(ActionEvent actionEvent) {


        User user = new User("1","1");
        UserDataTransaction userDataTransaction = new TransactionWithSql(user);

    }

    public void showAllEntries(ActionEvent actionEvent) {
        User user = new User("1","1");
        EntryController entryController = new EntryController();
        TransactionWithSql transactionWithSql = new TransactionWithSql(user);
        list = transactionWithSql.getAllEntries();
        entryController.showAllEntries(list);

    }
}