package com.FarmHelper.Controllers;

import com.FarmHelper.Repository.DataEntry;
import com.FarmHelper.Repository.TransactionWithSql;
import com.FarmHelper.Services.UserDataTransaction;
import com.FarmHelper.User;
import javafx.event.ActionEvent;

import java.util.List;


public class Controller {


    public void onClick(ActionEvent actionEvent) {



        UserDataTransaction userDataTransaction = new TransactionWithSql();
        User user = new User("1","1");
        List<DataEntry> entryDataList = userDataTransaction.getAllEntries(user);

        for(DataEntry entry : entryDataList){
            System.out.println(entry.getVarietyName());
        }
    }
}