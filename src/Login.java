import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JButton login, clear, SignUP, AboutUs;
    JTextField textField, textField_1;
    JPasswordField pintextField;
    JLabel text, loginScreen, Card_num, password, background;

    Login() {
        setLayout(null);

        // Background Image with 60% opacity
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("C:\\Users\\ASUS\\Pictures\\Background1.jpg"));
            BufferedImage transparentImage = new BufferedImage(backgroundImage.getWidth(), backgroundImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = transparentImage.createGraphics();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
            g2d.drawImage(backgroundImage, 0, 0, null);
            g2d.dispose();
            ImageIcon backgroundIcon = new ImageIcon(transparentImage.getScaledInstance(780, 480, Image.SCALE_SMOOTH));
            JLabel backgroundLabel = new JLabel(backgroundIcon);
            backgroundLabel.setBounds(0, 0, 780, 480);
            add(backgroundLabel);

            // Adding components to the background label
            backgroundLabel.setLayout(null);

            ImageIcon l1 = new ImageIcon("D:\\All of my lessons\\Project-PDI-Team4\\Project-PDI-_Team4\\src\\image.png");
            Image l2 = l1.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
            ImageIcon l3 = new ImageIcon(l2);
            JLabel label = new JLabel(l3);
            label.setBounds(50, 0, 150, 150);
            backgroundLabel.add(label);

            setSize(790, 480);
            setTitle("Bank Account System");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);

            ImageIcon image = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\ITC.png");
            setIconImage(image.getImage());

            // Welcome Screen
            text = new JLabel("Welcome to my Bank Company");
            text.setFont(new Font("Tahoma", Font.BOLD, 25));
            text.setBounds(205, 60, 800, 40);
            backgroundLabel.add(text);

            // Login screen
            loginScreen = new JLabel("Login Screen");
            loginScreen.setFont(new Font("Tahoma", Font.PLAIN, 20));
            loginScreen.setBounds(335, 100, 400, 40);
            backgroundLabel.add(loginScreen);

            // Fill username
            Card_num = new JLabel("Card No:");
            Card_num.setFont(new Font("Raleway", Font.BOLD, 20));
            Card_num.setBounds(100, 170, 100, 40);
            backgroundLabel.add(Card_num);

            // Fill password
            password = new JLabel("PIN:");
            password.setFont(new Font("Raleway", Font.BOLD, 20));
            password.setBounds(145, 240, 50, 40);
            backgroundLabel.add(password);

            // Text of the each username and password
            textField = new JTextField();
            textField.setBounds(200, 170, 400, 40);
            textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
            backgroundLabel.add(textField);
            textField.setColumns(15);

            textField_1 = new JPasswordField();
            textField_1.setBounds(200, 240, 400, 40);
            textField_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
            backgroundLabel.add(textField_1);
            textField_1.setColumns(15);

            // Button login
            login = new JButton("Login");
            login.setBounds(280, 300, 100, 40);
            login.addActionListener(this);
            login.setForeground(Color.BLACK);
            login.setBackground(Color.GREEN);
            login.setFocusable(false);
            backgroundLabel.add(login);

            // Button About Us

            AboutUs = new JButton("About Us");
            AboutUs.setBounds(680, 20, 80, 30);
            AboutUs.addActionListener(this);
            AboutUs.setFocusable(false);
            AboutUs.setForeground(Color.WHITE);
            AboutUs.setBackground(Color.ORANGE);
            AboutUs.setFont(new Font("Tahoma", Font.PLAIN, 10));
            backgroundLabel.add(AboutUs);
            
            // Button clear
            clear = new JButton("Clear");
            clear.setBounds(400, 300, 100, 40);
            clear.addActionListener(this);
            clear.setForeground(Color.WHITE);
            clear.setBackground(Color.RED);
            clear.setFocusable(false);
            backgroundLabel.add(clear);

            // Button Sign up
            SignUP = new JButton("Sign Up");
            SignUP.setBounds(280, 350, 220, 40);
            SignUP.addActionListener(this);
            SignUP.setForeground(Color.WHITE);
            SignUP.setBackground(Color.BLUE);
            SignUP.setFocusable(false);
            backgroundLabel.add(SignUP);

            setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == clear) {
            textField.setText("");
            textField_1.setText("");
        } else if (e.getSource() == login) {
            Bank bank = new Bank();
            String cardnumber = textField.getText();
            String pin = textField_1.getText();

            String qucry = "select * from login where cardnumber = '" + cardnumber + "' and pin = '" + pin + "'";
            try {
                ResultSet set = bank.s.executeQuery(qucry);
                if (set.next()) {
                    setVisible(false);
                    new Transaction(pin).setVisible(true);
                } else {
                    System.out.println("Pin verification failed");
                    JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin Number");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == SignUP) {
            setVisible(false);
            new signupOne().setVisible(true);
        } else if (e.getSource() == AboutUs) {
            setVisible(false);
            new Info_Developer().setVisible(true); // Corrected class name
        }
    }

    public static void main(String[] args) {
        new Login();
    }
} 
