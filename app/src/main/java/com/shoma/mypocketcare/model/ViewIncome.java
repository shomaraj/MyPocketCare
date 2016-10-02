package com.shoma.mypocketcare.model;

/**
 * Created by Administrator on 9/30/2016.
 */
public class ViewIncome {
    private int id;
    private  String category, description, date;
    private double amount;

    public ViewIncome(int id, String category, String description, String date, double amount) {
        this.category = category;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.id= id;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }
}
