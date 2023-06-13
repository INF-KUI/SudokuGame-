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

                int [][] a ={{   1,3,2,4},
                                {2,4,1,3,},
                                {3,2,4,1},
                                {4,1,3,2}};
if(SudokuArray.isValid(a)){
    System.out.println("11111");
}
else{
    System.out.println("000000");
}


               // difficultyChoiceInterface.addSubContainer(panel);

            }
        });
    }
}
