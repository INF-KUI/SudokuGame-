import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;

public class SudokuSaver {


    public static void saveSudokuToCSV(String filePath, JTextField[][] cells,boolean isDiagonalSudoku) {

        int rank=cells.length;

        try {
            FileWriter writer = new FileWriter(filePath,true);
            LocalDateTime now = LocalDateTime.now();

            writer.write("时间:");
            writer.write(",");
            writer.write(String.valueOf(now));
            writer.write("\n");
            if(isDiagonalSudoku){
                writer.write(Integer.toString(rank)+"阶对角线数独"+",");
            }
            else{
                writer.write(Integer.toString(rank)+"阶数独"+",");
            }

            writer.write("数独题目:\n");

            //写入数独题目内容到CSV文件
            for (int row = 0; row < rank; row++) {
                for (int col = 0; col < rank; col++) {

                    String cellValue = cells[row][col].getText();
                    writer.write(cellValue);

                    // 每个单元格之间使用逗号分隔
                    if (col < rank-1) {
                        writer.write(",");
                    }
                }

                // 每行结束时换行
                writer.write("\n");
            }

            writer.close();
            //System.out.println("数独保存成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSudokuToCSV(String filePath, int [][] sudoku,boolean isDiagonalSudoku) {

        int rank=sudoku.length;
        try {
            FileWriter writer = new FileWriter(filePath,true);
            writer.write("\n");
            writer.write("数独答案:\n");

            //写入数独答案内容到CSV文件
            for (int row = 0; row < rank; row++) {
                for (int col = 0; col < rank; col++) {
                    String  cellValue=Integer.toString(sudoku[row][col]);
                    //String cellValue = cells[row][col].getText();
                    writer.write(cellValue);

                    // 每个单元格之间使用逗号分隔
                    if (col < rank-1) {
                        writer.write(",");
                    }
                }

                // 每行结束时换行
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
}



}
