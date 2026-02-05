package cinema.DTO;

public class ViewerDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private int age;
    private boolean isPremium;

    // Default constructor
    public ViewerDTO() {}

    // Constructor with all fields
    public ViewerDTO(int id, String name, String email, String phone, int age, boolean isPremium) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.isPremium = isPremium;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public boolean isPremium() {
        return isPremium;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }
}
