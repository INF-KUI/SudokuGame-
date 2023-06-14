import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class Sudoku {




//检查当前整个数独是否有效
    //返回 1  代表完成且正确
    //返回 0  代表完成但错误
    //返回 -1 代表未完成
    public static int isValid(JTextField[][] cells){
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
    public static boolean isDiagonalSetValid(JTextField[][] cells, int row, int col){
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

    public static void checkAnswer (JTextField [][] cells) {

        int returnValue=isValid(cells);

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
