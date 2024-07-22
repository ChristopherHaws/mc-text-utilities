package io.chaws.textutilities.utils;

import org.junit.jupiter.api.Test;

import static io.chaws.textutilities.utils.FormattingUtils.splitWithFormatting;
import static net.minecraft.util.Formatting.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormattingUtilsTests {
	@Test
	public void getLastFormattingCodes_single() {
		var formatString = BLUE + ITALIC.toString() + "test";
		var result = FormattingUtils.getLastFormattingCodes(formatString, 1);

		assertEquals(result, ITALIC.toString());
	}

	@Test
	public void getLastFormattingCodes_single_endsWithFormattingPrefix() {
		var formatString = BLUE + ITALIC.toString() + "test" + FORMATTING_CODE_PREFIX;
		var result = FormattingUtils.getLastFormattingCodes(formatString, 1);

		assertEquals(result, ITALIC.toString() + FORMATTING_CODE_PREFIX);
	}

	@Test
	public void getLastFormattingCodes_multiple() {
		var formatString = BLUE + ITALIC.toString() + "test";
		var result = FormattingUtils.getLastFormattingCodes(formatString, 2);

		assertEquals(result, BLUE + ITALIC.toString());
	}

	@Test
	public void getLastFormattingCodes_multiple_split() {
		var formatString = BLUE + "blue" + ITALIC + "italic";
		var result = FormattingUtils.getLastFormattingCodes(formatString, 2);

		assertEquals(result, BLUE + ITALIC.toString());
	}

	@Test
	public void getLastFormattingCodes_zero() {
		var formatString = BLUE + ITALIC.toString() + "test";
		var result = FormattingUtils.getLastFormattingCodes(formatString, 0);

		assertEquals(result, "");
	}

	@Test
	public void getLastFormattingCodes_nill() {
		var result = FormattingUtils.getLastFormattingCodes(null, 1);

		assertEquals(result, "");
	}

	@Test
	public void getLastFormattingCodes_noneFound() {
		var result = FormattingUtils.getLastFormattingCodes("test",	1);

		assertEquals(result, "");
	}

	@Test
	public void splitWithFormatting_splitCharIsStartOfFormatCode() {
		var formatting = "red" + WHITE + "blue";
		var pair = splitWithFormatting(formatting, formatting.indexOf(FORMATTING_CODE_PREFIX));

		assertEquals("red", pair.getLeft());
		assertEquals(WHITE + "blue", pair.getRight());
	}

	@Test
	public void splitWithFormatting_splitCharIsEndOfFormatCode() {
		var formatting = "red" + WHITE + "blue";
		var pair = splitWithFormatting(formatting, formatting.indexOf(FORMATTING_CODE_PREFIX) + 1);

		assertEquals("red", pair.getLeft());
		assertEquals(WHITE + "blue", pair.getRight());
	}

	@Test
	public void splitWithFormatting_onlyFormattingCodePrefix_0() {
		var formatting = String.valueOf(FORMATTING_CODE_PREFIX);
		var pair = splitWithFormatting(formatting, 0);

		assertEquals("", pair.getLeft());
		assertEquals(formatting, pair.getRight());
	}

	@Test
	public void splitWithFormatting_onlyFormattingCodePrefix_1() {
		var formatting = String.valueOf(FORMATTING_CODE_PREFIX);
		var pair = splitWithFormatting(formatting, 1);

		assertEquals(formatting, pair.getLeft());
		assertEquals("", pair.getRight());
	}

	@Test
	public void splitWithFormatting_onlyFormattingCode_0() {
		var formatting = BOLD.toString();
		var pair = splitWithFormatting(formatting, 0);

		assertEquals("", pair.getLeft());
		assertEquals(formatting, pair.getRight());
	}

	@Test
	public void splitWithFormatting_onlyFormattingCode_1() {
		var formatting = BOLD.toString();
		var pair = splitWithFormatting(formatting, 1);

		assertEquals("", pair.getLeft());
		assertEquals(formatting, pair.getRight());
	}

	@Test
	public void splitWithFormatting_endsWithFormattingCodePrefix() {
		var formatting = "01" + FORMATTING_CODE_PREFIX;
		var pair = splitWithFormatting(formatting, 2);

		assertEquals("01", pair.getLeft());
		assertEquals(String.valueOf(FORMATTING_CODE_PREFIX), pair.getRight());
	}
}
