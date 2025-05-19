package albumBasicJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Album {

    private int idAlbum;
    private String titol;
    private Artista artista;
    private static final Connection con = Connexio.getConnection();

    public Album() {}

    public Album(int idAlbum, String titol, Artista artista) {
        this.idAlbum = idAlbum;
        this.titol = titol;
        this.artista = artista;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "Album{" +
                "idAlbum=" + idAlbum +
                ", titol='" + titol + '\'' +
                ", artista=" + artista +
                '}';
    }

    public static boolean existeixArtista(int idArtista) {
        try {
            String query = "SELECT 1 FROM Artist WHERE ArtistId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idArtista);
            ResultSet rs = ps.executeQuery();
            boolean existeix = rs.next();
            rs.close();
            ps.close();
            return existeix;
        } catch (SQLException e) {
            System.err.println("Error al comprovar l'artista: " + e.getMessage());
            return false;
        }
    }

    public int creaAlbum(String titol, int idArtista) {
        int idAlbumNou = -1;
        try {
            if (!existeixArtista(idArtista)) {
                System.out.println("No existeix cap artista amb aquest ID.");
                return -1;
            }

            String query = "INSERT INTO Album (Title, ArtistId) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, titol);
            ps.setInt(2, idArtista);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                idAlbumNou = rs.getInt(1);
            }

            rs.close();
            ps.close();
            System.out.println("Àlbum creat correctament");
        } catch (SQLException e) {
            System.err.println("Error al crear àlbum: " + e.getMessage());
        }
        return idAlbumNou;
    }

    public Album llegeixAlbum(int idAlbum) {
        Album album = null;
        try {
            String query = "SELECT * FROM Album WHERE AlbumId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idAlbum);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                int artistId = rs.getInt("ArtistId");

                Artista artista = Artista.getArtistaAmbId(artistId);
                album = new Album(albumId, title, artista);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error al llegir l'àlbum: " + e.getMessage());
        }
        return album;
    }

    public void modificaAlbum(int idAlbum, String nouTitol, int nouIdArtista) {
        try {
            if (!existeixArtista(nouIdArtista)) {
                System.out.println("No existeix cap artista amb aquest ID.");
                return;
            }

            String query = "UPDATE Album SET Title = ?, ArtistId = ? WHERE AlbumId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nouTitol);
            ps.setInt(2, nouIdArtista);
            ps.setInt(3, idAlbum);
            ps.executeUpdate();
            ps.close();
            System.out.println("Àlbum modificat correctament");
        } catch (SQLException e) {
            System.err.println("Error al modificar l'àlbum: " + e.getMessage());
        }
    }

    public void eliminaAlbum(int idAlbum) {
        try {
            String query = "DELETE FROM Album WHERE AlbumId=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idAlbum);
            ps.executeUpdate();
            ps.close();
            System.out.println("Àlbum eliminat correctament");
        } catch (SQLException e) {
            System.err.println("Error al eliminar l'àlbum: " + e.getMessage());
        }
    }

    public List<Album> seleccionaAlbums() {
        List<Album> albums = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Album;");
            while (rs.next()) {
                int albumId = rs.getInt("AlbumId");
                String title = rs.getString("Title");
                int artistId = rs.getInt("ArtistId");
                Artista artista = Artista.getArtistaAmbId(artistId);
                albums.add(new Album(albumId, title, artista));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error al seleccionar els àlbums: " + e.getMessage());
        }
        return albums;
    }
}
