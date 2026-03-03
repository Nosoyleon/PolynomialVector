package Enums;

public enum polyf1options {
	EXIT(0), REPLACE_POLY(1), INSERT_MONO(2), REMOVE_MONO(3), SOLVE(4), SUMPOLY(5);

	private final int value;

	polyf1options(int value) {
		this.value = value;
	}

	public static polyf1options fromInt(int value) {
		for (polyf1options option : polyf1options.values()) {
			if (option.value == value) {
				return option;
			}
		}
		throw new IllegalArgumentException("Invalid option: " + value);
	}
}
