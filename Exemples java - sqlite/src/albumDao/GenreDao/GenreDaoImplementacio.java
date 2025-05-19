package albumDao.GenreDao;

import albumDao.connexio.Connexio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImplementacio implements GenreDao {
    static Connection con = Connexio.getConnection();

    @Override
    public int create(Genre g) throws SQLException {
        String query = "INSERT INTO Genre (Name) VALUES (?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, g.getNom());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public Genre read(int id) throws SQLException {
        String query = "SELECT * FROM Genre WHERE GenreId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Genre(rs.getInt("GenreId"), rs.getString("Name"));
        }
        return null;
    }

    @Override
    public void update(Genre g) throws SQLException {
        String query = "UPDATE Genre SET Name = ? WHERE GenreId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, g.getNom());
        ps.setInt(2, g.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM Genre WHERE GenreId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Genre> getGenres() throws SQLException {
        List<Genre> llista = new ArrayList<>();
        String query = "SELECT * FROM Genre";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            llista.add(new Genre(rs.getInt("GenreId"), rs.getString("Name")));
        }
        return llista;
    }
}
