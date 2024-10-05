package classes.entities;

public abstract class MAP_ENTITY {
    private String name;
    private int hit_points;
    private int attack_stat;
    private float haste;
    private int defense_stat;
    private int id;

    public String getName() {
        return name;
    }

    public int getHit_points() {
        return hit_points;
    }

    public int getId() {
        return id;
    }
}
