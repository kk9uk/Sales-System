package menu;

import db.Database;

import java.sql.SQLException;
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
            System.out.println();
            printAdminMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: create_table(); break;
                case 2: delete_table(); break;
                case 3: break;
                case 4: show_content(); break;
                case 5: return;
                default: System.out.print("[Error]: Invalid operation, choose again!\n");
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
        System.out.print("Enter Your Choice: ");
    }

    private void create_table() {
        // TODO: Waiting test bug
        try {
            System.out.printf("Processing...");
            db.createAllTables();
            System.out.println("Done! Database is initialized!\n");
        } catch (SQLException e) {
            if (e.toString().contains("exists")) {
                System.out.println("[Error] Tables already created.\n");
            } else {
                System.out.println("[Error] Failed to create tables.\n");
            }
        }
    }
    private void delete_table() {
        // TODO:
        try {
            System.out.println("Processing...");
            db.deleteAllTables();
            System.out.println("Done! Database is removed!\n");
        } catch (SQLException e) {
            if (e.toString().contains("Unknown")) {
                System.out.println("[Error] Tables do not exist.\n");
            } else {
                System.out.println("[Error] Failed to delete tables.\n");
            }
        }
    }
    private void load_datafile() {
        // TODO:
    }
    private void show_content() {
        System.out.print("Which table would you like to show: ");
        db.ShowTableContent(scanner.next());
    }
}
