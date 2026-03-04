package polynomialVector;

import java.util.Scanner;

import Enums.Forms;

public class MainMenu {

	static String DIVIDER = "----------------------";
	private final Scanner scanner;

	public MainMenu(Scanner scanner) {
		this.scanner = scanner;
	}

	public Enums.mainMenu initialMenu(String PolyF1String, String PolyF2String, String PolyF3String) {
		System.out.println("Actual Forma1: " + PolyF1String);
		System.out.println("Actual Forma2: " + PolyF2String);
		System.out.println("Actual Forma3: " + PolyF3String);
		System.out.println(DIVIDER);
		System.out.println("(1) Menú Polinomio FORMA 1.");
		System.out.println("(2) Menú Polinomio FORMA 2.");
		System.out.println("(3) Menú Polinomio FORMA 3.");
		System.out.println("(0) Para Salir. :'(");
		int userInput = scanner.nextInt();
		scanner.nextLine();

		return Enums.mainMenu.fromInt(userInput);
	}

	public Enums.polyMenuOptions PolynomialOptions(String StringPoly, String StringForm, Forms Form) {
		System.out.println();
		System.out.println(DIVIDER);

		if (!StringPoly.isBlank()) {
			System.out.println("Que quieres hacer con el Polinomio "+ Form +"?");
			System.out.println(DIVIDER);
			System.out.println("Polimonio Actual: " + StringPoly);
			System.out.println("Forma: " + Form);
			System.out.println("Polimonio: " + StringForm);
			System.out.println(DIVIDER);
			System.out.println("(1) Reemplazar polinomio");
			System.out.println("(2) Ingresar un termino");
			System.out.println("(3) Remover un termino");
			System.out.println("(4) Evaluar con respecto a X");
			System.out.println("(5) Sumar polinomio");
			System.out.println("(6) Multiplicar polinomio");
			System.out.println("(0) <--- Menú anterior.");

			int userInput = scanner.nextInt();
			scanner.nextLine();

			return Enums.polyMenuOptions.fromInt(userInput);
		} else {
			System.out.println("No hay polinomio incial");
			return Enums.polyMenuOptions.REPLACE_POLY;
		}

	}
}