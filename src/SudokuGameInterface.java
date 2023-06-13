import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;

import java.awt.event.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static javax.print.attribute.standard.MediaSizeName.A;

public class SudokuGameInterface extends JPanel{


    private JTextField[][] cells;
    private JButton solveButton;
    private JButton returnButton;

    public SudokuGameInterface(int rank) {


        GridBagLayout gridBagLayout=new GridBagLayout();
        setLayout(gridBagLayout);
        GridBagConstraints constraints= new GridBagConstraints();//用来控制添加进的组件的显示位置
        constraints.weightx = 0;
        constraints.weighty = 0;

        cells = new JTextField [rank][rank];
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                cells[i][j] = createTextField();
                constraints.gridx = j;
                constraints.gridy = i;
                add(cells[i][j], constraints);
            }
        }
        GridBagConstraints con=new GridBagConstraints();
        solveButton = new JButton("完成");
        returnButton =new JButton("重新选择难度");

        solveButton.addActionListener(e->{
            Sudoku.cheakAnswer(cells);
                }

        );
        add(solveButton,con);
        //add(returnButton,con);

       // add(solveButton);
        //add(returnButton);

        //添加点击效果
        addClickEffect(cells);
        int [][] sudoku =new int[rank][rank];

        generateSudoku(sudoku);
        printSudoku(sudoku);
        copyToFieldd(sudoku,cells);
        removeNumbers(cells);

        }

    private void removeNumbers(JTextField[][] cells) {
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

    private void copyToFieldd(int[][] sudoku, JTextField[][] cells) {
        int rank= sudoku.length;
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                cells[i][j].setText(Integer.toString(sudoku[i][j]));
            }
        }
    }

    public static void generateSudoku(int[][] board) {
        solveSudoku(board);

    }

    public static boolean solveSudoku(int[][] board) {
        int SIZE= board.length;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                List<Integer> numbers = new ArrayList<>();
                for (int i = 1; i <= SIZE; i++) {
                    numbers.add(i);
                }
                Collections.shuffle(numbers);

                if (board[row][col] == 0) {
                    for (int num : numbers) {
                            board[row][col] = num;
                        if (SudokuArray.isValid(board)) {
                            board[row][col] = num;
                            if (solveSudoku(board)) {
                                return true;
                            }
                            board[row][col] = 0; // 回溯
                        }
                        else board[row][col] = 0;
                    }
                    return false;
                }
            }
        }
        return true;
    }


    public static void printSudoku(int[][] board) {
        int rank= board.length;
        for (int row = 0; row < rank; row++) {
            for (int col = 0; col < rank; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }


//    public static void generateSudoku(int[][] sudoku) {
//        fillSudoku(sudoku);
//    }
//
//    private static boolean fillSudoku(int[][] sudoku) {
//
//        //Random random=new Random();
//        int SIZE= sudoku.length;
//        int EMPTY=0;
//
//        //

////
//
//        for (int row = 0; row < SIZE; row++) {
//            for (int col = 0; col < SIZE; col++) {
//                if (sudoku[row][col] == EMPTY) {
//                    Random random=new Random();
//                    int nums=random.nextInt(SIZE)+1;
//                        sudoku[row][col] =nums;
//                        if (SudokuArray.isValid(sudoku)) {
//                            sudoku[row][col] =nums;
//                            if (fillSudoku(sudoku)) {
//                                return true;
//                            }
//                            sudoku[row][col] = EMPTY;
//                        }
//
//                    return false;
//                }
//            }
//        }
//        return true;
//    }


    // 为每个文本框添加鼠标事件处理
    private void addClickEffect(JTextField[][] cells){
        int rank=cells.length;
        for (int row = 0; row < rank; row++) {
        for (int col = 0; col < rank; col++) {
            //JTextField textField = ;
            int finalRow = row;
            int finalCol = col;
            cells[row][col].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // 清除之前选中的文本框背景色
                    clearCellBackground(cells);
                    if(cells[finalRow][finalCol].isEditable())
                    cells[finalRow][finalCol].setText("");
                    // 设置选中文本框所在的列和所在的行的背景色
                    fillBackground(cells,rank,finalRow,finalCol);
                    for (int i = 0; i < rank; i++) {
                        cells[finalRow][i].setBackground(new Color(187,222,251));
                        cells[i][finalCol].setBackground(new Color(187,222,251));
                    }
                    cells[finalRow][finalCol].setBackground(new Color(0x52ec7c));
                }
            });
        }
    }}




//    private static void generateSudokuPuzzle() {
//        // Generate a complete Sudoku grid
//        solveSudoku(0, 0);
//
//        // Remove some numbers to create a puzzle
//        int numToRemove = GRID_SIZE * GRID_SIZE / 2; // Adjust the number of cells to remove as desired
//        Random random = new Random();
//        for (int i = 0; i < numToRemove; i++) {
//            int row = random.nextInt(GRID_SIZE);
//            int col = random.nextInt(GRID_SIZE);
//            sudokuGrid[row][col] = 0;
//        }
//    }

    //清除所有背景颜色
    private static void clearCellBackground(JTextField[][] cells) {
        for (int row = 0; row < cells.length ; row++) {
            for (int col = 0; col < cells.length; col++) {
                cells[row][col].setBackground(new Color(255,255,255));
            }
        }
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        Font font = new Font("Arial", Font.BOLD, 25);
        textField.setFont(font);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setPreferredSize(new Dimension(50, 50));

        // 限制文本框只能输入一个数字
        PlainDocument document = (PlainDocument) textField.getDocument();//管理文本框的文本内容
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException, BadLocationException {
                String newText = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;
                if (newText.length() <= 1 && newText.matches("[1-9]?")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
        return textField;
    }




    private boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board)) {
                                return true;
                            } else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }

        int startRow = 3 * (row / 3);
        int startCol = 3 * (col / 3);
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private void updateBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText(Integer.toString(board[i][j]));
            }
        }
    }



    //点击更改所在宫格的背景颜色
    private void fillBackground(JTextField[][] cells,int rank,int finalRow,int finalCol ){

        Color backgroundColor=new Color(187,222,251);
        switch(rank){
            case 4:
                //1
                if(finalRow<2&&finalCol<2){
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j < 2; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //2
                if(finalRow>1&&finalCol<=1){
                    for (int i = 2; i < rank; i++) {
                        for (int j = 0; j < 2; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //3
                if(finalRow<2&&finalCol>1){
                    for (int i = 0; i < 2; i++) {
                        for (int j = 2; j < rank; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //4
                if(finalRow>1&&finalCol>1){
                    for (int i = 2; i < rank; i++) {
                        for (int j = 2; j < rank; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
            case 6:

                //1
                if(finalRow<2&&finalCol<3){
                    for (int i = 0; i < 2; i++) {
                        for (int j = 0; j <3; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //2
                if(finalRow<2&&finalCol>2){
                    for (int i = 0; i < 2; i++) {
                        for (int j = 3; j < rank; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //3
                if(finalRow>1&&finalRow<4&&finalCol<3){
                    for (int i = 2; i < 4; i++) {
                        for (int j = 0; j < 3; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //4
                if(finalRow>1&&finalRow<4&&finalCol>2){
                    for (int i = 2; i < 4; i++) {
                        for (int j = 3; j < rank; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }


                //5
                if(finalRow>3&&finalCol<3){
                    for (int i = 4; i < rank; i++) {
                        for (int j =0; j < 3; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //6
                if(finalRow>3&&finalCol>2){
                    for (int i = 4; i < rank; i++) {
                        for (int j = 3; j < rank; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
            case 9:
                //1
                if(finalRow<3&&finalCol<3){
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //2
                if(finalRow>2&&finalRow<6&&finalCol<3){
                    for (int i = 3; i < 6; i++) {
                        for (int j = 0; j < 3; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //3
                if(finalRow>5&&finalCol<3){
                    for (int i = 6; i < rank; i++) {
                        for (int j = 0; j < 3; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //4
                if(finalRow<3&&finalCol>2&&finalCol<6){
                    for (int i = 0; i < 3; i++) {
                        for (int j = 3; j < 6; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //5
                if(finalRow>2&&finalRow<6&&finalCol>2&&finalCol<6){
                    for (int i = 3; i < 6; i++) {
                        for (int j = 3; j < 6; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //6
                if(finalRow>5&&finalCol>2&&finalCol<6){
                    for (int i = 6; i < rank; i++) {
                        for (int j = 3; j < 6; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //7
                if(finalRow<3&&finalCol>5){
                    for (int i = 0; i < 3; i++) {
                        for (int j = 6; j < rank; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //8
                if(finalRow>2&&finalRow<6&&finalCol>5){
                    for (int i = 3; i < 6; i++) {
                        for (int j = 6; j < rank; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //9
                if(finalRow>5&&finalCol>5){
                    for (int i = 6; i < rank; i++) {
                        for (int j = 6; j < rank; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
        }

    }


}
