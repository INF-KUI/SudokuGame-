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
                    if(!isRowValid(rank,cells,i)||!isColValid(rank,cells,j)||!isGridValid(rank,cells,i,j)) {
                        return false;
                    }

                }
            }
        }

        return true;

    }
    //检查行是否有效
    public   static boolean isRowValid(int rank,int[][] cells,int row){
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
    public   static boolean isColValid(int rank,int[][] cells,int col){
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
    public static boolean isGridValid(int rank, int [][] cells, int row, int col){
        switch(rank){
            case 4:
                //1
                if(row<2&&col<2){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }

                    break;
                }
                //2
                if(row>1&&col<=1){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 2; i < rank; i++) {
                        for (int j = 0; j < 2; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //3
                if(row<2&&col>1){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 0; i < 2; i++) {
                        for (int j = 2; j < rank; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //4
                if(row>1&&col>1){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 2; i < rank; i++) {
                        for (int j = 2; j < rank; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
            case 6:

                //1
                if(row<2&&col<3){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j <3; j++) {
                            int value = cells[i][j];

                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //2
                if(row<2&&col>2){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 0; i < 2; i++) {
                        for (int j = 3; j < rank; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //3
                if(row>1&&row<4&&col<3){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 2; i < 4; i++) {
                        for (int j = 0; j < 3; j++) {
                            int value = cells[i][j];
                            System.out.println("value=="+value);
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //4
                if(row>1&&row<4&&col>2){
                    Set<Integer> gridSet = new HashSet<>();

                    for (int i = 2; i < 4; i++) {
                        for (int j = 3; j < rank; j++) {
                            int value = cells[i][j];
                            System.out.println("value=="+value);
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    gridSet.clear();
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //5
                if(row>3&&col<3){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 4; i < rank; i++) {
                        for (int j = 0; j < 3; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //6
                if(row>3&&col>2){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 4; i < rank; i++) {
                        for (int j = 3; j < rank; j++) {
                            int value = cells[i][j];
                            System.out.println("value=="+value);
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
            case 9:
                //1
                if(row<3&&col<3){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //2
                if(row>2&&row<6&&col<3){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 3; i < 6; i++) {
                        for (int j = 0; j < 3; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //3
                if(row>5&&col<3){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 6; i < rank; i++) {
                        for (int j = 0; j < 3; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //4
                if(row<3&&col>2&&col<6){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 3; j < 6; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //5
                if(row>2&&row<6&&col>2&&col<6){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 3; i < 6; i++) {
                        for (int j = 3; j < 6; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //6
                if(row>5&&col>2&&col<6){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 6; i < rank; i++) {
                        for (int j = 3; j < 6; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //7
                if(row<3&&col>5){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 6; j < rank; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //8
                if(row>2&&row<6&&col>5){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 3; i < 6; i++) {
                        for (int j = 6; j < rank; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
                //9
                if(row>5&&col>5){
                    Set<Integer> gridSet = new HashSet<>();
                    for (int i = 6; i < rank; i++) {
                        for (int j = 6; j < rank; j++) {
                            int value = cells[i][j];
                            if (value!=0) {
                                if (gridSet.contains(value)) {
                                    return false;
                                }
                                gridSet.add(value);
                            }
                        }
                    }
                    break;
                }
        }
        return  true;
    }
}
