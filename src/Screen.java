import javax.swing.*;
import java.awt.*;

public class Screen {

    public Screen(Play play) {

        JFrame frame = new JFrame("ping pong");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);


        frame.add(play);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        play.start();
    }
}
