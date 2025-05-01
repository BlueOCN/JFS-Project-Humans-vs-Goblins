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
        Land worldMap = new Land();

        Human user = new Human(new Coordinate(50,4),10, 5, 5);

        Goblin goblinCollision = new Goblin();
        Chest chestCollision = new Chest();

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

                        Coordinate nextPosition;
                        Coordinate userPosition = user.getCoordinates();

                        System.out.print("Where do you want to go? (N/S/E/W): ");
                        char direction = sc.nextLine().strip().toUpperCase().charAt(0);
                        switch (direction) {

                            case 'N':
                                // Calculate the next move coordinate assuming you move north
                                nextPosition = new Coordinate(userPosition.getX(), userPosition.getY()-1);
                                break;

                            case 'S':
                                // Calculate the next move coordinate assuming you move south
                                nextPosition = new Coordinate(userPosition.getX(), userPosition.getY()+1);
                                break;

                            case 'E':
                                // Calculate the next move coordinate assuming you move east
                                nextPosition = new Coordinate(userPosition.getX()-1, userPosition.getY());
                                break;

                            case 'W':
                                // Calculate the next move coordinate assuming you move east
                                nextPosition = new Coordinate(userPosition.getX()+1, userPosition.getY());
                                break;

                            default:
                                // TODO Add validation to direction
                                throw new IllegalStateException("Unexpected value: " + direction);
                        }


                        // Check if the next moves collides with a goblin
                        goblinCollision = worldMap.findGoblin(nextPosition);

                        // Check if the next move collides with a chest
                        chestCollision = worldMap.findChest(nextPosition);

                        // If collision exists and is with goblin
                        if (goblinCollision != null) {
                            // Go to Battle State
                            GameState = 'B';
                        }
                        // If collision exists and is with chest
                        else if (chestCollision != null) {
                            // Go to Chest State
                            GameState = 'C';
                        }
                        // If there is no collision
                        else {
                            // Remain in Play State
                            GameState = 'P';
                        }

                        // Update player position on the map
                        worldMap.updateCoordinates(nextPosition, user);
                        worldMap.updateEntities();

                        // Give up the turn to Goblin
//                        Turn = 'G';

                    }
                    // if Goblin turn
//                    else {
//                        System.out.print("Goblin moves 1 unit. Direction is random");
//                        collision = false;
//                        boolean isHuman = false;
//                        if (collision && isHuman) {
//                            System.out.println("Human collision");
//                            GameState = 'B';
//                        } else {
//                            GameState = 'P';
//                        }

//                        Turn = 'H';
//                    }
                    break;

                case 'B':

                    boolean won;

                    System.out.println("\n\033[1mBattle Simulator\033[0m");
                    // Batle loop: to exit battle player must win
                    // TODO Implement Battle Loop
                    System.out.println("Standing before you is a goblin—a creature of cunning and malice. Its wiry frame is hunched over, emphasizing its unnatural agility. \nIts skin is a sickly green, mottled with patches of darker tones, almost blending with the murk of the forest. \nBeady yellow eyes glint with a mixture of mischief and danger, constantly darting about as if calculating its next move.");
                    System.out.printf(
                            "\n%-23s %-23s %-20s %23s %23s\n\n" +
                            "%-20s %-20d %-16s %20s %20s\n" +
                            "%-20s %-20d %-16s %20s %20s\n" +
                            "%-20s %-20d %-16s %20s %20s\n\n",
                            "\033[1mYour Stats\033[0m", "", "", "", "\033[1mGoblin Stats\033[0m", // Header
                            "Health", user.getHealth(), "" , goblinCollision.getHealth(), "Health", // Health row
                            "Strength", user.getStrength(), "", goblinCollision.getStrength(), "Strength", // Strength row
                            "Endurance", user.getEndurance(), "", goblinCollision.getEndurance(), "Endurance" // Endurance row
                    );
                    System.out.print("Ready to meet your luck? [Y/N]: ");
                    userInput = sc.nextLine().strip(); // TODO increment stats if lucky

                    // TODO Better formulae
                    int randomNumber = (int) (Math.random() * 2);
                    if (randomNumber == 0) {
                        won = false;
                    } else {
                        won = true;
                    }

                    // if player win
                    if (won) {
                        System.out.println("Battle Simulator won");
                        // Remove Goblin of the map
                        worldMap.removeEntity(goblinCollision);
                        // Add more treasures to the map
                        Chest newChest = new Chest(worldMap.genRandomPosition(),"boost");
                        worldMap.addChest(newChest);
                        // Update Entities
                        worldMap.updateEntities();
                        GameState = 'P';
                    } else {
                        System.out.println("Battle Simulator lost");
                        // Remove Player from the map
                        worldMap.removeEntity(user);
                        // Update Entities
                        worldMap.updateEntities();
                        // Render map
                        displayMap(worldMap);
                        GameState = 'E';
                    }
                    break;

                case 'C':
                    System.out.println("\n\033[1mYou have found some Treasure...\033[0m");
                    System.out.println("\nThe treasure chest stands before you, an enigma waiting to be unraveled. \nIts weathered wooden planks bear the scars of time—scratches, gouges, and the faint marks\nof old battles. Bands of tarnished iron wrap around its edges, their once-polished surfaces \nnow dulled by age and rust. The lock at the center glints faintly in the dim light, \nan intricate mechanism that speaks of both craftsmanship and secrecy.");
                    System.out.print("\n" +
                            "*******************************************************************************\n" +
                            "          |                   |                  |                     |\n" +
                            " _________|________________.=\"\"_;=.______________|_____________________|_______\n" +
                            "|                   |  ,-\"_,=\"\"     `\"=.|                  |\n" +
                            "|___________________|__\"=._o`\"-._        `\"=.______________|___________________\n" +
                            "          |                `\"=._o`\"=._      _`\"=._                     |\n" +
                            " _________|_____________________:=._o \"=._.\"_.-=\"'\"=.__________________|_______\n" +
                            "|                   |    __.--\" , ; `\"=._o.\" ,-\"\"\"-._ \".   |\n" +
                            "|___________________|_._\"  ,. .` ` `` ,  `\"-._\"-._   \". '__|___________________\n" +
                            "          |           |o`\"=._` , \"` `; .\". ,  \"-._\"-._; ;              |\n" +
                            " _________|___________| ;`-.o`\"=._; .\" ` '`.\"\\` . \"-._ /_______________|_______\n" +
                            "|                   | |o;    `\"-.o`\"=._``  '` \" ,__.--o;   |\n" +
                            "|___________________|_| ;     (#) `-.o `\"=.`_.--\"_o.-; ;___|___________________\n" +
                            "____/______/______/___|o;._    \"      `\".o|o_.--\"    ;o;____/______/______/____\n" +
                            "/______/______/______/_\"=._o--._        ; | ;        ; ;/______/______/______/_\n" +
                            "____/______/______/______/__\"=._o--._   ;o|o;     _._;o;____/______/______/____\n" +
                            "/______/______/______/______/____\"=._o._; | ;_.--\"o.--\"_/______/______/______/_\n" +
                            "____/______/______/______/______/_____\"=.o|o_.--\"\"___/______/______/______/____\n" +
                            "/______/______/______/______/______/______/______/______/______/______/[TomekK]\n" +
                            "*******************************************************************************\n"
                            );
                    System.out.print("Ready to meet your luck? [Y/N]: ");
                    userInput = sc.nextLine().strip();

                    // Generate random boost
                    Random random = new Random();
                    int randInt = random.nextInt(1,4);
                    switch (randInt) {

                        case 1:
                            // +1 Health
                            System.out.println("\n\033[1mYou feel better now. +1 Health.\033[0m\n");
                            user.setHealth(user.getHealth() + 1);
                            break;

                        case 2:
                            // +1 Strength
                            System.out.println("\n\033[1mYou feel stronger now. +1 Strength.\033[0m\n");
                            user.setStrength(user.getStrength() + 1);
                            break;

                        case 3:
                            // +1 Endurance
                            System.out.println("\n\033[1mYou feel ready now. +1 Endurance.\033[0m\n");
                            user.setEndurance(user.getEndurance() + 1);
                            break;
                    }

                    // Remove Chest from the map
                    worldMap.removeEntity(chestCollision);
                    // Update Entities
                    worldMap.updateEntities();

                    GameState = 'P';
                    break;

                case 'E':

                    System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⡀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣤⠀⠀⠀⢀⣴⣿⡶⠀⣾⣿⣿⡿⠟⠛⠁\n" +
                            "⠀⠀⠀⠀⠀⠀⣀⣀⣄⣀⠀⠀⠀⠀⣶⣶⣦⠀⠀⠀⠀⣼⣿⣿⡇⠀⣠⣿⣿⣿⠇⣸⣿⣿⣧⣤⠀⠀⠀\n" +
                            "⠀⠀⢀⣴⣾⣿⡿⠿⠿⠿⠇⠀⠀⣸⣿⣿⣿⡆⠀⠀⢰⣿⣿⣿⣷⣼⣿⣿⣿⡿⢀⣿⣿⡿⠟⠛⠁⠀⠀\n" +
                            "⠀⣴⣿⡿⠋⠁⠀⠀⠀⠀⠀⠀⢠⣿⣿⣹⣿⣿⣿⣿⣿⣿⡏⢻⣿⣿⢿⣿⣿⠃⣼⣿⣯⣤⣴⣶⣿⡤⠀\n" +
                            "⣼⣿⠏⠀⣀⣠⣤⣶⣾⣷⠄⣰⣿⣿⡿⠿⠻⣿⣯⣸⣿⡿⠀⠀⠀⠁⣾⣿⡏⢠⣿⣿⠿⠛⠋⠉⠀⠀⠀\n" +
                            "⣿⣿⠲⢿⣿⣿⣿⣿⡿⠋⢰⣿⣿⠋⠀⠀⠀⢻⣿⣿⣿⠇⠀⠀⠀⠀⠙⠛⠀⠀⠉⠁⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠹⢿⣷⣶⣿⣿⠿⠋⠀⠀⠈⠙⠃⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣤⣴⣶⣦⣤⡀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡀⠀⠀⠀⠀⠀⠀⠀⣠⡇⢰⣶⣶⣾⡿⠷⣿⣿⣿⡟⠛⣉⣿⣿⣿⠆\n" +
                            "⠀⠀⠀⠀⠀⠀⢀⣤⣶⣿⣿⡎⣿⣿⣦⠀⠀⠀⢀⣤⣾⠟⢀⣿⣿⡟⣁⠀⠀⣸⣿⣿⣤⣾⣿⡿⠛⠁⠀\n" +
                            "⠀⠀⠀⠀⣠⣾⣿⡿⠛⠉⢿⣦⠘⣿⣿⡆⠀⢠⣾⣿⠋⠀⣼⣿⣿⣿⠿⠷⢠⣿⣿⣿⠿⢻⣿⣧⠀⠀⠀\n" +
                            "⠀⠀⠀⣴⣿⣿⠋⠀⠀⠀⢸⣿⣇⢹⣿⣷⣰⣿⣿⠃⠀⢠⣿⣿⢃⣀⣤⣤⣾⣿⡟⠀⠀⠀⢻⣿⣆⠀⠀\n" +
                            "⠀⠀⠀⣿⣿⡇⠀⠀⢀⣴⣿⣿⡟⠀⣿⣿⣿⣿⠃⠀⠀⣾⣿⣿⡿⠿⠛⢛⣿⡟⠀⠀⠀⠀⠀⠻⠿⠀⠀\n" +
                            "⠀⠀⠀⠹⣿⣿⣶⣾⣿⣿⣿⠟⠁⠀⠸⢿⣿⠇⠀⠀⠀⠛⠛⠁⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠈⠙⠛⠛⠛⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀");
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