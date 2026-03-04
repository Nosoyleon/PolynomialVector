package polynomialVector;

import java.util.Arrays;

public class Utils {

	/**
	 * @return
	 */
	public static int[] getPoliFromString(String userInput) {

		char charVector[] = userInput.toCharArray();
		int intVector[] = new int[charVector.length < 2 ? 2 : charVector.length];

		for (int i = 0; i < charVector.length; i++) {
			System.out.print("[" + charVector[i] + "],");
		}

		// Concatenar coheficiones y exponentes
		int posStringVector = 0;
		String currentString = "";

		for (int i = 0; i < charVector.length; i++) {
			boolean lastCharacter = i == charVector.length - 1;

			// isDigit
			if (Character.isDigit(charVector[i])) {
				currentString += charVector[i];

				if (lastCharacter) {
					intVector[posStringVector] = Integer.parseInt(currentString);
					intVector[posStringVector + 1] = 0; // Exponente
				} else if (charVector[i + 1] == '+' || charVector[i + 1] == '-') { // X^0
					intVector[posStringVector] = Integer.parseInt(currentString);
					;
					intVector[posStringVector + 1] = 0;
					currentString = "";
					posStringVector += 2;

				}

			} else if (charVector[i] == '-') {
				currentString += charVector[i];
			} else if (charVector[i] == '^') {
				intVector[posStringVector] = Integer.parseInt(currentString);
				intVector[posStringVector] = Integer.parseInt(Character.toString(charVector[i + 1]));
				currentString = "";
				posStringVector += 2;
				i++;
			} else if (charVector[i] != '+') {

				// X^1
				if (lastCharacter) {
					intVector[posStringVector] = Integer.parseInt(currentString);
					;
					intVector[posStringVector + 1] = 1;
					posStringVector += 2;
				} else if (charVector[i + 1] != '^') {
					intVector[posStringVector] = !currentString.isBlank() ? Integer.parseInt(currentString) : 1;
					currentString = "";
					intVector[posStringVector + 1] = 1;
					posStringVector += 2;
				} else {
					// X^n
					intVector[posStringVector] = Integer.parseInt(currentString);
					currentString = "";
					intVector[posStringVector + 1] = Integer.parseInt(Character.toString(charVector[i + 2]));
					posStringVector += 2;
					i += 2;
				}
			}

		}

		return intVector;
	}

	public static int[] SortByExpDesc(int unSorted[]) {

		int[] sorted = Arrays.copyOf(unSorted, unSorted.length);

		if (unSorted.length <= 2)
			return sorted;

		for (int i = 1; i < sorted.length; i = i + 2) {

			for (int j = i + 2; j < sorted.length; j = j + 2) {

				if (sorted[i] < sorted[j]) {

					int tempCohe = sorted[i - 1];
					int tempExp = sorted[i];

					sorted[i - 1] = sorted[j - 1];
					sorted[i] = sorted[j];

					sorted[j - 1] = tempCohe;
					sorted[j] = tempExp;

				}

			}

		}

		return sorted;
	}

}
