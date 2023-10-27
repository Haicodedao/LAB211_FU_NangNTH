package data;

import data.Storekeeper;
import java.util.Date;

public class Product {
    private int productId;
    private String productName;
    private String location;
    private double price;
    private Date expiryDate;
    private Date dateOfManu;
    private String category;
    private Storekeeper storekeeper;
    private Date receiptDate;

    public Product() {
    }

    public Product(int productId, String productName, String location, double price, Date expiryDate, Date dateOfManu, String category, Storekeeper storekeeper, Date receiptDate) {
        this.productId = productId;
        this.productName = productName;
        this.location = location;
        this.price = price;
        this.expiryDate = expiryDate;
        this.dateOfManu = dateOfManu;
        this.category = category;
        this.storekeeper = storekeeper;
        this.receiptDate = receiptDate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Date getDateOfManu() {
        return dateOfManu;
    }

    public void setDateOfManu(Date dateOfManu) {
        this.dateOfManu = dateOfManu;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Storekeeper getStorekeeper() {
        return storekeeper;
    }

    public void setStorekeeper(Storekeeper storekeeper) {
        this.storekeeper = storekeeper;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }
    
    
}