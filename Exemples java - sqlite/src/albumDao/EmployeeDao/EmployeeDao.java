package albumDao.EmployeeDao;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    int create(Employee employee) throws SQLException;
    Employee read(int id) throws SQLException;
    void update(Employee employee) throws SQLException;
    void delete(int id) throws SQLException;
    List<Employee> getEmployees() throws SQLException;
}
