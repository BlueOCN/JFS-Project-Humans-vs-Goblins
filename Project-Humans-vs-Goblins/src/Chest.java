import java.util.UUID;

public class Chest {

    // Chest ID
    private String Id;
    // Chest Coordinates
    private Coordinate Coordinates;
    // Chest Content
    private String content;

    public Chest() {
        Id = UUID.randomUUID().toString();
        this.Coordinates = null;
        this.content = null;
    }

    public Chest(Coordinate coordinates, String content) {
        Id = UUID.randomUUID().toString();
        this.Coordinates = coordinates;
        this.content = content;
    }

    public Coordinate getCoordinates() {
        return Coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        Coordinates = coordinates;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "+";
    }
}
