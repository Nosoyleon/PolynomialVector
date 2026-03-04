package polynomialVector;

import java.util.Arrays;

public class PolynomialF2 {
	private int DU, vector[];

	public PolynomialF2(int length) {
		this.DU = length;
		this.vector = new int[this.DU + 1];
		this.vector[0] = length / 2;
	}

	public int getDU() {
		return this.DU;
	}

	public int[] getVector() {
		return this.vector;
	}

	public void setDU(int DU) {
		this.DU = DU;
	}

	public void setVector(int[] vector) {
		this.vector = vector;
	}

	public void vectorToPolyF2(int[] orderedVector) {

		int countTerms = 0;
		for (int i = 0; i < orderedVector.length; i = i + 2) {
			if (orderedVector[i] != 0) {
				int pos = i + 1;

				this.vector[pos] = orderedVector[i];
				this.vector[pos + 1] = orderedVector[i + 1];
				countTerms++;
			}
		}
		this.vector[0] = countTerms;
		this.DU = countTerms * 2;
		this.vector = Arrays.copyOfRange(this.vector, 0, this.DU + 1);
	}

	public String rebuildToString() {

		String poly = "";

		for (int i = 1; i <= this.DU; i = i + 2) {
			int exp = this.vector[i + 1];

			String sufix = exp == 0 ? "" : "x^" + Integer.toString(exp);
			sufix = exp == 1 ? "x" : sufix;

			String prefix = (this.vector[i] > 0 && i > 1) ? "+" : "";
			String cohe = (this.vector[i] != 1 && this.vector[i] != -1) ? Integer.toString(this.vector[i]) : "";

			poly += prefix + cohe + sufix;

		}

		return poly;
	}

	public String showForm() {
		String F2String = "";

		for (int i = 0; i < this.vector.length; i++) {
			F2String += "[" + this.vector[i] + "],";
		}

		return F2String;
	}

	public void insert(String monomialString) {
		int[] monomial = Utils.getPoliFromString(monomialString);
		int cohe = monomial[0];
		int exp = monomial[1];

		if (exp > this.vector[2]) {
			System.out.println();
			System.out.println("Termino con grado mayor al polinomio: " + this.vector[2]);
			String currentF2String = this.rebuildToString();
			String newPrefix = cohe > 0 ? "+" : "";
			currentF2String += newPrefix + monomialString;

			System.out.println("Construyendo nuevo polinomio: " + currentF2String);

			int[] unsorted = Utils.getPoliFromString(currentF2String);
			int[] sorted = Utils.SortByExpDesc(unsorted);

			this.DU = sorted.length;
			this.vector = new int[this.DU + 1];
			this.vector[0] = sorted.length / 2;

			this.vectorToPolyF2(sorted);
			System.out.println();
			System.out.println("Nuevo polinomio construido.");

		} else {
			boolean found = false;
			for (int i = 2; i <= this.DU; i = i + 2) {
				if (this.vector[i] == exp) {
					this.vector[i - 1] = cohe;
					this.vector[i] = exp;
					found = true;
				}
			}
			if (!found) {
				String currentF2String = this.rebuildToString();
				String newPrefix = cohe > 0 ? "+" : "";
				currentF2String += newPrefix + monomialString;
				int[] unsorted = Utils.getPoliFromString(currentF2String);
				int[] sorted = Utils.SortByExpDesc(unsorted);

				this.DU = sorted.length;
				this.vector = new int[this.DU + 1];
				this.vector[0] = sorted.length / 2;
				this.vectorToPolyF2(sorted);
			}

			System.out.println();
			System.out.println("Ternimo Agregado: " + monomialString);
		}

	}

	public void remove(String monomialString) {
		int[] monomial = Utils.getPoliFromString(monomialString);
		int cohe = monomial[0];
		int exp = monomial[1];

		if (exp > this.vector[2]) {
			System.out.println();
			System.out.println("No hay un termino con el grado: " + exp);
		} else {
			boolean found = false;
			for (int i = 2; i <= this.DU; i = i + 2) {
				if (this.vector[i] == exp && this.vector[i - 1] == cohe) {

					this.vector[i] = 0;
					this.vector[i - 1] = 0;

					System.out.println();
					System.out.println("Ternimo Eliminado: " + monomialString);
					System.out.println("Ajustando...");
					adjustVector();
					found = true;
				}
			}
			if (!found) {
				System.out.println("No se encontró el termino: " + monomialString);
				System.out.println();
			}

		}

	}

	private void adjustVector() {
		int countTerms = 0;

		int j = 1;
		int[] newVector = new int[this.vector.length];

		for (int i = 1; i < this.DU; i = i + 2) {
			if (this.vector[i] != 0) {
				newVector[j] = this.vector[i];
				newVector[j + 1] = this.vector[i + 1];
				countTerms += 1;
				j=j+2;
			}
		}

		newVector[0] = countTerms;
		this.DU = countTerms * 2;
		this.vector = Arrays.copyOfRange(newVector, 0, this.DU + 1);
	}
}
