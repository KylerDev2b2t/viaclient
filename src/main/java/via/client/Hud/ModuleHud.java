package via.client.Hud;

import via.client.Viaclient;
import via.client.module.Module;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class ModuleHud {
    public static void register() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            render(drawContext);
        });
    }

    private static void render(DrawContext drawContext) {
        MinecraftClient mc = MinecraftClient.getInstance();
        TextRenderer textRenderer = mc.textRenderer;
        int y = 2;
        for (Module module : Viaclient.getModuleManager().getModules()) {
            if (module.isEnabled()) {
                String text = "-" + module.getName();
                drawContext.drawTextWithShadow(textRenderer, text, 2, y, 0xFFFFFF);
                y += textRenderer.fontHeight + 2;
            }
        }
    }
}