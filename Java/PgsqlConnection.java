import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PgsqlConnection {

    /********************************************************/
    public static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("Couldn't find driver class!");
            cnfe.printStackTrace();
            System.exit(1);
        }
    }

    /********************************************************/
    private static String getPassword() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("password.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String password = null;
        try {
            password = br.readLine();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return password;
    }

    /********************************************************/
    private static Connection getConnection(String password) {
        Connection postGresConn;
        try {
            postGresConn = DriverManager.getConnection("jdbc:postgresql://pgsql3.mif/studentu", "pane5605", password) ;
        }
        catch (SQLException sqle) {
            System.out.println("Couldn't connect to database!");
            sqle.printStackTrace();
            return null ;
        }
        System.out.println("Successfully connected to Postgres Database");

        return postGresConn ;
    }

    /********************************************************/
    public static void main(String[] args) {
        loadDriver() ;
        Connection connection = getConnection(getPassword()) ;
        if(connection != null) {
            System.out.println("Sekmingai prisijungta prie mokyklos pazymiu ir asmeniniu duomenu saugojimo sistemos!");
            MainMeniu mainMeniu = new MainMeniu(connection);
            mainMeniu.start();
        }
        if(connection != null) {
            try {
                connection.close() ;
            }
            catch (SQLException exp) {
                System.out.println("Cannot close connection!");
                exp.printStackTrace();
            }
        }
    }
}
