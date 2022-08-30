import java.util.Scanner;

public class RandomTableTerminal {
    public static void main(String[] args) {
        RandomTableTerminal program = new RandomTableTerminal();
        while (program.isRunning) {
            program.menu();
        }
        System.out.println("Bye!");
    }

    public boolean isRunning;
    private final Scanner scanner;
    private final RandomTable randomTable;

    public RandomTableTerminal() {
        isRunning = true;
        scanner = new Scanner(System.in);
        randomTable = new RandomTable(askDimensions());
        printDivider();
    }

    public void menu() {
        String menuOption = askMenu();
        printDivider();

        switch (menuOption) {
            case ("SEARCH"):
                System.out.println("\n" + randomTable.search(askString("Enter String To Search: ")));
                break;
            case ("EDIT"):
                randomTable.edit(askCoordinates(), askString("Enter Replacement: "));
                break;
            case ("PRINT"):
                System.out.println(randomTable.print());
                break;
            case ("RESET"):
                randomTable.reset(askDimensions());
                break;
            case ("EXIT"):
                isRunning = false;
                break;
            default:
                System.out.println("Wrong Menu Option");
        }

        // Just For Formatting
        if (!menuOption.equals("EXIT")) printDivider();
    }

    private String askMenu() {
        System.out.println("SEARCH");
        System.out.println("EDIT");
        System.out.println("PRINT");
        System.out.println("RESET");
        System.out.println("EXIT\n");
        return askString("Enter Choice: ").trim().toUpperCase();
    }

    private Dimensions askDimensions() {
        Dimensions dimensions = new Dimensions();
        while (true) {
            int x_size = askInt("Enter Size of X: ");
            int y_size = askInt("Enter Size of Y: ");
            if (dimensions.setSizeOfX(x_size) == -1 || dimensions.setSizeOfY(y_size) == -1) {
                System.out.println("\nOnly Positive Values\n");
                continue;
            }
            break;
        }
        return dimensions;
    }

    private Coordinates askCoordinates() {
        Coordinates coordinates = new Coordinates();
        while (true) {
            int x = askInt("Enter X: ");
            int y = askInt("Enter Y: ");
            if (coordinates.setX(x, randomTable.getSizeOfX()) == -1 || coordinates.setY(y, randomTable.getSizeOfY()) == -1) {
                System.out.println("\nIndex Out Of Range\n");
                continue;
            }
            break;
        }
        return coordinates;
    }

    private int askInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) break;
            scanner.nextLine();
            System.out.println("\nWrong Type\n");
        }
        int rtn_val = scanner.nextInt();
        scanner.nextLine();
        return rtn_val;
    }

    private String askString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private void printDivider() {
        System.out.println("\n========================================\n");
    }
}