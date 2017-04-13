package model.vo;

import java.time.Year;

/**
 * 
 */
public class CD extends Product {
    private String title;
    private String author;
    private Year year;

    public CD(int id, float price, int stock, EProductType type, String title, String author, Year year) {
        super(id, price, stock, type);
        this.title = title;
        this.author = author;
        this.year = year;
    }
}