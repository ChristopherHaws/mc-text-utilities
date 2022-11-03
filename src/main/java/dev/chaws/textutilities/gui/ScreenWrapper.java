package dev.chaws.textutilities.gui;

import dev.chaws.textutilities.common.FormattingCodePrefix;
import net.minecraft.client.gui.screen.Screen;

public class ScreenWrapper {
	Screen sc;

	public ScreenWrapper(Screen s) {
		this.sc = s;
	}

	public void commit(char a) {
		//TODO: This should be a section prefix and we should replace them
		// with ampersand on clicking done if the server is not fabric
		this.sc.charTyped(FormattingCodePrefix.AMPERSAND, 0);
		this.sc.charTyped(a, 0);
	}
}
