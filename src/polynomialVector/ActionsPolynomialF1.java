package polynomialVector;

import java.util.Scanner;

import Enums.polyf1options;

public class ActionsPolynomialF1 {
	
	private Scanner scanner;
	private PolynomialF1 F1;
	
	
	public ActionsPolynomialF1(Scanner scanner, PolynomialF1 F1) {
		this.scanner = scanner;
		this.F1 = F1;
	}

	public PolynomialF1 mainPolyF1() {

		MainMenu menu = new MainMenu(scanner);
		Enums.polyf1options polyF1MenuResponse = polyf1options.EXIT;

		do {
			polyF1MenuResponse = menu.PolynomialF1Options(F1.rebuildToString(), F1.showinF1(), F1.getDU());

			switch (polyF1MenuResponse) {
			case EXIT: {
				break;
			}

			case REPLACE_POLY: { // Replace Polynomial F1
				F1 = craeteNewolynomialF1(scanner);
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

				PolynomialF1 polyToSum = craeteNewolynomialF1(scanner);

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

				PolynomialF1 polyToMultiply = craeteNewolynomialF1(scanner);

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

		} while (polyF1MenuResponse != polyf1options.EXIT);

		return F1;
	}

	public static PolynomialF1 craeteNewolynomialF1(Scanner scanner) {

		System.out.println("Ingrese el polinomio: ");
		String strinPoly = scanner.nextLine().toLowerCase();

		int[] unsorted = Utils.getPoliFromString(strinPoly);

		int[] sorted = Utils.SortByExpDesc(unsorted);

		System.out.println();
		System.out.println("-- Sorted --");

		PolynomialF1 F1 = new PolynomialF1(sorted[1]);
		F1.vectorToPolyF1(sorted);

		System.out.println("El DU del polinormio es: " + F1.getDU());
		System.out.println("Polinomio Forma 1: " + F1.showinF1());

		return F1;

	}

}
