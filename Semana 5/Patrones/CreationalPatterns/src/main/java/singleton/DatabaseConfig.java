package singleton;

public class DatabaseConfig {
    private static DatabaseConfig instance;
    public String value;

    private DatabaseConfig(String value) {
        this.value = value;
    }

    public static DatabaseConfig getInstance(String value) {
        if (instance == null) {
            instance = new DatabaseConfig(value);
        }
        return instance;
    }

    public void connect(){
        System.out.println("Connecting to " + value + " database ...");
    }
}