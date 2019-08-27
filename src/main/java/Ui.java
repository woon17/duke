import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private static String oneLine = "    ____________________________________________________________\n";
    public static String frontSpace = "    ";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }

    public void showLine() {
        System.out.println(oneLine);
    }


    public void showError(String message) {
        System.out.println(" " + message);
    }

    public void showWelcome() {
        String greet = "     Hello! I'm Duke\n"
                + "     What can I do for you?\n";
        greet = oneLine + greet + oneLine;
        System.out.println(greet);
    }

    public void showLoadingError() {
        showLine();
        System.out.println(frontSpace + " duke.txt file has problem!");
        showLine();
    }
}