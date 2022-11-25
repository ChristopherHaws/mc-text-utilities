package io.chaws.textutilities;

import io.chaws.textutilities.config.TextUtilitiesConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextUtilities implements ModInitializer {
	public static final Logger logger = LoggerFactory.getLogger("textutilities");
	public static boolean enabled = true;

	public static TextUtilitiesConfig getConfig() {
		return AutoConfig.getConfigHolder(TextUtilitiesConfig.class).getConfig();
	}

	@Override
	public void onInitialize() {
		var configHolder = AutoConfig.register(TextUtilitiesConfig.class, Toml4jConfigSerializer::new);

		// Set this value as a static since we require a restart to change the value.
		TextUtilities.enabled = configHolder.getConfig().enabled;
	}
}
