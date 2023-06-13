import javax.swing.*;
import java.util.HashSet;
import java.util.Set;

public class Sudoku {




//检查当前整个数独是否有效
    public static boolean isValid(JTextField[][] cells){
        int rank=cells.length;
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                String value = cells[i][j].getText();
                if (!value.isEmpty()){
                    if(!isRowValid(rank,cells,i)||!isColValid(rank,cells,j)||!isGridValid(rank,cells,i,j)) {
                    }
                    return false;
                }
            }
        }

        return true;

    }
//检查行是否有效
    public   static boolean isRowValid(int rank,JTextField[][] cells,int row){
        Set<String> rowSet = new HashSet<>();
        for (int j = 0;j < rank; j++) {
            String value = cells[row][j].getText();
            if (!value.isEmpty()) {
                if (rowSet.contains(value)) {
                    return false;
                }
                rowSet.add(value);
            }
        }
        return true;
    }
    //检查列是否有效
    public   static boolean isColValid(int rank,JTextField[][] cells,int col){
        Set<String> colSet = new HashSet<>();
        for (int i = 0;i < rank; i++) {
            String value = cells[i][col].getText();
            if (!value.isEmpty()) {
                if (colSet.contains(value)) {
                    return false;
                }
                colSet.add(value);
            }
        }
        return true;
    }


public static void printAll(int rank,JTextField[][] cells){
    for (int i = 0; i < rank; i++) {
        for (int j = 0; j < rank; j++) {
            System.out.printf("%s ",cells[i][j].getText());
        }
        System.out.println();
    }
}


    public static void clearAll(int rank, JTextField[][] cells){

        printAll(rank, cells);
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                cells[i][j].setText("");
            }
        }
        System.out.println("clear all!!");

    }


    //检查宫格内是否有效
    public static boolean isGridValid(int rank, JTextField[][] cells, int row, int col){
        switch(rank){
            case 4:
                //1
                if(row<2&&col<2){
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 2; i < rank; i++) {
                        for (int j = 0; j < 2; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 0; i < 2; i++) {
                        for (int j = 2; j < rank; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 2; i < rank; i++) {
                        for (int j = 2; j < rank; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j <3; j++) {
                            String value = cells[i][j].getText();

                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 0; i < 2; i++) {
                        for (int j = 3; j < rank; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 2; i < 4; i++) {
                        for (int j = 0; j < 3; j++) {
                            String value = cells[i][j].getText();
                            System.out.println("value=="+value);
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();

                    for (int i = 2; i < 4; i++) {
                        for (int j = 3; j < rank; j++) {
                            String value = cells[i][j].getText();
                            System.out.println("value=="+value);
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 4; i < rank; i++) {
                        for (int j = 0; j < 3; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 4; i < rank; i++) {
                        for (int j = 3; j < rank; j++) {
                            String value = cells[i][j].getText();
                            System.out.println("value=="+value);
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 3; i < 6; i++) {
                        for (int j = 0; j < 3; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 6; i < rank; i++) {
                        for (int j = 0; j < 3; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 3; j < 6; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 3; i < 6; i++) {
                        for (int j = 3; j < 6; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 6; i < rank; i++) {
                        for (int j = 3; j < 6; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 6; j < rank; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 3; i < 6; i++) {
                        for (int j = 6; j < rank; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
                    Set<String> gridSet = new HashSet<>();
                    for (int i = 6; i < rank; i++) {
                        for (int j = 6; j < rank; j++) {
                            String value = cells[i][j].getText();
                            if (!value.isEmpty()) {
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
