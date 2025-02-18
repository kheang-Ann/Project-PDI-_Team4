import java.awt.*;
import java.util.*;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class signupOne extends JFrame implements ActionListener{
    Random random = new Random();
    Long Random = Math.abs((random.nextLong() % 9000L) + 1000L );
    JTextField textField_username, parenTextField, emailField, addressField, cityField, pinField, stateField;
    JButton next;
    JRadioButton male, female, single, married, other1, other;
    JDateChooser dateChooser;

    signupOne(){
        // Random random = new Random();
        // Long Random = Math.abs((random.nextLong() % 9000L) + 1000L ); 

        setLayout(null);

        setSize(850, 800);
        setLocation(350, 10);
        setVisible(true); 
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon I1 = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\Logo.png");
        Image I2 = I1.getImage().getScaledInstance(100, 100, DO_NOTHING_ON_CLOSE);
        ImageIcon I3 = new ImageIcon(I2);
        JLabel label = new JLabel(I3);
        label.setBounds(60, 10, 100, 100);
        add(label);

        JLabel Header = new JLabel("APPLICATION FORM NO." + Random);
        Header.setFont(new Font("Raleway", Font.BOLD, 30));
        Header.setBounds(250, 40,700,40);
        add(Header);

        JLabel page1 = new JLabel("Page 1: Personal Details");
        page1.setFont(new Font("Tahoma", Font.BOLD, 15));
        page1.setBounds(350, 80,400,40);
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
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(400,310,200,40);
        add(female);

        other = new JRadioButton("other");
        other.setBounds(600,310,200,40);
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
        add(single);

        married = new JRadioButton("married");
        married.setBounds(400,400,200,40);
        add(married);

        other1 = new JRadioButton("other");
        other1.setBounds(600,400,200,40);
        add(other1);

        ButtonGroup relationshiGroup = new ButtonGroup();
        relationshiGroup.add(single);
        relationshiGroup.add(married);
        relationshiGroup.add(other1);

        JLabel City = new JLabel("City: ");
        City.setFont(new Font("Tahoma", Font.PLAIN, 15));
        City.setBounds(50,450,200,40);
        add(City);

        JLabel Pin = new JLabel("Pin Code: ");
        Pin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Pin.setBounds(50,500,200,40);
        add(Pin);

        JLabel State = new JLabel("State: ");
        State.setFont(new Font("Tahoma", Font.PLAIN, 15));
        State.setBounds(50,550,200,40);
        add(State);

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
        addressField.setBounds(200,460, 444,25);
        addressField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(addressField);
        addressField.setColumns(15);

        cityField = new JTextField();
        cityField.setBounds(200,510, 444,25);
        cityField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(cityField);
        cityField.setColumns(15);

        pinField = new JTextField();
        pinField.setBounds(200,560, 444,25);
        pinField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(pinField);
        pinField.setColumns(15);

        stateField = new JTextField();
        stateField.setBounds(200,610, 444,25);
        stateField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        getContentPane().add(stateField);
        stateField.setColumns(15);

        //button next
        next = new JButton("Next");
        next.setBounds(570,670,70,40);
        next.addActionListener(this);
        add(next);


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
        String state = stateField.getText();
        String pin = pinField.getText();


        try {
            if(textField_username.getText().trim().equals("")){
                JOptionPane.showMessageDialog(null, "Name is Required");
            } else {
                Bank c = new Bank();
                String query =  "insert into signup value('" + form + "', '" + name + "', '" + Pname + "', '" + date + "', '" + gender + "', '" + email + "', '" + relationShip + "', '" + city + "', '" + pin + "', '" + state + "', '" + address + "')";
                c.s.executeUpdate(query);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new signupOne();
    }
}
