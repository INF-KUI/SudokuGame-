import java.util.HashSet;
import java.util.Set;

public class SudokuArrayChecker {

    //检查当前整个数独是否有效
    public static boolean isValid(int [][] cells,boolean isDiagonalSudoku){
        int rank=cells.length;

        if(isDiagonalSudoku){
            for (int i = 0; i < rank; i++) {
                for (int j = 0; j < rank; j++) {
                    int value = cells[i][j];
                    if (value!=0){
                        //检查行 列  对角线 宫格 是有否重复
                        //如果检测特殊宫格，条件严格题目生成得很慢
                        if(!isRowValid(cells,i)||!isColValid(cells,j)||!isDiagonalSetValid(cells,i,j)||!isGridValid(cells,i,j)) {
                            return false;
                        }
                    }
                }
            }
        }
        else {
            for (int i = 0; i < rank; i++) {
                for (int j = 0; j < rank; j++) {
                    int value = cells[i][j];
                    if (value!=0){

                        //只用 检查行 列  宫格 是有否重复
                        if(!isRowValid(cells,i)||!isColValid(cells,j)||!isGridValid(cells,i,j)) {
                            return false;
                        }
                    }
                }
            }
        }

        //System.out.printf("true\n");
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
                        if(i==j&&value != 0){
                                if (mainDiagonalSet.contains(value)) {
                                    return false;
                                }
                                mainDiagonalSet.add(value);
                        }
                    }
                }
                //检查反对角线的元素是否有重复
                for (int i = 0; i < rank; i++) {
                    for (int j = 0; j < rank; j++) {
                        int value = cells[i][j];
                        //反对角线
                        if(i+j==rank-1&&value!=0){
                                if (antiDiagonalSet.contains(value)) {
                                    return false;
                                }
                                antiDiagonalSet.add(value);
                        }
                    }
                }
                break;


            case "mainDiagonal":
                for (int i = 0; i < rank; i++) {
                    for (int j = 0; j < rank; j++) {
                        int value = cells[i][j];
                        //主对角线元素
                        if(i==j&&value!= 0){
                                if (mainDiagonalSet.contains(value)) {
                                    return false;
                                }
                                mainDiagonalSet.add(value);

                        }
                    }
                }
                break;

            case "antiDiagonal":
                for (int i = 0; i < rank; i++) {
                    for (int j = 0; j < rank; j++) {
                        int value = cells[i][j];
                        //反对角线
                        if(i+j==rank-1&&value!=0){
                                if (antiDiagonalSet.contains(value)) {
                                    return false;
                                }
                                antiDiagonalSet.add(value);
                        }
                    }
                }
                break;
        }

        return true;

    }

    //检查对角线数独的特殊宫格内是否有效
    public static boolean isSpecialGridValid(int[][] cells, int row, int col){
        int rank= cells.length;
        Set<Integer > specialGridSet = new HashSet<>();

        int fromWhich= SudokuChecker.detectFromWhichGrid(row,col);
        int value=0;
        switch(fromWhich){
            case 1:
                value =cells[0][0];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[0][1];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[0][2];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[0][3];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[1][0];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[1][1];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[2][0];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[3][0];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[3][1];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                break;
            case 2:
                value=cells[0][4];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[1][2];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[1][3];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[1][4];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[2][1];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[2][2];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[2][3];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[2][4];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[3][2];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                break;
            case 3:
                value =cells[0][5];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[0][6];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[0][7];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[0][8];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[1][5];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[1][6];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[1][8];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[2][5];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[2][8];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                break;
            case 4:
                value= cells[4][0];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[4][1];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[4][2];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[5][0];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[5][1];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[5][2];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[6][1];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[6][2];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[7][1];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
            case 5:
                value= cells[3][3];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[3][4];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[3][5];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[4][3];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[4][4];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[4][5];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[5][3];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[5][4];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[5][5];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
            case 6:
                value= cells[1][7];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[2][6];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[2][7];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[3][6];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[3][7];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[3][8];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[4][6];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[4][7];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[4][8];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
            case 7:
                value= cells[6][0];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[6][3];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[7][0];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[7][2];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[7][3];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[8][0];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[8][1];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[8][2];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[8][3];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
            case 8:
                value= cells[5][6];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[6][4];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[6][5];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[6][6];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[6][7];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[7][4];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[7][5];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[7][6];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[8][4];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
            case 9:
                value= cells[5][7];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[5][8];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[6][8];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[7][7];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[7][8];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[8][5];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[8][6];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[8][7];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                value= cells[8][8];
                if (value!=0) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
        }

        return true;
    }



}
