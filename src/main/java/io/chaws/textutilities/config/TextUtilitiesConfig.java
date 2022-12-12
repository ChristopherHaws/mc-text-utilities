package io.chaws.textutilities.config;

import blue.endless.jankson.Comment;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Config(name = "textutilities")
public class TextUtilitiesConfig implements ConfigData {
	@Comment("Show or hide the formatting buttons in the sign edit screen")
	public boolean signFormattingEnabled = true;
	@Comment("Show or hide the formatting buttons in the book edit screen")
	public boolean bookFormattingEnabled = true;
	@Comment("Show or hide the formatting buttons in the anvil screen")
	public boolean anvilFormattingEnabled = true;

	@Comment("When enabled, clicking a sign with another sign will open the edit window")
	public boolean signEditingEnabled = true;

	@Comment("When enabled, clicking on a sign will open the container it is attached to")
	public boolean signClickThroughEnabled = true;

	@Comment("When enabled, clicking on an item frame will open the container it is attached to")
	public boolean itemFrameClickThroughEnabled = true;

	public boolean formattingDisabled() {
		return !this.signFormattingEnabled && !this.bookFormattingEnabled && !this.anvilFormattingEnabled;
	}

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
