package dev.chaws.textutilities;

import dev.chaws.textutilities.config.TextUtilitiesConfig;
import dev.chaws.textutilities.gui.FormatButton;
import dev.chaws.textutilities.gui.ScreenWrapper;
import dev.chaws.textutilities.mixin.AnvilScreenAccessor;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextUtilitiesMod implements ClientModInitializer {
	public static final Logger Logger = LoggerFactory.getLogger("textutilities");

	@Override
	public void onInitializeClient() {
		var configHolder = AutoConfig.register(TextUtilitiesConfig.class, Toml4jConfigSerializer::new);
		if (!configHolder.getConfig().enabled) {
			return;
		}

		ScreenEvents.AFTER_INIT.register((client, screen, width, height) -> onScreenCreated(screen));
		//ScreenEvents.
	}

	public static void onScreenCreated(Screen screen) {
		var x = 16 * screen.width / 17;

		if (screen instanceof SignEditScreen) {
			var list = FormatButton.getSetOfFormatButton(new ScreenWrapper(screen), x);
			for (var b : list) {
				Screens.getButtons(screen).add(b);
			}
		} else if (screen instanceof BookEditScreen) {
			var list = FormatButton.getSetOfFormatButton(new ScreenWrapper(screen), x);
			for (var b : list) {
				Screens.getButtons(screen).add(b);
			}
		} else if (screen instanceof AnvilScreen) {
			var list = FormatButton.getSetOfFormatButton(new ScreenWrapper(screen), x);
			((AnvilScreenAccessor)screen).getNameField().setRenderTextProvider((abc, def) -> (Text.literal(abc)).asOrderedText());
			for (var b : list) {
				Screens.getButtons(screen).add(b);
			}
		}
	}
}
