import javax.swing.JFrame;
import java.awt.*;

public class Main  {
    /**
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("STATKI");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setMinimumSize(new Dimension(450, 450));
    Application app  = new Application(frame);


    app.showMainMenu();

    }

}
