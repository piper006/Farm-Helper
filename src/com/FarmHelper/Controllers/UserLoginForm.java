package com.FarmHelper.Controllers;

import com.FarmHelper.Services.TransactionWithSql;
import com.FarmHelper.Services.UserDataTransaction;
import com.FarmHelper.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class UserLoginForm {

    public TextField userTextArea;
    public PasswordField passTextArea;

    private User user;


    public void loginAttempt(ActionEvent actionEvent) throws IOException {

        user = new User(userTextArea.getText(),passTextArea.getText());
        UserDataTransaction userDataTransaction = new TransactionWithSql(user);
        if(userDataTransaction.verifyUserLoginData()) {
            NewEntryForm newEntryForm = new NewEntryForm();

            URL url = new File("src/com/FarmHelper/UI/NewEntryForm.fxml").toURL();
            Parent homePage = FXMLLoader.load(url);

            //Parent homePage = FXMLLoader.load(getClass().getResource("/UI/mainWindow.fxml"));
            Scene homeScene = new Scene(homePage);
            Stage nextStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            nextStage.setScene(homeScene);
            nextStage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Προβλημα συνδεσης");
            alert.setHeaderText("Λαθος ονομα χρηστη ή κωδικος.\nΠροσπαθηστε ξανα!!!");
            alert.setContentText("*ΣΗΜΕΙΩΣΗ : Παρακαλω ελενξτε την γλωσσα στην οποια πληκτρολογειτε");
            alert.showAndWait();
        }


    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
