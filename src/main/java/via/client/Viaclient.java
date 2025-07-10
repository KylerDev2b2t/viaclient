package via.client;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import via.client.Hud.ModuleHud;
import via.client.module.ModuleManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.text.Text;

public class Viaclient implements ClientModInitializer {
	public static final String MOD_ID = "viaclient";
	private static ModuleManager moduleManager;

	@Override
	public void onInitializeClient() {
		// Initialize module manager
		moduleManager = new ModuleManager();
		moduleManager.registerModules();

		// Register HUD
		ModuleHud.register();

		// Register toggle command
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			registerCommands(dispatcher);
		});
	}

	private void registerCommands(CommandDispatcher<FabricClientCommandSource> dispatcher) {
		dispatcher.register(
				ClientCommandManager.literal("toggle")
						.then(ClientCommandManager.argument("module", StringArgumentType.word())
								.executes(context -> {
									String moduleName = StringArgumentType.getString(context, "module");
									boolean success = moduleManager.toggleModule(moduleName);
									context.getSource().sendFeedback(
											Text.literal(success ? "Toggled " + moduleName : "Module " + moduleName + " not found")
									);
									return 1;
								})
						)
		);
	}

	public static ModuleManager getModuleManager() {
		return moduleManager;
	}
}