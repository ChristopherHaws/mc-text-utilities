package io.chaws.textutilities.client.handlers;

import com.google.common.collect.ImmutableList;
import io.chaws.textutilities.TextUtilities;
import io.chaws.textutilities.client.mixin.AnvilScreenAccessor;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.BookEditScreen;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class FormatButtonsHandler {
	private final static ImmutableList<Formatting> colorFormattings = ImmutableList.of(
		Formatting.BLACK,
		Formatting.DARK_GRAY,
		Formatting.DARK_BLUE,
		Formatting.BLUE,
		Formatting.DARK_GREEN,
		Formatting.GREEN,
		Formatting.DARK_AQUA,
		Formatting.AQUA,
		Formatting.DARK_RED,
		Formatting.RED,
		Formatting.DARK_PURPLE,
		Formatting.LIGHT_PURPLE,
		Formatting.GOLD,
		Formatting.YELLOW,
		Formatting.GRAY,
		Formatting.WHITE
	);

	private final static ImmutableList<Formatting> modifierFormattings = ImmutableList.of(
		Formatting.BOLD,
		Formatting.ITALIC,
		Formatting.UNDERLINE,
		Formatting.STRIKETHROUGH,
		Formatting.OBFUSCATED,
		Formatting.RESET
	);

	public static void initialize() {
		ScreenEvents.AFTER_INIT.register((client, screen, width, height) ->
			onScreenOpened(screen)
		);
	}

	private static void onScreenOpened(Screen screen) {
		var config = TextUtilities.getConfig();
		var xOffsetFromCenter = 0;
		var yOffset = 0;

		if (screen instanceof SignEditScreen) {
			if (!config.signFormattingEnabled) {
				return;
			}

			xOffsetFromCenter += 50;
			yOffset += 70;
		} else if (screen instanceof BookEditScreen) {
			if (!config.bookFormattingEnabled) {
				return;
			}

			xOffsetFromCenter += 70;
			yOffset += 20;
		} else if (screen instanceof AnvilScreen anvilScreen) {
			if (!config.anvilFormattingEnabled) {
				return;
			}

			xOffsetFromCenter += 85;
			yOffset += (screen.height / 2) - 80;
			((AnvilScreenAccessor)anvilScreen).getNameField().setRenderTextProvider((abc, def) ->
				Text.literal(abc).asOrderedText()
			);
		} else {
			// Not a supported screen.
			return;
		}

		var colorButtons = getFormatButtons(
			screen,
			colorFormattings,
			(screen.width / 2) - (120 + xOffsetFromCenter),
			yOffset,
			4
		);

		var modifierButtons = getFormatButtons(
			screen,
			modifierFormattings,
			(screen.width / 2) + (xOffsetFromCenter),
			yOffset,
			6
		);

		var screenButtons = Screens.getButtons(screen);
		screenButtons.addAll(colorButtons);
		screenButtons.addAll(modifierButtons);
	}

	private static List<ButtonWidget> getFormatButtons(Screen screen, List<Formatting> formats, int x, int yOffset, int rows) {
		List<ButtonWidget> list = new ArrayList<>();
		var i = 0;
		var gap = 0;
		var buttonSize = 20;

		for (var formatting : formats) {
			var buttonX = x + (i / rows + 1) * (buttonSize + gap);
			var buttonY = i % rows * (buttonSize + gap) + yOffset;

			list.add(getFormatButton(
				screen,
				buttonX,
				buttonY,
				buttonSize,
				buttonSize,
				formatting
			));

			i++;
		}

		return list;
	}

	private static ButtonWidget getFormatButton(
		Screen screen,
		int buttonX,
		int buttonY,
		int buttonWidth,
		int buttonHeight,
		Formatting formatting
	) {
		if (formatting.isModifier() || formatting == Formatting.RESET) {
			var label = formatting.toString().concat(formatting.getName());
			return ButtonWidget.builder(
					Text.literal(label),
					cod -> {
						screen.charTyped(Formatting.FORMATTING_CODE_PREFIX, 0);
						screen.charTyped(formatting.getCode(), 0);
					})
				.position(buttonX, buttonY)
				.size(buttonWidth * 4, buttonHeight)
				.tooltip(Tooltip.of(Text.literal(label)))
				.build();
		}

		return ButtonWidget.builder(
			Text.literal(formatting.toString().concat("⬛")),
			cod -> {
				screen.charTyped(Formatting.FORMATTING_CODE_PREFIX, 0);
				screen.charTyped(formatting.getCode(), 0);
			})
			.position(buttonX, buttonY)
			.size(buttonWidth, buttonHeight)
			.tooltip(Tooltip.of(Text.literal(formatting.toString().concat(formatting.getName()))))
			.build();
	}
}
