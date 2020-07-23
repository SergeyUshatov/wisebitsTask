package utils;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public enum EnvironmentConfigurator {
    INSTANCE;

    EnvironmentConfigurator() {
        this.properties = getEnvConfig();
    }

    private Properties properties;

    private Properties getEnvConfig() {
        Properties props = new Properties();
        String property = System.getProperty("propFile", "application.properties");

        try(InputStream is = EnvironmentConfigurator.class.getClassLoader().getResourceAsStream(property)) {
            props.load(is);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to load properties");
        }
        return props;
    }

    public String getDesktopTestUrl() {
        return properties.getProperty("desktop.test.url");
    }

    public String getMobileTestUrl() { return properties.getProperty("mobile.test.url"); }

    public String getWebDriverHubUrl() { return properties.getProperty("webdriver.hub.url"); }

    public String getTestUserName() {
        return properties.getProperty("amazon.user.name");
    }

    public String getTestUserPassword() {
        return properties.getProperty("amazon.user.password");
    }

    public String getAppiumVersion() {
        return properties.getProperty("appium.version");
    }

    public String getAndroidVersion() {
        return properties.getProperty("android.version");
    }

    public String getAndroidDeviceName() {
        return properties.getProperty("android.device.name");
    }

    public URL getAppiumServerUrl() {
        try {
            return new URL(properties.getProperty("appium.server.url"));
        } catch (MalformedURLException e) {
            throw new RuntimeException();
        }
    }

    public String getUserNameOnUi() {
        return properties.getProperty("amazon.user.name.ui");
    }
}
