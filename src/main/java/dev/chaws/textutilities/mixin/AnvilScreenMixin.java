package dev.chaws.textutilities.mixin;

import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
	@Shadow private TextFieldWidget nameField;

	@Inject(at = {@At("HEAD")}, method = {"keyPressed"})
	private void inject(int i1, int i2, int i3, CallbackInfoReturnable<Boolean> ci) {
		((AnvilScreen)(Object)this).setFocused(this.nameField);
	}
}
