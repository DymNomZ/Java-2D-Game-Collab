package classes.items;

public abstract class ITEM {
    private String name;
    private boolean isStackable;
    private int quantity;   //suggest to move to only CONSUMABLE since no reason to give weapons and stat mods quantity since limited to only 1? -Raymond
    private String item_type;
    private int quality;    //suggest to move to only WEAPONS since no reason to give consumables quality level? -Raymond
    private int id;

    public ITEM(String name, String item_type, int quality, int id)    //for weapons and stat-modifiers -Raymond
    {
        this.name = name;
        this.isStackable = false;
        this.quantity = 1;
        this.item_type = item_type;
        this.quality = quality;
        this.id = id;
    }
    public ITEM(String name, int quantity, String item_type, int id)    //for consumables   -Raymond
    {
        this.name = name;
        this.isStackable = true;
        this.quantity = quantity;
        this.item_type = item_type;
        this.quality = 1;
        this.id = id;
    }


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
