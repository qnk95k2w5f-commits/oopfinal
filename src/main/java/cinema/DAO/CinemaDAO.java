package cinema.DAO;

import cinema.data.PostgresDB;
import cinema.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAO {

    public void create(Cinema c) throws SQLException {
        String sql = "INSERT INTO cinema(id, name, address, capacity, type, screen_number) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getAddress());
            ps.setInt(4, c.getCapacity());
            ps.setString(5, c.getType());
            ps.setInt(6, c.getScreenNumber());

            ps.executeUpdate();
        }
    }

    public List<Cinema> readAll() throws SQLException {
        List<Cinema> cinemas = new ArrayList<>();
        String sql = "SELECT id, name, address, capacity, type, screen_number FROM cinema";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                cinemas.add(new Cinema(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("address"),
                        rs.getInt("capacity"),
                        rs.getString("type"),
                        rs.getInt("screen_number")
                ));
            }
        }
        return cinemas;
    }

    public List<Cinema> filterByType(String type) throws SQLException {
        List<Cinema> cinemas = new ArrayList<>();
        String sql = "SELECT id, name, address, capacity, type, screen_number FROM cinema WHERE type = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, type);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cinemas.add(new Cinema(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getInt("capacity"),
                            rs.getString("type"),
                            rs.getInt("screen_number")
                    ));
                }
            }
        }
        return cinemas;
    }

    public boolean updateCapacity(int id, int newCapacity) throws SQLException {
        String sql = "UPDATE cinema SET capacity = ? WHERE id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newCapacity);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM cinema WHERE id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
