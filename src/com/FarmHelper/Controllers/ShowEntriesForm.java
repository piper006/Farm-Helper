package com.FarmHelper.Controllers;

import com.FarmHelper.Repository.DataEntry;
import com.FarmHelper.Services.TransactionWithSql;
import com.FarmHelper.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ShowEntriesForm implements Initializable {
    @FXML
    public TableColumn<DataEntry,String> col_name;
    @FXML
    public TableColumn<DataEntry,String> col_type;
    @FXML
    public TableColumn<DataEntry,String> col_pack;
    @FXML
    public TableColumn<DataEntry,String> col_date;
    @FXML
    public TableColumn<DataEntry,String> col_amount;
    @FXML
    private TableView<DataEntry> tableView;
    @FXML
    private ChoiceBox<String> fruitSelection, varietySelection, packageSelection;
    @FXML
    private Label totalAmount;
    private User user;
    private ObservableList<String> observableListType,observableListVar,observableListPack ;
    private TransactionWithSql transactionWithSql;

    private List<String> list = new ArrayList<>();

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

        fillFruitChoiceBox();
        fruitSelection.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> fillFruitChoiceBox());
        varietySelection.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> updateLabel());
        packageSelection.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> updateLabel());

        varietySelection.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> updateTableView(newValue,packageSelection.getValue()));
        packageSelection.getSelectionModel().selectedItemProperty().addListener((v,oldValue,newValue) -> updateTableView(varietySelection.getValue(),newValue));

        col_name.setCellValueFactory(new PropertyValueFactory<>("varietyName"));
        col_pack.setCellValueFactory(new PropertyValueFactory<>("typeOfPackage"));
        col_amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("entryDate"));

        tableView.setItems(transactionWithSql.getEntries(varietySelection.getValue(),packageSelection.getValue()));
        System.out.println();

    }


    private void fillFruitChoiceBox(){
        list.clear();
        varietySelection.getItems().clear();
        list = transactionWithSql.getFruitsNames(fruitSelection.getValue());
        observableListVar.addAll(list);
        varietySelection.setItems(observableListVar);
        varietySelection.getSelectionModel().select(0);
        DataEntry entry = transactionWithSql.calculateTotalAmountForExactVariety(varietySelection.getValue(),packageSelection.getValue());
        totalAmount.setText((Integer.toString(entry.getAmount())));
    }

    private void updateTableView(String varType,String packType){
        tableView.setItems(transactionWithSql.getEntries(varType,packType));

    }

    private void updateLabel(){
        DataEntry entry = transactionWithSql.calculateTotalAmountForExactVariety(varietySelection.getValue(),packageSelection.getValue());
        totalAmount.setText((Integer.toString(entry.getAmount())));
    }
}
