package data;


import BO.Checkinput;
import data.ShelfStorekeeper;
import data.ShelfProducts;
import BO.BoProducts;
import BO.Bostorekeeper;
import java.util.ArrayList;

public class Productmanagerment {

    public static void main(String[] args) {

        Checkinput ci = new Checkinput();
        Bostorekeeper bs = new Bostorekeeper();
        ShelfStorekeeper ss = new ShelfStorekeeper(bs);
        ShelfProducts sp = new ShelfProducts(bs);
        int choice;
        do {
            sp.Menu();
            choice = ci.getInput("Enter your choice: ");
            switch (choice) {
                case 1:
                    ss.addStorekeeper();
                    break;
                case 2:
                    sp.addProduct();
                    break;
                case 3:
                    sp.updateProduct();
                    break;
                case 4:
                    sp.search();
                    break;
                case 5:
                    sp.sortProduct();
                    break;
                case 6:
                    System.out.println("See you again.");
                    break;

            }
        } while (choice != 6);

    }

}
