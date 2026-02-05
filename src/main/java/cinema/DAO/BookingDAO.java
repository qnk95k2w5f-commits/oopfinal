package cinema.DAO;

import cinema.data.PostgresDB;
import cinema.models.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public void create(Booking b) throws SQLException {
        String sql = "INSERT INTO booking(id, viewer_id, movie_id, cinema_id, booking_date, show_time, seat_number, price, is_paid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, b.getId());
            ps.setInt(2, b.getViewerId());
            ps.setInt(3, b.getMovieId());
            ps.setInt(4, b.getCinemaId());
            ps.setString(5, b.getBookingDate());
            ps.setString(6, b.getShowTime());
            ps.setInt(7, b.getSeatNumber());
            ps.setDouble(8, b.getPrice());
            ps.setBoolean(9, b.isPaid());

            ps.executeUpdate();
        }
    }

    public List<Booking> readAll() throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT id, viewer_id, movie_id, cinema_id, booking_date, show_time, seat_number, price, is_paid FROM booking";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("id"),
                        rs.getInt("viewer_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("cinema_id"),
                        rs.getString("booking_date"),
                        rs.getString("show_time"),
                        rs.getInt("seat_number"),
                        rs.getDouble("price"),
                        rs.getBoolean("is_paid")
                ));
            }
        }
        return bookings;
    }

    public List<Booking> getByViewerId(int viewerId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT id, viewer_id, movie_id, cinema_id, booking_date, show_time, seat_number, price, is_paid FROM booking WHERE viewer_id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, viewerId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookings.add(new Booking(
                            rs.getInt("id"),
                            rs.getInt("viewer_id"),
                            rs.getInt("movie_id"),
                            rs.getInt("cinema_id"),
                            rs.getString("booking_date"),
                            rs.getString("show_time"),
                            rs.getInt("seat_number"),
                            rs.getDouble("price"),
                            rs.getBoolean("is_paid")
                    ));
                }
            }
        }
        return bookings;
    }

    public List<Booking> filterByPrice(double maxPrice) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT id, viewer_id, movie_id, cinema_id, booking_date, show_time, seat_number, price, is_paid FROM booking WHERE price <= ? ORDER BY price";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, maxPrice);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    bookings.add(new Booking(
                            rs.getInt("id"),
                            rs.getInt("viewer_id"),
                            rs.getInt("movie_id"),
                            rs.getInt("cinema_id"),
                            rs.getString("booking_date"),
                            rs.getString("show_time"),
                            rs.getInt("seat_number"),
                            rs.getDouble("price"),
                            rs.getBoolean("is_paid")
                    ));
                }
            }
        }
        return bookings;
    }

    public boolean updatePaymentStatus(int id, boolean isPaid) throws SQLException {
        String sql = "UPDATE booking SET is_paid = ? WHERE id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, isPaid);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM booking WHERE id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
