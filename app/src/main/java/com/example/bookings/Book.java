package com.example.bookings;

class Book {
    String date;
    String time;

    public Book(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public Book() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
