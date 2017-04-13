package model.vo;

/**
 * 
 */
public class Cactus extends Product {
    private String species;
    private String origin;

    public Cactus(int id, float price, int stock, EProductType type, String species, String origin) {
        super(id, price, stock, type);
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