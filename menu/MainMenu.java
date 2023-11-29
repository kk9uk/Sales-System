package menu;

import db.Database;
import java.util.Scanner;

public class MainMenu implements Menu {
    private Database db;
    private Scanner scanner;

    public MainMenu(Database db) {
        this.db = db;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            Menu menu = null;
            printMainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: menu = new AdminMenu(db, scanner); break;
                case 2: menu = new SalesMenu(db, scanner); break;
                case 3: menu = new ManagerMenu(db, scanner); break;
                case 4: return;
                default: System.out.println("[Error]: Invalid operation, choose again!\n");
            }
            if (menu != null) menu.start();
        }
    }

    private static void printMainMenu() {
        System.out.println("-----Main menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Operations for administrator");
        System.out.println("2. Operations for salesperson");
        System.out.println("3. Operations for manager");
        System.out.println("4. Exit this program");
        System.out.println("Enter Your Choice: ");
    }
}
