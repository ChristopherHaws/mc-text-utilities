package io.chaws.textutilities.config;

import com.google.common.collect.Lists;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.ApiStatus;

import java.util.List;

@ApiStatus.Internal
@Config(name = "textutilities")
public class TextUtilitiesConfig implements ConfigData {
	@ConfigEntry.Gui.Tooltip
	@Comment("The formatting code type to use. Vanilla = §, Ampersand = &")
	public String formattingCodePrefix = String.valueOf(Formatting.FORMATTING_CODE_PREFIX);

	@ConfigEntry.Gui.Tooltip
	@Comment("Show or hide the formatting buttons in the sign edit screen")
	public boolean signFormattingEnabled = true;

	@ConfigEntry.Gui.Tooltip
	@Comment("Show or hide the formatting buttons in the book edit screen")
	public boolean bookFormattingEnabled = true;

	@ConfigEntry.Gui.Tooltip
	@Comment("Show or hide the formatting buttons in the anvil screen")
	public boolean anvilFormattingEnabled = true;

	@ConfigEntry.Gui.Tooltip
	@Comment("When enabled, clicking a sign with another sign will open the edit window")
	public boolean signEditingEnabled = true;

	@ConfigEntry.Gui.Tooltip
	@Comment("Whether or not to open the edit screen when sneaking and placing signs")
	public boolean bypassEditScreenWhenSneakPlacingSigns = true;

	@ConfigEntry.Gui.Tooltip
	@Comment("When enabled, clicking on a sign will open the container it is attached to")
	public boolean signClickThroughEnabled = true;

	@ConfigEntry.Gui.Tooltip
	@Comment("When enabled, clicking on an item frame will open the container it is attached to")
	public boolean itemFrameClickThroughEnabled = true;

	@Comment("Additional item identifiers of blocks or entities to allow clicking through.")
	public List<String> additionalClickThroughIdentifiers = Lists.newArrayList(
		"create:placard"
	);

	public boolean formattingDisabled() {
		return !this.signFormattingEnabled && !this.bookFormattingEnabled && !this.anvilFormattingEnabled;
	}
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
