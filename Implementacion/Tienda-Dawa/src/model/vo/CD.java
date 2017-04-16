package model.vo;

import java.time.Year;

/**
 * 
 */
public class CD extends Product {
    private String title;
    private String author;
    private Year year;

    public CD (Product product, String title, String author, Year year) {
        super(product.getId(), product.getProductName(), product.getPrice(), product.getStock(), product.getType());
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public CD(int id, float price, int stock, String title, String author, Year year) {
        super(id, String.format("%s - %s (%s)", title, author, year.toString()), price, stock, EProductType.CD);
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }
}