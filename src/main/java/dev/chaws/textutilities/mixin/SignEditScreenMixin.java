package dev.chaws.textutilities.mixin;

import dev.chaws.textutilities.common.FormattingCodePrefix;
import dev.chaws.textutilities.config.TextUtilitiesConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
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
		TextUtilitiesConfig config = AutoConfig.getConfigHolder(TextUtilitiesConfig.class).getConfig();

		if (config.formattingCodePrefix == FormattingCodePrefix.VANILLA) {
			return;
		}

		for (var i = 0; i < text.length; i++) {
			var currentLine = text[i];
			text[i] = currentLine.replace(
				config.formattingCodePrefix.getPrefix(),
				FormattingCodePrefix.VANILLA.getPrefix()
			);
		}
	}

	@Inject(at = {@At("HEAD")}, method = {"removed"})
	private void removed(CallbackInfo ci) {
		TextUtilitiesConfig config = AutoConfig.getConfigHolder(TextUtilitiesConfig.class).getConfig();

		if (config.formattingCodePrefix == FormattingCodePrefix.VANILLA) {
			return;
		}

		for (var i = 0; i < text.length; i++) {
			var currentLine = text[i];

			text[i] = currentLine.replace(
				FormattingCodePrefix.VANILLA.getPrefix(),
				config.formattingCodePrefix.getPrefix()
			);
		}
	}
}
