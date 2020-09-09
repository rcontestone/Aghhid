package com.rcons.fcallbacks.Model;

public class Crop {
    private String cropID;
    private String cropEnglishName;
    private String cropUrduName;
    private String cropCategory;
    private Boolean isSelected;


    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public String getCropID() {
        return cropID;
    }

    public void setCropID(String cropID) {
        this.cropID = cropID;
    }

    public String getCropEnglishName() {
        return cropEnglishName;
    }

    public void setCropEnglishName(String cropEnglishName) {
        this.cropEnglishName = cropEnglishName;
    }

    public String getCropUrduName() {
        return cropUrduName;
    }

    public void setCropUrduName(String cropUrduName) {
        this.cropUrduName = cropUrduName;
    }

    public String getCropCategory() {
        return cropCategory;
    }

    public void setCropCategory(String cropCategory) {
        this.cropCategory = cropCategory;
    }
}
