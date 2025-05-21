package albumDao.EmployeeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImplementacio implements EmployeeDao {

    private final Connection con;

    public EmployeeDaoImplementacio(Connection con) {
        this.con = con;
    }

    @Override
    public void inserir(Employee e) throws SQLException {
        String sql = "INSERT INTO Employee(FirstName, LastName, DepartmentId) VALUES (?, ?, NULL)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, e.getNom());
        ps.setString(2, e.getCognom());
        ps.executeUpdate();
    }

    @Override
    public List<Employee> mostrarTots() throws SQLException {
        List<Employee> llista = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        PreparedStatement ps = con.prepareStatement(sql);
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

    @Override
    public Employee obtenirPerId(int id) throws SQLException {
        String sql = "SELECT * FROM Employee WHERE EmployeeId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
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
    public void eliminarPerId(int id) throws SQLException {
        String sql = "DELETE FROM Employee WHERE EmployeeId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    @Override
    public void actualitzar(Employee e) throws SQLException {
        String sql = "UPDATE Employee SET FirstName = ?, LastName = ? WHERE EmployeeId = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, e.getNom());
        ps.setString(2, e.getCognom());
        ps.setInt(3, e.getId());
        ps.executeUpdate();
    }

    @Override
    public List<Employee> getEmployeesAmbDepartament() throws SQLException {
        List<Employee> llista = new ArrayList<>();
        String query = """
                SELECT e.EmployeeId, e.FirstName, e.LastName, d.DepartmentName
                FROM Employee e
                LEFT JOIN Department d ON e.DepartmentId = d.DepartmentId
                """;

        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            llista.add(new Employee(
                    rs.getInt("EmployeeId"),
                    rs.getString("FirstName"),
                    rs.getString("LastName"),
                    rs.getString("DepartmentNamea")
            ));
        }
        return llista;
    }
}
