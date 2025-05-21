package albumDao.EmployeeDao;

public class Employee {
    private int id;
    private String nom;
    private String cognom;
    private String departamentNom;

    public Employee() {}

    public Employee(int id, String nom, String cognom) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
    }

    public Employee(int id, String nom, String cognom, String departamentNom) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
        this.departamentNom = departamentNom;
    }

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getCognom() { return cognom; }
    public String getDepartamentNom() { return departamentNom; }

    public void setId(int id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setCognom(String cognom) { this.cognom = cognom; }
    public void setDepartamentNom(String departamentNom) { this.departamentNom = departamentNom; }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                ", departament='" + departamentNom + '\'' +
                '}';
    }
}
