package data;

import BO.Bostorekeeper;
import BO.Checkinput;
import java.util.ArrayList;
import java.util.List;

public class ShelfStorekeeper {

    private Bostorekeeper bs;
    private Checkinput ci;

    public ShelfStorekeeper(Bostorekeeper bs) {
        this.bs = bs;
        this.ci = new Checkinput();
    }

    public void addStorekeeper() {
        System.out.println("=========Add Storekeeper===========");

        while (true) {
            int idstore = ci.getInt("Enter id storekeeper: ", "Enter a positive number", 1, Integer.MAX_VALUE);
            String namestore = ci.getString("Enter name storeKeeper: ");

            try {
                if (bs.checkDuplicateStoreID(idstore)) {
                    throw new Exception("Duplicate store ID.");
                }
//                if (bs.checkDuplicateStoreName(namestore)) {
//                    throw new Exception("Duplicate store name.");
//                }
                Storekeeper storeList = new Storekeeper(idstore, namestore);
                bs.addStorekeeper(storeList);
                displayStorekeeper();
                System.out.println("Storekeeper added successfully.");
                break; // Thoát khỏi vòng lặp nếu không có trùng lặp
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void displayStorekeeper() {
        if (bs.getStoreList().isEmpty()) {
            System.out.println("storeList is empty!");
        } else {
            System.out.println("__________Storekeeper List__________");
            System.out.format("%-5s%-20s\n", "ID", "Name");
            for (Storekeeper storekeeper : bs.getStoreList()) {
                storekeeper.display();
            }
        }
    }

}
