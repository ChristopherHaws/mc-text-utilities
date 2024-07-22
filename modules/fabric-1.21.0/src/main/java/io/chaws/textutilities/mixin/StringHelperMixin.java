package io.chaws.textutilities.mixin;

import io.chaws.textutilities.TextUtilities;
import net.minecraft.util.Formatting;
import net.minecraft.util.StringHelper;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StringHelper.class)
public class StringHelperMixin {
	@Inject(method = "isValidChar", at = @At("HEAD"), cancellable = true)
	private static void isValidChar(char p, CallbackInfoReturnable<Boolean> ci) {
		if (TextUtilities.getConfig().formattingDisabled()) {
			return;
		}

		// Allow for items and signs to contain the formatting code prefix
		if (p == Formatting.FORMATTING_CODE_PREFIX) {
			ci.setReturnValue(true);
		}
	}
}
