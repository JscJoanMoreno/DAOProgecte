package albumBasicJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Artista {
    private int id;
    private String nom;

    public Artista(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Artista{" + "id=" + id + ", nom='" + nom + '\'' + '}';
    }

    public static Artista getArtistaAmbId(int id) {
        Artista artista = null;
        try {
            Connection conn = Connexio.getConnection();
            String query = "SELECT * FROM Artist WHERE ArtistId = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nom = rs.getString("Name");
                artista = new Artista(id, nom);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error al obtenir l'artista: " + e.getMessage());
        }
        return artista;
    }
}
