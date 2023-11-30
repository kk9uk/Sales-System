package menu;

import db.Database;
import java.util.Scanner;

public class ManagerMenu implements Menu {
    private Database db;
    private Scanner scanner;

    public ManagerMenu(Database db, Scanner scanner) {
        this.db = db;
        this.scanner = scanner;
    }

    public void start() {
        while (true) {
            System.out.println();
            printManagerMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: listManager();break;
                case 2: countNoOfSales();break;
                case 3: totalSalesValues();break;
                case 4: popularPart();break;
                case 5: return;
                default: System.out.print("[Error]: Invalid operation, choose again!\n");
            }
        }
    }

    private static void printManagerMenu() {
        System.out.println("-----Operations for manager menu-----");
        System.out.println("What kinds of operation would you like to perform?");
        System.out.println("1. List all salespersons");
        System.out.println("2. Count the no. of sales record of each salesperson under a specific range on years of experience");
        System.out.println("3. Show the total sales value of each manufacturer");
        System.out.println("4. Show the N most popular part");
        System.out.println("5. Return to the main menu");
        System.out.print("Enter Your Choice: ");
    }
    private void listManager(){
        System.out.println("Choose ordering:");
        System.out.println("1. By ascending order");
        System.out.println("2. By descending order");
        System.out.print("Choose the list ordering: ");
        int choice = scanner.nextInt();
        db.showSales(choice);
    }

    private void countNoOfSales(){
        System.out.print("Type in the lower bound for years of experience: ");
        int lower = scanner.nextInt();
        System.out.print("Type in the upper bound for years of experience: ");
        int higher = scanner.nextInt();
        System.out.println("Transaction Record:");
        db.showTransactionRecord(lower,higher);

    }

    private void totalSalesValues(){
        db.showSalesValues();
    }


    private void popularPart(){
        System.out.print("Type in the number of parts: ");
        int num = scanner.nextInt();
        db.showPopularPart(num);


    }

}
