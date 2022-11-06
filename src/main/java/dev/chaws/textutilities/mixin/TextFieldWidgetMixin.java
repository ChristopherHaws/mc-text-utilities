package dev.chaws.textutilities.mixin;

import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {
	@Redirect(method = "renderButton", at = @At(value = "INVOKE", target = "Ljava/lang/String;substring(I)Ljava/lang/String;"))
	public String renderButton_substring(String string, int beginInt) {
		return string;
//		if (beginInt <= 0) {
//			return string;
//		}
//
//		// Don't trim the characters here. If we do, then the formatting stops
//		// working because the formatting chars get removed
//		var lastCharIsFormatCodePrefix = string.charAt(beginInt) == Formatting.FORMATTING_CODE_PREFIX;
//		var outOfBoundsText = lastCharIsFormatCodePrefix
//			? string.substring(0, beginInt + 1)
//			: string.substring(0, beginInt);
//
//		var outOfBoundsFormattingOnly = new StringBuilder();
//
//		// Subtract 1 from length because we look at the next two chars in the loop
//		for (var i = 0; i < outOfBoundsText.length() - 1; i++) {
//			var currentChar = outOfBoundsText.charAt(i);
//			var nextChar = outOfBoundsText.charAt(++i);
//
//			if (currentChar == Formatting.FORMATTING_CODE_PREFIX) {
//				outOfBoundsFormattingOnly.append(currentChar);
//				outOfBoundsFormattingOnly.append(nextChar);
//			}
//		}
//
//		return outOfBoundsFormattingOnly.toString().concat(string.substring(beginInt));
	}
}
