package com.linkwee.web.util;

import java.util.Random;

public final class RandomTextCreator {

	private static final char[] CHARACTERS = "abcde2345678gfynmnpwx".toCharArray();

	private RandomTextCreator() {
	}

	public static String getText(final int length) {
		final StringBuffer text = new StringBuffer();
		final Random rand = new Random();
		for (int i = 0; i < length; i++) {
			final int index = rand.nextInt(CHARACTERS.length);
			text.append(CHARACTERS[index]);
		}

		return text.toString();
	}
}