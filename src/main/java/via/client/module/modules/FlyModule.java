package via.client.module.modules;

import via.client.module.Module;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

public class FlyModule implements Module {
    private final String name = "Fly";
    private boolean enabled = false;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    @Override
    public void onEnable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            mc.player.getAbilities().allowFlying = true;
        }
    }

    @Override
    public void onDisable() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player != null) {
            PlayerEntity player = mc.player;
            player.getAbilities().allowFlying = false;
            if (!player.getAbilities().creativeMode) {
                player.getAbilities().flying = false;
            }
        }
    }

    @Override
    public void onTick() {
        if (enabled) {
            MinecraftClient mc = MinecraftClient.getInstance();
            if (mc.player != null && !mc.player.getAbilities().creativeMode) {
                mc.player.getAbilities().allowFlying = true;
            }
        }
    }
}