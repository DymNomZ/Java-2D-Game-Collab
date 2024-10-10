package classes.entities;

public abstract class MAP_ENTITY {
    private String name;
    private int hit_points;
    private int attack_stat;
    private float haste;
    private int defense_stat;
    private int id;
    public MAP_ENTITY(){}
    public MAP_ENTITY(String name, int hit_points, int attack_stat, float haste, int defense_stat, int id){
        this.name = name;
        this.hit_points = hit_points;
        this.attack_stat = attack_stat;
        this.haste = haste;
        this.defense_stat = defense_stat;
        this.id = id;
    }

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
