package Class.Items;

public abstract class ITEM {
    private String name;
    private boolean isStackable;
    private int quantity;
    private String item_type;
    private int quality;
    private int id;

    public String getName() {
        return name;
    }

    public boolean isStackable() {
        return isStackable;
    }

    public int getId() {
        return id;
    }
}
