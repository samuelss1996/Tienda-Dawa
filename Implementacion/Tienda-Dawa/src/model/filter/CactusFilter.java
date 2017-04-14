package model.filter;

/**
 * 
 */
public class CactusFilter extends ProductFilter {
    private String species;
    private String origin;


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