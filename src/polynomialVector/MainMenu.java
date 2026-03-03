package polynomialVector;

import java.util.Scanner;

public class MainMenu {

	static String DIVIDER = "###########";

	public Enums.mainMenu initialMenu(Scanner scanner) {
		System.out.println("(1) Para ingresar Polinomio.");
		System.out.println("(0) Para Salir. :'(");
		int userInput = scanner.nextInt();
		scanner.nextLine();

		return Enums.mainMenu.fromInt(userInput);
	}

	public Enums.polyf1options PolynomialF1Options(Scanner scanner, String StringPoly, String StringF1, int DU) {
		System.out.println();
		System.out.println(DIVIDER);
		System.out.println("Que quieres hacer con el Polinomio?");
		System.out.println(DIVIDER);
		System.out.println("Polimonio Actual: " + StringPoly);
		System.out.println("Polimonio Forma1: " + StringF1);
		System.out.println("Datos útiles: " + DU);
		System.out.println(DIVIDER);
		System.out.println("(1) Reemplazar polinomio");
		System.out.println("(2) Ingresar un termino");
		System.out.println("(3) Remover un termino");
		System.out.println("(4) Evaluar con respecto a X");
		System.out.println("(5) Sumar polinomio");
		System.out.println("(6) Multiplicar polinomio");
		System.out.println("(0) Para Salir. :'(");

		int userInput = scanner.nextInt();
		scanner.nextLine();

		return Enums.polyf1options.fromInt(userInput);

	}
}