package albumBasicJDBC;

import java.sql.*;

public class Genre {
    private int id;
    private String nom;

    public Genre(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public static Genre getById(int id) throws SQLException {
        Connection con = Connexio.getConnection();
        String query = "SELECT * FROM Genre WHERE GenreId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return new Genre(id, rs.getString("Name"));
        return null;
    }

    @Override
    public String toString() {
        return "Genre{" + "id=" + id + ", nom='" + nom + '\'' + '}';
    }
}
