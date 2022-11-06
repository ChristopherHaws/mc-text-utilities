package dev.chaws.textutilities.mixin;

import dev.chaws.textutilities.TextUtilitiesMod;
import dev.chaws.textutilities.utils.FormattingUtils;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.c2s.play.RenameItemC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {
	@Shadow private TextFieldWidget nameField;

	private AnvilScreen getAnvilScreen() {
		return ((AnvilScreen)(Object)this);
	}

	@Inject(method = "keyPressed", at = @At("HEAD"))
	private void inject(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> ci) {
		if (!TextUtilitiesMod.enabled) {
			return;
		}

		this.getAnvilScreen().setFocused(this.nameField);
	}

	@ModifyArg(method = "onRenamed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V"))
	private Packet onRenamed_sendPacket(Packet packet) {
		if (!(packet instanceof RenameItemC2SPacket renameItemC2SPacket)) {
			return packet;
		}

		var name = renameItemC2SPacket.getName();
		name = FormattingUtils.replaceBuiltInPrefixWithConfiguredPrefix(name);
		return new RenameItemC2SPacket(name);
	}

	@ModifyArg(method = "onSlotUpdate", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setText(Ljava/lang/String;)V"))
	private String onSlotUpdate_TextFieldWidget_setText(String name) {
		return FormattingUtils.replaceConfiguredPrefixWithBuiltInPrefix(name);
	}
}
