import javax.swing.*;
import java.awt.*;

public class DifficultyChoiceInterface extends JPanel {
    private JComboBox<String> difficultyComboBox;
    private JButton startButton;

    public DifficultyChoiceInterface() {

        //JPanel mainPanel=new JPanel();  //主容器

        //setTitle("数独");
        //setSize(1000,1000);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        difficultyComboBox = new JComboBox<>(new String[]{"简单4x4", "中等6x6", "困难9x9"});
        startButton = new JButton("开始游戏");
        startButton.addActionListener(e -> {
            generateSudoku();
        });

        add(new JLabel("请选择数独难度:"));
        add(difficultyComboBox);
        add(startButton);
        //add(mainPanel);     //把主容器加入到窗口

        //pack();
        //setLocationRelativeTo(null); // 居中显示
        setVisible(true);
    }


    private void generateSudoku(){
        String difficulty = (String) difficultyComboBox.getSelectedItem();
        //dispose(); // 关闭难度选择窗口
        removeAll();
        //取最后一个数字就是数独的阶数
        int rank=Integer.parseInt(String.valueOf(difficulty.charAt(difficulty.length()-1)));
        SudokuGameInterface sudokuGame = new SudokuGameInterface(rank); // 创建数独游戏窗口
        add(sudokuGame);

        Window window = SwingUtilities.getWindowAncestor(sudokuGame);
        if (window instanceof JFrame) {
            JFrame parentFrame = (JFrame) window;
            switch(rank){
                case 4:
                    parentFrame.setSize(280, 450);
                    break;
                case 6:
                    parentFrame.setSize(420, 530);
                    break;
                default:
                    parentFrame.setSize(540, 680);
                    break;
            }

        }
        //super.pack();
        revalidate();
        repaint();
        sudokuGame.setVisible(true); // 显示数独游戏窗口
    }


}
