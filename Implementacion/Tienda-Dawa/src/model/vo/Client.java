package model.vo;

/**
 * 
 */
public class Client extends User {
    private EClientType type;
    private float totalExpenses;

    public EClientType getType() {
        return type;
    }

    public void setType(EClientType type) {
        this.type = type;
    }

    public float getTotalExpenses() {
        return totalExpenses;
    }

    public void setTotalExpenses(float totalExpenses) {
        this.totalExpenses = totalExpenses;
    }


}