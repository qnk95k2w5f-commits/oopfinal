package cinema.DTO;

public class MovieDTO {
    private int id;
    private String name;
    private String genre;
    private int duration;
    private double rating;
    private String director;

    // Default constructor
    public MovieDTO() {}

    // Constructor with all fields
    public MovieDTO(int id, String name, String genre, int duration, double rating, String director) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.director = director;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public double getRating() {
        return rating;
    }

    public String getDirector() {
        return director;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
