package com.majd.bookzapp.Classes;

/**
 * Created by majd on 1/2/18.
 */

public class Book {
    private String bookId;
    private String title;
    private String subTitle;
    private String desc;
    private String imageUrl;
    private double price;
    public Book(String bookId,String title,String subTitle,String desc,String imageUrl){
        this.bookId=bookId;
        this.title=title;
        this.subTitle=subTitle;
        this.desc=desc;
        this.imageUrl=imageUrl;

    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBookId() {
        return bookId;
    }

    public String getDesc() {
        return desc;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
