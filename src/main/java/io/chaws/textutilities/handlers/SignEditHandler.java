package io.chaws.textutilities.handlers;

import io.chaws.textutilities.TextUtilities;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.SignChangingItem;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import static io.chaws.textutilities.utils.PlayerUtils.*;

public class SignEditHandler {
	public static void initialize() {
		UseBlockCallback.EVENT.register(SignEditHandler::onUseSignBlock);
	}

	private static ActionResult onUseSignBlock(
		final PlayerEntity player,
		final World world,
		final Hand hand,
		final BlockHitResult hitResult
	) {
		if (world.isClient) {
			return ActionResult.PASS;
		}

		if (player.isSneaking()) {
			return ActionResult.PASS;
		}

		if (!TextUtilities.getConfig().signEditingEnabled) {
			return ActionResult.PASS;
		}

		var blockPos = hitResult.getBlockPos();
		var blockEntity = world.getBlockEntity(blockPos);

		if (!(blockEntity instanceof SignBlockEntity signBlock)) {
			return ActionResult.PASS;
		}

		if (signBlock.isWaxed()) {
			player.sendMessage(Text.literal("Waxed signs cannot be edited."), true);
			return ActionResult.PASS;
		}

		if (!isHoldingSign(player)) {
			return ActionResult.PASS;
		}

		// Dyes, Ink Sacs, etc can be applied to signs directly when they are in the main hand
		if (isHoldingSignChangingItem(player, Hand.MAIN_HAND)) {
			return ActionResult.PASS;
		}

		var editorId = signBlock.getEditor();
		var playerId = player.getUuid();

		if (editorId != null && editorId != playerId) {
			player.sendMessage(Text.literal("Sign is being edited by someone else."), true);
			return ActionResult.FAIL;
		}

		signBlock.setEditor(playerId);
		player.openEditSignScreen(signBlock, true);
		return ActionResult.SUCCESS;
	}
}
