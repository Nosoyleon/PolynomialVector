package polynomialVector;

import java.util.Scanner;

import Enums.Forms;
import Enums.polyMenuOptions;

public class ActionsPolynomialF2 {
	private Scanner scanner;
	private PolynomialF2 F2;

	public ActionsPolynomialF2(Scanner scanner, PolynomialF2 F2) {
		this.scanner = scanner;
		this.F2 = F2;
	}

	public PolynomialF2 mainPolyF2() {

		MainMenu menu = new MainMenu(scanner);
		Enums.polyMenuOptions polyMenuResponse = polyMenuOptions.EXIT;

		do {
			polyMenuResponse = menu.PolynomialOptions(F2.rebuildToString(), F2.showForm(), Forms.FORMA_2);

			switch (polyMenuResponse) {
			case EXIT: {
				break;
			}

			case REPLACE_POLY: { // Replace Polynomial
				F2 = craeteNewpolynomialF2(scanner);
				scanner.nextLine();
				break;
			}

			case INSERT_MONO: { // Add Monomial
				System.out.println("Ingrese el Nuevo termino: ");
				String newMonomial = scanner.nextLine();
				F2.insert(newMonomial);
				scanner.nextLine();
				break;
			}

			case REMOVE_MONO: { // Remove Monomial
				System.out.println("Ingrese el termino a remover: ");
				String monomialToRemove = scanner.nextLine();
				F2.remove(monomialToRemove);
				scanner.nextLine();
				break;
			}

			case SOLVE: {
				System.out.println("Ingrese el valor de X: ");
				int value = scanner.nextInt();
				scanner.nextLine();

				int result = F2.solvePolynomial(value);
				System.out.println();
				System.out.println("Total: " + result);
				scanner.nextLine();
				break;

			}

			case SUMPOLY: {

				PolynomialF2 polyToSum = craeteNewpolynomialF2(scanner);

				int degreeCurrent = F2.getVector()[2];
				int degreeToSum = polyToSum.getVector()[2];

				int maxExp = degreeCurrent > degreeToSum ? degreeCurrent : degreeToSum;

				PolynomialF2 totalPoly = new PolynomialF2(maxExp * 2);

				totalPoly.sumPolynomial(F2, polyToSum);

				System.out.println();
				System.out.println("Polynomio Sumado: " + totalPoly.rebuildToString());
				F2 = totalPoly;
				scanner.nextLine();

				break;
			}

			case MULTIPLY: {

				PolynomialF2 polyToMultiply = craeteNewpolynomialF2(scanner);

				int degreeCurrent = F2.getVector()[2];
				int degreeToMultiply = polyToMultiply.getVector()[2];

				int newDegree = degreeCurrent + degreeToMultiply;

				PolynomialF2 totalPoly = new PolynomialF2(newDegree*2);

				totalPoly.muliplyPolinomial(polyToMultiply, F2, newDegree);

				System.out.println();
				System.out.println("Multiplication terminada: " + totalPoly.rebuildToString());
				F2 = totalPoly;
				scanner.nextLine();

				break;
			}

			default:
				System.out.println("Opción incorrecta -.-");
			}

		} while (polyMenuResponse != polyMenuOptions.EXIT);

		return F2;
	}

	public static PolynomialF2 craeteNewpolynomialF2(Scanner scanner) {

		System.out.println("Ingrese el polinomio: ");
		String strinPoly = scanner.nextLine().toLowerCase();

		int[] unsorted = Utils.getPoliFromString(strinPoly);

		int[] sorted = Utils.SortByExpDesc(unsorted);

		System.out.println();
		System.out.println("-- Sorted --");

		PolynomialF2 F2 = new PolynomialF2(sorted.length);
		F2.vectorToPolyF2(sorted);

		System.out.println("El DU del polinomio es: " + F2.getDU());

		return F2;

	}
}
