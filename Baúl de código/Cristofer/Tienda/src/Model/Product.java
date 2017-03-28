package Model;

/**
 * Created by Cristofer Canosa Domínguez on 04/03/2017.
 */
public class Product {
    private String title;
    private String artist;
    private String country;
    private float price;
    private int reservedQuantity;
    private int availableQuantity;

    public Product() {
        this.title = new String();
        this.artist = new String();
        this.country = new String();
        this.price = 0.0f;
        this.reservedQuantity = 0;
        this.availableQuantity = 0;
    }

    public Product(String title, String artist, String country, float price) {
        this.title = title;
        this.artist = artist;
        this.country = country;
        this.price = price;
        this.reservedQuantity = 0;
        this.availableQuantity = availableQuantity;
    }

    public Product(String title, String artist, String country, float price, int availableQuantity) {
        this.title = title;
        this.artist = artist;
        this.country = country;
        this.price = price;
        this.reservedQuantity = 0;
        this.availableQuantity = availableQuantity;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (!title.equals(product.title)) return false;
        if (!artist.equals(product.artist)) return false;
        return country.equals(product.country);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + country.hashCode();
        return result;
    }

    public void addOrder(int i) {
        if (reservedQuantity < availableQuantity)
            this.reservedQuantity += i;
        //TODO: Else throw exception, then catch, update availability and show error
    }
}
