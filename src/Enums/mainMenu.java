package Enums;

public enum mainMenu {
	EXIT(0), INSERT_POLYNOMIAL(1);

	private final int value;

	mainMenu(int value) {
		this.value = value;
	}

	public static mainMenu fromInt(int value) {
		for (mainMenu option : mainMenu.values()) {
			if (option.value == value) {
				return option;
			}
		}
		throw new IllegalArgumentException("Invalid option: " + value);
	}
}