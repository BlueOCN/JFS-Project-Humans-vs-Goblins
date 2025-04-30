import java.util.ArrayList;
import java.util.Random;

public class Land {
    char[][] map;
    private final int width;
    private final int height;
    private ArrayList<Human> humansCollection;
    private ArrayList<Goblin> goblinsCollection;
    private ArrayList<Chest> chestsCollection;

    public Land() {
        this.width = 1;
        this.height = 1;
        humansCollection = new ArrayList<>();
        goblinsCollection = new ArrayList<>();
        chestsCollection = new ArrayList<>();
        map = new char[height][width];
        initializeMap();
    }

    public Land(int width, int height) {
        this.width = width;
        this.height = height;
        humansCollection = new ArrayList<>();
        goblinsCollection = new ArrayList<>();
        chestsCollection = new ArrayList<>();
        map = new char[height][width];
        initializeMap();
    }

    // Initialize the grid with borders and empty spaces
    private void initializeMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    map[i][j] = '#'; // Borders
                } else {
                    map[i][j] = ' '; // Empty space
                }
            }
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public ArrayList<Human> getHumansCollection() {
        return humansCollection;
    }

    public void setHumansCollection(ArrayList<Human> humansCollection) {
        this.humansCollection = humansCollection;
    }

    public ArrayList<Goblin> getGoblinsCollection() {
        return goblinsCollection;
    }

    public void setGoblinsCollection(ArrayList<Goblin> goblinsCollection) {
        this.goblinsCollection = goblinsCollection;
    }

    public ArrayList<Chest> getChestsCollection() {
        return chestsCollection;
    }

    public void setChestsCollection(ArrayList<Chest> chestsCollection) {
        this.chestsCollection = chestsCollection;
    }

    public char pickEntity(Coordinate coordinate) {
        return map[coordinate.getY()][coordinate.getX()];
    }

    //Update all collections
    public void updateEntities() {

        for (Goblin goblin : goblinsCollection) {
            Coordinate coordinates = goblin.getCoordinates();

        if (coordinates.getX() < 0 || coordinates.getX() >= width || coordinates.getY() < 0 || coordinates.getY() >= height) {
            throw new IndexOutOfBoundsException("Error: " + goblin + " cannot be assigned outside the map`s border");
        }

        if (coordinates.getX() == 0 || coordinates.getY() == 0 || coordinates.getX() == width - 1 || coordinates.getY() == height - 1) {
            throw new IndexOutOfBoundsException("Error: " + goblin + " cannot be assigned to the map`s border");
        }

        if (map[coordinates.getY()][coordinates.getX()] != ' ') {
            throw new IndexOutOfBoundsException("Error: " + goblin + " collision detected.");
        }

            map[coordinates.getY()][coordinates.getX()] = goblin.toString().charAt(0);
        }

        for (Chest chest : chestsCollection) {
            Coordinate coordinates = chest.getCoordinates();

            if (coordinates.getX() < 0 || coordinates.getX() >= width || coordinates.getY() < 0 || coordinates.getY() >= height) {
                throw new IndexOutOfBoundsException("Error: " + chest + " cannot be assigned outside the map`s border");
            }

            if (coordinates.getX() == 0 || coordinates.getY() == 0 || coordinates.getX() == width - 1 || coordinates.getY() == height - 1) {
                throw new IndexOutOfBoundsException("Error: " + chest + " cannot be assigned to the map`s border");
            }

            if (map[coordinates.getY()][coordinates.getX()] != ' ') {
                throw new IndexOutOfBoundsException("Error: " + chest + " collision detected.");
            }

            map[coordinates.getY()][coordinates.getX()] = chest.toString().charAt(0);
        }

        for (Human human : humansCollection) {
            Coordinate coordinates = human.getCoordinates();

            if (coordinates.getX() < 0 || coordinates.getX() >= width || coordinates.getY() < 0 || coordinates.getY() >= height) {
                throw new IndexOutOfBoundsException("Error: " + human + " cannot be assigned outside the map`s border");
            }

            if (coordinates.getX() == 0 || coordinates.getY() == 0 || coordinates.getX() == width - 1 || coordinates.getY() == height - 1) {
                throw new IndexOutOfBoundsException("Error: " + human + " cannot be assigned to the map`s border");
            }

            if (map[coordinates.getY()][coordinates.getX()] != ' ') {
                throw new IndexOutOfBoundsException("Error: " + human + " collision detected.");
            }

            map[coordinates.getY()][coordinates.getX()] = human.toString().charAt(0);
        }
    }

//
//    public void updatePosition(Coordinate position, Human human) {
//        int x = position.getX();
//        int y = position.getY();
//
//        if (x < 0 || x >= width || y < 0 || y >= height) {
//            throw new IndexOutOfBoundsException("Error: " + human + " cannot be assigned outside the map`s border");
//        }
//
//        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
//            throw new IndexOutOfBoundsException("Error: " + human + " cannot be assigned to the map`s border");
//        }
//
//        if (map[y][x] != ' ') {
//            throw new IndexOutOfBoundsException("Error: " + human + " collision detected.");
//        }
//
//        map[y][x] = human.toString().charAt(0);
//    }
//
//    public void updatePosition(Coordinate position, Goblin goblin) {
//        int x = position.getX();
//        int y = position.getY();
//
//        if (x < 0 || x >= width || y < 0 || y >= height) {
//            throw new IndexOutOfBoundsException("Error: " + goblin + " cannot be assigned outside the map`s border");
//        }
//
//        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
//            throw new IndexOutOfBoundsException("Error: " + goblin + " cannot be assigned to the map`s border");
//        }
//
//        if (map[y][x] != ' ') {
//            throw new IndexOutOfBoundsException("Error: " + goblin + " collision detected.");
//        }
//
//        map[y][x] = goblin.toString().charAt(0);
//    }
//
//    public void updatePosition(Coordinate position, Chest chest) {
//        int x = position.getX();
//        int y = position.getY();
//
//        if (chest.getCoordinates() != null){
//            Coordinate chestCoordinates = chest.getCoordinates();
//            map[chestCoordinates.getY()][chestCoordinates.getX()] = ' ';
//        }
//
//        if (x < 0 || x >= width || y < 0 || y >= height) {
//            throw new IndexOutOfBoundsException("Error: " + chest + " cannot be assigned outside the map`s border");
//        }
//
//        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
//            throw new IndexOutOfBoundsException("Error: " + chest + " cannot be assigned to the map`s border");
//        }
//
//        if (map[y][x] != ' ') {
//            throw new IndexOutOfBoundsException("Error: " + chest + " collision detected.");
//        }
//
//        map[y][x] = chest.toString().charAt(0);
//    }
//
//    // Add character to the map
//    public void updatePosition(Coordinate position, char symbol) {
//
//        int x = position.getX();
//        int y = position.getY();
//
//        if (x < 0 || x >= width || y < 0 || y >= height) {
//            throw new IndexOutOfBoundsException("Error: " + symbol + " cannot be assigned outside the map`s border");
//        }
//
//        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
//            throw new IndexOutOfBoundsException("Error: " + symbol + " cannot be assigned to the map`s border");
//        }
//
//        if (map[y][x] != ' ') {
//            throw new IndexOutOfBoundsException("Error: " + symbol + " collision detected.");
//        }
//
//        map[y][x] = symbol;
//    }

    public Coordinate genRandomPosition(){
        int x = new Random().nextInt(1, width - 2);
        int y = new Random().nextInt(1,height - 2);
        return new Coordinate(x, y);
    }

    // Display the grid
    public void display() {
        for (char[] row : map) {
            System.out.println(row);
        }
    }
}
