package dev.chaws.textutilities.common;

import me.shedaniel.clothconfig2.gui.entries.SelectionListEntry;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public enum FormattingCodePrefix implements SelectionListEntry.Translatable {
	VANILLA("Vanilla", Formatting.FORMATTING_CODE_PREFIX),
	AMPERSAND("Ampersand", '&');

	private final String name;
	private final char prefix;

	FormattingCodePrefix(String name, char prefix) {
		this.name = name;
		this.prefix = prefix;
	}

	public char getPrefix() {
		return prefix;
	}

	@Override
	public String toString() {
		return String.valueOf(prefix);
	}

	@Override
	public @NotNull String getKey() {
		return this.name;
	}
}
