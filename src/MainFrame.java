import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame{

    public MainFrame(){
        setLocation(640,300);
        setTitle("数独 3121005183 王宗奎");  //窗口名称
        setSize(400,200);

        setMinimumSize(new Dimension(300,200));
        new DifficultyChoiceInterface();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame =new MainFrame();
                DifficultyChoiceInterface difficultyChoiceInterface=new DifficultyChoiceInterface();
                mainFrame.add(difficultyChoiceInterface);

            }
        });
    }
}
