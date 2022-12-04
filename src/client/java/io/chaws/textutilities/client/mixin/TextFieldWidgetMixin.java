package io.chaws.textutilities.client.mixin;

import io.chaws.textutilities.utils.FormattingUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Environment(EnvType.CLIENT)
@Mixin(TextFieldWidget.class)
public class TextFieldWidgetMixin {
	@Redirect(method = "renderButton", at = @At(value = "INVOKE", target = "Ljava/lang/String;substring(I)Ljava/lang/String;", ordinal = 1))
	private String appendFormatting(String string, int i) {
		var strings = FormattingUtils.splitWithFormatting(string, i);

		return FormattingUtils.getLastFormattingCodes(strings.getLeft(), 2)
			.concat(strings.getRight());
	}
}
