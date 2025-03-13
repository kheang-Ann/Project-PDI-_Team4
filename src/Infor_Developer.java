
import java.awt.Image;
import javax.swing.*;

public class Infor_Developer extends JFrame {

    Infor_Developer() {
        setLayout(null);

        ImageIcon icon = new ImageIcon("E:\\Java Y2\\Project(PDI)\\src\\ITC.png");
        setIconImage(icon.getImage());

        ImageIcon Ann = new ImageIcon("E:\\Java Y2\\Project(PDI)\\ann.JPG");
        Image Ann1 = Ann.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon Ann2 = new ImageIcon(Ann1);
        JLabel A_1 = new JLabel(Ann2);
        A_1.setBounds(10, 0, 180, 180);
        add(A_1);

        ImageIcon Chun = new ImageIcon("E:\\Java Y2\\Project(PDI)\\chhun.jpg");
        Image Chun1 = Chun.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon Chun3 = new ImageIcon(Chun1);
        JLabel C_1 = new JLabel(Chun3);
        C_1.setBounds(200, 0, 180, 180);
        add(C_1);



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
