package io.chaws.textutilities.mixin;

import io.chaws.textutilities.TextUtilitiesMod;
import io.chaws.textutilities.utils.FormattingUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.SignEditScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SignEditScreen.class)
public class SignEditScreenMixin {
	@Final @Shadow private String[] text;

	@Inject(method = "init", at = @At("TAIL"))
	private void init(CallbackInfo ci) {
		if (!TextUtilitiesMod.enabled) {
			return;
		}

		FormattingUtils.replaceConfiguredPrefixWithBuiltInPrefix(text);
	}

	@Inject(method = "removed", at = @At("HEAD"))
	private void removed(CallbackInfo ci) {
		if (!TextUtilitiesMod.enabled) {
			return;
		}

		FormattingUtils.replaceBuiltInPrefixWithConfiguredPrefix(text);
	}
}
