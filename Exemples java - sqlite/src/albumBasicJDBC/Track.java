package albumBasicJDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Track {
    private int id;
    private String nom;
    private int durada;
    private MediaType mediaType;
    private Genre genre;
    private static final Connection con = Connexio.getConnection();

    public Track() {}

    public Track(int id, String nom, int durada, MediaType mediaType, Genre genre) {
        this.id = id;
        this.nom = nom;
        this.durada = durada;
        this.mediaType = mediaType;
        this.genre = genre;
    }

    public int creaTrack(String nom, int durada, int idMediaType, int idGenre) {
        int idNou = -1;
        try {
            String query = "INSERT INTO Track (Name, Milliseconds, MediaTypeId, GenreId) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, nom);
            ps.setInt(2, durada);
            ps.setInt(3, idMediaType);
            ps.setInt(4, idGenre);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) idNou = rs.getInt(1);
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error creant track: " + e.getMessage());
        }
        return idNou;
    }

    public Track llegeixTrack(int idTrack) {
        Track track = null;
        try {
            String query = "SELECT * FROM Track WHERE TrackId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idTrack);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                int durada = rs.getInt("Milliseconds");
                int mediaId = rs.getInt("MediaTypeId");
                int genreId = rs.getInt("GenreId");
                MediaType media = MediaType.getById(mediaId);
                Genre genre = Genre.getById(genreId);
                track = new Track(idTrack, name, durada, media, genre);
            }
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error llegint track: " + e.getMessage());
        }
        return track;
    }

    public void modificaTrack(int idTrack, String nouNom, int novaDurada, int idMediaType, int idGenre) {
        try {
            String query = "UPDATE Track SET Name = ?, Milliseconds = ?, MediaTypeId = ?, GenreId = ? WHERE TrackId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, nouNom);
            ps.setInt(2, novaDurada);
            ps.setInt(3, idMediaType);
            ps.setInt(4, idGenre);
            ps.setInt(5, idTrack);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error modificant track: " + e.getMessage());
        }
    }

    public void eliminaTrack(int idTrack) {
        try {
            String query = "DELETE FROM Track WHERE TrackId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idTrack);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.err.println("Error eliminant track: " + e.getMessage());
        }
    }

    public List<Track> seleccionaTracks() {
        List<Track> tracks = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Track");
            while (rs.next()) {
                int id = rs.getInt("TrackId");
                String nom = rs.getString("Name");
                int durada = rs.getInt("Milliseconds");
                int mediaId = rs.getInt("MediaTypeId");
                int genreId = rs.getInt("GenreId");
                MediaType media = MediaType.getById(mediaId);
                Genre genre = Genre.getById(genreId);
                tracks.add(new Track(id, nom, durada, media, genre));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error seleccionant tracks: " + e.getMessage());
        }
        return tracks;
    }

    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", durada=" + durada +
                ", mediaType=" + mediaType +
                ", genre=" + genre +
                '}';
    }
}
