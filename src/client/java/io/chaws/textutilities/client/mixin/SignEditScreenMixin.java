package io.chaws.textutilities.client.mixin;

import io.chaws.textutilities.TextUtilities;
import io.chaws.textutilities.utils.FormattingUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AbstractSignEditScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(AbstractSignEditScreen.class)
public class SignEditScreenMixin {
	@Final @Shadow
	private String[] messages;

	@Inject(method = "init", at = @At("TAIL"))
	private void init(CallbackInfo ci) {
		if (!TextUtilities.getConfig().signFormattingEnabled) {
			return;
		}

		FormattingUtils.replaceConfiguredPrefixWithBuiltInPrefix(messages);
	}

	@Inject(method = "removed", at = @At("HEAD"))
	private void removed(CallbackInfo ci) {
		if (!TextUtilities.getConfig().signFormattingEnabled) {
			return;
		}

		FormattingUtils.replaceBuiltInPrefixWithConfiguredPrefix(messages);
	}
}
