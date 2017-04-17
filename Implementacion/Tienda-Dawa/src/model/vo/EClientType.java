package model.vo;

public enum EClientType {
    STANDARD(1),
    VIP(2);

    private final int id;

    EClientType(int id) {
        this.id = id;
    }

    public static EClientType valueOf(int id) {
        for(EClientType type : EClientType.values()) {
            if(type.id == id) {
                return type;
            }
        }

        return null;
    }

    public int getId() {
        return this.id;
    }
}