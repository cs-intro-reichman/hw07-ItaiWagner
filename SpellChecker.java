
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1, str.length());
	}

	// This function represents the levenshtien algorithm to find the
	// the distance between to given words
	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		if (word1.length() == 0) {
			return word2.length();
		} else if (word2.length() == 0) {
			return word1.length();
		} else if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		} else {
			int sum = 0;
			int opt1 = levenshtein(tail(word1), word2);
			int opt2 = levenshtein(word1, tail(word2));
			int opt3 = levenshtein(tail(word1), tail(word2));
			sum = 1 + (Math.min(opt1,(Math.min(opt2, opt3))));
			return sum;
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	// This function loops through the words in the given dictionary file
	// and returns the most resemblenced word according to a given treshold
	// of differences
	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = threshold;
		String mostCloseWord = word;
		int minlength = 0;
		for (String str : dictionary) {
			int tmpDist = levenshtein(word, str);
			if ((tmpDist <= min) && (str.length() >= minlength)) {
				min = tmpDist;
				mostCloseWord = str;
				minlength = str.length();
			}
		}
		return mostCloseWord;
	}

}
