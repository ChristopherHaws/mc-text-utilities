package dev.chaws.textutilities.mixin;

import dev.chaws.textutilities.TextUtilitiesMod;
import net.minecraft.SharedConstants;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SharedConstants.class)
public class SharedConstantsMixin {
	@Inject(at = {@At("HEAD")}, method = {"isValidChar"}, cancellable = true)
	private static void isValidChar(char p, CallbackInfoReturnable<Boolean> ci) {
		if (!TextUtilitiesMod.getConfig().enabled) {
			return;
		}

		// Allow for items and signs to contain the formatting code prefix
		if (p == Formatting.FORMATTING_CODE_PREFIX) {
			ci.setReturnValue(true);
		}
	}
}
