package cinema.DTO;

public class CinemaDTO {
    private int id;
    private String name;
    private String address;
    private int capacity;
    private String type;
    private int screenNumber;

    // Default constructor
    public CinemaDTO() {}

    // Constructor with all fields
    public CinemaDTO(int id, String name, String address, int capacity, String type, int screenNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.type = type;
        this.screenNumber = screenNumber;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
