package via.client.feature;

public class Autoleave {
    private static boolean enabled = false;
    private static float healthThreshold = 5.0f;

    public static void toggle() {
        enabled = !enabled;
    }

    public static void setHealthThreshold(float health) {
        healthThreshold = health;
    }

    public static void onTick() {
        if (!enabled) {
            return;
        }

        if (getPlayerHealth() <= healthThreshold) {
            logout();
        }
    }

    private static float getPlayerHealth() {
        return 20.0f;
    }

    private static void logout() {
        System.exit(0);
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static float getHealthThreshold() {
        return healthThreshold;
    }
}