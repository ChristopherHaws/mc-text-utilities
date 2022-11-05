package dev.chaws.textutilities;

import dev.chaws.textutilities.config.TextUtilitiesConfig;
import dev.chaws.textutilities.handlers.FormatButtonsHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextUtilitiesMod implements ClientModInitializer {
	public static final Logger logger = LoggerFactory.getLogger("textutilities");

	public static TextUtilitiesConfig getConfig() {
		return AutoConfig.getConfigHolder(TextUtilitiesConfig.class).getConfig();
	}

	@Override
	public void onInitializeClient() {
		var configHolder = AutoConfig.register(TextUtilitiesConfig.class, Toml4jConfigSerializer::new);
		if (!configHolder.getConfig().enabled) {
			logger.warn("Text Utilities mod is disabled.");
			return;
		}

		FormatButtonsHandler.initialize();
	}
}
