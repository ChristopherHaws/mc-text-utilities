package io.chaws.textutilities.test;

import net.fabricmc.fabric.api.gametest.v1.FabricGameTest;
import net.minecraft.test.GameTest;
import net.minecraft.test.TestContext;

public class TextUtilitiesGameTestSuite implements FabricGameTest {
	@GameTest(templateName = EMPTY_STRUCTURE)
	public void Foo(TestContext ctx) {
		var player = ctx.createMockPlayer();
	}
}
