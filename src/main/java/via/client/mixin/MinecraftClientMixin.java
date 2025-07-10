package via.client.mixin;

import via.client.Viaclient;
import via.client.module.Module;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void onTick(CallbackInfo info) {
        for (Module module : Viaclient.getModuleManager().getModules()) {
            if (module.isEnabled()) {
                module.onTick();
            }
        }
    }
}