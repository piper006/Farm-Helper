package com.FarmHelper.Controllers;

import com.FarmHelper.Repository.TransactionWithSql;
import com.FarmHelper.Services.UserDataTransaction;
import com.FarmHelper.User;
import javafx.event.ActionEvent;
import java.util.Map;


public class Controller {


    public void onClick(ActionEvent actionEvent) {


       User user = new User("1","1");
       UserDataTransaction userDataTransaction = new TransactionWithSql(user);
       for(Map.Entry<String,Integer> entry : userDataTransaction.calculateTotalAmountPerVariety().entrySet()){
           System.out.println(entry.getKey() + " :" +entry.getValue());
       }

    }
}