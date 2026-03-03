package polynomialVector;

public class Utils {

	/**
	 * @return
	 */
	public static String[] getPoliFromString(String userInput) {

		char charVector[] = userInput.toCharArray();
		String stringVector[] = new String[charVector.length < 2 ? 2 : charVector.length];

		System.out.println("Initial Char polynomial: ");
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
					stringVector[posStringVector] = currentString;
					stringVector[posStringVector + 1] = "0"; // Exponente
				} else if (charVector[i + 1] == '+' || charVector[i + 1] == '-') { // X^0
					stringVector[posStringVector] = currentString;
					stringVector[posStringVector + 1] = "0";
					currentString = "";
					posStringVector += 2;

				}

			} else if (charVector[i] == '-') {
				currentString += charVector[i];
			} else if (charVector[i] == '^') {
				stringVector[posStringVector] = currentString;
				stringVector[posStringVector] = Character.toString(charVector[i + 1]);
				currentString = "";
				posStringVector += 2;
				i++;
			} else if (charVector[i] != '+') {

				// X^1
				if (lastCharacter) {
					stringVector[posStringVector] = currentString;
					stringVector[posStringVector + 1] = "1";
					posStringVector += 2;
				} else if (charVector[i + 1] != '^') {
					stringVector[posStringVector] = !currentString.isBlank() ? currentString : "1";
					currentString = "";
					stringVector[posStringVector + 1] = "1";
					posStringVector += 2;
				} else {
					// X^n
					stringVector[posStringVector] = currentString;
					currentString = "";
					stringVector[posStringVector + 1] = Character.toString(charVector[i + 2]);
					posStringVector += 2;
					i += 2;
				}
			}

		}

		return stringVector;
	}

	public static String[] SortByExpDesc(String unSorted[]) {

		String[] sorted = unSorted;

		for (int i = 1; i < sorted.length; i = i + 2) {
			if (sorted[i] != null) {

				for (int j = 1; j < sorted.length; j = j + 2) {

					if (sorted[j] != null && sorted[j + 2] != null) {

						if (Integer.parseInt(sorted[j]) < Integer.parseInt(sorted[j + 2])) {

							String tempCohe = sorted[j - 1];
							String tempExp = sorted[j];

							sorted[j - 1] = sorted[j + 1];
							sorted[j] = sorted[j + 2];

							sorted[j + 1] = tempCohe;
							sorted[j + 2] = tempExp;

						}

					}
				}
			}
		}

		return sorted;
	}

}
