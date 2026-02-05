package cinema.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDB {
    // Измените название БД, пользователя и пароль на свои
    private static final String URL = "jdbc:postgresql://localhost:4112/oopendka";
    private static final String USER = "postgres";
    private static final String PASS = "100208Knn";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
