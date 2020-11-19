import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

public class MainMeniu {
    private Connection connection;
    private static BufferedReader br;

    public MainMeniu(Connection connection) {
        this.connection = connection;
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() {
        System.out.println("----------------------------------------------------------");
        System.out.println("Mokyklos pazymiu ir asmeniniu duomenu saugojimo sistema");
        System.out.println("----------------------------------------------------------");

        while (true) {
            int choice = displayOptions();
            switch (choice) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Iveskite savo mokinio numeri");
                    try {
                        int mokinioNr = Integer.parseInt(br.readLine());
                        MokinioMeniu mokinioMeniu = new MokinioMeniu(connection, mokinioNr);
                        mokinioMeniu.start();
                    } catch {
                        System.out.println("Blogai ivestas pasirinkimas. Reikia ivesti savo mokinio numeri.");
                    }
                    break;
                case 2:
                    AdministratoriausMeniu administratoriausMeniu = new AdministratoriausMeniu(connection);
                    administratoriausMeniu.start();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private int displayOptions() {
        System.out.println();
        System.out.println("---PASIRINKIMAI---");
        System.out.println("0. Iseiti");
        System.out.println("1. Mokinio meniu");
        System.out.println("2. Administratoriaus meniu");
        System.out.println();
        return readInt(0, 2);
    }

    public static int readInt(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(br.readLine());
                if (choice >= min && choice <= max) {
                    break;
                }
                else{
                    System.out.println("Reikia rinktis tarp " + min + " ir " + max);
                }
            } catch {
                System.out.println("Blogai ivestas pasirinkimas. Reikia rinktis tarp " + min + " ir " + max);
            }
        }
        return choice;
    }
}
