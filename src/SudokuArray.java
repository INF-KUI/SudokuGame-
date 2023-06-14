import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class SudokuArray {

    //检查当前整个数独是否有效
    public static boolean isValid(int [][] cells){
        int rank=cells.length;
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                int value = cells[i][j];
                if (value!=0){
                    if(!isRowValid(cells,i)||!isColValid(cells,j)||!isGridValid(cells,i,j)) {
                        return false;
                    }
                }
            }
        }

        return true;

    }
    //检查行是否有效
    public   static boolean isRowValid(int[][] cells,int row){
        int rank= cells.length;
        Set<Integer> rowSet = new HashSet<>();
        for (int j = 0;j < rank; j++) {
            int value = cells[row][j];
            if (value!=0) {
                if (rowSet.contains(value)) {
                    return false;
                }
                rowSet.add(value);
            }
        }
        return true;
    }
    //检查列是否有效
    public   static boolean isColValid(int[][] cells,int col){
        int rank= cells.length;
        Set<Integer> colSet = new HashSet<>();
        for (int i = 0;i < rank; i++) {
            int value = cells[i][col];
            if (value!=0) {
                if (colSet.contains(value)) {
                    return false;
                }
                colSet.add(value);
            }
        }
        return true;
    }

    //检查宫格内是否有效
    public static boolean isGridValid(int [][] cells, int row, int col){
        int rank= cells.length;
        Set<Integer> gridSet = new HashSet<>();
        int starRow;
        int starCol;
        switch(rank) {

            case 4:
                starRow = row / 2;
                starCol = col / 2;
                for (int i = starRow*2; i < starRow*2 + 2; i++) {
                    for (int j = starCol*2; j < starCol*2+2; j++) {
                        int value = cells[i][j];
                        if (value != 0) {
                            if (gridSet.contains(value)) {
                                return false;
                            }
                            gridSet.add(value);
                        }
                    }
                }
                break;


            case 6:
                starRow = row / 2;
                starCol = col / 3;
                for (int i = starRow*2; i < starRow*2 + 2; i++) {
                    for (int j = starCol*3; j < starCol*3+3; j++) {
                        int value = cells[i][j];
                        if (value != 0) {
                            if (gridSet.contains(value)) {
                                return false;
                            }
                            gridSet.add(value);
                        }
                    }
                }
                break;

            case 9:

                starRow = row / 3;
                starCol = col / 3;

                for (int i = starRow*3; i < starRow*3 + 3; i++) {
                    for (int j = starCol*3; j < starCol*3+3; j++) {
                        int value = cells[i][j];
                        if (value != 0) {
                            if (gridSet.contains(value)) {
                                return false;
                            }
                            gridSet.add(value);
                        }
                    }
                }
                break;
        }

        return true;
    }

    //检查对角线内是否有效
    public static boolean isDiagonalSetValid(int [][] cells, int row, int col) {
        int rank= cells.length;
        String diagonalType ="null";  //所处的对角线类型

        Set<Integer> mainDiagonalSet = new HashSet<>();
        Set<Integer> antiDiagonalSet = new HashSet<>();

        if(row==col&&row+col==rank-1){
            diagonalType="bothDiagonal";    //既是主对角线也是反对角线
        }
        else if(row==col){
            diagonalType="mainDiagonal";    //主主对角线
        }
        else if(row+col==rank-1){
            diagonalType="antiDiagonal";    //反对角线
        }
        else {
            return true;
        }

        switch (diagonalType){

            case "bothDiagonal":

                //检查主对角线的元素是否有重复
                for (int i = 0; i < rank; i++) {
                    for (int j = 0; j < rank; j++) {
                        int value = cells[i][j];
                        //主对角线元素
                        if(i==j){
                            if (value != 0) {
                                if (mainDiagonalSet.contains(value)) {
                                    return false;
                                }
                                mainDiagonalSet.add(value);
                            }
                        }
                    }
                }
                //检查反对角线的元素是否有重复
                for (int i = 0; i < rank; i++) {
                    for (int j = 0; j < rank; j++) {
                        int value = cells[i][j];
                        //反对角线
                        if(i+j==rank-1){
                            if (value != 0) {
                                if (antiDiagonalSet.contains(value)) {
                                    return false;
                                }
                                antiDiagonalSet.add(value);
                            }
                        }
                    }
                }
                break;


            case "mainDiagonal":
                for (int i = 0; i < rank; i++) {
                    for (int j = 0; j < rank; j++) {
                        int value = cells[i][j];
                        //主对角线元素
                        if(i==j){
                            if (value != 0) {
                                if (mainDiagonalSet.contains(value)) {
                                    return false;
                                }
                                mainDiagonalSet.add(value);
                            }
                        }
                    }
                }
                break;

            case "antiDiagonal":
                for (int i = 0; i < rank; i++) {
                    for (int j = 0; j < rank; j++) {
                        int value = cells[i][j];
                        //反对角线
                        if(i+j==rank-1){
                            if (value != 0) {
                                if (antiDiagonalSet.contains(value)) {
                                    return false;
                                }
                                antiDiagonalSet.add(value);
                            }
                        }
                    }
                }
                break;
        }

        return true;

    }


}
