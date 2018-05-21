package com.FarmHelper.Services;

import com.FarmHelper.Repository.DataEntry;
import com.FarmHelper.User;

import java.util.HashMap;
import java.util.List;

public interface UserDataTransaction {

    boolean verifyUserLoginData();
    List<DataEntry> getAllEntries();
    HashMap<String,Integer> calculateTotalAmountPerVariety();
}
