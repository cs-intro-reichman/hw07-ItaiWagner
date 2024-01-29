

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		boolean exitsts = false;
		for (String str : dictionary) {
			if (word.equals(str)) {
				exitsts = true;
				break;
			}
		}
		return exitsts;
	} 

	public static void breakHashTag(String hashtag, String[] dictionary) {

		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
 
        int N = hashtag.length();
		hashtag = hashtag.toLowerCase();
        for (int i = 1; i <= N; i++) {
			String tmpStr = hashtag.substring(0, i);
			if (existInDictionary(tmpStr, dictionary)) {
				System.out.println(tmpStr);
				breakHashTag(hashtag.substring(i, N), dictionary);
				break;
			}
        }
    }

}
