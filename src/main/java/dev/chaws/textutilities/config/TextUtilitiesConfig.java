package dev.chaws.textutilities.config;

import blue.endless.jankson.Comment;
import dev.chaws.textutilities.common.FormattingCodePrefix;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import org.jetbrains.annotations.ApiStatus;

@ApiStatus.Internal
@Config(name = "textutilities")
public class TextUtilitiesConfig implements ConfigData {
	@ConfigEntry.Gui.RequiresRestart
	public boolean enabled = true;

	@ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
	@Comment("The formatting code type to use.")
	public FormattingCodePrefix formattingCodePrefix = FormattingCodePrefix.SECTION;

}
