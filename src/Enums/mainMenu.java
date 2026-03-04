package Enums;

public enum mainMenu {
	EXIT(0), MAIN_POLYNOMIAL_F1(1), MAIN_POLYNOMIAL_F2(2), MAIN_POLYNOMIAL_F3(3), INVALID(999);

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