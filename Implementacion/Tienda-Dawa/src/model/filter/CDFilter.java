package model.filter;

import java.time.Year;

public class CDFilter extends ProductFilter {
    private String title;
    private String author;
    private Year minYear;
    private Year maxYear;

    public CDFilter(String productName, Float minPrice, Float maxPrice, String title, String author, Year minYear, Year maxYear) {
        super(productName, minPrice, maxPrice);
        this.title = title;
        this.author = author;
        this.minYear = minYear;
        this.maxYear = maxYear;
    }

    public CDFilter(String productName) {
        super(productName);
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

    public Year getMaxYear() {
        return maxYear;
    }

    public void setMaxYear(Year maxYear) {
        this.maxYear = maxYear;
    }

    public Year getMinYear() {
        return minYear;
    }

    public void setMinYear(Year minYear) {
        this.minYear = minYear;
    }
}