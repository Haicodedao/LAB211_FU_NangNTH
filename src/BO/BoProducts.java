package BO;

import data.Product;
import data.Storekeeper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class BoProducts {

    private List<Product> listProduct;

    public BoProducts() {
        listProduct = new ArrayList<>();
    }

    public BoProducts(List<Product> productList) {
        this.listProduct = productList;
    }

    public void addProduct(Product newProduct) throws Exception {
        if (newProduct == null) {
            throw new Exception("New product must have a value.");
        }
        listProduct.add(newProduct);
    }

    public void updateProduct(int index, int id, String productName, String productLocation, double price, Date expiryDate, Date dateOfManu, String category, Storekeeper storeName, Date receiptDate) throws Exception {
        if (index >= 0 && index < listProduct.size()) {
            listProduct.set(index, new Product(id, productName, productLocation, price, expiryDate, dateOfManu, category, storeName, receiptDate));
        } else {
            throw new Exception("Invalid index.");
        }
    }

    public ArrayList<Product> searchByCategory(String category) throws Exception {
        if (category.isEmpty()) {
            throw new Exception("Category is empty.");
        }
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : listProduct) {
            if (product.getCategory().contains(category)) {
                results.add(product);
            }
        }
        return results;
    }

    public ArrayList<Product> searchByStorekeeper(String store) throws Exception {
        if (store.isEmpty()) {
            throw new Exception("Store is empty.");
        }
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : listProduct) {
            if (product.getStorekeeper().getStorekeeperName().contains(store)) {
                results.add(product);
            }
        }
        return results;
    }

    public ArrayList<Product> searchByReceiptDate(Date receiptDate) {
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : listProduct) {
            if (product.getReceiptDate().equals(receiptDate)) {
                results.add(product);
            }
        }
        return results;
    }

    public ArrayList<Product> searchByName(String name) throws Exception {
        if (name.isEmpty()) {
            throw new Exception("Name is empty.");
        }
        ArrayList<Product> results = new ArrayList<>();
        for (Product product : listProduct) {
            if (product.getProductName().contains(name)) {
                results.add(product);
            }
        }
        return results;
    }

    public boolean checkDuplicateIdProduct(int id) {
        boolean isExist = false;
        for (Product product : listProduct) {
            if (product.getProductId() == id) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    public int searchById(int id) {
        int resultIndex = -1;
        for (Product product : listProduct) {
            if (product.getProductId() == id) {
                resultIndex = listProduct.indexOf(product);
                break;
            }
        }
        return resultIndex;
    }

    public void sort() throws Exception {
        if (listProduct.isEmpty()) {
            throw new Exception("List is empty.");
        }
        Collections.sort(listProduct, new Comparator<Product>() {
            @Override
            // sort theo ngày hết hạn
            public int compare(Product o1, Product o2) {
                if (o1.getExpiryDate().equals(o2.getExpiryDate())) {
                    return o1.getDateOfManu().compareTo(o2.getDateOfManu());
                } else {
                    return o1.getExpiryDate().compareTo(o2.getExpiryDate());
                }
            }
        });
    }
 
    public List<Product> getListProduct() {
        return listProduct;
    } 
    
}