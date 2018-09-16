package net.thumbtack.school.base;

public class StringOperations {


  //Возвращает суммарную длину строк, заданных массивом strings.
  public static int getSummaryLength (String[]strings){

    int len = 0;
    for (String elem: strings) {
      len += elem.length();
    }
    return len;
  }

  //Возвращает двухсимвольную строку, состоящую из начального и конечного символов заданной строки.
  public static String getFirstAndLastLetterString(String string){
    String firstLetter = String.valueOf( string.charAt(0) );
    String lastLetter = String.valueOf( string.charAt(string.length()-1) );
    String firstAndLastLetter = firstLetter + lastLetter;
    return firstAndLastLetter;
  }

  //Возвращает true, если обе строки в позиции index содержат один и тот же символ, иначе false.
  public static boolean isSameCharAtPosition(String string1, String string2, int index){
    char ch1 = string1.charAt(index);
    char ch2 = string2.charAt(index);
    boolean isSameChar = false;
    if (ch1==ch2) {
      isSameChar = true;
    }
    return isSameChar;
  }

  // Возвращает true, если в обеих строках первый встреченный символ character находитсяв одной и той же позиции.
  // Просмотр строк ведется от начала.
  public static boolean isSameFirstCharPosition(String string1, String string2, char character){
    int indexCharInString1 = string1.indexOf(character);
    int indexCharInString2 = string2.indexOf(character);
    return indexCharInString1==indexCharInString2 ;
  }

  // Возвращает true, если в обеих строках первый встреченный символ character находится в одной и той же позиции.
  // Просмотр строк ведется от конца.
  public static boolean isSameLastCharPosition(String string1, String string2, char character){
    int indexCharInString1 = string1.lastIndexOf(character);
    int indexCharInString2 = string2.lastIndexOf(character);
    return indexCharInString1==indexCharInString2;
  }

// Возвращает true, если в обеих строках первая встреченная подстрока str начинается в одной и той же позиции.
// Просмотр строк ведется от начала.
  public static boolean isSameFirstStringPosition(String string1, String string2, String str){
    int indexStrInString1 = string1.indexOf(str);
    int indexStrInString2 = string2.indexOf(str);
    return indexStrInString1==indexStrInString2;
  }

// Возвращает true, если в обеих строках первая встреченная подстрока str начинается в одной и той же позиции.
// Просмотр строк ведется от конца.
  public static boolean isSameLastStringPosition(String string1, String string2, String str){
    int indexStrInString1 = string1.lastIndexOf(str);
    int indexStrInString2 = string2.lastIndexOf(str);
    return indexStrInString1==indexStrInString2;
  }

// Возвращает true, если строки равны.
  public static boolean isEqual(String string1, String string2){
    return string1.equals(string2);
  }
/*

  public static boolean isEqual(String string1, String string2){
    if (string1.compareTo(string2)==0) {
      return true;
    }
    return false;
  }
*/

  //  Возвращает true, если строки равны без учета регистра.
  // (например, строки “abc” и “aBC” в этом смысле равны)
  public static boolean isEqualIgnoreCase(String string1, String string2){
    return string1.equalsIgnoreCase(string2);
  }

  //Возвращает true, если строка string1 меньше строки string2.
  public static boolean isLess(String string1, String string2){
    if (string1.compareTo(string2) <0) {
      return true;
    }
    return false;
  }

  //  Возвращает true, если строка string1 меньше строки string2 без учета регистра.
  // (например, строка “abc” меньше строки “ABCd” в этом смысле)
  public static boolean isLessIgnoreCase(String string1, String string2) {
    if (string1.compareToIgnoreCase(string2) <0) {
      return true;
    }
    return false;
  }

//  Возвращает строку, полученную путем сцепления двух строк.
  public static String concat(String string1, String string2){
    return string1.concat(string2);
  }

 // Возвращает true, если обе строки string1 и string2 начинаются с одной и той же подстроки prefix.
  public static boolean isSamePrefix(String string1, String string2, String prefix){
    if ( string1.startsWith(prefix) && string2.startsWith(prefix)){
      return true;
    }
    return false;
  }

//  Возвращает true, если обе строки string1 и string2 заканчиваются одной и той же подстрокой suffix.
  public static boolean isSameSuffix(String string1, String string2, String suffix){
    if ( string1.endsWith(suffix) && string2.endsWith(suffix)){
      return true;
    }
    return false;
  }

  //  Возвращает самое длинное общее “начало” двух строк.
  //  Если у строк нет общего начала, возвращает пустую строку.
  public static String getCommonPrefix(String string1, String string2){
    int minLength = Math.min(string1.length(), string2.length());
    for (int i = 0; i < minLength; i++) {
      if (string1.charAt(i) != string2.charAt(i)) {
        return string1.substring(0, i);
      }
    }
    return string1.substring(0, minLength);
  }


  //  Возвращает перевернутую строку.
  public static String reverse(String string) {
    StringBuilder sb = new StringBuilder(string);
    sb.reverse();
    return sb.toString();
  }
  /*
  public static String reverse(String string){
    char[] array = string.toCharArray();
    for(int i=0; i<array.length/2; i++){
      char swap = array[i];
      array[i] = array[array.length -i -1];
      array[array.length -i -1] = swap;
    }
    return String.copyValueOf(array);
  }
*/


  //  Возвращает true, если строка является палиндромом, то есть читается слева направо так же, как и справа налево.

/*
  public static boolean isPalindrome(String string) {
    char[] array = string.toCharArray();
    boolean isPalindrome = true;
    for (int i = 0; i < array.length; i++) {
      if (array[i] != array[array.length - i - 1]) {
        isPalindrome = false;
        break;
      }
    }
    return isPalindrome;
  }
  */

   /*public static boolean isPalindrome(String string) {
  boolean isPalindrome = true;
  for (int i = 0; i < string.length(); i++) {
    if (string.charAt(i) != string.charAt(string.length() - i - 1)) {
      isPalindrome = false;
      break;
    }
  }
  return isPalindrome;*/

  public static boolean isPalindrome(String string) {
    String reversString = StringOperations.reverse(string);
    return StringOperations.isEqual(string,reversString);
  }

  //  Возвращает true, если строка является палиндромом, то есть читается слева направо так же,
  //   как и справа налево, без учета регистра.
  public static boolean isPalindromeIgnoreCase(String string){
    String reversString = StringOperations.reverse(string);
    return string.equalsIgnoreCase(reversString);
  }

  //  Возвращает самый длинный палиндром (без учета регистра) из массива заданных строк.
  //  Если в массиве нет палиндромов, возвращает пустую строку.
  public static String getLongestPalindromeIgnoreCase(String[] strings){
    String longestPalindromeIgnoreCase = "";
    for (String elem: strings) {
      if (StringOperations.isPalindromeIgnoreCase(elem)) {
        if (elem.length()>longestPalindromeIgnoreCase.length()) {
          longestPalindromeIgnoreCase=elem;
        }
      }
    }
    return longestPalindromeIgnoreCase;
  }

  // Возвращает true, если обе строки содержат один и тот же фрагмент длиной length, начиная с позиции index.
  public static boolean hasSameSubstring(String string1, String string2, int index, int length){
  if (string1.length()<index+length||string2.length()<index+length){
     return false;
   }
    String string3 = string1.substring(index,index+length);
    String string4 = string2.substring(index,index+length);
    return string3.equals(string4);
  }
  /*public static boolean hasSameSubstring(String string1, String string2, int index, int length){
   if (string1.length()<index+length||string2.length()<index+length){
     return false;
   }
    return string2.matches("(.*)"+string1.substring(index,index+length)+"(.*)");
  }*/

  //Возвращает true, если после замены в string1 всех вхождений replaceInStr1 на replaceByInStr1
  // и замены в string2 всех вхождений replaceInStr2 на replaceByInStr2 полученные строки равны.
  public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1, String string2, char replaceInStr2, char replaceByInStr2){
    String string3 = string1.replace(replaceInStr1,replaceByInStr1) ;
    String string4 = string2.replace(replaceInStr2,replaceByInStr2) ;
    return string3.equals(string4);
  }

  // Возвращает true, если после замены в string1 всех вхождений строки replceInStr1 на replaceByInStr1
  //  и замены в string2 всех вхождений replceInStr2 на replaceByInStr2 полученные строки равны.
  public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1, String string2, String replaceInStr2, String replaceByInStr2){
    String string3 = string1.replaceAll(replaceInStr1,replaceByInStr1);
    String string4 = string2.replaceAll(replaceInStr2,replaceByInStr2);
    return string3.equals(string4);
  }

//  Возвращает true, если строка после выбрасывания из нее всех пробелов является палиндромом, без учета регистра.
  public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string){
    return StringOperations.isPalindromeIgnoreCase(string.replaceAll(" ",""));
  }

//  Возвращает true, если две строки равны, если не принимать во внимание все пробелы в начале и конце каждой строки.
  public static boolean isEqualAfterTrimming(String string1, String string2){
    return string1.trim().equals(string2.trim());
  }

  //  Для заданного массива целых чисел создает текстовую строку, в которой числа разделены знаком “запятая”
  // (т.н. формат CSV - comma separated values).Для пустого массива возвращается пустая строка.
  public static String makeCsvStringFromInts(int[] array){
    StringBuilder csv = new StringBuilder();
    for (int  elem: array) {
      csv.append(elem);
      csv.append(',');
    }
    if (csv.length()>0) {
      csv.deleteCharAt(csv.length()-1);
    }
    return csv.toString();
  }


//  Для заданного массива вещественных чисел создает текстовую строку, в которой числа разделены знаком “запятая”,
// причем каждое число записывается с двумя знаками после точки. Для пустого массива возвращается пустая строка.
  public static String makeCsvStringFromDoubles(double[] array) {
    StringBuilder csv = new StringBuilder();
    for (double elem : array) {
      csv.append((double)(Math.round(elem*100))/100);
      if (csv.length()-csv.lastIndexOf(".")<3){
        csv.append('0');
      }
      csv.append(',');
      }
    if (csv.length() > 0) {
      csv.deleteCharAt(csv.length() - 1);
    }
    return csv.toString();
  }






  //  То же, что и в упражнении 25, но возвращает StringBuilder.
  public static StringBuilder makeCsvStringBuilderFromInts(int[] array){
    StringBuilder csv = new StringBuilder();
    for (int  elem: array) {
      csv.append(elem);
      csv.append(',');
    }
    if (csv.length()>0) {
      csv.deleteCharAt(csv.length()-1);
    }
    return csv;
  }

  //  То же, что и в упражнении 26, но возвращает StringBuilder.
  public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array){
    StringBuilder csv = new StringBuilder();
    for (double elem : array) {
      csv.append((double)(Math.round(elem*100))/100);
      if (csv.length()-csv.lastIndexOf(".")<3){
        csv.append('0');
      }
      csv.append(',');
    }
    if (csv.length() > 0) {
      csv.deleteCharAt(csv.length() - 1);
    }
    return csv;
  }


// Удаляет из строки символы, номера которых заданы в массиве positions.
// Предполагается, что будут передаваться только допустимые номера, упорядоченные по возрастанию.
// Номера позиций для удаления указаны для исходной строки. Возвращает полученный в результате StringBuilder.
  public static StringBuilder removeCharacters(String string, int[] positions){
    StringBuilder sb = new StringBuilder(string);
    for (int i= positions.length-1; i>=0; i--) {
      sb.deleteCharAt(positions[i]);
    }
    return sb;
  }

  // Вставляет в строку символы. Массивы positions и characters имеют одинаковую длину.
  // В позицию positions[i] в исходной строке string вставляется символ characters[i].
  // Если в массиве positions один и тот же номер позиции повторяется несколько раз, это значит,
  // что в указанную позицию вставляется несколько символов, в том порядке, в котором они перечислены в массиве characters.
  // Предполагается, что будут передаваться только допустимые номера, упорядоченные по неубыванию.
  // Возвращает полученный в результате StringBuilder.
  public static StringBuilder insertCharacters(String string, int[] positions, char[] characters){
    StringBuilder sb = new StringBuilder(string);
    for (int i=characters.length-1; i>=0; i--){
      sb.insert(positions[i],characters[i]);
    }
    return sb;
  }








}