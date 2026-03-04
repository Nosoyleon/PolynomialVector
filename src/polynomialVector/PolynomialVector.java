package polynomialVector;

import java.util.Scanner;

import Enums.mainMenu;

public class PolynomialVector {

	// 3x^2-18x^5+2x-10
	// 2x+3x^2-18x^5-10
	// -10+3x^2-18x^5+2x
	// 10+3x^2-18x^5+2x
	// 10-3x^2-18x^5+2x
	// 3x^2+18x^7+2x-10+6x^4
	// 3x^2-18x^5+x-10

	static String DIVIDER = "----------------------";

	public static void main(String[] args) {
		System.out.println("Bienvenido!");

		Scanner scanner = new Scanner(System.in);
		PolynomialF1 F1 = new PolynomialF1(0);
		PolynomialF1 F2 = new PolynomialF1(0);
		PolynomialF1 F3 = new PolynomialF1(0);

		Enums.mainMenu mainMenuResponse = mainMenu.EXIT;
		do {
			System.out.println(DIVIDER);
			System.out.println("MENU PRINCIPAL DE POLINOMIOS");
			System.out.println(DIVIDER);

			MainMenu menu = new MainMenu(scanner);
			ActionsPolynomialF1 actionsF1 = new ActionsPolynomialF1(scanner, F1);

			try {
				mainMenuResponse = menu.initialMenu(F1.rebuildToString(), F2.rebuildToString(), F3.rebuildToString());

				switch (mainMenuResponse) {
				case EXIT: {
					System.out.println("Bye!!");
					break;
				}
				case MAIN_POLYNOMIAL_F1: {
					F1 = actionsF1.mainPolyF1();
					break;
				}
				
				case MAIN_POLYNOMIAL_F2: {
					
					break;
				}
				default:
					System.out.println("Opción incorrecta -.-");
				}
			} catch (Exception e) {
				System.out.println("Opción incorrecta -.-");
				mainMenuResponse = mainMenu.INVALID;
				scanner.nextLine();
			}

		} while (mainMenuResponse != mainMenu.EXIT);

		scanner.close();

	}

}
