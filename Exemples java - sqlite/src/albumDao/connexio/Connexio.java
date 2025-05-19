package albumDao.connexio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexio {
    private static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection("jdbc:sqlite:Chinook_Sqlite.sqlite");
                System.out.println("Base de dades oberta correctament.");
            } catch (ClassNotFoundException | SQLException e) {
                System.err.println("Error de connexió: " + e.getMessage());
            }
        }
        return con;
    }
}
