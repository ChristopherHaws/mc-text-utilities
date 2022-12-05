package io.chaws.textutilities.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;

import java.util.function.Predicate;

public class PlayerUtils {
	public static boolean isHolding(PlayerEntity player, Hand hand, Item item) {
		return isHolding(player, hand, holding -> holding.isOf(item));
	}

	public static boolean isHolding(PlayerEntity player, Hand hand, Predicate<ItemStack> predicate) {
		var holding = hand == Hand.MAIN_HAND ? player.getMainHandStack() : player.getOffHandStack();
		return predicate.test(holding);
	}

	public static boolean isHoldingSign(PlayerEntity player) {
		return player.isHolding(x -> x.getItem() instanceof SignItem);
	}
}
