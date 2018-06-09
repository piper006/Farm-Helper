package com.FarmHelper.Services;

import com.FarmHelper.Repository.DataEntry;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface UserDataTransaction {

    boolean verifyUserLoginData();
    List<DataEntry> getAllEntries();
    HashMap<String,Integer> calculateTotalAmountPerVariety();
    void addNewRegistry(DataEntry entry);
    List<String> getFruitsNames(String typeOfFruits);
    List<String> getFruitsTypes();
}
