
import javax.swing.*;

public class Infor_Developer extends JFrame {

    Infor_Developer() {

        ImageIcon icon = new ImageIcon("E:\\Java Y2\\Project(PDI)\\src\\ITC.png");
        setIconImage(icon.getImage());

        setTitle("Information of Developer Bank account management system");
        setSize(1000,800);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

    }


    public static void main(String[] args) {
        new Infor_Developer();
    }
}
