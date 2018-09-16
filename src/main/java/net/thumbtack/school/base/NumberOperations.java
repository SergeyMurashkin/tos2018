package net.thumbtack.school.base;

import java.math.BigDecimal;
import java.math.BigInteger;

public class NumberOperations {

  //  Ищет в массиве array первый элемент, значение которого равно value.
  // Если такое значение найдено, возвращает его позицию в массиве array, в противном случае возвращает null.
  public static Integer find(int[] array, int value){
    for (int i=0; i<array.length; i++){
      if(array[i] == value) {
        return new Integer(i);
      }
    }
    return null;
  }



// Ищет в массиве array первый элемент, значение которого по модулю не отличается от value более чем на eps.
// Если такое значение найдено, возвращает его позицию в массиве array, в противном случае возвращает null.
  public static Integer find(double[] array, double value, double eps){
    for (int i=0; i<array.length; i++){
      if (Math.abs(Math.abs(array[i])-value)<=eps){
        return new Integer(i);
      }
    }
    return null;
  }

  //Вычисляет плотность вещества по формуле weight / volume.
  // Если получившееся значение не превышает max и не меньше min, возвращает полученное значение,
  // в противном случае возвращает null.
  public static Double calculateDensity(double weight, double volume, double min, double max){
    double density = weight/volume;
    if (density<=max && density>=min){
      return new Double(density);
    }else{
      return null;
    }
  }

  // Ищет в массиве array первый элемент, значение которого равно value.
  // Если такое значение найдено, возвращает его позицию в массиве array, в противном случае возвращает null.
  public static Integer find(BigInteger[] array, BigInteger value){
    for (int i=0; i<array.length; i++){
      if(array[i].compareTo(value)==0) {
        return new Integer(i);
      }
    }
    return null;
  }

  // Вычисляет плотность вещества по формуле weight / volume.
  // Если получившееся значение не превышает max и не меньше min, возвращает полученное значение,
  // в противном случае возвращает null.
  public static BigDecimal calculateDensity(BigDecimal weight, BigDecimal volume, BigDecimal min, BigDecimal max){
    BigDecimal density = weight.divide(volume);
    if (density.compareTo(min)>=0&&density.compareTo(max)<=0) {
      return density;
    }
     return null;
  }



}
