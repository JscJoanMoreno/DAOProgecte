package albumDao.EmployeeDao;

import albumDao.EmployeeDao.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class MainEmployee {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection con = DriverManager.getConnection("jdbc:sqlite:bbdd/empleat.db");
        EmployeeDao dao = new EmployeeDaoImplementacio(con);

        int opcio;
        do {
            System.out.println("""
                    1. Inserir empleat
                    2. Mostrar tots
                    3. Mostrar per ID
                    4. Eliminar per ID
                    5. Actualitzar
                    6. Mostrar amb departament (LEFT JOIN)
                    0. Sortir
                    """);
            opcio = sc.nextInt();
            sc.nextLine();

            switch (opcio) {
                case 1 -> {
                    System.out.print("Nom: ");
                    String nom = sc.nextLine();
                    System.out.print("Cognom: ");
                    String cognom = sc.nextLine();
                    dao.inserir(new Employee(0, nom, cognom));
                }
                case 2 -> System.out.println(dao.mostrarTots());
                case 3 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    System.out.println(dao.obtenirPerId(id));
                }
                case 4 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    dao.eliminarPerId(id);
                }
                case 5 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Nou nom: ");
                    String nom = sc.nextLine();
                    System.out.print("Nou cognom: ");
                    String cognom = sc.nextLine();
                    dao.actualitzar(new Employee(id, nom, cognom));
                }
                case 6 -> System.out.println(dao.getEmployeesAmbDepartament());
                case 0 -> System.out.println("Fi del programa");
                default -> System.out.println("Opció no vàlida");
            }
        } while (opcio != 0);
    }
}
