import java.awt.*;
import java.util.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class signupOne extends JFrame implements ActionListener{
    Random random = new Random();
    Long Random = Math.abs((random.nextLong() % 9000L) + 1000L );
    JTextField textField_username, parenTextField, emailField, addressField, cityField, pinField, Khan;
    JButton next;
    JRadioButton male, female, single, married, other1, other;
    JDateChooser dateChooser;

    signupOne(){
        // Random random = new Random();
        // Long Random = Math.abs((random.nextLong() % 9000L) + 1000L ); 

        setLayout(null);

        setSize(750, 800);
        setLocation(350, 10);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon image = new ImageIcon("E:\\Java Y2\\Project(PDI)\\Pictures\\ITC.png");
        setIconImage(image.getImage());
        
        ImageIcon I1 = new ImageIcon("E:\\Java Y2\\Project(PDI)\\Pictures\\image.png");
        Image I2 = I1.getImage().getScaledInstance(100, 100, DO_NOTHING_ON_CLOSE);
        ImageIcon I3 = new ImageIcon(I2);
        JLabel label = new JLabel(I3);
        label.setBounds(48, 10, 100, 100);
        add(label);
        
        JLabel Header = new JLabel("APPLICATION FORM NO." + Random);
        Header.setFont(new Font("Raleway", Font.BOLD, 30));
        Header.setBounds(208, 40,700,40);
        add(Header);
        
        JLabel page1 = new JLabel("Page 1: Personal Details");
        page1.setFont(new Font("Tahoma", Font.BOLD, 15));
        page1.setBounds(330, 80,400,40);
        add(page1);
        
        //name
        JLabel username = new JLabel("Full Name: ");
        username.setFont(new Font("Tahoma", Font.PLAIN, 15));
        username.setBounds(50, 150, 100, 40);
        add(username);
        //ParentName
        JLabel parentname = new JLabel("Parent's Name: ");
        parentname.setFont(new Font("Tahoma", Font.PLAIN, 15));
        parentname.setBounds(50,200,200,40);
        add(parentname);
        
        JLabel DateOfBirth = new JLabel("Date of Birth: ");
        DateOfBirth.setFont(new Font("Tahoma", Font.PLAIN, 15));
        DateOfBirth.setBounds(50,250,200,40);
        add(DateOfBirth);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(200,260,444,20);
        dateChooser.setForeground(new Color(105,105,105));
        add(dateChooser);
        
        JLabel Gender = new JLabel("Gender: ");
        Gender.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Gender.setBounds(50,300,200,40);
        add(Gender);
        
        male = new JRadioButton("Male");
        male.setBounds(200,310,60,30);
        male.setFocusable(false);
        add(male);
        
        female = new JRadioButton("Female");
        female.setBounds(400,310,200,40);
        female.setFocusable(false);
        add(female);
        
        other = new JRadioButton("Other");
        other.setBounds(600,310,200,40);
        other.setFocusable(false);
        add(other);

        ButtonGroup gendeGroup = new ButtonGroup();
        gendeGroup.add(female);
        gendeGroup.add(male);
        gendeGroup.add(other);
        
        JLabel Email = new JLabel("Email Address: ");
        Email.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Email.setBounds(50,350,200,40);
        add(Email);
        
        JLabel relationship = new JLabel("Relationship: ");
        relationship.setFont(new Font("Tahoma", Font.PLAIN, 15));
        relationship.setBounds(50,400,200,40);
        add(relationship);
        
        single = new JRadioButton("Single");
        single.setBounds(200,400,200,40);
        single.setFocusable(false);
        add(single);
        
        married = new JRadioButton("Married");
        married.setBounds(400,400,200,40);
        married.setFocusable(false);
        add(married);
        
        other1 = new JRadioButton("Other");
        other1.setBounds(600,400,200,40);
        other1.setFocusable(false);
        add(other1);
        
        ButtonGroup relationshiGroup = new ButtonGroup();
        relationshiGroup.add(single);
        relationshiGroup.add(married);
        relationshiGroup.add(other1);
        
        JLabel City = new JLabel("City: ");
        City.setFont(new Font("Tahoma", Font.PLAIN, 15));
        City.setBounds(50,450,200,40);
        add(City);
        
        JLabel Pin = new JLabel("PostCode: ");
        Pin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Pin.setBounds(50,500,200,40);
        add(Pin);
        
        JLabel KHAN = new JLabel("Khan/District: ");
        KHAN.setFont(new Font("Tahoma", Font.PLAIN, 15));
        KHAN.setBounds(50,550,200,40);
        add(KHAN);
        
        JLabel Address = new JLabel("Address: ");
        Address.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Address.setBounds(50,600,200,40);
        add(Address);
        
        //Fill name
        textField_username = new JTextField();
        textField_username.setBounds(200,160,444,25);
        textField_username.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(textField_username);
        textField_username.setColumns(15);
        
        //Fill parent name
        parenTextField = new JTextField();
        parenTextField.setBounds(200,210, 444,25);
        parenTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(parenTextField);
        parenTextField.setColumns(15);
        
        //Fill email
        emailField = new JTextField();
        emailField.setBounds(200,360, 444,25);
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(emailField);
        emailField.setColumns(15);
        
        //Fill Address
        addressField = new JTextField();
        addressField.setBounds(200,610, 444,25);
        addressField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(addressField);
        addressField.setColumns(15);
        
        cityField = new JTextField();
        cityField.setBounds(200,460, 444,25);
        cityField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(cityField);
        cityField.setColumns(15);
        
        pinField = new JTextField();
        pinField.setBounds(200,510, 444,25);
        pinField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(pinField);
        pinField.setColumns(15);

        pinField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });
        
        Khan = new JTextField();
        Khan.setBounds(200,560, 444,25);
        Khan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(Khan);
        Khan.setColumns(15);
        
        //button next
        next = new JButton("Next");
        next.setBounds(570,670,70,40);
        next.setBackground(Color.BLUE);
        next.setForeground(Color.WHITE);
        next.setFocusable(false);
        next.addActionListener(this);
        add(next);

        textField_username.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isLetter(c)){
                    e.consume();
                }
            }
        });

        parenTextField.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isLetter(c)){
                    e.consume();
                }
            }
        });        

        addressField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isLetter(c)){
                    e.consume();
                }
            }
        });

        Khan.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isLetter(c)){
                    e.consume();
                }
            }
        });

        addressField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isLetter(c)){
                    e.consume();
                }
            }
        });

        cityField.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isLetter(c)){
                    e.consume();
                }
            }
        });
        
        setVisible(true); 
    }
    
    public void actionPerformed(ActionEvent as){
        String form = "" + Random; //long
        String name = textField_username.getText(); //setText
        String Pname = parenTextField.getText();
        String date = ((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()){
            gender = "Male";
        } else if (female.isSelected()){
            gender = "Female";
        } else if (other.isSelected()){
            gender = "Other";
        }

        String email = emailField.getText();
        String relationShip = null;
        if (single.isSelected()){
            relationShip = "Single";
        } else if (married.isSelected()){
            relationShip = "Married";
        } else if (other1.isSelected()){
            relationShip = "Other";
        }

        String address = addressField.getText();
        String city = cityField.getText();
        String khans = Khan.getText();
        String pin = pinField.getText();


        try {
            if(textField_username.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null, "Name is Required");
            } else {
                Bank c = new Bank();
                String query =  "insert into signupOne value('" + form + "', '" + name + "', '" + Pname + "', '" + date + "', '" + gender + "', '" + email + "', '" + relationShip + "', '" + city + "', '" + pin + "', '" + khans + "', '" + address + "')";
                c.s.executeUpdate(query);

                setVisible(false);
                new signupTwo(form).setVisible(true);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new signupOne();
    }
}

