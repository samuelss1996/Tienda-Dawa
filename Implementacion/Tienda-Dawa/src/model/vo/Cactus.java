package model.vo;

/**
 * 
 */
public class Cactus extends Product {
    private String species;
    private String origin;

    public Cactus (Product product, String species, String origin) {
        super (product.getId(), product.getProductName(), product.getPrice(), product.getStock(), product.getType());
        this.species = species;
        this.origin = origin;
    }



    public Cactus(int id, float price, int stock, EProductType type, String species, String origin) {
        super(id, String.format("Cactus %s de %s", species, origin), price, stock, type);
        this.species = species;
        this.origin = origin;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}