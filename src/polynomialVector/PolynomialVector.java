package polynomialVector;

import java.util.Scanner;

import Enums.mainMenu;
import Enums.polyf1options;

public class PolynomialVector {

	// 3x^2-18x^5+2x-10
	// 2x+3x^2-18x^5-10
	// -10+3x^2-18x^5+2x
	// 10+3x^2-18x^5+2x
	// 10-3x^2-18x^5+2x
	// 3x^2+18x^7+2x-10+6x^4
	// 3x^2-18x^5+x-10

	static String DIVIDER = "###########";

	public static void main(String[] args) {
		System.out.println("Bienvenido!");
		System.out.println(DIVIDER);

		userInitialOptions();

	}

	private static void userInitialOptions() {
		Scanner scanner = new Scanner(System.in);
		PolynomialF1 F1 = new PolynomialF1(0);

		MainMenu menu = new MainMenu();
		Enums.mainMenu mainMenuResponse = mainMenu.EXIT;
		Enums.polyf1options polyF1MenuResponse = polyf1options.EXIT;
		int userInput = 1;

		do {
			// Without polynomial
			if (F1.getVector()[0] == 0) {

				mainMenuResponse = menu.initialMenu(scanner);

				switch (mainMenuResponse) {
				case EXIT: {
					System.out.println("Bye!!!");
					userInput = 0;
					break;
				}

				case INSERT_POLYNOMIAL: {
					F1 = addNewPolynomialF1(scanner);
					scanner.nextLine();
					break;
				}

				default:
					System.out.println("Opción incorrecta -.-");
				}

			} else {
				// With Polynomial
				polyF1MenuResponse = menu.PolynomialF1Options(scanner, F1.rebuildToString(), F1.showinF1(), F1.getDU());

				switch (polyF1MenuResponse) {
				case EXIT: {
					System.out.println("Bye!!!");
					userInput = 0;
					break;
				}

				case REPLACE_POLY: { // Replace Polynomial F1
					F1 = addNewPolynomialF1(scanner);
					scanner.nextLine();
					break;
				}

				case INSERT_MONO: { // Add Monomial
					System.out.println("Ingrese el Nuevo termino: ");
					String newMonomial = scanner.nextLine();
					F1.insert(newMonomial);
					scanner.nextLine();
					break;
				}

				case REMOVE_MONO: { // Remove Monomial
					System.out.println("Ingrese el termino a remover: ");
					String monomialToRemove = scanner.nextLine();
					F1.remove(monomialToRemove);
					scanner.nextLine();
					break;
				}

				case SOLVE: {
					System.out.println("Ingrese el valor de X: ");
					int value = scanner.nextInt();
					scanner.nextLine();

					int result = F1.solvePolynomial(value);
					System.out.println();
					System.out.println("Total: " + result);
					scanner.nextLine();
					break;

				}

				case SUMPOLY: {

					PolynomialF1 polyToSum = addNewPolynomialF1(scanner);

					int degreeCurrent = F1.getVector()[0];
					int degreeToSum = polyToSum.getVector()[0];

					int maxExp = degreeCurrent > degreeToSum ? degreeCurrent : degreeToSum;

					PolynomialF1 totalPoly = new PolynomialF1(maxExp);

					totalPoly.sumPolynomial(F1, polyToSum);

					System.out.println();
					System.out.println("Polynomio Sumado: " + totalPoly.rebuildToString());
					F1 = totalPoly;
					scanner.nextLine();

					break;
				}

				case MULTIPLY: {

					PolynomialF1 polyToMultiply = addNewPolynomialF1(scanner);

					int degreeCurrent = F1.getVector()[0];
					int degreeToMultiply = polyToMultiply.getVector()[0];

					int newDegree = degreeCurrent + degreeToMultiply;

					PolynomialF1 totalPoly = new PolynomialF1(newDegree);

					totalPoly.muliplyPolinomial(polyToMultiply, F1);

					System.out.println();
					System.out.println("Multiplication terminada: " + totalPoly.rebuildToString());
					F1 = totalPoly;
					scanner.nextLine();

					break;
				}

				default:
					System.out.println("Opción incorrecta -.-");
				}
			}

		} while (userInput != 0);

		scanner.close();
	}

	public static PolynomialF1 addNewPolynomialF1(Scanner scanner) {

		System.out.println("Ingrese el polinomio: ");
		String strinPoly = scanner.nextLine().toLowerCase();

		String[] unsorted = Utils.getPoliFromString(strinPoly);

		String[] sorted = Utils.SortByExpDesc(unsorted);

		System.out.println();
		System.out.println("Sorted: ");
		System.out.println(String.join(",", sorted));

		PolynomialF1 F1 = new PolynomialF1(Integer.parseInt(sorted[1]));
		F1.StringToPolyF1(sorted);

		System.out.println("El DU del polinormio es: " + F1.getDU());
		System.out.println("Polinomio Forma 1: " + F1.showinF1());

		return F1;

	}

}
