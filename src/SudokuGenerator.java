import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGenerator {



    public static void generate(JTextField[][] cells, boolean isDiagonalSudoku){

        int rank= cells.length;
        //
        SudokuChecker.clearAll(cells);
        int [][] sudoku =new int[rank][rank];
        generateSudoku(sudoku,isDiagonalSudoku);
        //printSudoku(sudoku);
        copyToJTextField(sudoku,cells);
        // 调用保存函数，并指定保存路径和文件名
        String filePath = "sudoku.csv";

        removeNumbers(cells);
        SudokuSaver.saveSudokuToCSV(filePath,cells,isDiagonalSudoku);
        SudokuSaver.saveSudokuToCSV(filePath,sudoku,isDiagonalSudoku);
    }

    private static void removeNumbers(JTextField[][] cells) {
        int rank=cells.length;
        Random random=new Random();
        for (int i = 0; i < rank*rank/2; ) {
            int row=random.nextInt(rank);
            int col=random.nextInt(rank);
            if(cells[row][col].getText().isEmpty()){
                continue;
            }
            else {
                cells[row][col].setText("");
                cells[row][col].setForeground(new Color(46,115,250));
                i++;
                continue;
            }

        }

        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                if(!cells[i][j].getText().isEmpty()){
                    cells[i][j].setEditable(false);
                }
            }

        }
    }
    //把数独数组的值复制到文本框
    private static void copyToJTextField(int[][] sudoku, JTextField[][] cells) {
        int rank= sudoku.length;
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                cells[i][j].setText(Integer.toString(sudoku[i][j]));
            }
        }
    }
    //生成数独函数
    public static void generateSudoku(int[][] board,boolean isDiagonalSudoku) {
        solveSudoku(board,isDiagonalSudoku);
    }

//    public static void main(String[] args) {
//        int[][] board=new int[9][9];
//        board[0][3]=9;
//        board[1][5]=8;
//        board[1][6]=5;
//        board[1][8]=2;
//        board[2][7]=3;
//        board[3][0]=4;
//        board[3][3]=8;
//        board[3][5]=7;
//        board[3][7]=2;
//        board[4][7]=5;
//        board[5][1]=7;
//        board[5][3]=6;
//        board[6][1]=8;
//        board[6][8]=3;
//        board[7][2]=2;
//        board[7][4]=9;
//        board[8][1]=5;
//        board[8][6]=2;
//        board[8][8]=7;
//        printSudoku(board);
//        System.out.println();
//        solveSudoku(board,true);
//        printSudoku(board);
//
//
//    }

    //回溯法解决数独问题
    public static boolean solveSudoku(int[][] board,boolean isDiagonalSudoku) {

        int SIZE= board.length;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                List<Integer> numbers = new ArrayList<>();
                for (int i = 1; i <= SIZE; i++) {
                    numbers.add(i);
                }
                Collections.shuffle(numbers);       //打乱可填入的数字
                if (board[row][col] == 0) {
                    for (int num : numbers) {
                        board[row][col] = num;

                        if (SudokuArrayChecker.isValid(board,isDiagonalSudoku)) {
                            board[row][col] = num;
                            if (solveSudoku(board,isDiagonalSudoku)) {
                                return true;
                            }
                            board[row][col] = 0; //回溯
                        }
                        else board[row][col] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }


//打印输出数独数组(调试用)
    public static void printSudoku(int[][] board) {
        int rank= board.length;
        for (int row = 0; row < rank; row++) {
            for (int col = 0; col < rank; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }


}
