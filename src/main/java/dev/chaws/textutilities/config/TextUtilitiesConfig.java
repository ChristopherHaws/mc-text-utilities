package dev.chaws.textutilities.config;

import blue.endless.jankson.Comment;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Config(name = "textutilities")
public class TextUtilitiesConfig implements ConfigData {
	@ConfigEntry.Gui.RequiresRestart
	public boolean enabled = true;

	@ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
	@Comment("The formatting code type to use. Vanilla = §, Ampersand = &")
	public String formattingCodePrefix = String.valueOf(Formatting.FORMATTING_CODE_PREFIX);

	public char getFormattingCodePrefix() {
		return this.formattingCodePrefix.charAt(0);
	}

	@Override
	public void validatePostLoad() throws ValidationException {
		if (this.formattingCodePrefix == null || this.formattingCodePrefix.length() > 1) {
			throw new ValidationException("Formatting code should be 1 character long.");
		}
	}
}
