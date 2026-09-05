package config;

public class Config {

    private static Config instance;
    private int maxCountTest = 10;

    private Config() {
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public int getMaxCountTest() {
        return maxCountTest;
    }
}
