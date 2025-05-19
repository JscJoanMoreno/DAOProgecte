package albumBasicJDBC;

import java.sql.*;

public class MediaType {
    private int id;
    private String nom;

    public MediaType(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public static MediaType getById(int id) throws SQLException {
        Connection con = Connexio.getConnection();
        String query = "SELECT * FROM MediaType WHERE MediaTypeId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) return new MediaType(id, rs.getString("Name"));
        return null;
    }

    @Override
    public String toString() {
        return "MediaType{" + "id=" + id + ", nom='" + nom + '\'' + '}';
    }
}
