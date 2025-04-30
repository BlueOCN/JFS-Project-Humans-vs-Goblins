// Main Game Class

/* Game States & Screens:
   - Start(S): Menu
   - Play(P): WorldMap
   - Battle(B): Battle Simulator
   - End(E):   End Menu
* */

import java.util.*;
import java.util.Random;

public class HumanVSGoblins {
    public static void main(String[] args) {

        boolean MasterSwitch = true;
        char GameState = 'S'; // Start
        char Turn = 'H'; // Human Starts
        int worldMaxWidth = 100;
        int worldMaxHeight = 9;
        Land worldMap = new Land();;

        Human user = new Human(new Coordinate(50,4),10, 5, 5);

        Scanner sc = new Scanner(System.in);
        String userInput;

        do {
            switch (GameState) {
                case 'S':

                    // Generate Map
                    worldMap = new Land(worldMaxWidth, worldMaxHeight);

                    // Generate Goblins
                    Goblin goblin1 = new Goblin(worldMap.genRandomPosition(),5,5,5);
                    Goblin goblin2 = new Goblin(worldMap.genRandomPosition(),5,5,5);
                    ArrayList<Goblin> goblins = new ArrayList<>();
                    goblins.add(goblin1);
                    goblins.add(goblin2);
                    worldMap.setGoblinsCollection(goblins);

                    // Generate Chests
                    Chest chest1 = new Chest(worldMap.genRandomPosition(),"boost");
                    Chest chest2 = new Chest(worldMap.genRandomPosition(),"boost");
                    ArrayList<Chest> chests = new ArrayList<>();
                    chests.add(chest1);
                    chests.add(chest2);
                    worldMap.setChestsCollection(chests);

                    // Generate Player
                    user = new Human(new Coordinate(50,4),10, 5, 5);
                    ArrayList<Human> humans = new ArrayList<>();
                    humans.add(user);
                    worldMap.setHumansCollection(humans);

                    // Update entities on the world map
                    worldMap.updateEntities();

                    // Game Instructions and Mechanics
                    System.out.println("The \"Humans VS Goblins\" game is an engaging turn-based strategy game where players control characters represented as objects, including land, goblins, and humans.\n");
                    System.out.println("The game features turn-based movement (n/s/e/w), and combat is initiated when a human and goblin collide, utilizing random math for combat outcomes.");

                    System.out.print("Ready to Play? [Y/N]: ");
                    userInput = sc.nextLine().strip();
                    if (userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("YES")) {
                        GameState = 'P';
                    } else {
                        GameState = 'S';
                    }
                    break;

                case 'P':
                    // Render Map
                    displayMap(worldMap);
                    // If user turn
                    if (Turn == 'H') {
                        System.out.print("Where do you want to go? (N/S/E/W): ");
                        char direction = sc.nextLine().strip().toUpperCase().charAt(0);
                        switch (direction) {
                            case 'N':
                                // Check the cell
                                Coordinate userPosition = user.getCoordinates();
                                Coordinate nextPosition = new Coordinate(userPosition.getX(), userPosition.getY()-1);
                                System.out.println(worldMap.pickEntity(nextPosition));
                                // If there is collision
                                // then collision is true
                                // if there is a goblin
                                // collisionIsGoblin = true
                                // if there is a chest
                                // collisionIsChest = true
                                // If there is no collision
                                // then move north:
                                //      land.updatePosition(coor,human)
                                //
                                System.out.println("Move North by 1 unit");
                                break;
                            case 'S':
                                System.out.println("Move South by 1 unit");
                                break;
                            case 'E':
                                System.out.println("Move East by 1 unit");
                                break;
                            case 'W':
                                System.out.println("Move West by 1 unit");
                                break;
                            default:
                                System.out.println("Invalid direction");
                        }

                        boolean collision = true;
                        boolean isGoblin = true;
                        boolean isChest = false;

                        if (collision && isGoblin) {
                            System.out.println("Goblin collision");
                            GameState = 'B';
                        } else if ( collision && isChest) {
                            System.out.println("Chest collision");
                            GameState = 'C';
                        } else {
                            GameState = 'P';
                        }

                        Turn = 'G';

                    } else {
                        System.out.print("Goblin moves 1 unit. Direction is random");
                        boolean collision = true;
                        boolean isHuman = false;
                        if (collision && isHuman) {
                            System.out.println("Human collision");
                            GameState = 'B';
                        } else {
                            GameState = 'P';
                        }

                        Turn = 'H';
                    }
                    break;

                case 'B':
                    System.out.println("Battle Simulator");
                    // Batle loop: to exit battle player must win
                    // TODO Implement Battle Loop
                    boolean won = true;
                    // if player win
                    if (won) {
                        System.out.println("Battle Simulator won");
                        System.out.println("Updating Map");
                        System.out.println("Generating random treasure chests on the map");
                        GameState = 'P';
                    } else {
                        System.out.println("Battle Simulator lost");
                        GameState = 'E';
                    }
                    break;

                case 'C':
                    System.out.println("Chest Gatcha"); //TODO implement Gatcha
                    // Generate random boost or item
                    // Show user old vs new stats
                    GameState = 'P';
                    break;

                case 'E':
                    System.out.println("End Menu");
                    System.out.print("Do you want to Exit? [Y/N]: ");
                    userInput = sc.nextLine().strip();
                    if (userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("YES")) {
                        GameState = 'X';
                        MasterSwitch = false;
                    } else {
                        GameState = 'S';
                    }
                    break;

                default:
                    MasterSwitch = false;
                    break;
            }
        } while (MasterSwitch);

    }

    public static void displayMap(Land map) {
        String title = "Humans VS Goblins";
        int margin = (map.getWidth()-title.length())/2;
        System.out.printf("%-"+margin+"s %s\n", "", title);
        map.display();
    }
}