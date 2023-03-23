package singleton;

public class Client {

    public static void main(String[] args) {
        DatabaseConfig databaseConfig = DatabaseConfig.getInstance("MySQL");
        databaseConfig.connect();

        DatabaseConfig databaseConfig2 = DatabaseConfig.getInstance("Oracle");
        databaseConfig2.connect();
    }
}
