// Main Game Class

/* Game States & Screens:
   - Start(S): Menu
   - Play(P): WorldMap
   - Battle(B): Battle Simulator
   - End(E):   End Menu
* */

import java.util.*;

public class HumanVSGoblins {
    public static void main(String[] args) {

        boolean MasterSwitch = true;
        char GameState = 'S'; // Start

        String menu = "Menu";
        String worldmap = "World Map";
        String battlesim = "Battle Simulator";
        String end = "End Menu";

        Scanner sc = new Scanner(System.in);
        String userInput;

        // Define the map using a String
        String map =
                "###############\n" +
                        "#             #\n" +
                        "#  H          #\n" +
                        "#  G   +      #\n" +
                        "#             #\n" +
                        "###############";


        do {
            switch (GameState) {
                case 'S':
                    System.out.println("Initializing Game");
                    System.out.println("Game Instructions and Mechanics");

                    System.out.print("Ready to Play? [Y/N]: ");
                    userInput = sc.nextLine().strip();
                    if (userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("YES")) {
                        GameState = 'P';
                    } else {
                        GameState = 'S';
                    }
                    break;

                case 'P':
                    System.out.println("Playing Game");
                    // Render Map
                    System.out.println("Map is rendered");
                    System.out.print(map);
                    // Identify whose turn is it
                    char owner = 'h'; //TODO extend functionality
                    // If user turn
                    if (owner == 'h') {
                        System.out.print("Where do you want to go? (N/S/E/W): ");
                        char direction = sc.nextLine().strip().toUpperCase().charAt(0);
                        switch (direction) {
                            case 'N':
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
}