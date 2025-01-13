package model;

public class CustomerPreferences {
    private int id;
    private String language;
    private boolean prefersEmailNotifications;
    private boolean prefersSmsNotifications;
    private boolean prefersPushNotifications;

    public CustomerPreferences(int id, String language, boolean prefersEmailNotifications, boolean prefersSmsNotifications, boolean prefersPushNotifications) {
        this.id = id;
        this.language = language;
        this.prefersEmailNotifications = prefersEmailNotifications;
        this.prefersSmsNotifications = prefersSmsNotifications;
        this.prefersPushNotifications = prefersPushNotifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isPrefersEmailNotifications() {
        return prefersEmailNotifications;
    }

    public void setPrefersEmailNotifications(boolean prefersEmailNotifications) {
        this.prefersEmailNotifications = prefersEmailNotifications;
    }

    public boolean isPrefersSmsNotifications() {
        return prefersSmsNotifications;
    }

    public void setPrefersSmsNotifications(boolean prefersSmsNotifications) {
        this.prefersSmsNotifications = prefersSmsNotifications;
    }

    public boolean isPrefersPushNotifications() {
        return prefersPushNotifications;
    }

    public void setPrefersPushNotifications(boolean prefersPushNotifications) {
        this.prefersPushNotifications = prefersPushNotifications;
    }
}
