package BO;

import data.Storekeeper;
import java.util.ArrayList;
import java.util.List;

public class Bostorekeeper {

    private List<Storekeeper> storeList;

    public Bostorekeeper() {
        storeList = new ArrayList<>();
    }

    public Bostorekeeper(List<Storekeeper> storeList) {
        this.storeList = storeList;
    }

    public void addStorekeeper(Storekeeper store) throws Exception {
        if (store == null) {
            throw new Exception("StoreKeeper empty.");
        }
        if (checkDuplicateStoreID(store.getStorekeeperID())) {
            throw new Exception("Duplicate store ID.");
        }
//        if (checkDuplicateStoreName(store.getStorekeeperName())) {
//            throw new Exception("Duplicate store name.");
//        }
        storeList.add(store);
    }

//    public boolean checkDuplicateStoreName(String storeName) {
//        boolean isDuplicate = false;
//        for (Storekeeper storekeeper : storeList) {
//            if (storekeeper.getStorekeeperName().equalsIgnoreCase(storeName)) {
//                isDuplicate = true;
//                break;
//            }
//        }
//        return isDuplicate;
//    }

    public boolean checkDuplicateStoreID(int storeID) {
        boolean isDuplicate = false;
        for (Storekeeper storekeeper : storeList) {
            if (storekeeper.getStorekeeperID() == storeID) {
                isDuplicate = true;
                break;
            }
        }
        return isDuplicate;
    }

    public List<Storekeeper> getStoreList() {
        return storeList;
    }

}
