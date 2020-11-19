import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MokinioMeniu {
    private Connection connection;
    private int mokinioNr;

    public MokinioMeniu(Connection connection, int mokinioNr) {
        this.connection = connection;
        this.mokinioNr = mokinioNr;
    }

    public void start() {
        boolean toLoop = true;

        while (toLoop) {
            int choice = displayOptions();
            switch (choice) {
                case 0:
                    toLoop = false;
                    break;
                case 1:
                    displayInfo();
                    break;
                case 2:
                    displayGrades();
                    break;
                case 3:
                    displayPastabos();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private int displayOptions() {
        System.out.println("---PASIRINKIMAI---");
        System.out.println("0. Grizti i pagrindini meniu");
        System.out.println("1. Mano informacija");
        System.out.println("2. Mano pazymiai");
        System.out.println("3. Mano pastabos");
        System.out.println();
        return MainMeniu.readInt(0, 3);
    }

    private void displayInfo() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Mokinys WHERE mokinio_nr = " + mokinioNr);
            rs.next();
            System.out.println();
            System.out.println("-------------JUSU INFORMACIJA------------");
            System.out.println("Mokinio numeris: " + rs.getInt("mokinio_nr"));
            System.out.println("AK: " + rs.getString("AK"));
            System.out.println("Vardas: " + rs.getString("vardas"));
            System.out.println("Pavarde: " + rs.getString("pavarde"));
            System.out.println("Gimimo data: " + rs.getDate("gimimo_data"));
            System.out.println("Adresas: " + rs.getString("adresas"));
            System.out.println("Globejo AK: " + rs.getString("globejo_AK"));
            System.out.println("Klases pavadinimas: " + rs.getString("klases_pavadinimas"));
            System.out.println();
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException exp) {
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    private void displayGrades(){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT dalykas, pazymys FROM Mokinys JOIN Pazymys ON Mokinys.mokinio_nr = Pazymys.mokinys WHERE mokinio_nr = "
                    + mokinioNr);
            System.out.println();
            if (!rs.isBeforeFirst() ) {
                System.out.println("Jus neturite pazymiu.");
            }
            else {
                System.out.println("-------------JUSU PAZYMIAI------------");
                while (rs.next()) {
                    System.out.print(rs.getString("dalykas") + "  ");
                    System.out.println(rs.getShort("pazymys"));
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } finally {
            System.out.println();
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException exp) {
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    private void displayPastabos(){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT data, laikas, priezastis FROM Pastaba WHERE mokinys = " + mokinioNr);
            System.out.println();
            if (!rs.isBeforeFirst() ) {
                System.out.println("Jus neturite pastabu.");
            }
            else {
                System.out.println("-------------JUSU PASTABOS------------");
                while (rs.next()) {
                    System.out.println(rs.getDate("data"));
                    System.out.println(rs.getTime("laikas"));
                    System.out.println(rs.getString("priezastis"));
                    System.out.println();
                }
            }
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } finally {
            System.out.println();
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
            } catch (SQLException exp) {
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

}
