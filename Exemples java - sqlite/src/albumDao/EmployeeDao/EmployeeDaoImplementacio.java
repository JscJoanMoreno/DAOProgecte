package albumDao.EmployeeDao;

import albumDao.connexio.Connexio;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImplementacio implements EmployeeDao {
    static Connection con = Connexio.getConnection();

    @Override
    public int create(Employee e) throws SQLException {
        String query = "INSERT INTO Employee (FirstName, LastName) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, e.getNom());
        ps.setString(2, e.getCognom());
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public Employee read(int id) throws SQLException {
        String query = "SELECT * FROM Employee WHERE EmployeeId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Employee(
                    rs.getInt("EmployeeId"),
                    rs.getString("FirstName"),
                    rs.getString("LastName")
            );
        }
        return null;
    }

    @Override
    public void update(Employee e) throws SQLException {
        String query = "UPDATE Employee SET FirstName = ?, LastName = ? WHERE EmployeeId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, e.getNom());
        ps.setString(2, e.getCognom());
        ps.setInt(3, e.getId());
        ps.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM Employee WHERE EmployeeId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        List<Employee> llista = new ArrayList<>();
        String query = "SELECT * FROM Employee";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            llista.add(new Employee(
                    rs.getInt("EmployeeId"),
                    rs.getString("FirstName"),
                    rs.getString("LastName")
            ));
        }
        return llista;
    }
}
