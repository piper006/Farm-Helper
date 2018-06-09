package com.FarmHelper.Controllers;


import com.FarmHelper.Repository.DataEntry;

import java.util.List;

public class EntryController {

    public void showAllEntries(List<DataEntry> list){

        for(DataEntry entry : list){
            System.out.println(entry.getVarietyName() + ": " +entry.getAmount() +" \\ " + entry.getEntryDate().toString());
        }
    }




}
