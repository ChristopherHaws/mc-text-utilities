package io.chaws.textutilities.handlers;

import io.chaws.textutilities.TextUtilities;
import io.chaws.textutilities.config.TextUtilitiesConfig;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.AbstractDecorationEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static io.chaws.textutilities.utils.ItemFrameEntityUtils.rotateItemCounterClockwise;
import static io.chaws.textutilities.utils.PlayerUtils.*;

public class ClickThroughHandler {
	public static void initialize() {
		UseBlockCallback.EVENT.register(ClickThroughHandler::handleUseBlock);
		UseEntityCallback.EVENT.register(ClickThroughHandler::handleUseEntity);
	}

	private static ActionResult handleUseBlock(
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

		var config = TextUtilities.getConfig();

		var clickedSide = hitResult.getSide();
		var clickedBlockPos = hitResult.getBlockPos();

		return tryClickThrough(world, player, config, clickedBlockPos, clickedSide, hand)
			? ActionResult.SUCCESS
			: ActionResult.PASS;
	}

	private static ActionResult handleUseEntity(
		final PlayerEntity player,
		final World world,
		final Hand hand,
		final Entity entity,
		final @Nullable EntityHitResult entityHitResult
	) {
		if (world.isClient) {
			return ActionResult.PASS;
		}

		var config = TextUtilities.getConfig();

		if (player.isSneaking()) {
			return ActionResult.PASS;
		}

		if (entityHitResult == null) {
			return ActionResult.PASS;
		}

		if (!canClickThroughEntity(config, entity)) {
			return ActionResult.PASS;
		}

		var entityFacing = entity.getHorizontalFacing();

		if (entity instanceof AbstractDecorationEntity decorationEntity) {
			var decorationBlockPos = decorationEntity.getDecorationBlockPos().add(
				entityFacing.getOpposite().getVector()
			);

			useBlock(world, player, decorationBlockPos, entityFacing, hand);

			//HACK: Need to figure out a better way to disable the rotation of the item
			if (decorationEntity instanceof ItemFrameEntity itemFrameEntity) {
				// Reset the item rotation
				rotateItemCounterClockwise(itemFrameEntity);
			}

			return ActionResult.SUCCESS;
		}

		//HACK: Need to find a better way of detecting the side of the entity that was clicked
		var clickedSide = player.getHorizontalFacing().getOpposite();
		var clickedEntityPos = new BlockPos(entityHitResult.getPos());

		return tryClickThrough(world, player, config, clickedEntityPos, clickedSide, hand)
			? ActionResult.SUCCESS
			: ActionResult.PASS;
	}

	private static boolean tryClickThrough(
		final World world,
		final PlayerEntity player,
		final TextUtilitiesConfig config,
		final BlockPos clickedBlockPos,
		final Direction clickedBlockSide,
		final Hand hand
	) {
		var playerFacing = player.getHorizontalFacing().getVector();
		var attachedBlockPos = clickedBlockPos.add(playerFacing);
		var blockState = world.getBlockState(clickedBlockPos);

		var blockEntity = world.getBlockEntity(clickedBlockPos);
		if (blockEntity != null && canClickThroughBlockEntity(player, config, blockEntity)) {
			useBlock(world, player, attachedBlockPos, clickedBlockSide, hand);
			return true;
		}

		var block = blockState.getBlock();
		if (canClickThroughBlock(config, block)) {
			useBlock(world, player, attachedBlockPos, clickedBlockSide, hand);
			return true;
		}

		return false;
	}

	private static boolean canClickThroughBlock(
		final TextUtilitiesConfig config,
		final Block block
	) {
		var blockId = Registries.BLOCK.getId(block);
		return config.additionalClickThroughIdentifiers.contains(blockId.toString());
	}

	private static boolean canClickThroughBlockEntity(
		final PlayerEntity player,
		final TextUtilitiesConfig config,
		final BlockEntity blockEntity
	) {
		if (blockEntity instanceof SignBlockEntity) {
			// Dyes and Ink Sacs can be applied to signs directly when they are in the main hand
			return
				config.signClickThroughEnabled &&
				!isHolding(player, Hand.MAIN_HAND, Items.INK_SAC) &&
				!isHolding(player, Hand.MAIN_HAND, Items.GLOW_INK_SAC) &&
				!isHoldingDye(player, Hand.MAIN_HAND) &&
				!isHoldingSign(player, Hand.MAIN_HAND);
		}

		var blockEntityType = blockEntity.getType();
		var blockIdentifier = BlockEntityType.getId(blockEntityType);
		if (blockIdentifier == null) {
			return false;
		}

		return config.additionalClickThroughIdentifiers.contains(blockIdentifier.toString());
	}

	private static boolean canClickThroughEntity(
		final TextUtilitiesConfig config,
		final Entity entity
	) {
		if (entity instanceof ItemFrameEntity itemFrameEntity) {
			if (!config.itemFrameClickThroughEnabled) {
				return false;
			}

			// If the item frame has no item attached to it,
			// attach the item and don't click through to the chest.
			var attachedItem = itemFrameEntity.getHeldItemStack();
			return !attachedItem.isOf(Items.AIR);
		}

		var entityType = entity.getType();
		var entityIdentifier = EntityType.getId(entityType);
		if (entityIdentifier == null) {
			return false;
		}

		return config.additionalClickThroughIdentifiers.contains(entityIdentifier.toString());
	}

	private static void useBlock(
		final World world,
		final PlayerEntity player,
		final BlockPos attachedBlockPos,
		final Direction clickedSide,
		final Hand hand
	) {
		var attachedBlockState = world.getBlockState(attachedBlockPos);
		var attachedBlockHitResult = new BlockHitResult(
			attachedBlockPos.toCenterPos(),
			clickedSide,
			attachedBlockPos,
			false
		);
		attachedBlockState.onUse(world, player, hand, attachedBlockHitResult);
	}
}
