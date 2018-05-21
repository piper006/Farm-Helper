package com.FarmHelper.Controllers;

import com.FarmHelper.Repository.TransactionWithSql;
import com.FarmHelper.Services.UserDataTransaction;
import com.FarmHelper.User;
import javafx.event.ActionEvent;


public class Controller {


    public void onClick(ActionEvent actionEvent) {



        UserDataTransaction userDataTransaction = new TransactionWithSql();
        User user = new User("1","1");
        if(userDataTransaction.verifyUserLoginData(user)){
            System.out.println("true");
        }
        else System.out.println("Wrong Given Data");
    }
}