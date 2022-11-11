package io.chaws.textutilities.utils;

import io.chaws.textutilities.TextUtilitiesMod;
import net.minecraft.util.Formatting;

public class FormattingUtils {
	public static String replaceConfiguredPrefixWithBuiltInPrefix(String value) {
		if (!TextUtilitiesMod.enabled) {
			return value;
		}

		var config = TextUtilitiesMod.getConfig();
		var formattingCodePrefix = config.getFormattingCodePrefix();
		if (formattingCodePrefix == Formatting.FORMATTING_CODE_PREFIX) {
			return value;
		}

		return value.replace(
			formattingCodePrefix,
			Formatting.FORMATTING_CODE_PREFIX
		);
	}

	public static void replaceConfiguredPrefixWithBuiltInPrefix(String[] values) {
		if (!TextUtilitiesMod.enabled) {
			return;
		}

		var config = TextUtilitiesMod.getConfig();
		var formattingCodePrefix = config.getFormattingCodePrefix();
		if (formattingCodePrefix == Formatting.FORMATTING_CODE_PREFIX) {
			return;
		}

		for (var i = 0; i < values.length; i++) {
			values[i] = values[i].replace(
				config.getFormattingCodePrefix(),
				Formatting.FORMATTING_CODE_PREFIX
			);
		}
	}

	public static String replaceBuiltInPrefixWithConfiguredPrefix(String value) {
		if (!TextUtilitiesMod.enabled) {
			return value;
		}

		var config = TextUtilitiesMod.getConfig();
		var formattingCodePrefix = config.getFormattingCodePrefix();
		if (formattingCodePrefix == Formatting.FORMATTING_CODE_PREFIX) {
			return value;
		}

		return value.replace(
			Formatting.FORMATTING_CODE_PREFIX,
			formattingCodePrefix
		);
	}

	public static void replaceBuiltInPrefixWithConfiguredPrefix(String[] values) {
		if (!TextUtilitiesMod.enabled) {
			return;
		}

		var config = TextUtilitiesMod.getConfig();
		var formattingCodePrefix = config.getFormattingCodePrefix();
		if (formattingCodePrefix == Formatting.FORMATTING_CODE_PREFIX) {
			return;
		}

		for (var i = 0; i < values.length; i++) {
			values[i] = values[i].replace(
				Formatting.FORMATTING_CODE_PREFIX,
				config.getFormattingCodePrefix()
			);
		}
	}
}
