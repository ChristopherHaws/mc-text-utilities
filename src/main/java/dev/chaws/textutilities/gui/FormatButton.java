package dev.chaws.textutilities.gui;

import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;

public class FormatButton extends ButtonWidget {
	public Formatting formatting;

	public FormatButton(int x, int y, int width, int height, Formatting formatting, String display, ButtonWidget.PressAction pressAction) {
		super(x, y, width, height, Text.literal(formatting.toString().concat(display)), pressAction);
		this.formatting = formatting;
	}

	public static List<FormatButton> getSetOfFormatButton(ScreenWrapper screenWrapper, int x) {
		List<FormatButton> list = new ArrayList<>();
		var i = 0;

		for (var formatting : Formatting.values()) {
			var buttonX = x - (i / 11 + 1) * 40;
			var buttonY = i % 11 * 20 + 15;

			if (formatting == Formatting.RESET) {
				list.add(new FormatButton(
					buttonX,
					buttonY,
					40,
					20,
					formatting,
					"Reset",
					cod -> screenWrapper.commit(formatting.getCode())
				));
			} else {
				list.add(new FormatButton(
					buttonX,
					buttonY,
					20,
					20,
					formatting,
					formatting.getName().substring(0, 1),
					cod -> screenWrapper.commit(formatting.getCode())
				));
			}
			i++;
		}
		return list;
	}
}
