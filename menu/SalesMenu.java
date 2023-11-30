package menu;

import db.Database;
import java.util.Scanner;

public class SalesMenu implements Menu {
    private Database db;
    private Scanner scanner;

    public SalesMenu(Database db, Scanner scanner) {
        this.db = db;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println();
            printSalesMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: break;
                case 2: break;
                case 3: return;
                default: System.out.print("[Error]: Invalid operation, choose again!\n");
            }
        }
    }

    private static void printSalesMenu() {
        System.out.println("-----Operations for salesperson menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. Search for parts");
        System.out.println("2. Sell a part");
        System.out.println("3. Return to the main menu");
        System.out.print("Enter Your Choice: ");
    }
}
