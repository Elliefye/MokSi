import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class AdministratoriausMeniu {
    private Connection connection;
    private BufferedReader br;

    public AdministratoriausMeniu(Connection connection) {
        this.connection = connection;
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start(){
        boolean toLoop = true;

        while (toLoop) {
            int choice = displayOptions();
            switch (choice) {
                case 0:
                    toLoop = false;
                    break;
                case 1:
                    registerNewParent();
                    break;
                case 2:
                    registerNewStudent();
                    break;
                case 3:
                    evaluateStudent();
                    break;
                case 4:
                    changeGrade();
                    break;
                case 5:
                    changeStudentAddress();
                    break;
                case 6:
                    removeStudent();
                    break;
                case 7:
                    removeTeacher();
                    break;
                case 8:
                    displayParents();
                    break;
                case 9:
                    chooseClass();
                    break;
                case 10:
                    displayTeachers();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private int displayOptions() {
        System.out.println();
        System.out.println("---PASIRINKIMAI---");
        System.out.println("0. Grizti i pagrindini meniu");
        System.out.println("1. Prideti nauja globeja");
        System.out.println("2. Prideti nauja mokini");
        System.out.println("3. Parasyti pazymi mokiniui");
        System.out.println("4. Pakeisti mokinio pazymi");
        System.out.println("5. Pakeisti mokinio adresa");
        System.out.println("6. Pasalinti mokini");
        System.out.println("7. Pasalinti mokytoja");
        System.out.println("8. Perziureti visus globejus");
        System.out.println("9. Perziureti visus mokinius");
        System.out.println("10. Perziureti visus mokytojus");
        System.out.println();
        return MainMeniu.readInt(0, 10);
    }

    private void registerNewParent(){
        PreparedStatement ps = null;
        System.out.println();
        System.out.println("-------------IVESKITE GLOBEJO INFORMACIJA------------");
        System.out.println();
        try {
            String query = "INSERT INTO Globejas (AK, vardas, pavarde) VALUES (?, ?, ?)";
            ps = connection.prepareStatement(query);
            System.out.println("************AK**********");
            ps.setString(1, br.readLine());
            System.out.println("************VARDAS**********");
            ps.setString(2, br.readLine());
            System.out.println("************PAVARDE**********");
            ps.setString(3, br.readLine());
            System.out.println();

            int success = ps.executeUpdate();
            if(success == 1) {
                System.out.println("-------------GLOBEJAS PRIDETAS------------");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException exp) {
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    private void changeGrade(){
        PreparedStatement ps = null;
        displayClasses();
        chooseClass();

        System.out.println("*************PASIRINKITE MOKINIO NUMERI************");
        int mokinioNr = 0;
        try {
            mokinioNr = Integer.parseInt(br.readLine());
        } catch {
            System.out.println("Blogai ivestas pasirinkimas. Reikia irasyti mokinio numeri.");
        }
        displaySubjects(mokinioNr);
        String dalykas = chooseSubject(mokinioNr);

        try {
            String query = "UPDATE Pazymys SET pazymys = ? WHERE mokinys = " + mokinioNr + " AND dalykas = \'" + dalykas + "\' AND data = ? AND laikas = ?";
            ps = connection.prepareStatement(query);
            System.out.println("*************NAUJAS PAZYMYS************");
            ps.setShort(1, Short.parseShort(br.readLine()));
            System.out.println("*************DATA************");
            ps.setDate(2, java.sql.Date.valueOf(br.readLine()));
            System.out.println("*************LAIKAS************");
            ps.setTime(3, java.sql.Time.valueOf(br.readLine()));

            int success = ps.executeUpdate();
            if(success == 1){
                System.out.println("-------------PAZYMYS ATNAUJINTAS------------");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException exp) {
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    private void changeStudentAddress(){
        PreparedStatement ps = null;
        displayClasses();
        chooseClass();
        int mokinioNr = chooseStudentNr();

        System.out.println("*************IVESKITE NAUJA MOKINIO ADRESA************");
        try {
            String naujasAdresas = br.readLine();
            ps = connection.prepareStatement("UPDATE Mokinys SET adresas = ? WHERE mokinio_nr = " + mokinioNr);
            ps.setString(1, naujasAdresas);
            int success = ps.executeUpdate();
            if(success == 1){
                System.out.println("-------------ADRESAS ATNAUJINTAS------------");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException exp) {
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    private void registerNewStudent(){
        PreparedStatement ps = null;
        System.out.println();
        System.out.println("-------------IVESKITE MOKINIO INFORMACIJA------------");
        System.out.println();
        try {
            String query = "INSERT INTO Mokinys (mokinio_nr, AK, vardas, pavarde, gimimo_data, adresas, globejo_AK, klases_pavadinimas)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            System.out.println("************MOKINIO NUMERIS**********");
            ps.setInt(1, Integer.parseInt(br.readLine()));
            System.out.println("************AK**********");
            ps.setString(2, br.readLine());
            System.out.println("************VARDAS**********");
            ps.setString(3, br.readLine());
            System.out.println("************PAVARDE**********");
            ps.setString(4, br.readLine());
            System.out.println("************GIMIMO DATA**********");
            ps.setDate(5, java.sql.Date.valueOf(br.readLine()));
            System.out.println("************ADRESAS**********");
            ps.setString(6, br.readLine());
            System.out.println("************GLOBEJO AK**********");
            ps.setString(7, br.readLine());
            System.out.println("************KLASES PAVADINIMAS**********");
            ps.setString(8, br.readLine());
            System.out.println();

            int success = ps.executeUpdate();
            if(success == 1) {
                System.out.println("-------------MOKINYS PRIDETAS------------");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException exp) {
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    private void evaluateStudent(){
        PreparedStatement ps = null;
        displayClasses();
        chooseClass();

        System.out.println("--------------PASIRINKITE MOKINIO NUMERI--------------");
        int mokinioNr = 0;
        try {
            mokinioNr = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            System.out.println("Blogai ivestas pasirinkimas. Reikia ivesti mokinio numeri.");
        }
        System.out.println("-------------UZPILDYKITE PAZYMIO INFORMACIJA------------");
        try {
            String query = "INSERT INTO Pazymys (pazymio_nr, pazymys, mokinys, dalykas)"
                    + " VALUES (DEFAULT, ?, ?, ?)";
            ps = connection.prepareStatement(query);
            System.out.println("************PAZYMYS**********");
            ps.setShort(1, Short.parseShort(br.readLine()));
            ps.setInt(2, mokinioNr);
            System.out.println("************DALYKAS**********");
            ps.setString(3, br.readLine());
            System.out.println();

            int success = ps.executeUpdate();
            if(success == 1) {
                System.out.println("-------------PAZYMYS IRASYTAS------------");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException exp) {
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    private void removeStudent(){
        PreparedStatement ps = null;
        displayClasses();
        chooseClass();
        int mokinioNr = chooseStudentNr();

        System.out.println("*************IVESKITE 0, JEI NORITE GRIZTI ATGAL************");
        System.out.println("*************IVESKITE 1, JEI NORITE PASALINTI MOKINI************");
        try {
            int choice = Integer.parseInt(br.readLine());
            if(choice == 0){
                return;
            }
            ps = connection.prepareStatement("DELETE FROM Mokinys WHERE mokinio_nr = " + mokinioNr);
            int success = ps.executeUpdate();
            if(success == 1){
                System.out.println("-------------MOKINYS PASALINTAS------------");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException exp) {
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    private void removeTeacher() {
        PreparedStatement ps = null;
        Statement stmt = null;
        ResultSet rs = null;
        displayTeachers();
        int mokytojoNr = chooseTeacherNr();

        System.out.println("*************IVESKITE 0, JEI NORITE GRIZTI ATGAL************");
        System.out.println("*************IVESKITE 1, JEI NORITE PASALINTI MOKYTOJA************");

        try {
            int choice = Integer.parseInt(br.readLine());
            if(choice == 0){
                return;
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT dalykas FROM Mokytojas WHERE mokytojo_nr = " + mokytojoNr);
            rs.next();
            String mokytojoDalykas = rs.getString("dalykas");

/////////////////////////////
            rs = stmt.executeQuery("SELECT mokytoju_skaicius FROM Dalykas WHERE pavadinimas = \'" + mokytojoDalykas + "\'");
            rs.next();
            Short senasSkaicius = rs.getShort("mokytoju_skaicius");
/////////////////////////////

            // transaction start
            connection.setAutoCommit(false);
            ps = connection.prepareStatement("DELETE FROM Mokytojas WHERE mokytojo_nr = " + mokytojoNr);
            ps.executeUpdate();

            String query = "UPDATE Dalykas SET mokytoju_skaicius = " +
                    "(SELECT mokytoju_skaicius FROM Dalykas WHERE pavadinimas = \'" + mokytojoDalykas + "\') - 1 " +
                    "WHERE pavadinimas = \'" + mokytojoDalykas + "\'";
            ps = connection.prepareStatement(query);
            ps.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            // transaction end

            //////////////////////
            rs = stmt.executeQuery("SELECT mokytoju_skaicius FROM Dalykas WHERE pavadinimas = \'" + mokytojoDalykas + "\'");
            rs.next();
            Short naujasSkaicius = rs.getShort("mokytoju_skaicius");
            //////////////////

            if(senasSkaicius - naujasSkaicius == 1){
                System.out.println("-------------MOKYTOJAS PASALINTAS------------");
                System.out.println();
            }
            else{
                System.out.println("-------------NEPAVYKO PASALINTI MOKYTOJO------------");
                System.out.println();
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException exp) {
                System.out.println("Unexpected SQL Error!");
                exp.printStackTrace();
            }
        }
    }

    private void displayClasses(){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT pavadinimas FROM Klase");
            System.out.println();

            if (!rs.isBeforeFirst() ) {
                System.out.println("Neegzistuoja nei viena klase.");
                System.out.println();
                return;
            }

            System.out.println("*************KLASES************");
            while(rs.next()) {
                System.out.println("Klase: " + rs.getString("pavadinimas"));
            }
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

    private void chooseClass(){
        Statement stmt = null;
        ResultSet rs = null;
        System.out.println("*************PASIRINKITE KLASE************");
        try {
            String klase = br.readLine();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT mokinio_nr, vardas, pavarde FROM Mokinys WHERE klases_pavadinimas = \'" + klase + "\'");
            System.out.println();
            if (!rs.isBeforeFirst() ) {
                System.out.println("Sioje klaseje nera mokiniu.");
                System.out.println();
                return;
            }

            System.out.println("-------------" + klase + " MOKINIAI------------");
            while(rs.next()) {
                System.out.print(rs.getString("mokinio_nr") + "  ");
                System.out.print(rs.getString("vardas") + "  ");
                System.out.println(rs.getString("pavarde"));
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } catch (IOException e) {
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

    private int chooseStudentNr(){
        Statement stmt = null;
        ResultSet rs = null;
        System.out.println("*************PASIRINKITE MOKINIO NUMERI************");
        int mokinioNr = 0;
        try {
            mokinioNr = Integer.parseInt(br.readLine());
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Mokinys WHERE mokinio_nr = " + mokinioNr);
            rs.next();
            System.out.println();
            System.out.println("-------------MOKINIO INFORMACIJA------------");
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
        } catch (IOException e) {
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
        return mokinioNr;
    }

    private void displaySubjects(int mokinioNr){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT DISTINCT dalykas FROM Mokinys JOIN Pazymys ON Mokinys.mokinio_nr = Pazymys.mokinys WHERE mokinio_nr = "
                    + mokinioNr);
            System.out.println();
            System.out.println("-------------DALYKAI------------");
            while (rs.next()) {
                System.out.println(rs.getString("dalykas"));
            }
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

    private void displayTeachers(){
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT mokytojo_nr, vardas, pavarde, dalykas FROM Mokytojas");
            System.out.println();
            if (!rs.isBeforeFirst() ) {
                System.out.println("Nera mokytoju.");
            }
            else {
                System.out.println("-------------MOKYTOJAI------------");
                while (rs.next()) {
                    System.out.print(rs.getInt("mokytojo_nr") + "  ");
                    System.out.print(rs.getString("vardas") + "  ");
                    System.out.print(rs.getString("pavarde") + "  ");
                    System.out.println(rs.getString("dalykas"));
                }
            }
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

    private int chooseTeacherNr(){
        Statement stmt = null;
        ResultSet rs = null;
        System.out.println("*************PASIRINKITE MOKYTOJO NUMERI************");
        int mokytojoNr = 0;
        try {
            mokytojoNr = Integer.parseInt(br.readLine());
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Mokytojas WHERE mokytojo_nr = " + mokytojoNr);
            rs.next();
            System.out.println();
            System.out.println("-------------MOKYTOJO INFORMACIJA------------");
            System.out.println("Mokytojo numeris: " + rs.getInt("mokytojo_nr"));
            System.out.println("Vardas: " + rs.getString("vardas"));
            System.out.println("Pavarde: " + rs.getString("pavarde"));
            System.out.println("Dalykas: " + rs.getString("dalykas"));
            System.out.println();
        } catch (SQLException e) {
            System.out.println("SQL Error!");
            e.printStackTrace();
        } catch (IOException e) {
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
        return mokytojoNr;
    }

    private String chooseSubject(int mokinioNr){
        Statement stmt = null;
        ResultSet rs = null;
        System.out.println("*************PASIRINKITE DALYKA************");
        String dalykas = null;
        try {
            dalykas = br.readLine();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT pazymys, data, laikas FROM Pazymys WHERE mokinys = " + mokinioNr + " AND dalykas = \'" + dalykas + "\'");
            System.out.println();

            if (!rs.isBeforeFirst() ) {
                System.out.println("Sis mokinys neturi pazymiu is " + dalykas);
            }
            else {
                System.out.println("-------------MOKINIO PAZYMIAI IS " + dalykas + "------------");
                while (rs.next()) {
                    System.out.print(rs.getShort("pazymys") + "  ");
                    System.out.print(rs.getDate("data") + "  ");
                    System.out.println(rs.getTime("laikas"));
                }
            }
            System.out.println();
        } catch (SQLException e) {
            System.out.println("SQL Klaida! Tikriausiai pasirinktas dalykas neegzistuoja.");
            e.printStackTrace();
        } catch (IOException e) {
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
        return dalykas;
    }

    private void displayParents() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Globejas");
            System.out.println();
            if (!rs.isBeforeFirst() ) {
                System.out.println("Nera globeju.");
            }
            else {
                System.out.println("-------------GLOBEJAI------------");
                while (rs.next()) {
                    System.out.print(rs.getInt("AK") + "  ");
                    System.out.print(rs.getString("vardas") + "  ");
                    System.out.print(rs.getString("pavarde") + "  ");
                }
            }
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
}
