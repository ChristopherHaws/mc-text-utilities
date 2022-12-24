package io.chaws.textutilities.handlers;

import io.chaws.textutilities.TextUtilities;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static io.chaws.textutilities.utils.ItemFrameEntityUtils.rotateItemCounterClockwise;

public class ItemFrameClickThroughHandler {
	public static void initialize() {
		UseEntityCallback.EVENT.register(ItemFrameClickThroughHandler::onUseEntity);
	}

	private static ActionResult onUseEntity(
		final PlayerEntity player,
		final World world,
		final Hand hand,
		final Entity entity,
		final @Nullable EntityHitResult entityHitResult
	) {
		if (world.isClient) {
			return ActionResult.PASS;
		}

		if (!TextUtilities.getConfig().itemFrameClickThroughEnabled) {
			return ActionResult.PASS;
		}

		if (player.isSneaking()) {
			return ActionResult.PASS;
		}

		if (entityHitResult == null) {
			return ActionResult.PASS;
		}

		if (!(entity instanceof AbstractDecorationEntity decorationEntity)) {
			return ActionResult.PASS;
		}

		if (decorationEntity instanceof ItemFrameEntity itemFrameEntity) {
			var heldItem = itemFrameEntity.getHeldItemStack();
			if (heldItem.isOf(Items.AIR)) {
				// If the item frame has not item attached to it,
				// attach the item and don't click through to the chest.
				return ActionResult.PASS;
			}
		}

		var attachedBlockPos = decorationEntity.getDecorationBlockPos().add(
			decorationEntity.getHorizontalFacing().getOpposite().getVector()
		);

		var attachedBlockState = world.getBlockState(attachedBlockPos);
		var attachedHitResult = new BlockHitResult(
			attachedBlockPos.toCenterPos(),
			entity.getHorizontalFacing(),
			attachedBlockPos,
			false
		);

		attachedBlockState.onUse(world, player, hand, attachedHitResult);

		if (decorationEntity instanceof ItemFrameEntity itemFrameEntity) {
			// Reset the item rotation
			rotateItemCounterClockwise(itemFrameEntity);
		}

		return ActionResult.PASS;
	}
}
