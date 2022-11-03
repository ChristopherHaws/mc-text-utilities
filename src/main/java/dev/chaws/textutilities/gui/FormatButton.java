package dev.chaws.textutilities.gui;

import dev.chaws.textutilities.common.FormattingCodePrefix;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class FormatButton extends ButtonWidget {
	public char code;

	public FormatButton(int width, int height, int widthin, int heightin, char code, String display, ButtonWidget.PressAction pressAction) {
		super(width, height, widthin, heightin, Text.literal(String.valueOf(FormattingCodePrefix.SECTION).concat(String.valueOf(code)).concat(display)), pressAction);
		this.code = code;
	}

	public static List<FormatButton> getSetOfFormatButton(ScreenWrapper screenWrapper, int x) {
		List<FormatButton> list = new ArrayList<>();
		char[] ColorCode = {
				'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f', 'k', 'l', 'm', 'n',
				'o', 'r' };
		int i = 0;
		while (i != ColorCode.length) {
			if (ColorCode[i] == 'r') {
				list.add(new FormatButton(x - (i / 11 + 1) * 40, i % 11 * 20 + 15, 40, 20, ColorCode[i], "Reset", cod -> screenWrapper.commit(((FormatButton)cod).code)));
			} else {
				list.add(new FormatButton(x - (i / 11 + 1) * 40, i % 11 * 20 + 15, 20, 20, ColorCode[i], "A", cod -> screenWrapper.commit(((FormatButton)cod).code)));
			}
			i++;
		}
		return list;
	}
}
