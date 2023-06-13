import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    public  Main(){
        setTitle("数独");  //窗口名称
        this.setSize(300,100);
        new DifficultyChoiceInterface();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main main=new Main();
                DifficultyChoiceInterface difficultyChoiceInterface=new DifficultyChoiceInterface();
                main.add(difficultyChoiceInterface);



               // difficultyChoiceInterface.addSubContainer(panel);

            }
        });
    }
}
