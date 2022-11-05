package dev.chaws.textutilities.mixin;

import dev.chaws.textutilities.TextUtilitiesMod;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SignEditScreen.class)
public class SignEditScreenMixin {
	@Final @Shadow private String[] text;

	@Inject(at = {@At("TAIL")}, method = {"init"})
	private void init(CallbackInfo ci) {
		var config = TextUtilitiesMod.getConfig();
		if (config.getFormattingCodePrefix() == Formatting.FORMATTING_CODE_PREFIX) {
			return;
		}

		for (var i = 0; i < text.length; i++) {
			var currentLine = text[i];
			text[i] = currentLine.replace(
				config.getFormattingCodePrefix(),
				Formatting.FORMATTING_CODE_PREFIX
			);
		}
	}

	@Inject(at = {@At("HEAD")}, method = {"removed"})
	private void removed(CallbackInfo ci) {
		var config = TextUtilitiesMod.getConfig();
		if (config.getFormattingCodePrefix() == Formatting.FORMATTING_CODE_PREFIX) {
			return;
		}

		for (var i = 0; i < text.length; i++) {
			var currentLine = text[i];

			text[i] = currentLine.replace(
				Formatting.FORMATTING_CODE_PREFIX,
				config.getFormattingCodePrefix()
			);
		}
	}
}
