package model.vo;

/**
 * 
 */
public enum EProductType {
    CD(1),
    CACTUS(2);

    private int id;

    EProductType(int id) {
        this.id = id;
    }

    public static EProductType valueOf(int id) {
        for(EProductType type : EProductType.values()) {
            if(type.id == id) {
                return type;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }
}