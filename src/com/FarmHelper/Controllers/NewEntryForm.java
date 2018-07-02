package com.FarmHelper.Controllers;

import com.FarmHelper.Repository.DataEntry;
import com.FarmHelper.Repository.TypeOfFruits;
import com.FarmHelper.Services.TransactionWithSql;
import com.FarmHelper.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewEntryForm implements Initializable {


    public TextField amountField;
    @FXML
    private ChoiceBox<String> fruitSelection, varietySelection, packageSelection;
    private ObservableList<String> observableListType,observableListVar,observableListPack ;
    private TransactionWithSql transactionWithSql;

    private List<String> list = new ArrayList<>();
    private User user;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        transactionWithSql = new TransactionWithSql(user);
        observableListType = FXCollections.observableArrayList();
        observableListVar = FXCollections.observableArrayList();
        observableListPack = FXCollections.observableArrayList();

        list =transactionWithSql.getFruitsTypes();
        observableListType.addAll(list);
        fruitSelection.setItems(observableListType);
        fruitSelection.getSelectionModel().select(0);
        list.clear();

        list =transactionWithSql.getFruitsPackage();
        observableListPack.addAll(list);
        packageSelection.setItems(observableListPack);
        packageSelection.getSelectionModel().select(0);
        list.clear();

        fillChoiceBoxes();
        fruitSelection.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> fillChoiceBoxes());


    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void submitNewEntry(ActionEvent actionEvent) {
        DataEntry entry = new DataEntry();
        TypeOfFruits typeOfFruits = new TypeOfFruits();
        typeOfFruits.setTypeName(fruitSelection.getValue());
        entry.setAmount(Integer.parseInt(amountField.getText()));
        entry.setVarietyName(varietySelection.getValue());
        entry.setTypeOfFruits(typeOfFruits);
        entry.setTypeOfPackage(packageSelection.getValue());
        transactionWithSql.addNewRegistry(entry);

    }


    private void fillChoiceBoxes(){
        list.clear();
        varietySelection.getItems().clear();
        list = transactionWithSql.getFruitsNames(fruitSelection.getValue());
        observableListVar.addAll(list);
        varietySelection.setItems(observableListVar);
        varietySelection.getSelectionModel().select(0);
    }
}
