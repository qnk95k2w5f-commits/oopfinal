package cinema.DAO;

import cinema.data.PostgresDB;
import cinema.models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

    public void create(Movie m) throws SQLException {
        String sql = "INSERT INTO movie(id, name, genre, duration, rating, director) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getId());
            ps.setString(2, m.getName());
            ps.setString(3, m.getGenre());
            ps.setInt(4, m.getDuration());
            ps.setDouble(5, m.getRating());
            ps.setString(6, m.getDirector());

            ps.executeUpdate();
        }
    }

    public List<Movie> readAll() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT id, name, genre, duration, rating, director FROM movie";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("genre"),
                        rs.getInt("duration"),
                        rs.getDouble("rating"),
                        rs.getString("director")
                ));
            }
        }
        return movies;
    }

    public List<Movie> readAllSortedByRating() throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT id, name, genre, duration, rating, director FROM movie ORDER BY rating DESC";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("genre"),
                        rs.getInt("duration"),
                        rs.getDouble("rating"),
                        rs.getString("director")
                ));
            }
        }
        return movies;
    }

    public List<Movie> filterByGenre(String genre) throws SQLException {
        List<Movie> movies = new ArrayList<>();
        String sql = "SELECT id, name, genre, duration, rating, director FROM movie WHERE genre = ? ORDER BY rating DESC";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, genre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    movies.add(new Movie(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("genre"),
                            rs.getInt("duration"),
                            rs.getDouble("rating"),
                            rs.getString("director")
                    ));
                }
            }
        }
        return movies;
    }

    public boolean updateRating(int id, double newRating) throws SQLException {
        String sql = "UPDATE movie SET rating = ? WHERE id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDouble(1, newRating);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM movie WHERE id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
