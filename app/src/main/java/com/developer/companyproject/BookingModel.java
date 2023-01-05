package com.developer.companyproject;

class BookingModel {
    public String bookingId;
    public String category;
    public String subCategory;
     public String date;
     public String time;
     public String price;
     public String address;
     public String contact;
     public String status;

    public BookingModel(String bookingId,String category, String subCategory, String date, String time, String price, String address, String contact, String status) {
        this.bookingId=bookingId;
        this.category = category;
        this.subCategory = subCategory;
        this.date = date;
        this.time = time;
        this.price = price;
        this.address = address;
        this.contact = contact;
        this.status = status;
    }
}
