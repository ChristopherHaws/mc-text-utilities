package io.chaws.textutilities.client.mixin;

import io.chaws.textutilities.TextUtilities;
import io.chaws.textutilities.utils.FormattingUtils;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.gui.screen.ingame.ForgingScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin
	extends ForgingScreen<AnvilScreenHandler> {

	public AnvilScreenMixin(
		AnvilScreenHandler handler,
		PlayerInventory playerInventory,
		Text title,
		Identifier texture
	) {
		super(handler, playerInventory, title, texture);
	}

	@Shadow
	private TextFieldWidget nameField;

	private AnvilScreen getAnvilScreen() {
		return ((AnvilScreen) (Object) this);
	}

	@Inject(method = "setup", at = @At(value = "TAIL"))
	protected void setup(CallbackInfo ci) {
		// Defaults to: OrderedText.styledForwardsVisitedString(string, Style.EMPTY);
		this.nameField.setRenderTextProvider((abc, def) ->
				Text.literal(abc).asOrderedText()
			);
	}

	@Inject(method = "keyPressed", at = @At("HEAD"))
	private void inject(
		int keyCode,
		int scanCode,
		int modifiers,
		CallbackInfoReturnable<Boolean> ci
	) {
		if (!TextUtilities.getConfig().anvilFormattingEnabled) {
			return;
		}

		this.getAnvilScreen().setFocused(this.nameField);
	}

	@Inject(method = "onSlotUpdate", at = @At(value = "TAIL"))
	private void onSlotUpdate(
		ScreenHandler handler,
		int slotId,
		ItemStack stack,
		CallbackInfo ci
	) {
		if (!TextUtilities.getConfig().anvilFormattingEnabled) {
			return;
		}

		if (slotId != 0) {
			return;
		}

		var displayElement = stack.getSubNbt(ItemStack.DISPLAY_KEY);
		if (displayElement == null) {
			return;
		}

		var nameElement = displayElement.get(ItemStack.NAME_KEY);
		if (nameElement == null) {
			return;
		}

		var json = nameElement.asString();
		var text = Text.Serialization.fromJson(json);
		if (text == null) {
			return;
		}

		var sb = new StringBuilder();
		text.visit(
			(StringVisitable.StyledVisitor<String>) (style, asString) -> {
				var color = style.getColor();
				if (color != null) {
					var formatting = Formatting.byName(color.getName());
					if (formatting != null) {
						sb.append(formatting);
					}
				}

				if (style.isBold()) {
					sb.append(Formatting.BOLD);
				}

				if (style.isItalic()) {
					sb.append(Formatting.ITALIC);
				}

				if (style.isUnderlined()) {
					sb.append(Formatting.UNDERLINE);
				}

				if (style.isStrikethrough()) {
					sb.append(Formatting.STRIKETHROUGH);
				}

				if (style.isObfuscated()) {
					sb.append(Formatting.OBFUSCATED);
				}

				if (style.isEmpty()) {
					sb.append(Formatting.RESET);
				}

				sb.append(asString);
				return Optional.empty();
			},
			Style.EMPTY
		);

		var formattedName = sb.toString();
		this.nameField.setText(formattedName);
	}

	//	@ModifyArg(method = "onRenamed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayNetworkHandler;sendPacket(Lnet/minecraft/network/Packet;)V"))
	//	private Packet onRenamed_sendPacket(Packet packet) {
	//		if (!(packet instanceof RenameItemC2SPacket renameItemC2SPacket)) {
	//			return packet;
	//		}
	//
	//		var name = renameItemC2SPacket.getName();
	//		name = FormattingUtils.replaceBuiltInPrefixWithConfiguredPrefix(name);
	//		return new RenameItemC2SPacket(name);
	//	}

	@ModifyVariable(method = "onRenamed", at = @At("HEAD"), argsOnly = true)
	private String onRenamed(String name) {
		if (!TextUtilities.getConfig().anvilFormattingEnabled) {
			return name;
		}

		return FormattingUtils.replaceBuiltInPrefixWithConfiguredPrefix(name);
	}

	@ModifyArg(
		method = "onSlotUpdate",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/gui/widget/TextFieldWidget;setText(Ljava/lang/String;)V"
		)
	)
	private String onSlotUpdate_TextFieldWidget_setText(String name) {
		if (!TextUtilities.getConfig().anvilFormattingEnabled) {
			return name;
		}

		return FormattingUtils.replaceConfiguredPrefixWithBuiltInPrefix(name);
	}
}
