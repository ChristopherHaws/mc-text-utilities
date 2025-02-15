package io.chaws.textutilities.client.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatUtils {
	public static void sendMessage(String message) {
		sendMessage(Text.of(message));
	}

	public static void sendMessage(Text message) {
		var mc = MinecraftClient.getInstance();
		if (mc == null || mc.player == null || !mc.isInSingleplayer()) {
			return;
		}

		mc.player.sendMessage(message, false);
	}

	public static void sendMessage(String message, boolean overlay) {
		sendMessage(Text.of(message), overlay);
	}

	public static void sendMessage(Text message, boolean overlay) {
		var mc = MinecraftClient.getInstance();
		if (mc == null || mc.player == null || !mc.isInSingleplayer()) {
			return;
		}

		mc.player.sendMessage(message, overlay);
	}
}
