import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;

import java.awt.event.*;
import java.util.Random;

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
        solveButton = new JButton("完成");
        returnButton =new JButton("重新选择难度");
       // add(solveButton);
        //add(returnButton);

        // 为每个文本框添加鼠标事件处理
        for (int row = 0; row < rank; row++) {
            for (int col = 0; col < rank; col++) {
                JTextField textField = cells[row][col];
                int finalRow = row;
                int finalCol = col;
                textField.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // 清除之前选中的文本框背景色
                        clearCellBackground(cells);
                        // 设置选中文本框所在的列和所在的行的背景色
                        for (int i = 0; i < rank; i++) {
                                cells[finalRow][i].setBackground(new Color(187,222,251));
                                cells[i][finalCol].setBackground(new Color(187,222,251));
                        }
                        switch(rank){
                            case 4:
                            case 6:
                            case 9:
                        }


                        cells[finalRow][finalCol].setBackground(new Color(226,225,255));
                    }
                });

            }
            }
        }


        private void fillBackground(JTextField[][] cells,int finalRow,int finalCol ){
                if()
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


}
