package io.chaws.textutilities.handlers;

import io.chaws.textutilities.TextUtilities;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;

import static io.chaws.textutilities.utils.PlayerUtils.isHolding;
import static io.chaws.textutilities.utils.PlayerUtils.isHoldingSign;

public class SignClickThroughHandler {
	public static void initialize() {
		UseBlockCallback.EVENT.register(SignClickThroughHandler::onUseSignBlock);
	}

	private static ActionResult onUseSignBlock(final PlayerEntity player, final World world, final Hand hand, final BlockHitResult hitResult) {
		if (world.isClient) {
			return ActionResult.PASS;
		}

		if (!TextUtilities.getConfig().signClickThroughEnabled) {
			return ActionResult.PASS;
		}

		// Dyes and Ink Sacs can be applied to signs directly when they are in the main hand
		if (isHolding(player, Hand.MAIN_HAND, Items.INK_SAC) ||
			isHolding(player, Hand.MAIN_HAND, Items.GLOW_INK_SAC) ||
			isHoldingSign(player)) {
			return ActionResult.PASS;
		}

		var blockPos = hitResult.getBlockPos();
		var blockEntity = world.getBlockEntity(blockPos);

		if (!(blockEntity instanceof SignBlockEntity)) {
			return ActionResult.PASS;
		}

		var newBlockPos = blockPos.add(player.getHorizontalFacing().getVector());
		var newBlockState = world.getBlockState(newBlockPos);
		var newHitResult = new BlockHitResult(newBlockPos.toCenterPos(), hitResult.getSide(), newBlockPos, false);
		newBlockState.onUse(world, player, hand, newHitResult);

		return ActionResult.SUCCESS;
	}
}