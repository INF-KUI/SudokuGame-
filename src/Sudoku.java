import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Sudoku {




//检查当前整个数独是否有效
    //返回 1  代表完成且正确
    //返回 0  代表完成但错误
    //返回 -1 代表未完成
    public static int isValid(JTextField[][] cells,boolean isDiagonalSudoku){
        int rank=cells.length;
        //未完成
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                String value = cells[i][j].getText();
            if(value.isEmpty()){
                return -1;
            }
            }
        }
        if(isDiagonalSudoku){
            for (int i = 0; i < rank; i++) {
                for (int j = 0; j < rank; j++) {
                    String value = cells[i][j].getText();
                    if (!value.isEmpty()){
                        if(!isRowValid(cells,i)||!isColValid(cells,j)||!isDiagonalValid(cells,i,j)||!isGridValid(cells,i,j)){
                            lockAll(cells,new Color(255,0,0));
                            return 0;
                        }
                    }
                }
            }
        }
        else
            {
            for (int i = 0; i < rank; i++) {
        for (int j = 0; j < rank; j++) {
            String value = cells[i][j].getText();
            if (!value.isEmpty()){
                if(!isRowValid(cells,i)||!isColValid(cells,j)||!isGridValid(cells,i,j)){
                    lockAll(cells,new Color(255,0,0));
                    return 0;
                }
            }
        }
    }
}



        lockAll(cells,new Color(0x52ec7c));
        return 1;

    }

    //锁定所有元素
    private static void lockAll(JTextField[][] cells,Color color) {
        int rank=cells.length;
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                cells[i][j].setBackground(color);
                cells[i][j].setEditable(false);
            }}

    }


    public static void printAll(int rank,JTextField[][] cells){
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                System.out.printf("%s ",cells[i][j].getText());
            }
            System.out.println();
        }
    }

    //清除所有元素，设置权限可修改
    public static void clearAll(JTextField[][] cells){

        //printAll(rank, cells);
        int rank=cells.length;
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                cells[i][j].setBackground(new Color(255,255,255));
                cells[i][j].setEditable(true);
                cells[i][j].setText("");
            }
        }
        //System.out.println("clear all!!");

    }
    //检查行是否有效
    public   static boolean isRowValid(JTextField[][] cells,int row){

        int rank= cells.length;
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
    public   static boolean isColValid(JTextField[][] cells,int col){
        int rank= cells.length;
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
//检查对角线内是否有效
    public static boolean isDiagonalValid(JTextField[][] cells, int row, int col){
        int rank= cells.length;
        String diagonalType ="null";  //所处的对角线类型

        Set<String> mainDiagonalSet = new HashSet<>();
        Set<String> antiDiagonalSet = new HashSet<>();

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
                        String value = cells[i][j].getText();
                        //主对角线元素
                        if(i==j){
                            if (!value.isEmpty()) {
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
                        String value = cells[i][j].getText();
                        //主对角线
                        if(i+j==rank-1){
                            if (!value.isEmpty()) {
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
                        String value = cells[i][j].getText();
                        //主对角线元素
                        if(i==j){
                            if (!value.isEmpty()) {
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
                        String value = cells[i][j].getText();
                        //主对角线
                        if(i+j==rank-1){
                            if (!value.isEmpty()) {
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


    //检查宫格内是否有效
    public static boolean isGridValid(JTextField[][] cells, int row, int col){
         int rank= cells.length;
        Set<String> gridSet = new HashSet<>();
        int starRow;
        int starCol;

        switch(rank){

            case 4:
            starRow = row / 2;
            starCol = col / 2;
            for (int i = starRow*2; i < starRow*2 + 2; i++) {
                for (int j = starCol*2; j < starCol*2+2; j++) {
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

            case 6:
                starRow = row / 2;
                starCol = col / 3;
                for (int i = starRow*2; i < starRow*2 + 2; i++) {
                    for (int j = starCol*3; j < starCol*3+3; j++) {
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

            case 9:
                starRow = row / 3;
                starCol = col / 3;
                for (int i = starRow*3; i < starRow*3 + 3; i++) {
                    for (int j = starCol*3; j < starCol*3+3; j++) {
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
        return  true;
    }
    //检查对角线数独的特殊宫格内是否有效
    public static boolean isSpecialGridValid(JTextField[][] cells, int row, int col){
        int rank= cells.length;
        Set<String > specialGridSet = new HashSet<>();

        int fromWhich=detectFromWhichGrid(row,col);
         String value=null;
        switch(fromWhich){
            case 1:
                 value =cells[0][0].getText();
                 if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                 value = cells[0][1].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                 value =cells[0][2].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                 value =cells[0][3].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                 value =cells[1][0].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                 value = cells[1][1].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                 value =cells[2][0].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                 value =cells[3][0].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                 value = cells[3][1].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                break;
            case 2:
                value=cells[0][4].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[1][2].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[1][3].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[1][4].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[2][1].getText();
                if (!value.isEmpty()) {
                if (specialGridSet.contains(value)) {
                    return false;
                }
                specialGridSet.add(value);
            }
                value =cells[2][2].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[2][3].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value =cells[2][4].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[3][2].getText();
                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                break;
            case 3:
                value =cells[0][5].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[0][6].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[0][7].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[0][8].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[1][5].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[1][6].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[1][8].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[2][5].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                value = cells[2][8].getText();
                   if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }
                break;
            case 4:
               value= cells[4][0].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[4][1].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[4][2].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[5][0].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[5][1].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[5][2].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[6][1].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[6][2].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[7][1].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
            case 5:
               value= cells[3][3].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[3][4].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[3][5].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[4][3].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[4][4].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[4][5].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[5][3].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[5][4].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[5][5].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
            case 6:
               value= cells[1][7].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[2][6].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[2][7].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[3][6].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[3][7].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[3][8].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[4][6].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[4][7].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[4][8].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
            case 7:
               value= cells[6][0].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[6][3].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[7][0].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[7][2].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[7][3].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[8][0].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[8][1].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[8][2].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[8][3].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
            case 8:
               value= cells[5][6].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[6][4].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[6][5].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[6][6].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[6][7].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[7][4].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[7][5].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[7][6].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[8][4].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
            case 9:
               value= cells[5][7].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[5][8].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[6][8].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[7][7].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[7][8].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[8][5].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[8][6].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[8][7].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

               value= cells[8][8].getText();
                                if (!value.isEmpty()) {
                    if (specialGridSet.contains(value)) {
                        return false;
                    }
                    specialGridSet.add(value);
                }

                break;
        }

        return true;
    }

    public static int detectFromWhichGrid(int row, int col) {
        int fromWhich=0;

        if(     (row==0&&col==0)||
                (row==0&&col==1)||
                (row==0&&col==2)||
                (row==0&&col==3)||
                (row==1&&col==0)||
                (row==1&&col==1)||
                (row==2&&col==0)||
                (row==3&&col==0)||
                (row==3&&col==1))
        {fromWhich=1;}
        else if((row==0&&col==4)||
                (row==1&&col==2)||
                (row==1&&col==3)||
                (row==1&&col==4)||
                (row==2&&col==1)||
                (row==2&&col==2)||
                (row==2&&col==3)||
                (row==2&&col==4)||
                (row==3&&col==2))
        {fromWhich=2;}
        else if((row==0&&col==5)||
                (row==0&&col==6)||
                (row==0&&col==7)||
                (row==0&&col==8)||
                (row==1&&col==5)||
                (row==1&&col==6)||
                (row==1&&col==8)||
                (row==2&&col==5)||
                (row==2&&col==8))
        {fromWhich=3;}
        else if((row==4&&col==0)||
                (row==4&&col==1)||
                (row==4&&col==2)||
                (row==5&&col==0)||
                (row==5&&col==1)||
                (row==5&&col==2)||
                (row==6&&col==1)||
                (row==6&&col==2)||
                (row==7&&col==1))
        {fromWhich=4;}
        else if((row==3&&col==3)||
                (row==3&&col==4)||
                (row==3&&col==5)||
                (row==4&&col==3)||
                (row==4&&col==4)||
                (row==4&&col==5)||
                (row==5&&col==3)||
                (row==5&&col==4)||
                (row==5&&col==5))
        {fromWhich=5;}
        else if((row==1&&col==7)||
                (row==2&&col==6)||
                (row==2&&col==7)||
                (row==3&&col==6)||
                (row==3&&col==7)||
                (row==3&&col==8)||
                (row==4&&col==6)||
                (row==4&&col==7)||
                (row==4&&col==8))
        {fromWhich=6;}
        else if((row==6&&col==0)||
                (row==6&&col==3)||
                (row==7&&col==0)||
                (row==7&&col==2)||
                (row==7&&col==3)||
                (row==8&&col==0)||
                (row==8&&col==1)||
                (row==8&&col==2)||
                (row==8&&col==3))
        {fromWhich=7;}
        else if((row==5&&col==6)||
                (row==6&&col==4)||
                (row==6&&col==5)||
                (row==6&&col==6)||
                (row==6&&col==7)||
                (row==7&&col==4)||
                (row==7&&col==5)||
                (row==7&&col==6)||
                (row==8&&col==4))
        {fromWhich=8;}
        else if((row==5&&col==7)||
                (row==5&&col==8)||
                (row==6&&col==8)||
                (row==7&&col==7)||
                (row==7&&col==8)||
                (row==8&&col==5)||
                (row==8&&col==6)||
                (row==8&&col==7)||
                (row==8&&col==8))
        {fromWhich=9;}

        return fromWhich;
    }


    public static void checkAnswer (JTextField [][] cells,boolean isDiagonalSudoku) {

        int returnValue=isValid(cells,isDiagonalSudoku);

        switch(returnValue){
            case 1:
                JOptionPane.showMessageDialog(null, "答案正确！", "完成", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("正确！！！");
                break;
            case 0:
                JOptionPane.showMessageDialog(null, "答案错误！", "错误", JOptionPane.INFORMATION_MESSAGE);
                //System.out.println("错误！！！！！！");
                break;
            case -1:
                JOptionPane.showMessageDialog(null, "未完成！", "未完成", JOptionPane.INFORMATION_MESSAGE);
               // System.out.println("错误！！！！！！");
        }
    }
}
