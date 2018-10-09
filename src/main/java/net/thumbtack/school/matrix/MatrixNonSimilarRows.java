package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {

    private int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        this.matrix = matrix;
    }

    public List<int[]> getNonSimilarRows() {
        List<int[]> nonSimilarRows = new ArrayList<>();

        List<List<Integer>> rowListList = new ArrayList<>();
        List<Integer> rowList = new ArrayList<>();

        for (int[] row : matrix) {
            for (int elem : row) {
                rowList.add(elem);
            }
            rowListList.add((List<Integer>) ((ArrayList<Integer>) rowList).clone());
            rowList.clear();
        }

        Set<Set<Integer>> rowSetSet = new HashSet<>();
        Set<Integer> rowSet = new HashSet<>();

        for (int[] row : matrix) {
            for (int elem : row) {
                rowSet.add(elem);
            }
            rowSetSet.add((Set<Integer>) ((HashSet<Integer>) rowSet).clone());
            rowSet.clear();
        }

        for(Set<Integer> rowS:rowSetSet){

            int count=0;
            int indexSimilarRow = 0;
            for(List<Integer> rowL:rowListList) {
                if (rowL.containsAll(rowS) && rowS.containsAll(rowL)) {
                    indexSimilarRow=count;
                    count++;
                }else{
                    count++;
                }
            }
            nonSimilarRows.add(matrix[indexSimilarRow]);
        }
        return nonSimilarRows;
    }


}
