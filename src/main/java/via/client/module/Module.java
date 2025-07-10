package via.client.module;

public interface Module {
    String getName();
    boolean isEnabled();
    void setEnabled(boolean enabled);
    void onEnable();
    void onDisable();
    void onTick();
}