import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import java.awt.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SudokuGameInterface extends JPanel{

    private JTextField[][] cells;
    private JButton returnButton;//返回按钮
    private JButton resetButton;//重置按钮
    private JButton finishButton;  //完成按钮
    private JButton exitButton;//退出按钮

    private static Timer timer ;

    public SudokuGameInterface(int rank,boolean isDiagonalSudoku ) {


        GridBagLayout gridBagLayout=new GridBagLayout();
        setLayout(gridBagLayout);
        GridBagConstraints constraints= new GridBagConstraints();//用来控制添加进的组件的显示位置
        constraints.weightx = 100;
        constraints.weighty = 100;
        cells = new JTextField [rank][rank];
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                cells[i][j] = createTextField();
                constraints.gridx = j;
                constraints.gridy = i;
                add(cells[i][j], constraints);
            }
        }
        //为文本框添加点击效果
        addClickEffect(cells,isDiagonalSudoku);
        generate(cells,isDiagonalSudoku);
////////////////////////////////////////////////////////
//        int delay = 1000;
//
//        // 创建计时显示的组件
//        JLabel timerLabel = new JLabel("00:00:00");
//        GridBagConstraints timerConstraints = new GridBagConstraints();
//        timerConstraints.gridx = 0;
//        timerConstraints.gridy = 9;
//        timerConstraints.gridwidth = 9;
//        timerConstraints.insets = new Insets(10, 5, 10, 5);
//        timer = new Timer(delay, e -> {
//            updateTimer(timerLabel);
//            // 在计时器触发时执行的操作
//            // 这里可以更新计时显示的文本或执行其他操作
//        });
//        add(timerLabel, timerConstraints);
//        timer.start();
//


///////////////////////////////////////////////////////////////

        finishButton = new JButton("完成");
        resetButton =new JButton("重置");
        returnButton = new JButton("返回");
        exitButton=new JButton("退出");

    // 设置"完成"按钮的位置和大小
        GridBagConstraints finishButtonConstraints = new GridBagConstraints();
        finishButtonConstraints.gridx = 0;
        finishButtonConstraints.gridy = 10;
        finishButtonConstraints.gridwidth = 9;
        finishButtonConstraints.insets = new Insets(10, 5, 10, 5);
        add(finishButton, finishButtonConstraints);
        //完成按钮事件监听器
        finishButton.addActionListener(e->{
                    Sudoku.checkAnswer(cells,isDiagonalSudoku);
                }
        );


        // 设置"返回"按钮的位置和大小
        GridBagConstraints returnButtonConstraints = new GridBagConstraints();
        returnButtonConstraints.gridx = 0;
        returnButtonConstraints.gridy = 11;
        returnButtonConstraints.gridwidth = 9;
        returnButtonConstraints.insets = new Insets(10, 5, 10, 5);
        add(returnButton, returnButtonConstraints);

        returnButton.addActionListener(e -> {
            //timer.stop();
            //获取顶层窗口
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.dispose(); // 关闭顶层窗口

            MainFrame mainFrame =new MainFrame();
            DifficultyChoiceInterface difficultyChoiceInterface=new DifficultyChoiceInterface();
            mainFrame.add(difficultyChoiceInterface);
        }
        );

    // 设置"重置"按钮的位置和大小
        GridBagConstraints resetButtonConstraints = new GridBagConstraints();
        resetButtonConstraints.gridx = 0;
        resetButtonConstraints.gridy = 12;
        resetButtonConstraints.gridwidth = 9;
        resetButtonConstraints.insets = new Insets(10, 5, 10, 5);
        add(resetButton, resetButtonConstraints);
        //重置按钮事件监听器
        resetButton.addActionListener(e -> {

            generate(cells,isDiagonalSudoku);

        });

        // 设置"退出"按钮的位置和大小
        GridBagConstraints exitButtonConstraints = new GridBagConstraints();
        exitButtonConstraints.gridx = 0;
        exitButtonConstraints.gridy = 13;
        exitButtonConstraints.gridwidth = 9;
        exitButtonConstraints.insets = new Insets(10, 5, 10, 5);
        add(exitButton, exitButtonConstraints);

        exitButton.addActionListener(e ->{
            System.exit(0);
        });




        }


    private static void updateTimer(JLabel timerLabel) {
        int seconds = Integer.parseInt(timerLabel.getText().replace(":", ""));
        seconds++; // 每次触发计时器增加1秒
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timerLabel.setText(timeString);
    }


        private static void generate(JTextField[][] cells,boolean isDiagonalSudoku){

        int rank= cells.length;
        Sudoku.clearAll(cells);
        int [][] sudoku =new int[rank][rank];
        generateSudoku(sudoku,isDiagonalSudoku);
        //printSudoku(sudoku);
        copyToFieldd(sudoku,cells);
        // 调用保存函数，并指定保存路径和文件名
        String filePath = "sudoku.csv";
        removeNumbers(cells);
        SudokuSaver.saveSudokuToCSV(filePath,cells);
        SudokuSaver.saveSudokuToCSV(filePath,sudoku);
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

    private static void copyToFieldd(int[][] sudoku, JTextField[][] cells) {
        int rank= sudoku.length;
        for (int i = 0; i < rank; i++) {
            for (int j = 0; j < rank; j++) {
                cells[i][j].setText(Integer.toString(sudoku[i][j]));
            }
        }
    }

    public static void generateSudoku(int[][] board,boolean isDiagonalSudoku) {
        solveSudoku(board,isDiagonalSudoku);
    }
    //回溯法解决数独问题
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
    public static boolean solveSudoku(int[][] board,boolean isDiagonalSudoku) {

        int SIZE= board.length;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                List<Integer> numbers = new ArrayList<>();
                for (int i = 1; i <= SIZE; i++) {
                    numbers.add(i);
                }
                //deleteNumbers(numbers,board);
                //System.out.println(numbers);
                Collections.shuffle(numbers);
                //System.out.println(numbers);

                if (board[row][col] == 0) {
                    for (int num : numbers) {
                            board[row][col] = num;
                        //System.out.printf("row=%d col=%d num=%d \n",row ,col,num);
                        if (SudokuArray.isValid(board,isDiagonalSudoku)) {
                            board[row][col] = num;
                            if (solveSudoku(board,isDiagonalSudoku)) {
                                //printSudoku(board);
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

    private static void deleteNumbers(List<Integer> numbers, int[][] board) {
        int rank=board.length;


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


    // 为每个文本框添加鼠标事件处理
    private void addClickEffect(JTextField[][] cells,boolean isDiagonalSudoku){
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
                    //判断权限若是可编辑代表是玩家需要填的数字，将格子内文字消除
                    if(cells[finalRow][finalCol].isEditable()){
                        cells[finalRow][finalCol].setText("");
                    }

                    // 设置选中文本框所在的列和所在的行的背景色
                    for (int i = 0; i < rank; i++) {
                        cells[finalRow][i].setBackground(new Color(187,222,251));
                        cells[i][finalCol].setBackground(new Color(187,222,251));
                    }
                    // 设置选中文本框所在的对角线的背景色
                    if(isDiagonalSudoku){
                        fillDiagonalBackground(cells,finalRow,finalCol);
                        // fillSpecialBackground(cells,finalRow,finalCol);
                    }
                    //设置选中文本框所在的宫格的背景色
                    fillBackground(cells,finalRow,finalCol);
                    //更改当前选中的格子的背景色
                    cells[finalRow][finalCol].setBackground(new Color(0x52ec7c));
                }
            });
        }
    }}


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



    private void updateBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cells[i][j].setText(Integer.toString(board[i][j]));
            }
        }
    }



    //点击更改所在宫格的背景颜色
    private void fillBackground(JTextField[][] cells,int row,int col ){
        int rank= cells.length;

        Color backgroundColor=new Color(187,222,251);
        int starRow;
        int starCol;
        switch(rank){
            case 4:
                starRow = row / 2;
                starCol = col / 2;
                for (int i = starRow*2; i < starRow*2 + 2; i++) {
                    for (int j = starCol*2; j < starCol*2+2; j++) {
                        cells[i][j].setBackground(backgroundColor);
                    }
                }
                break;

            case 6:
                starRow = row / 2;
                starCol = col / 3;
                for (int i = starRow*2; i < starRow*2 + 2; i++) {
                    for (int j = starCol*3; j < starCol*3+3; j++) {
                        cells[i][j].setBackground(backgroundColor);
                    }
                }
                break;

            case 9:
                starRow = row / 3;
                starCol = col / 3;
                for (int i = starRow*3; i < starRow*3 + 3; i++) {
                    for (int j = starCol*3; j < starCol*3+3; j++) {
                        cells[i][j].setBackground(backgroundColor);
                    }
                }
                break;
        }

    }
    //点击更改所在对角线的背景颜色
    private void fillDiagonalBackground(JTextField[][] cells,int row,int col ){
        Color backgroundColor=new Color(187,222,251);
        int rank= cells.length;
        String diagonalType="null" ;  //所处的对角线类型
        if(row==col&&row+col==rank-1){
            diagonalType="bothDiagonal";    //既是主对角线也是反对角线
        }
        else if(row==col){
            diagonalType="mainDiagonal";    //主主对角线
        }
        else if(row+col==rank-1){
            diagonalType="antiDiagonal";    //反对角线
        }


        switch (diagonalType){

            case "bothDiagonal":
                for (int i = 0; i < rank; i++) {
                    for (int j = 0; j < rank; j++) {
                        //主对角线元素
                        if(i==j||i+j==rank-1){
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                }
                break;
            case "mainDiagonal":
                for (int i = 0; i < rank; i++) {
                    for (int j = 0; j < rank; j++) {
                        //主对角线元素
                        if(i==j){
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                }
                break;
            case "antiDiagonal":
                for (int i = 0; i < rank; i++) {
                    for (int j = 0; j < rank; j++) {

                        //反对角线
                        if(i+j==rank-1){
                            cells[i][j].setBackground(backgroundColor);
                        }
                    }
                }
                break;
        }

    }


    //填充特殊宫格内的背景
    private void fillSpecialBackground(JTextField[][] cells,int row,int col ){
       int fromWhich= Sudoku.detectFromWhichGrid(row,col);
        Color backgroundColor=new Color(127,152,251);


       switch(fromWhich){
           case 1:
               cells[0][0].setBackground(backgroundColor);
               cells[0][1].setBackground(backgroundColor);
               cells[0][2].setBackground(backgroundColor);
               cells[0][3].setBackground(backgroundColor);
               cells[1][0].setBackground(backgroundColor);
               cells[1][1].setBackground(backgroundColor);
               cells[2][0].setBackground(backgroundColor);
               cells[3][0].setBackground(backgroundColor);
               cells[3][1].setBackground(backgroundColor);
               break;
           case 2:
               cells[0][4].setBackground(backgroundColor);
               cells[1][2].setBackground(backgroundColor);
               cells[1][3].setBackground(backgroundColor);
               cells[1][4].setBackground(backgroundColor);
               cells[2][1].setBackground(backgroundColor);
               cells[2][2].setBackground(backgroundColor);
               cells[2][3].setBackground(backgroundColor);
               cells[2][4].setBackground(backgroundColor);
               cells[3][2].setBackground(backgroundColor);
               break;
           case 3:
               cells[0][5].setBackground(backgroundColor);
               cells[0][6].setBackground(backgroundColor);
               cells[0][7].setBackground(backgroundColor);
               cells[0][8].setBackground(backgroundColor);
               cells[1][5].setBackground(backgroundColor);
               cells[1][6].setBackground(backgroundColor);
               cells[1][8].setBackground(backgroundColor);
               cells[2][5].setBackground(backgroundColor);
               cells[2][8].setBackground(backgroundColor);
               break;
           case 4:
               cells[4][0].setBackground(backgroundColor);
               cells[4][1].setBackground(backgroundColor);
               cells[4][2].setBackground(backgroundColor);
               cells[5][0].setBackground(backgroundColor);
               cells[5][1].setBackground(backgroundColor);
               cells[5][2].setBackground(backgroundColor);
               cells[6][1].setBackground(backgroundColor);
               cells[6][2].setBackground(backgroundColor);
               cells[7][1].setBackground(backgroundColor);
               break;
           case 5:
               cells[3][3].setBackground(backgroundColor);
               cells[3][4].setBackground(backgroundColor);
               cells[3][5].setBackground(backgroundColor);
               cells[4][3].setBackground(backgroundColor);
               cells[4][4].setBackground(backgroundColor);
               cells[4][5].setBackground(backgroundColor);
               cells[5][3].setBackground(backgroundColor);
               cells[5][4].setBackground(backgroundColor);
               cells[5][5].setBackground(backgroundColor);
               break;
           case 6:
               cells[1][7].setBackground(backgroundColor);
               cells[2][6].setBackground(backgroundColor);
               cells[2][7].setBackground(backgroundColor);
               cells[3][6].setBackground(backgroundColor);
               cells[3][7].setBackground(backgroundColor);
               cells[3][8].setBackground(backgroundColor);
               cells[4][6].setBackground(backgroundColor);
               cells[4][7].setBackground(backgroundColor);
               cells[4][8].setBackground(backgroundColor);
               break;
           case 7:
               cells[6][0].setBackground(backgroundColor);
               cells[6][3].setBackground(backgroundColor);
               cells[7][0].setBackground(backgroundColor);
               cells[7][2].setBackground(backgroundColor);
               cells[7][3].setBackground(backgroundColor);
               cells[8][0].setBackground(backgroundColor);
               cells[8][1].setBackground(backgroundColor);
               cells[8][2].setBackground(backgroundColor);
               cells[8][3].setBackground(backgroundColor);
               break;
           case 8:
               cells[5][6].setBackground(backgroundColor);
               cells[6][4].setBackground(backgroundColor);
               cells[6][5].setBackground(backgroundColor);
               cells[6][6].setBackground(backgroundColor);
               cells[6][7].setBackground(backgroundColor);
               cells[7][4].setBackground(backgroundColor);
               cells[7][5].setBackground(backgroundColor);
               cells[7][6].setBackground(backgroundColor);
               cells[8][4].setBackground(backgroundColor);
               break;
           case 9:
               cells[5][7].setBackground(backgroundColor);
               cells[5][8].setBackground(backgroundColor);
               cells[6][8].setBackground(backgroundColor);
               cells[7][7].setBackground(backgroundColor);
               cells[7][8].setBackground(backgroundColor);
               cells[8][5].setBackground(backgroundColor);
               cells[8][6].setBackground(backgroundColor);
               cells[8][7].setBackground(backgroundColor);
               cells[8][8].setBackground(backgroundColor);
               break;
       }





    }




    }
