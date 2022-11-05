package dev.chaws.textutilities.handlers;

import dev.chaws.textutilities.mixin.AnvilScreenAccessor;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class FormatButtonsHandler {
	public static void initialize() {
		ScreenEvents.AFTER_INIT.register((client, screen, width, height) ->
			onScreenOpened(screen)
		);
	}

	private static void onScreenOpened(Screen screen) {
		if (!(screen instanceof SignEditScreen) &&
			!(screen instanceof BookEditScreen) &&
			!(screen instanceof AnvilScreen)) {
			return;
		}

		if (screen instanceof AnvilScreen anvilScreen) {
			((AnvilScreenAccessor)anvilScreen).getNameField().setRenderTextProvider((abc, def) ->
				Text.literal(abc).asOrderedText()
			);
		}

		var x = 16 * screen.width / 17;
		
		var buttons = getFormatButtons(screen, x);
		for (var button : buttons) {
			Screens.getButtons(screen).add(button);
		}
	}

	private static List<ButtonWidget> getFormatButtons(Screen screen, int x) {
		List<ButtonWidget> list = new ArrayList<>();
		var i = 0;

		for (var formatting : Formatting.values()) {
			var buttonX = x - (i / 11 + 1) * 40;
			var buttonY = i % 11 * 20 + 15;

			if (formatting == Formatting.RESET) {
				list.add(new ButtonWidget(
					buttonX,
					buttonY,
					40,
					20,
					Text.literal(formatting.toString().concat("Reset")),
					cod -> {
						screen.charTyped(Formatting.FORMATTING_CODE_PREFIX, 0);
						screen.charTyped(formatting.getCode(), 0);
					}
				));
			} else {
				list.add(new ButtonWidget(
					buttonX,
					buttonY,
					20,
					20,
					Text.literal(formatting.toString().concat("A")),
					cod -> {
						screen.charTyped(Formatting.FORMATTING_CODE_PREFIX, 0);
						screen.charTyped(formatting.getCode(), 0);
					}
				));
			}

			i++;
		}

		return list;
	}
}
