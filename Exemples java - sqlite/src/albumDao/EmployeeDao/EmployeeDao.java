package albumDao.EmployeeDao;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    void inserir(Employee e) throws SQLException;
    List<Employee> mostrarTots() throws SQLException;
    Employee obtenirPerId(int id) throws SQLException;
    void eliminarPerId(int id) throws SQLException;
    void actualitzar(Employee e) throws SQLException;

    // NOVA FUNCIÓ AMB JOIN
    List<Employee> getEmployeesAmbDepartament() throws SQLException;
}
