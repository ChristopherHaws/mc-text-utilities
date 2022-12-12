package io.chaws.textutilities;

import io.chaws.textutilities.config.TextUtilitiesConfig;
import io.chaws.textutilities.handlers.ItemFrameClickThroughHandler;
import io.chaws.textutilities.handlers.SignClickThroughHandler;
import io.chaws.textutilities.handlers.SignEditHandler;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextUtilities implements ModInitializer {
	@SuppressWarnings("unused")
	public static final Logger logger = LoggerFactory.getLogger("textutilities");

	public static TextUtilitiesConfig getConfig() {
		return AutoConfig.getConfigHolder(TextUtilitiesConfig.class).getConfig();
	}

	@Override
	public void onInitialize() {
		AutoConfig.register(TextUtilitiesConfig.class, Toml4jConfigSerializer::new);
		SignEditHandler.initialize();
		SignClickThroughHandler.initialize();
		ItemFrameClickThroughHandler.initialize();
	}
}
