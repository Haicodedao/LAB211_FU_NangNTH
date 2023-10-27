package data;


public class Storekeeper {

    private int StorekeeperID;
    private String StorekeeperName;

    public Storekeeper() {
    }

    public Storekeeper(int StorekeeperID, String StorekeeperName) {
        this.StorekeeperID = StorekeeperID;
        this.StorekeeperName = StorekeeperName;
    }

    public int getStorekeeperID() {
        return StorekeeperID;
    }

    public void setStorekeeperID(int StorekeeperID) {
        this.StorekeeperID = StorekeeperID;
    }

    public String getStorekeeperName() {
        return StorekeeperName;
    }

    public void setStorekeeperName(String StorekeeperName) {
        this.StorekeeperName = StorekeeperName;
    }
    public void display(){
        System.out.printf("%-2d%-20s\n",StorekeeperID, StorekeeperName );
    }
}
