package model;

public class CDProduct {
    private String title;
    private String artist;
    private String country;
    private Float prize;

    public CDProduct() {
        this.title = null;
        this.artist = null;
        this.country = null;
        this.prize = 0f;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Float getPrize() {
        return prize;
    }

    public void setPrize(Float prize) {
        this.prize = prize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CDProduct product = (CDProduct) o;

        if (title != null ? !title.equals(product.title) : product.title != null) return false;
        if (artist != null ? !artist.equals(product.artist) : product.artist != null) return false;
        if (country != null ? !country.equals(product.country) : product.country != null) return false;
        return prize != null ? prize.equals(product.prize) : product.prize == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (prize != null ? prize.hashCode() : 0);
        return result;
    }
}