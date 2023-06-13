import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;

import java.awt.event.*;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class SudokuGame extends JPanel{


    private JTextField[][] cells;
    private JButton solveButton;
    private JButton returnButton;

    public SudokuGame(int rank) {


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
        //add(solveButton,con);
        //add(returnButton,con);

       // add(solveButton);
        //add(returnButton);

        generateSudokuPuzzle(rank,cells);
        // 为每个文本框添加鼠标事件处理
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
            }
        }

private static  void generateSudokuPuzzle(int rank,JTextField[][] cells){
    //Random random = new Random(System.currentTimeMillis());

    for (int i = 0; i < rank; i++) {
        for (int j = 0; j < rank; j++) {
            //ThreadLocalRandom random = ThreadLocalRandom.current();
            // 增加延迟
            SecureRandom random = new SecureRandom();


            do {
                cells[i][j].setText("");
                cells[i][j].setText(Integer.toString((random.nextInt( rank)+1)));
                System.out.println(cells[i][j].getText());
            }
            while(!isRowValid(rank,cells,i)||!isColValid(rank,cells,j));


            while(!isRowValid(rank,cells,j));
            isColValid(rank,cells,i);

            cells[i][j].setEditable(false);
    }
}
    }


//检查行是否有效
    private  static boolean isRowValid(int rank,JTextField[][] cells,int row){
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
    private  static boolean isColValid(int rank,JTextField[][] cells,int col){
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
                cells[row][col].setBackground(null);
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



//    private void solveSudoku() {
//        int[][] board = new int[9][9];
//
//        for (int i = 0; i < 9; i++) {
//            for (int j = 0; j < 9; j++) {
//                String cellValue = cells[i][j].getText();
//                if (cellValue.isEmpty()) {
//                    board[i][j] = 0;
//                } else {
//                    board[i][j] = Integer.parseInt(cellValue);
//                }
//            }
//        }
//
//        if (solve(board)) {
//            updateBoard(board);
//            JOptionPane.showMessageDialog(this, "Sudoku solved successfully!");
//        } else {
//            JOptionPane.showMessageDialog(this, "Unable to solve the Sudoku puzzle!");
//        }
//    }

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
                if(finalRow<3&&finalCol<3){
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j <3; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //2
                if(finalRow>2&&finalCol<3){
                    for (int i = 3; i < rank; i++) {
                        for (int j = 0; j < 3; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //3
                if(finalRow<3&&finalCol>2){
                    for (int i = 0; i < 3; i++) {
                        for (int j = 3; j < rank; j++) {
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                    break;
                }
                //4
                if(finalRow>2&&finalCol>2){
                    for (int i = 3; i < rank; i++) {
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
