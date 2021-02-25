package com.example.trip_application.objects;

public class Place {
    private String pictureUrl="";
    private String area="";
    private String address="";
    private String tips="";
    private String description="";
    private double cost=0.0;

    public Place(){

    }

    public Place(String pictureUrl, String area, String address, String tips, String description, double cost) {
        this.pictureUrl = pictureUrl;
        this.area = area;
        this.address = address;
        this.tips = tips;
        this.description = description;
        this.cost = cost;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
