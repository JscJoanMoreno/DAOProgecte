package albumDao.EmployeeDao;

import java.sql.SQLException;
import java.util.Scanner;

public class MainEmployee {
    public static void menu() {
        System.out.println("Tria una opció:\n" +
                "1) Llistar empleats\n" +
                "2) Mostrar empleat per ID\n" +
                "3) Afegir empleat\n" +
                "4) Modificar empleat\n" +
                "5) Eliminar empleat\n" +
                "0) Sortir");
    }

    public static void main(String[] args) throws SQLException {
        EmployeeDao employeeDao = new EmployeeDaoImplementacio();
        Scanner sc = new Scanner(System.in);
        int opcio;

        do {
            menu();
            opcio = sc.nextInt(); sc.nextLine();

            switch (opcio) {
                case 1:
                    System.out.println(employeeDao.getEmployees());
                    break;
                case 2:
                    System.out.println("id ;");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.println(employeeDao.read(id));
                    break;
                case 3:
                    System.out.println("Nom:");
                    String nom = sc.nextLine();
                    System.out.println("Cognom:");
                    String cognom = sc.nextLine();
                    int nouId = employeeDao.create(new Employee(0, nom, cognom));
                    System.out.println("creat amb ID: " + nouId);
                    break;
                case 4:
                    System.out.println("Id a modificar:");
                    int idMod = sc.nextInt(); sc.nextLine();
                    System.out.println("Nou nom:");
                    String nouNom = sc.nextLine();
                    System.out.println("Nou cognom:");
                    String nouCognom = sc.nextLine();
                    employeeDao.update(new Employee(idMod, nouNom, nouCognom));
                    System.out.println("Actualitzat.");
                    break;
                case 5:
                    System.out.println("id a eliminar:");
                    int idDel = sc.nextInt(); sc.nextLine();
                    employeeDao.delete(idDel);
                    System.out.println("Eliminat.");
                    break;
                case 0:
                    System.out.println("Sortint...");
                    break;
                default:
                    System.out.println("opció no vàlida.");
            }

        } while (opcio != 0);
    }
}
