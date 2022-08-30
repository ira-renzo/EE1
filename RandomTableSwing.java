import javax.swing.*;
import java.util.Scanner;
import java.awt.Font;

public class RandomTableSwing {
    public static void main(String[] args) {
        RandomTableSwing program = new RandomTableSwing();
        while (program.isRunning) {
            program.menu();
        }
    }

    public boolean isRunning;
    private final RandomTable randomTable;

    public RandomTableSwing() {
        Dimensions dimensions = askDimensions();
        if (dimensions == null) {
            isRunning = false;
            randomTable = null;
        } else {
            isRunning = true;
            randomTable = new RandomTable(dimensions);
        }
    }

    public void menu() {
        MenuOptions menuOption = askMenu();

        switch (menuOption) {
            case SEARCH:
                String response = askString("Enter String To Search: ");
                if (response != null) showMultilineText(randomTable.print() + "\n\n" + randomTable.search(response));
                break;
            case EDIT:
                Coordinates coordinates = askCoordinates();
                if (coordinates == null) break;
                String replacement = askString("Enter Replacement: ");
                if (replacement == null) break;
                randomTable.edit(coordinates, replacement);
                break;
            case PRINT:
                showMultilineText(randomTable.print());
                break;
            case RESET:
                Dimensions dimensions = askDimensions();
                if (dimensions != null) randomTable.reset(dimensions);
                break;
            case EXIT:
                isRunning = false;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid Input", "Error", JOptionPane.ERROR_MESSAGE);
       }
    }

    private void showMultilineText(String message) {
        JTextArea table = new JTextArea(message);
        table.setFont(new Font("Monospaced", Font.PLAIN, 12));
        table.setEditable(false);
        JOptionPane.showMessageDialog(null, table);
    }

    private MenuOptions askMenu() {
        JComboBox<MenuOptions> comboBox = new JComboBox<>(MenuOptions.values());
        int option = JOptionPane.showConfirmDialog(null, comboBox, "Menu", JOptionPane.DEFAULT_OPTION);
        if (option == -1) return MenuOptions.EXIT;
        return (MenuOptions) comboBox.getSelectedItem();
    }

    private Dimensions askDimensions() {
        Dimensions dimensions = new Dimensions();
        while (true) {
            Integer x_size, y_size;
            if ((x_size = askInt("Enter Size of X: ")) == null) return null;
            if ((y_size = askInt("Enter Size of Y: ")) == null) return null;
            if (dimensions.setSizeOfX(x_size) == -1 || dimensions.setSizeOfY(y_size) == -1) {
                JOptionPane.showMessageDialog(null, "Only Positive Values", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }
           break;
        }
        return dimensions;
    }

    private Coordinates askCoordinates() {
        Coordinates coordinates = new Coordinates();
        while (true) {
            Integer x, y;
            if ((x = askInt("Enter X: ")) == null) return null;
            if ((y = askInt("Enter Y: ")) == null) return null;
            if (coordinates.setX(x, randomTable.getSizeOfX()) == -1 || coordinates.setY(y, randomTable.getSizeOfY()) == -1) {
                JOptionPane.showMessageDialog(null, "Index Out Of Range", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            break;
        }
        return coordinates;
    }

    private Integer askInt(String prompt) {
        String response = JOptionPane.showInputDialog(null, prompt, "Input", JOptionPane.QUESTION_MESSAGE);
        while (true) {
            if (response == null) {
                return null;
            } else {
                Scanner scanner = new Scanner(response.trim());
                if (scanner.hasNextInt()) return scanner.nextInt();
            }
            response = JOptionPane.showInputDialog(null, prompt, "Input (Only Integers)", JOptionPane.WARNING_MESSAGE);
        }
    }

    private String askString(String prompt) {
        return JOptionPane.showInputDialog(null, prompt, "Input", JOptionPane.QUESTION_MESSAGE);
    }
}