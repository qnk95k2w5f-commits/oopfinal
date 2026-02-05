package cinema.DTO;

public class BookingDTO {
    private int id;
    private int viewerId;
    private int movieId;
    private int cinemaId;
    private String bookingDate;
    private String showTime;
    private int seatNumber;
    private double price;
    private boolean isPaid;

    // Default constructor
    public BookingDTO() {}

    // Constructor with all fields
    public BookingDTO(int id, int viewerId, int movieId, int cinemaId,
                      String bookingDate, String showTime, int seatNumber,
                      double price, boolean isPaid) {
        this.id = id;
        this.viewerId = viewerId;
        this.movieId = movieId;
        this.cinemaId = cinemaId;
        this.bookingDate = bookingDate;
        this.showTime = showTime;
        this.seatNumber = seatNumber;
        this.price = price;
        this.isPaid = isPaid;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getViewerId() {
        return viewerId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public double getPrice() {
        return price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setViewerId(int viewerId) {
        this.viewerId = viewerId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
