package dev.chaws.textutilities;

import dev.chaws.textutilities.gui.FormatButton;
import dev.chaws.textutilities.gui.ScreenWrapper;
import dev.chaws.textutilities.mixin.AnvilScreenAccessor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextUtilitiesMod implements ClientModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger Logger = LoggerFactory.getLogger("textutilities");
	public static Path configDir;

	@Override
	public void onInitializeClient() {
		ScreenEvents.AFTER_INIT.register((client, screen, width, height) -> onScreenCreated(screen));

		configDir = FabricLoader.getInstance().getConfigDir().resolve("textutilities");
		try {
			Files.createDirectories(configDir);
		} catch (IOException e) {
			Logger.error("Failed to create config directory", e);
		}
	}

	public static void onScreenCreated(Screen screen) {
		if (screen instanceof SignEditScreen) {
			List<FormatButton> list = FormatButton.getSetOfFormatButton(new ScreenWrapper(screen), 16 * screen.width / 17);
			for (FormatButton b : list)
				Screens.getButtons(screen).add(b);
		} else if (screen instanceof BookEditScreen) {
			List<FormatButton> list = FormatButton.getSetOfFormatButton(new ScreenWrapper(screen), 16 * screen.width / 17);
			for (FormatButton b : list)
				Screens.getButtons(screen).add(b);
		} else if (screen instanceof AnvilScreen) {
			List<FormatButton> list = FormatButton.getSetOfFormatButton(new ScreenWrapper(screen), 16 * screen.width / 17);
			((AnvilScreenAccessor)screen).getNameField().setRenderTextProvider((abc, def) -> (Text.literal(abc)).asOrderedText());
			for (FormatButton b : list)
				Screens.getButtons(screen).add(b);
		}
	}
}
