package data;

import BO.Checkinput;
import BO.BoProducts;
import BO.Bostorekeeper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ShelfProducts {

    Checkinput ci = new Checkinput();
    BoProducts bp = new BoProducts();
    Bostorekeeper bs ;
    ShelfStorekeeper ss = new ShelfStorekeeper(bs);

    public ShelfProducts(Bostorekeeper bs) {
        this.bs = bs;
    }

    public void addProduct() {
        System.out.println("=========Add Product===========");
        displayProductList();

        int productId;
        do {
            productId = ci.getInt("Enter product's id: ", "Input out of range.", 0, Integer.MAX_VALUE);
            boolean isExist = bp.checkDuplicateIdProduct(productId);
            if (isExist) {
                String answer = ci.getStringAdd("Product already exists. Do you want to add a new product? [y|n]: ",
                        "Answer must be 'y' or 'n'", "[ynYN]");
                if (answer.equalsIgnoreCase("y")) {
                    continue;
                } else {
                    return;
                }
            } else {
                break;
            }
        } while (true);

        String productName = ci.getStringAdd("Enter product's name: ", "Enter a String type", "^[a-zA-Z ]*$");
        String productLocation = ci.getString("Enter product's location: ");
        double price = ci.getDouble("Enter product's price: ");
        Date dateOfManufacture;
        Date expiryDate;
        do {
            dateOfManufacture = ci.getDate("Enter product's manufacture date [dd/MM/YYYY]: ");
            Date now = new Date();
            if (dateOfManufacture.after(now)) {
                System.out.println("Date of manufacture must be in the past.");
                continue;
            } else {
                break;
            }
        } while (true);

        do {
            expiryDate = ci.getDate("Enter product's expiry date [dd/MM/YYYY]: ");
            if (expiryDate.before(dateOfManufacture)) {
                System.out.println("Expiry date must be after date of manufacture.");
                continue;
            } else {
                break;
            }
        } while (true);

        String category = ci.getStringAdd("Enter category: ", "Please enter a string type", "^[a-zA-Z ]*$");

        List<Storekeeper> listStore = bs.getStoreList();
        System.out.println("__________Choose Storekeeper_________");
        for (Storekeeper storekeeper : listStore) {
            storekeeper.display();
        }
        Storekeeper selectedStorekeeper = null;
        int storekeeperChoice;
        do {
            storekeeperChoice = ci.getIntStore("Choose storekeeper: ", "The storekeeper list is empty.",
                    1, bs.getStoreList().size());
            for (Storekeeper storekeeper : listStore) {
                if (storekeeper.getStorekeeperID() == storekeeperChoice) {
                    selectedStorekeeper = storekeeper;
                    break;
                }
            }
            if (selectedStorekeeper == null) {
                System.out.println("Invalid storekeeper choice. Please try again.");
            }
        } while (selectedStorekeeper == null);

        Date receiptDate;
        do {    
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String manufacturingDateStr = dateFormat.format(dateOfManufacture);
            String expiryDateStr = dateFormat.format(expiryDate);
            Date now = new Date();
            receiptDate = ci.getDate("Enter receipt date: ");              
           
            if (receiptDate.before(dateOfManufacture) || receiptDate.after(expiryDate) || receiptDate.after(now)) {
                System.out.println("Receipt date must be between " + manufacturingDateStr + " and " + expiryDateStr + ".");
                System.out.println("Receipt date must after today.");
                continue;
            } else {
                break;
            }
        } while (true);

        try {
            Product newProduct = new Product(productId, productName, productLocation, price, expiryDate, dateOfManufacture, category, selectedStorekeeper, receiptDate);
            bp.addProduct(newProduct);
            System.out.println("Product added successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProduct() {
        System.out.println("=========Update Product===========");
        if (!bp.getListProduct().isEmpty()) {
            displayProductList();
            int idToUpdate = ci.getInt("Enter id product want update: ", "Enter a pos number.", 0, Integer.MAX_VALUE);
            int index = bp.searchById(idToUpdate);
            if (index == -1) {
                System.out.println("Product is not found!");
            } else {
                try {
                    Product product = bp.getListProduct().get(index);
                    String productName = ci.UpdateNamecate("Enter new name (Leave blank if you want to keep the old value): ", product.getProductName(), "Enter a string type", "^[a-zA-Z ]*$");
                    String productLocation = ci.UpdateNamecate("Enter new product's location (Leave blank if you want to keep the old value): ", product.getLocation(), "Enter a string type.", "");
                    double price = ci.Updatesalary("Enter new product's price (Leave blank if you want to keep the old value): ", product.getPrice());
                    Date dateOfManu = new Date();
                    Date expiryDate = new Date();
                    do {
                        dateOfManu = ci.UpdateDate("Enter new product's (Leave blank if you want to keep the old value) manufacture date[dd/MM/YYYY]: ", product.getDateOfManu());
                        Date now = new Date();
                        if (dateOfManu.after(now)) {
                            System.out.println("Date of manufacture must be in the past");
                            continue;
                        } else {
                            break;
                        }
                    } while (true);

                    do {
                        expiryDate = ci.UpdateDate("Enter new product's expiry (Leave blank if you want to keep the old value) date[dd/MM/YYYY]: ", product.getExpiryDate());
                        if (expiryDate.before(dateOfManu)) {
                            System.out.println("Expiry date must be after date of manufacture");
                            continue;
                        } else {
                            break;
                        }
                    } while (true);

                    String category = ci.UpdateNamecate("Enter new category (Leave blank if you want to keep the old value): ", product.getCategory(), "Enter a string type", "^[a-zA-Z ]+$");
                    Storekeeper store = null;
                    do {
                        System.out.println("__________Choose Storekeeper_________");
                        for (Storekeeper storelist : bs.getStoreList()) {
                            storelist.display();
                        }
                        String msg = "Choice must be in range [" + 1 + "-" + (bs.getStoreList().size()) + "]";
                        int storekeeperChoice = ci.getInt("Choose storekeeper: ", msg, 1, bs.getStoreList().size());
                        for (Storekeeper storeInlist : bs.getStoreList()) {
                            if (storeInlist.getStorekeeperID() == storekeeperChoice) {
                                store = storeInlist;
                                break;
                            }
                        }
                    } while (store == null);

                    Date receiptDate;
                    do {
                        Date now = new Date();
                        receiptDate = ci.UpdateDate("Enter new product's expiry (Leave blank if you want to keep the old value) date[dd/MM/YYYY]: ", product.getReceiptDate());
                        if (receiptDate.before(dateOfManu) || receiptDate.after(expiryDate) || receiptDate.after(now)) {
                            System.out.println("Receipt date must be from " + dateOfManu + " to "
                                    + expiryDate);
                            System.out.println("Receipt date have to after today.");
                            continue;
                        } else {
                            break;
                        }
                    } while (true);

                    bp.updateProduct(index, bp.getListProduct().get(index).getProductId(), productName, productLocation, price, expiryDate, dateOfManu, category, store, receiptDate);
                    System.out.println("Update successfully.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            System.out.println("List is empty!");
        }
    }

    public void search() {
        System.out.println("=========Search===========");
        System.out.println("Menu Search:");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by category");
        System.out.println("3. Search by storekeeper");
        System.out.println("4. Search by receipt date");
        int choice = ci.getInt("Your choice:", "Choice must be in range [1-6]", 1, 4);
        switch (choice) {
            case 1:
                searchByName();
                break;
            case 2:
                searchByCategory();
                break;
            case 3:
                searchByStorekeeper();
                break;
            case 4:
                searchByReceiptdate();
                break;
        }

    }

    void searchByName() {
        System.out.println("=========Search By Name===========");
        if (bp.getListProduct().isEmpty()) {
            System.out.println("List is empty!");
        } else {
            try {
                String SearchName = ci.getStringAdd("Enter name for searching: ", "", "");
                ArrayList<Product> resultList = bp.searchByName(SearchName);
                if (resultList.isEmpty()) {
                    System.out.println("Product is not found!");
                } else {
                    displayProductList(resultList);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void searchByCategory() {
        System.out.println("=========Search By Category===========");
        if (bp.getListProduct().isEmpty()) {
            System.out.println("List is empty!");
        } else {
            try {
                String searchcategory = ci.getStringAdd("Enter category for searching: ", "", "");
                ArrayList<Product> resultList = bp.searchByCategory(searchcategory);
                if (resultList.isEmpty()) {
                    System.out.println("Product is not found!");
                } else {
                    displayProductList(resultList);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void searchByStorekeeper() {
        System.out.println("=========Search By Storekeeper===========");
        if (bp.getListProduct().isEmpty()) {
            System.out.println("List is empty!");
        } else {
            try {
                String storekeeperName = ci.getStringAdd("Enter storekeeper name for searching: ", "", "");
                ArrayList<Product> resultList = bp.searchByStorekeeper(storekeeperName);
                if (resultList.isEmpty()) {
                    System.out.println("Product is not found!");
                } else {
                    displayProductList(resultList);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    void searchByReceiptdate() {
        System.out.println("=========Search By Receipt Date===========");
        if (bp.getListProduct().isEmpty()) {
            System.out.println("List is empty!");
        } else {

            Date receiptdate = ci.getDate("Enter receipt date for search: ");
            ArrayList<Product> resultList = bp.searchByReceiptDate(receiptdate);
            if (resultList.isEmpty()) {
                System.out.println("Product is not found!");
            } else {
                displayProductList(resultList);
            }
        }
    }

    public void sortProduct() {
        System.out.println("=========Sort===========");
        try {
            bp.sort();
            displayProductList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void displayProductList() {
        if (bp.getListProduct().isEmpty()) {
            System.out.println("List is empty!");
        } else {
            System.out.println("___________Product List___________");
            System.out.format("%-3s%-15s%-10s%-10s%-20s%-20s%-15s%-20s%-15s\n", "Id", "Name", "Location", "Price", "Manufacture date", "Expiry date",
                    "Category", "Storekeeper", "Receipt date");
            for (Product product : bp.getListProduct()) {
                displayProduct(product);
            }
        }
    }

    public void displayProductList(List<Product> list) {
        if (bp.getListProduct().isEmpty()) {
            System.out.println("List is empty!");
        } else {
            System.out.println("___________Product List___________");
            System.out.format("%-3s%-15s%-10s%-10s%-20s%-20s%-15s%-20s%-15s\n", "Id", "Name", "Location", "Price", "Manufacture date", "Expiry date",
                    "Category", "Storekeeper", "Receipt date");
            for (Product product : list) {
                displayProduct(product);
            }
        }
    }

    public void displayProduct(Product product) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String expiryDate = dateFormat.format(product.getExpiryDate());
        String dateOfManu = dateFormat.format(product.getDateOfManu());
        String receiptDate = dateFormat.format(product.getReceiptDate());

        System.out.format("%-3d%-15s%-10s%-10f%-20s%-20s%-15s%-20s%-15s\n", product.getProductId(), product.getProductName(), product.getLocation(),
                product.getPrice(), dateOfManu, expiryDate, product.getCategory(), product.getStorekeeper().getStorekeeperName(),
                receiptDate);
    }

    public void Menu() {
        System.out.println("1. Add Storekeeper");
        System.out.println("2. Add product");
        System.out.println("3. Update product");
        System.out.println("4. Search product by Name, Category, Storekeeper, ReceiptDate");
        System.out.println("5. Sort product by Expiry date, Date of manufacture");
        System.out.println("6. Exit");
    }

}
