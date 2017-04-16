package model.filter;

/**
 * 
 */
public class CactusFilter extends ProductFilter {
    private String species;
    private String origin;

    public CactusFilter(String productName, Float minPrice, Float maxPrice, String species, String origin) {
        super(productName, minPrice, maxPrice);
        this.species = species;
        this.origin = origin;
    }

    public CactusFilter(String productName) {
        super(productName);
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