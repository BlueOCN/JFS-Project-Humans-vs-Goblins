import java.util.UUID;

public class Goblin {

    // Goblin ID
    private String Id;
    // Goblin Coordinates
    private Coordinate Coordinates;
    // Goblin health
    private int health;
    // Goblin strength
    private int strength;
    // Goblin endurance
    private int endurance;

    public Goblin() {
        this.Id = UUID.randomUUID().toString();
        this.health = 5;
        this.strength = 5;
        this.endurance = 5;
    }

    public Goblin(Coordinate coordinates, int health, int strength, int endurance) {
        this.Id = UUID.randomUUID().toString();
        this.Coordinates = coordinates;
        this.health = health;
        this.strength = strength;
        this.endurance = endurance;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public Coordinate getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.Coordinates = coordinates;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    @Override
    public String toString() {
        return "G";
    }

}
