package albumDao.GenreDao;

import java.sql.SQLException;
import java.util.List;

public interface GenreDao {
    int create(Genre genre) throws SQLException;
    Genre read(int id) throws SQLException;
    void update(Genre genre) throws SQLException;
    void delete(int id) throws SQLException;
    List<Genre> getGenres() throws SQLException;
}
