package com.FarmHelper.Services;

import com.FarmHelper.Repository.DataEntry;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface UserDataTransaction {

    boolean verifyUserLoginData();
    List<DataEntry> getAllEntries();
    DataEntry calculateTotalAmountForExactVariety(String varietyName,String pack);
    void addNewRegistry(DataEntry entry);
    List<String> getFruitsNames(String typeOfFruits);
    List<String> getFruitsTypes();
    List<String> getFruitsPackage();
    ObservableList<DataEntry> getEntries(String varType,String packType);


}
