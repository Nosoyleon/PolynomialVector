package polynomialVector;

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

		for (int i = 1; i < orderedVector.length; i = i + 2) {
			if (orderedVector[i] != 0) {
				int pos = this.DU - orderedVector[i];

				this.vector[pos] = orderedVector[i - 1];
			}
		}
	}


}
