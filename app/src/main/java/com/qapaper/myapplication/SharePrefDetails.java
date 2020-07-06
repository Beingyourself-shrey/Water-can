package com.qapaper.myapplication;

public class SharePrefDetails {
    String location;
    String address;
    String cantype;
    String quantity;
    String totalPrice;
    String usrname;
    String phn;
    String email;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public SharePrefDetails(){
this.quantity="1";
    }
    public SharePrefDetails(String location, String address, String cantype, String quantity, String totalPrice, String usrname, String phn, String email, String price,String date) {
        this.location = location;
        this.address = address;
        this.cantype = cantype;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.usrname = usrname;
        this.phn = phn;
        this.email = email;
        this.price = price;
        this.date=date;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    String price;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCantype() {
        return cantype;
    }

    public void setCantype(String cantype) {
        this.cantype = cantype;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
