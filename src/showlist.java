
import java.awt.Color;
import javax.swing.JFrame;

public class showlist extends JFrame {

    String pin;
    public showlist(String pin) {
        this.pin = pin;


        setSize(500,500);
        setLocation(300,0);
        getContentPane().setBackground(Color.decode("#00BD99"));
        //setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new showlist("");
    }
}
