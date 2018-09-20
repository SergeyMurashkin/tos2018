package net.thumbtack.school.introduction;

import java.util.Arrays;

public class FirstSteps {

    public int sum(int x, int y) {
        return x + y;
    }

    public int mul(int x, int y) {
        return x * y;
    }

    public int div(int x, int y) {
        return x / y;
    }

    public int mod(int x, int y) {
        return x % y;
    }

    public boolean isEqual(int x, int y) {
        return x == y;
    }

    public boolean isGreater(int x, int y) {
        return x > y;
    }

    public boolean isInsideRect(int xL,
                                int yT,
                                int xR,
                                int yB,
                                int x,
                                int y) {
        return xL <= x && x <= xR && yT <= y && y <= yB;
    }

    public int sum(int[] array) {
        int sumArray = 0;
        if (array.length != 0) {
            for (int elem : array) {
                sumArray += elem;
            }
        }
        return sumArray;
    }

    public int mul(int[] array) {
        int mulArray = 0;
        if (array.length != 0) {
            mulArray = 1;
            for (int elem : array) {
                mulArray *= elem;
            }
        }
        return mulArray;
    }

    public int min(int[] array) {
        if (array.length == 0) {
            return Integer.MAX_VALUE;
        } else {
            Arrays.sort(array);
            return array[0];
        }
    }

    public int max(int[] array) {
        if (array.length == 0) {
            return Integer.MIN_VALUE;
        } else {
            Arrays.sort(array);
            return array[array.length - 1];
        }
    }

    public double average(int[] array) {
        if (array.length == 0) {
            return 0;
        } else {
            return (double) sum(array) / array.length;
        }
    }

    public boolean isSortedDescendant(int[] array) {

        if (array.length == 0) {
            return true;
        }
        for (int i = 1; i < array.length; i++) {
            if (array[i] >= array[i - 1]) {
                return false;
            }
        }
        return true;
    }

    public void cube(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] * array[i] * array[i];
        }
    }

    public boolean find(int[] array, int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    public void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    public boolean isPalindrome(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != array[array.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int sum = 0;
        if (matrix.length == 0) {
            return sum;
        } else {
            for (int[] elem : matrix) {
                sum += sum(elem);
            }
            return sum;
        }
    }

    public int max(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        if (matrix.length == 0) {
            return max;
        } else {
            for (int[] elem : matrix) {
                if (max < max(elem)) {
                    max = max(elem);
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
            for (int i = 0; i < matrix.length; i++) {
                if (max < matrix[i][i]) {
                    max = matrix[i][i];
                }
            }
            for (int j = matrix.length - 1; j > 0; j--) {
                if (max < matrix[j][j]) {
                    max = matrix[j][j];
                }
            }
            return max;
        }
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for (int[] elem : matrix) {
            if (!isSortedDescendant(elem)) {
                return false;
            }
        }
        return true;
    }

}



