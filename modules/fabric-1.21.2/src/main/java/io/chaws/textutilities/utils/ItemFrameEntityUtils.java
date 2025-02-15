package io.chaws.textutilities.utils;

import net.minecraft.entity.decoration.ItemFrameEntity;

public class ItemFrameEntityUtils {
	public static void rotateItemCounterClockwise(ItemFrameEntity itemFrameEntity) {
		var itemRotation = itemFrameEntity.getRotation();
		if (itemRotation == 0 || itemRotation == 8) {
			itemFrameEntity.setRotation(7);
		} else {
			itemFrameEntity.setRotation(itemRotation - 1);
		}
	}
}
