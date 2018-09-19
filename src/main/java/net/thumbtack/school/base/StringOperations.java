package net.thumbtack.school.base;

public class StringOperations {

    public static int getSummaryLength(String[] strings) {
	int len = 0;
	for (String elem : strings) {
	    len += elem.length();
	}
	return len;
    }

    public static String getFirstAndLastLetterString(String string) {
	return String.valueOf(string.charAt(0)) + String.valueOf(string.charAt(string.length() - 1));
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
	return string1.charAt(index) == string2.charAt(index);
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
	return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
	return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
	return string1.indexOf(str) == string2.indexOf(str);
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
	return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    public static boolean isEqual(String string1, String string2) {
	return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2) {
	return string1.equalsIgnoreCase(string2);
    }

    public static boolean isLess(String string1, String string2) {
	return string1.compareTo(string2) < 0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2) {
	return string1.compareToIgnoreCase(string2) < 0;
    }

    public static String concat(String string1, String string2) {
	return string1.concat(string2);
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {
	return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix) {
	return string1.endsWith(suffix) && string2.endsWith(suffix);
    }

    public static String getCommonPrefix(String string1, String string2) {
	int minLength = Math.min(string1.length(), string2.length());
	for (int i = 0; i < minLength; i++) {
	    if (string1.charAt(i) != string2.charAt(i)) {
		return string1.substring(0, i);
	    }
	}
	return string1.substring(0, minLength);
    }

    public static String reverse(String string) {
	StringBuilder sb = new StringBuilder(string);
	sb.reverse();
	return sb.toString();
    }

    public static boolean isPalindrome(String string) {
	String reversString = StringOperations.reverse(string);
	return StringOperations.isEqual(string, reversString);
    }

    public static boolean isPalindromeIgnoreCase(String string) {
	String reversString = StringOperations.reverse(string);
	return string.equalsIgnoreCase(reversString);
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
	String longestPalindromeIgnoreCase = "";
	for (String elem : strings) {
	    if (StringOperations.isPalindromeIgnoreCase(elem)) {
		if (elem.length() > longestPalindromeIgnoreCase.length()) {
		    longestPalindromeIgnoreCase = elem;
		}
	    }
	}
	return longestPalindromeIgnoreCase;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
	if (string1.length() < index + length || string2.length() < index + length) {
	    return false;
	}
	String string3 = string1.substring(index, index + length);
	String string4 = string2.substring(index, index + length);
	return string3.equals(string4);
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1
	    , String string2, char replaceInStr2, char replaceByInStr2) {
	String string3 = string1.replace(replaceInStr1, replaceByInStr1);
	String string4 = string2.replace(replaceInStr2, replaceByInStr2);
	return string3.equals(string4);
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1
	    , String string2, String replaceInStr2, String replaceByInStr2) {
	String string3 = string1.replaceAll(replaceInStr1, replaceByInStr1);
	String string4 = string2.replaceAll(replaceInStr2, replaceByInStr2);
	return string3.equals(string4);
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
	return StringOperations.isPalindromeIgnoreCase(string.replaceAll(" ", ""));
    }

    public static boolean isEqualAfterTrimming(String string1, String string2) {
	return string1.trim().equals(string2.trim());
    }

    public static String makeCsvStringFromInts(int[] array) {
	StringBuilder csv = new StringBuilder();
	for (int elem : array) {
	    csv.append(elem);
	    csv.append(',');
	}
	if (csv.length() > 0) {
	    csv.deleteCharAt(csv.length() - 1);
	}
	return csv.toString();
    }

    public static String makeCsvStringFromDoubles(double[] array) {
	StringBuilder csv = new StringBuilder();
	for (double elem : array) {
	    csv.append((double) (Math.round(elem * 100)) / 100);
	    if (csv.length() - csv.lastIndexOf(".") < 3) {
		csv.append('0');
	    }
	    csv.append(',');
	}
	if (csv.length() > 0) {
	    csv.deleteCharAt(csv.length() - 1);
	}
	return csv.toString();
    }

    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
	StringBuilder csv = new StringBuilder();
	for (int elem : array) {
	    csv.append(elem);
	    csv.append(',');
	}
	if (csv.length() > 0) {
	    csv.deleteCharAt(csv.length() - 1);
	}
	return csv;
    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
	StringBuilder csv = new StringBuilder();
	for (double elem : array) {
	    csv.append((double) (Math.round(elem * 100)) / 100);
	    if (csv.length() - csv.lastIndexOf(".") < 3) {
		csv.append('0');
	    }
	    csv.append(',');
	}
	if (csv.length() > 0) {
	    csv.deleteCharAt(csv.length() - 1);
	}
	return csv;
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {
	StringBuilder sb = new StringBuilder(string);
	for (int i = positions.length - 1; i >= 0; i--) {
	    sb.deleteCharAt(positions[i]);
	}
	return sb;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
	StringBuilder sb = new StringBuilder(string);
	for (int i = characters.length - 1; i >= 0; i--) {
	    sb.insert(positions[i], characters[i]);
	}
	return sb;
    }

}