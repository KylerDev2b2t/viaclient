package via.client.module;

import via.client.module.modules.FlyModule;
import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final List<Module> modules = new ArrayList<>();

    public void registerModules() {
        // Register all modules here
        modules.add(new FlyModule());
        // Add more modules here as needed
    }

    public boolean toggleModule(String name) {
        for (Module module : modules) {
            if (module.getName().equalsIgnoreCase(name)) {
                module.setEnabled(!module.isEnabled());
                return true;
            }
        }
        return false;
    }

    public List<Module> getModules() {
        return modules;
    }
}