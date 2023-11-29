package menu;

import db.Database;
import java.util.Scanner;

public class AdminMenu implements Menu {
    private Database db;
    private Scanner scanner;

    public AdminMenu(Database db, Scanner scanner) {
        this.db = db;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            printAdminMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: return;
                default: System.out.println("[Error]: Invalid operation, choose again!\n");
            }
        }
    }

    private static void printAdminMenu() {
        System.out.println("-----Operations for administrator menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Create all tables");
        System.out.println("2. Delete all tables");
        System.out.println("3. Load from datafile");
        System.out.println("4. Show content of a table");
        System.out.println("5. Return to the main menu");
        System.out.println("Enter Your Choice: ");
    }
}
