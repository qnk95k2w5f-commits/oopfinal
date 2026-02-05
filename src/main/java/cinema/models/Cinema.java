package cinema.models;

public class Cinema {
    private int id;
    private String name;
    private String address;
    private int capacity;
    private String type; // IMAX, 3D, Standard
    private int screenNumber;

    public Cinema(int id, String name, String address, int capacity, String type, int screenNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.type = type;
        this.screenNumber = screenNumber;
    }

    // Getters
    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getType() {
        return type;
    }

    public int getScreenNumber() {
        return screenNumber;
    }
    public int getId() {return id;}
    public String getName() {return name;}
    // Setters
    public void setAddress(String address) {
        this.address = address;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setScreenNumber(int screenNumber) {
        this.screenNumber = screenNumber;
    }

    @Override
    public String toString() {
        return super.toString() + 
               ", Address: " + address + 
               ", Capacity: " + capacity + 
               ", Type: " + type + 
               ", Screen: " + screenNumber;
    }
}
