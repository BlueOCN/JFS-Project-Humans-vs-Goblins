import java.util.UUID;

public class Human {

    // Human ID
    private String Id;
    // Coordinates
    private Coordinate Coordinates;
    // Human health
    private int health;
    // Human strength
    private int strength;
    // Human endurance
    private int endurance;

    public Human() {
        this.Id = UUID.randomUUID().toString();
        this.health = 10;
        this.strength = 5;
        this.endurance = 5;
    }

    public Human(Coordinate coordinates, int health, int strength, int endurance) {
        this.Id = UUID.randomUUID().toString();
        this.Coordinates = coordinates;
        this.health = health;
        this.strength = strength;
        this.endurance = endurance;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public Coordinate getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        Coordinates = coordinates;
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
        return "H";
    }
}
