package apitesting.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Logger logger = LoggerFactory.getLogger(ConfigLoader.class);
    private static volatile ConfigLoader instance;
    private Properties properties;
    private String currentEnvironment;

    private ConfigLoader() {
        currentEnvironment = System.getProperty("environment", "dev");
        loadProperties();
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            synchronized (ConfigLoader.class) {
                if (instance == null) {
                    instance = new ConfigLoader();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        properties = new Properties();
        String fileName = "environments/" + currentEnvironment + ".properties";

        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                logger.error("Unable to find properties file: {}", fileName);
                throw new RuntimeException("Properties file not found: " + fileName);
            }

            properties.load(input);
            logger.info("Successfully loaded properties from: {}", fileName);
            logger.info("Current environment: {}", getProperty("environment.name"));

        } catch (IOException e) {
            logger.error("Error loading properties file: {}", fileName, e);
            throw new RuntimeException("Failed to load properties file: " + fileName, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public boolean getBooleanProperty(String key) {
        String value = getProperty(key);
        return Boolean.parseBoolean(value);
    }

    public boolean getBooleanProperty(String key, boolean defaultValue) {
        String value = getProperty(key);
        return value != null ? Boolean.parseBoolean(value) : defaultValue;
    }

    public void setEnvironment(String environment) {
        if (!environment.equals(currentEnvironment)) {
            currentEnvironment = environment;
            loadProperties();
        }
    }
}

