package albumDao.EmployeeDao;

public class Employee {
    private int id;
    private String nom;
    private String cognom;

    public Employee() {
    }

    public Employee(int id, String nom, String cognom) {
        this.id = id;
        this.nom = nom;
        this.cognom = cognom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", cognom='" + cognom + '\'' +
                '}';
    }
}
