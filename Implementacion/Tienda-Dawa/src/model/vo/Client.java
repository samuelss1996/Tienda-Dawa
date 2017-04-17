package model.vo;

import java.util.Date;

public class Client extends User {
    private EClientType type;
    private float totalExpenses;

    public Client(String username) {
        super(username);
    }

    public Client(String username, String email) {
        super(-1, username, email, null);
        this.type = EClientType.STANDARD;
        this.totalExpenses = 0f;
    }

    public Client (int id, int type, float totalExpenses) {
        super(id, "", "", null);
        this.type = EClientType.valueOf(type);
        this.totalExpenses = totalExpenses;
    }

    public Client(int id, String username, String email, Date signupDate, int type, float totalExpenses) {
        super(id, username, email, signupDate);
        this.type = EClientType.valueOf(type);
        this.totalExpenses = totalExpenses;
    }

    public Client(int id, String username, String email, Date signupDate, EClientType type, float totalExpenses) {
        super(id, username, email, signupDate);
        this.type = type;
        this.totalExpenses = totalExpenses;
    }

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