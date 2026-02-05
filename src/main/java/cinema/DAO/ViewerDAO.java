package cinema.DAO;

import cinema.data.PostgresDB;
import cinema.models.Viewer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewerDAO {

    public void create(Viewer v) throws SQLException {
        String sql = "INSERT INTO viewer(id, name, email, phone, age, is_premium) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, v.getId());
            ps.setString(2, v.getName());
            ps.setString(3, v.getEmail());
            ps.setString(4, v.getPhone());
            ps.setInt(5, v.getAge());
            ps.setBoolean(6, v.isPremium());

            ps.executeUpdate();
        }
    }

    public List<Viewer> readAll() throws SQLException {
        List<Viewer> viewers = new ArrayList<>();
        String sql = "SELECT id, name, email, phone, age, is_premium FROM viewer";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                viewers.add(new Viewer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("age"),
                        rs.getBoolean("is_premium")
                ));
            }
        }
        return viewers;
    }

    public List<Viewer> filterPremium(boolean isPremium) throws SQLException {
        List<Viewer> viewers = new ArrayList<>();
        String sql = "SELECT id, name, email, phone, age, is_premium FROM viewer WHERE is_premium = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, isPremium);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    viewers.add(new Viewer(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getInt("age"),
                            rs.getBoolean("is_premium")
                    ));
                }
            }
        }
        return viewers;
    }

    public boolean updatePremiumStatus(int id, boolean isPremium) throws SQLException {
        String sql = "UPDATE viewer SET is_premium = ? WHERE id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setBoolean(1, isPremium);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        }
    }

    public boolean deleteById(int id) throws SQLException {
        String sql = "DELETE FROM viewer WHERE id = ?";

        try (Connection conn = PostgresDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
