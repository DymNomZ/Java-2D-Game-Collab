package classes.items;

public abstract class ITEM {
    private String name;
    private boolean isStackable;
    private int quantity; // suggest to move to only CONSUMABLE since no reason to give weapons and stat
                          // mods quantity since limited to only 1? -Raymond
    private String item_type; // why isn't this visible? - FV
    private int quality; // suggest to move to only WEAPONS since no reason to give consumables quality
                         // level? -Raymond
                         // da heck is quality level - Ervin    //replied in gc -Raymond
    private int id;

    public ITEM(String name, String item_type, int quality, int id) // for weapons and stat-modifiers -Raymond
    {
        this.name = name;
        this.isStackable = false;
        this.quantity = 1;
        this.item_type = item_type;
        this.quality = quality;
        this.id = id;
    }

    public ITEM(String name, int quantity, String item_type, int id) // for consumables -Raymond
    {
        this.name = name;
        this.isStackable = true;
        this.quantity = quantity;
        this.item_type = item_type;
        this.quality = 1;
        this.id = id;
    }

    protected void setQuantity(int amount) {
        this.quantity -= amount;
    }   //what's the idea for this? -Raymond

    public String getName() {
        return name;
    }

    public boolean isStackable() {
        return isStackable;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity; // may be used to check if item is 0 and therefore be removed
    }

    public void consumeItem() { // consume method for consumables here, since quantity is private -FV
                                // suggest to move to CONSUMABLE class, same reason as quantity variable -Raymond
        if (quality != 0) {     //should be quality not quantity? also prolly no need for if statement since if
                                    // it reaches 0 it should automatically be removed from inventory -Raymond
            quantity -= 1;
        }
    }

}
