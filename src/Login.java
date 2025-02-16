import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {

    JButton login, clear, SignUP;
    JTextField textField, textField_1;
    JPasswordField pintextField;
    Login(){
        

        setLayout(null);
        
        ImageIcon l1 = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\Logo.png");
        Image l2 = l1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon l3 = new ImageIcon(l2);
        JLabel label = new JLabel(l3);
        label.setBounds(70, 10, 100, 100);
        add(label);

        
        setSize(800, 480);
        setVisible(true);
        setTitle("Bank account system");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon image = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\ITC.png");
        setIconImage(image.getImage());

        //welcome Screen
        JLabel text = new JLabel("Welcome to my Bank Company");
        text.setFont(new Font("Tahoma", Font.BOLD, 25));
        text.setBounds(250, 40, 800, 40);
        add(text);
        
        //Login screen
        JLabel loginScreen = new JLabel("Login Screen");
        loginScreen.setFont(new Font("Tahoma", Font.PLAIN, 20));
        loginScreen.setBounds(400, 80, 400, 40);
        getContentPane().add(loginScreen);

        //Fill username
        JLabel username = new JLabel("Card No:");
        username.setFont(new Font("Raleway", Font.BOLD, 20));
        username.setBounds(100, 160,100,40);
        getContentPane().add(username);

        //Fill password
        JLabel password = new JLabel("PIN:");
        password.setFont(new Font("Raleway", Font.BOLD, 20));
        password.setBounds(140,240,50,40);
        getContentPane().add(password);
        
        //Text of the each username and password
        textField = new JTextField();
        textField.setBounds(200,160,400,40);
        textField.setFont(new Font("Tahoma", Font.PLAIN,22));
        getContentPane().add(textField);
        textField.setColumns(15);
        //textField.setText("admin"); //for username

        textField_1 = new JPasswordField();
        textField_1.setBounds(200,240,400,40);
        textField_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
        getContentPane().add(textField_1);
        textField_1.setColumns(15);

        //Button login
        login = new JButton("Login");
        login.setBounds(280,300,100,40);
        login.addActionListener(this);
        getContentPane().add(login);
        //Buttonclear 
        clear = new JButton("Clear");
        clear.setBounds(400,300,100,40);
        clear.addActionListener(this);
        getContentPane().add(clear);

        //Button Sign up
        SignUP = new JButton("Sign Up");
        SignUP.setBounds(280,350,220,40);
        SignUP.addActionListener(this);;
        getContentPane().add(SignUP);

    }
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == clear){
            textField.setText("");
            textField_1.setText("");

        } else if (e.getSource() == login) {
            
        } else if (e.getSource() == SignUP) {

        }
    }
    
    public static void main(String[] args) {
        new Login();
    }
}