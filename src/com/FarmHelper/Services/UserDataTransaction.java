package com.FarmHelper.Services;

import com.FarmHelper.Repository.DataEntry;
import com.FarmHelper.User;

import java.util.List;

public interface UserDataTransaction {

    boolean verifyUserLoginData(User user);
    List<DataEntry> getAllEntries(User user);
}
