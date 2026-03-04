package polynomialVector;

public class PolynomialF1 {

	private int DU, vector[];

	public PolynomialF1(int degree) {
		this.DU = degree + 1;
		this.vector = new int[this.DU + 1];
		this.vector[0] = degree;
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

	public void vectorToPolyF1(int[] orderedVector) {
		

		for (int i = 1; i <= orderedVector.length; i = i + 2) {
			if(orderedVector[i -1] != 0) {
				int pos = this.DU - orderedVector[i];
				this.vector[pos] = orderedVector[i-1];
				
			}

		}
	}

	public void insert(String monomialString) {
		int[] monomial = Utils.getPoliFromString(monomialString);
		int cohe = monomial[0];
		int exp = monomial[1];

		if (exp > this.vector[0]) {
			System.out.println();
			System.out.println("Termino con grado mayor al polinomio: " + this.vector[0]);
			String currentF1String = this.rebuildToString();
			String newPrefix = cohe > 0 ? "+" : "";
			currentF1String += newPrefix + monomialString;

			System.out.println("Construyendo nuevo polinomio: " + currentF1String);

			int[] unsorted = Utils.getPoliFromString(currentF1String);
			int[] sorted = Utils.SortByExpDesc(unsorted);

			this.DU = sorted[1] + 1;
			this.vector = new int[this.DU + 1];
			this.vector[0] = sorted[1];

			this.vectorToPolyF1(sorted);
			System.out.println();
			System.out.println("Nuevo polinomio construido.");

		} else {
			int pos = this.DU - exp;
			this.vector[pos] = cohe;
			System.out.println();
			System.out.println("Ternimo Agregado: " + monomialString);
		}

	}

	public void remove(String monomialString) {
		int[] monomial = Utils.getPoliFromString(monomialString);
		int cohe = monomial[0];
		int exp = monomial[1];

		if (exp > this.vector[0]) {
			System.out.println();
			System.out.println("No hay un termino con el grado: " + exp);
		} else {
			int pos = this.DU - exp;
			if (this.vector[pos] != cohe) {
				System.out.println();
				System.out.println("No se encontró eltermino: " + monomialString);
			} else {
				this.vector[pos] = 0;
				System.out.println();
				System.out.println("Ternimo Eliminado: " + monomialString);
				System.out.println("Ajustando...");
				adjustVector();
			}
		}

	}

	public String rebuildToString() {

		String poly = "";

		for (int i = 1; i <= this.DU; i++) {
			int exp = this.DU - i;
			if (this.vector[i] != 0) {
				String sufix = exp == 0 ? "" : "x^" + Integer.toString(exp);
				sufix = exp == 1 ? "x" : sufix;

				String prefix = (this.vector[i] > 0 && i > 1) ? "+" : "";
				String cohe = (this.vector[i] != 1 && this.vector[i] != -1) ? Integer.toString(this.vector[i]) : "";

				poly += prefix + cohe + sufix;
			}
		}

		return poly;
	}

	public String showForm() {
		String F1String = "";

		for (int i = 0; i < this.vector.length; i++) {
			F1String += "[" + this.vector[i] + "],";
		}

		return F1String;
	}

	private void adjustVector() {
		int zeroCount = 0;
		int i = 1;
		boolean found = false;

		while (found == false && i < this.DU) {
			if (this.vector[i] == 0) {
				zeroCount += 1;
			} else {
				found = true;
			}
			i++;
		}

		int j = zeroCount + 1;

		while (j <= this.DU && zeroCount > 0) {
			this.vector[j - zeroCount] = this.vector[j];
			this.vector[j] = 0;
			j++;

		}
		this.vector[0] = vector[0] - zeroCount;
		this.DU = this.DU - zeroCount;
	}

	public int solvePolynomial(int value) {

		int result = 0;

		for (int i = 1; i <= this.DU; i++) {
			int cohe = this.vector[i];
			int exp = this.DU - i;
			int monoresult = cohe * (int) Math.pow(value, exp);
			result += monoresult;

		}

		return result;
	}

	public void sumPolynomial(PolynomialF1 polyA, PolynomialF1 polyB) {

		int posA = 1;
		int posB = 1;
		int posC = 1;

		while (posB <= polyB.getDU() && posA <= polyA.getDU()) {
			int expA = polyA.getDU() - posA;
			int expB = polyB.getDU() - posB;

			if (expA == expB) {
				this.vector[posC] = polyA.getVector()[posA] + polyB.getVector()[posB];
				posA++;
				posB++;
				posC++;
			} else if (expA > expB) {
				this.vector[posC] = polyA.getVector()[posA];
				posA++;
				posC++;
			} else {
				this.vector[posC] = polyB.getVector()[posB];
				posB++;
				posC++;
			}

		}
		adjustVector();
	}

	public void muliplyPolinomial(PolynomialF1 polyA, PolynomialF1 polyB) {

		for (int posA = 1; posA <= polyA.getDU(); posA++) {
			int[] auxVector = new int[this.getVector().length];
			auxVector[0] = this.getVector()[0];

			for (int posB = 1; posB <= polyB.getDU(); posB++) {
				int coheA = polyA.getVector()[posA];
				int expoA = polyA.getDU() - posA;

				int coheB = polyB.getVector()[posB];
				int expoB = polyB.getDU() - posB;

				int coheTotal = coheA * coheB;
				int expoTotal = expoA + expoB;

				int newPos = this.getDU() - expoTotal;

				auxVector[newPos] = coheTotal;

			}

			PolynomialF1 auxPoly = new PolynomialF1(this.getVector()[0]);
			auxPoly.setVector(auxVector);

			this.sumPolynomial(this, auxPoly);
		}

	}

}
