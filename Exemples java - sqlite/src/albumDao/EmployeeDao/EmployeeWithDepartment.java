package albumDao.EmployeeDao;

public class EmployeeWithDepartment {
    private int id;
    private String nom;
    private String cognom;
    private String departmentName;

    public EmployeeWithDepartment(int id, String nom, String cognom, String departmentName) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.departmentName = departmentName;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getCognom() {
        return cognom;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    @Override
    public String toString() {
        return "EmployeeWithDepartment{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
