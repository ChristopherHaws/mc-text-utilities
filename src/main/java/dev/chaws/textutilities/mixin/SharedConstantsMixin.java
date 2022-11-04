package dev.chaws.textutilities.mixin;

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
		if (p == Formatting.FORMATTING_CODE_PREFIX) {
			ci.setReturnValue(true);
		}
	}
}
