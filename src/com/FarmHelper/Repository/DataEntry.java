package com.FarmHelper.Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class DataEntry {



    private String entryID;
    private String varietyName;
    private int amount;

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    private String entryDate;

    public String getTypeOfPackage() {
        return typeOfPackage;
    }

    public void setTypeOfPackage(String typeOfPackage) {
        this.typeOfPackage = typeOfPackage;
    }

    private String typeOfPackage;

    public TypeOfFruits getTypeOfFruits() {
        return typeOfFruits;
    }

    public void setTypeOfFruits(TypeOfFruits typeOfFruits) {
        this.typeOfFruits = typeOfFruits;
    }

    private TypeOfFruits typeOfFruits;

    public DataEntry(){
        this.entryDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
        this.entryID = UUID.randomUUID().toString();
    }
    public String getVarietyName() {
        return varietyName;
    }

    public void setVarietyName(String varietyName) {
        this.varietyName = varietyName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getEntryID() {
        return entryID;
    }



}
