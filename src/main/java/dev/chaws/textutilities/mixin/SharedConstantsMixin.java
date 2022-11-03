package dev.chaws.textutilities.mixin;

import dev.chaws.textutilities.common.FormattingCodePrefix;
import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SharedConstants.class)
public class SharedConstantsMixin {
	@Inject(at = {@At("HEAD")}, method = {"isValidChar"}, cancellable = true)
	private static void isValidChar(char p, CallbackInfoReturnable<Boolean> ci) {
		if (p == FormattingCodePrefix.SECTION) {
			ci.setReturnValue(true);
		}
	}
}
