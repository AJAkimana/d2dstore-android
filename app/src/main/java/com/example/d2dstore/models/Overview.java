package com.example.d2dstore.models;

public class Overview {
    int totalItems, income, expenditure, balance;

    public Overview(int totalItems, int income, int expenditure) {
        this.totalItems = totalItems;
        this.income = income;
        this.expenditure = expenditure;
        this.balance = income - expenditure;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(int expenditure) {
        this.expenditure = expenditure;
    }

    public int getBalance() {
        return balance;
    }
}
