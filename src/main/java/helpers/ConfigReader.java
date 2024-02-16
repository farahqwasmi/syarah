package helpers;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    // Load properties when the class is initialized
    static {
        properties = new Properties();
        System.out.println(System.getProperty( "user.dir"));
        try (
                InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get the value for a given key
    public static String getString(String key) {
        String value = properties.getProperty(key);
        // If the value is not found, return an empty string
        return value != null ? value : "";

    }
}