package engineer.pol.omnipurpur.common.utils;

public enum EServerType {

    MASTER("master"),
    SLAVE("slave"),;

    private final String key;

    EServerType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static EServerType fromKey(String key) {
        for (EServerType type : values()) {
            if (type.getKey().equals(key)) {
                return type;
            }
        }
        return null;
    }
}
