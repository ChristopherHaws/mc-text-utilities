package io.chaws.textutilities;

import io.chaws.textutilities.config.TextUtilitiesConfig;
import io.chaws.textutilities.handlers.FormatButtonsHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextUtilitiesMod implements ClientModInitializer {
	public static final Logger logger = LoggerFactory.getLogger("textutilities");
	public static boolean enabled = true;

	public static TextUtilitiesConfig getConfig() {
		return AutoConfig.getConfigHolder(TextUtilitiesConfig.class).getConfig();
	}

	@Override
	public void onInitializeClient() {
		var configHolder = AutoConfig.register(TextUtilitiesConfig.class, Toml4jConfigSerializer::new);

		// Set this value as a static since we require a restart to change the value.
		enabled = configHolder.getConfig().enabled;
		if (!enabled) {
			logger.warn("Text Utilities mod is disabled.");
			return;
		}

		FormatButtonsHandler.initialize();
	}
}
