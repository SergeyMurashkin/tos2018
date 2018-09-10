package net.thumbtack.school.introduction;

import java.util.Arrays;


public class FirstSteps {

  public int sum( int x, int y){
    int sum;
    sum = x + y;
    return sum;
  }

  public int mul( int x, int y){
    int mul;
    mul = x * y;
    return (mul);

  }


  public int div ( int x, int y){
    int div;
    div = x / y;
    return div;
  }

  public int mod ( int x, int y){
    int mod;
    mod = x%y;
    return (mod);
  }

 /* public boolean isEqual (int x, int y) {
    Integer a = new Integer(x);
    Integer b = new Integer(y);
    boolean isEqual;
    isEqual = a.equals(b);
    return isEqual;
  }*/

 public boolean isEqual (int x, int y) {
   if (x == y) {
     return true;
   } else {
     return false;
   }
 }


  public boolean isGreater (int x, int y) {
    if (x>y) {
      return true;
    } else {
      return false;
    }
  }


  public boolean isInsideRect(int xL, int yT, int xR, int yB, int x, int y) {
    if ( xL<=x && x<=xR && yT<=y && y<=yB ){
      return true;
    } else {
      return false;
    }
  }

  public int sum(int[] array) {
    if (array.length==0) {
      return 0;
    }else {
      int sumArray = 0;
      for (int i = 0; i < array.length; i++) {
        sumArray = sumArray + array[i];
      }
      return sumArray;
    }
  }

  public int mul(int[] array) {
    if (array.length==0) {
      return 0;
    }else {
      int mulArray = 1;
      for (int i = 0; i < array.length; i++) {
        mulArray = mulArray *array[i];
      }
      return mulArray;
    }
  }

  public  int min(int[] array) {
    if (array.length==0) {
      return Integer.MAX_VALUE;
    } else {
      Arrays.sort(array);
      int min = array[0];
      return min;
    }
  }

  public  int max(int[] array) {
    if (array.length==0) {
      return Integer.MIN_VALUE;
    } else {
      Arrays.sort(array);
      int max = array[array.length-1];
      return max;
    }
  }

  public double average(int[] array) {
    if (array.length==0) {
      return 0;
    }else {
      int sumArray = 0;
      for (int i = 0; i < array.length; i++) {
        sumArray = sumArray + array[i];
      }
      double average = (double) sumArray/array.length;
      return average;
    }
  }

/*
  public boolean isSortedDescendant(int[] array) {
    int[] arraySort = array.clone();
    Arrays.sort(arraySort);
    for(int i=0; i<arraySort.length/2; i++){
      int temp = arraySort[i];
      arraySort[i] = arraySort[arraySort.length -i -1];
      arraySort[arraySort.length -i -1] = temp;
    }
    boolean isSorted;
    isSorted = Arrays.equals(array,arraySort);

    return isSorted;
  }*/

public boolean isSortedDescendant (int[] array) {

  boolean isSorted = true;
  if (array.length==0){
    isSorted = true;
  } else {
    for (int i = 1; i < array.length; i++) {
      if (array[i] >= array[i - 1]) {
        isSorted = false;
        break;
      }
    }
  }
  return isSorted;
}
  public void cube(int[]array) {
  for (int i=0; i<array.length; i++) {
    array[i]= array[i]*array[i]*array[i];
  }
  }

  public boolean find(int[]array, int value) {
  for (int i: array){
    if (i==value) {
      return true;
    }
  }
  return false;
  }

  public void reverse(int[]array) {
    for(int i=0; i<array.length/2; i++){
      int temp = array[i];
      array[i] = array[array.length -i -1];
      array[array.length -i -1] = temp;
    }
  }


  public boolean isPalindrome(int[] array) {
    boolean isPalindrome = true;
    for (int i = 0; i < array.length ; i++) {
      if (array[i] != array[array.length - i - 1]) {
        isPalindrome = false;
        break;
      }
    }
  return isPalindrome;
  }

  public int sum(int[][] matrix) {
    int sum = 0;
    if (matrix.length == 0) {
      return sum;
    } else {
      for (int i=0; i < matrix.length; i++) {
        sum += sum(matrix[i]);
      }
      return sum;
    }
  }


  public int max(int[][] matrix){
    int max = Integer.MIN_VALUE;
    if (matrix.length == 0) {
      return max;
    } else {
      for (int i=0; i < matrix.length; i++) {
        if (max < max(matrix[i])) {
          max = max(matrix[i]);
        }
      }
      return max;
    }
  }


  public int diagonalMax(int[][] matrix) {
    int max = Integer.MIN_VALUE;
    if (matrix.length == 0) {
      return max;
    } else {
      for (int i=0; i<matrix.length; i++) {
        if (max < matrix[i][i]) {
          max = matrix[i][i];
        }
      }
      for (int j=matrix.length-1; j>0; j--) {
        if (max < matrix[j][j]) {
          max = matrix[j][j];
        }
      }
      return max;
    }
  }

  public boolean isSortedDescendant(int[][] matrix) {
  boolean isSorted = true;
  for (int i=0; i<matrix.length; i++) {
    if (isSorted!=isSortedDescendant(matrix[i])){
      isSorted=false;
      break;
    }
  }
  return isSorted;
  }


}



