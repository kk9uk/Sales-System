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
                case 1: searchForParts(); break;
                case 2: sellPart(); break;
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

    private void searchForParts() {
        System.out.println("Choose the search criterion: ");
        System.out.println("1. Part Name");
        System.out.println("2. Manufacturer Name");
        System.out.print("Choose the search criterion: ");
        int search_crit = scanner.nextInt();
        if (search_crit != 1 && search_crit != 2) {
            System.out.println("[Error]: Invalid input.");
            return;
        }

        System.out.print("Type in the Search Keyboard: ");
        String search_word = scanner.next();

        System.out.println("Choose ordering: ");
        System.out.println("1. By price, ascending order");
        System.out.println("2. By price, descending order");
        System.out.print("Choose the search criterion:  ");
        int search_order = scanner.nextInt();
        if (search_order != 1 && search_order != 2) {
            System.out.println("[Error]: Invalid input.");
            return;
        }

        db.Search_for_Parts(search_crit, search_word, search_order);
    }

    private void sellPart() {
        System.out.print("Enter The Part ID: ");
        int part_id = scanner.nextInt();
        System.out.print("Enter Salesperson ID: ");
        int salesperson_id = scanner.nextInt();
        db.Sell_a_part(part_id, salesperson_id);
    }
}
