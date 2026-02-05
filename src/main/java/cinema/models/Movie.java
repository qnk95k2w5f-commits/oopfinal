package cinema.models;

public class Movie {
    private int id;
    private String name;
    private String genre;
    private int duration; // в минутах
    private double rating;
    private String director;

    public Movie(int id, String name, String genre, int duration, double rating, String director) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.duration = duration;
        this.rating = rating;
        this.director = director;
    }

    // Getters
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
    public int getId() {return id;}
    public String getName() {return name;}
    // Setters
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

    @Override
    public String toString() {
        return super.toString() + 
               ", Genre: " + genre + 
               ", Duration: " + duration + " min" +
               ", Rating: " + rating + 
               ", Director: " + director;
    }
}
