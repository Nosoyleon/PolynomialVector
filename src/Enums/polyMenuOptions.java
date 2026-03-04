package Enums;

public enum polyMenuOptions {
	EXIT(0), REPLACE_POLY(1), INSERT_MONO(2), REMOVE_MONO(3), SOLVE(4), SUMPOLY(5), MULTIPLY(6);

	private final int value;

	polyMenuOptions(int value) {
		this.value = value;
	}

	public static polyMenuOptions fromInt(int value) {
		for (polyMenuOptions option : polyMenuOptions.values()) {
			if (option.value == value) {
				return option;
			}
		}
		throw new IllegalArgumentException("Invalid option: " + value);
	}
}
