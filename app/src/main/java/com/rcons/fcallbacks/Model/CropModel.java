package com.rcons.fcallbacks.Model;

import java.util.ArrayList;

public class CropModel {
    private ArrayList<String> cropID;
    private ArrayList<String> cropCategory;
    private ArrayList<String> cropUrduName;
    private ArrayList<String> cropEnglishName;
    private ArrayList<Boolean> isSelected;

    public ArrayList<Boolean> getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(ArrayList<Boolean> isSelected) {
        this.isSelected = isSelected;
    }

    public CropModel(){
         cropID = new ArrayList<>();
         cropCategory = new ArrayList<>();
         cropUrduName = new ArrayList<>();
         cropEnglishName = new ArrayList<>();
        isSelected = new ArrayList<>();

    }


    public ArrayList<String> getCropID() {
        return cropID;
    }

    public void setCropID(ArrayList<String> cropID) {
        this.cropID = cropID;
    }

    public ArrayList<String> getCropCategory() {
        return cropCategory;
    }

    public void setCropCategory(ArrayList<String> cropCategory) {
        this.cropCategory = cropCategory;
    }

    public ArrayList<String> getCropUrduName() {
        return cropUrduName;
    }

    public void setCropUrduName(ArrayList<String> cropUrduName) {
        this.cropUrduName = cropUrduName;
    }

    public ArrayList<String> getCropEnglishName() {
        return cropEnglishName;
    }

    public void setCropEnglishName(ArrayList<String> cropEnglishName) {
        this.cropEnglishName = cropEnglishName;
    }
}
