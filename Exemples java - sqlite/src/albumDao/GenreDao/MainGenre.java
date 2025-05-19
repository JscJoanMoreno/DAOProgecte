package albumDao.GenreDao;

import java.sql.SQLException;
import java.util.Scanner;

public class MainGenre {
    public static void menu() {
        System.out.println("Tria una opció:\n" +
                "1) Llistar gèneres\n" +
                "2) Mostrar gènere per id\n" +
                "3) afegir gènere\n" +
                "4) Modificar gènere\n" +
                "5) eliminar gènere\n" +
                "0) Sortir");
    }

    public static void main(String[] args) throws SQLException {
        GenreDao genreDao = new GenreDaoImplementacio();
        Scanner sc = new Scanner(System.in);
        int opcio;

        do {
            menu();
            opcio = sc.nextInt(); sc.nextLine();

            switch (opcio) {
                case 1:
                    System.out.println(genreDao.getGenres());
                    break;
                case 2:
                    System.out.print("ID del genere : ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.println(genreDao.read(id));
                    break;
                case 3:
                    System.out.print("Nom del nou genere: ");
                    String nom = sc.nextLine();
                    int nouId = genreDao.create(new Genre(0, nom));
                    System.out.println("Creat amb ID: " + nouId);
                    break;
                case 4:
                    System.out.print("Id a modificar: ");
                    int idMod = sc.nextInt(); sc.nextLine();
                    System.out.print("Nou nom: ");
                    String nouNom = sc.nextLine();
                    genreDao.update(new Genre(idMod, nouNom));
                    System.out.println("gènere actualitzat.");
                    break;
                case 5:
                    System.out.print("ID a eliminar : ");
                    int idDel = sc.nextInt(); sc.nextLine();
                    genreDao.delete(idDel);
                    System.out.println("Gènere eliminat.");
                    break;
                case 0:
                    System.out.println("Sortint...");
                    break;
                default:
                    System.out.println("Opció no valida.");
            }
        } while (opcio != 0);
    }
}
