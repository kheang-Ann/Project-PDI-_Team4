import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class signupTwo extends JFrame implements ActionListener {
    JTextField idCard, ph;
    JButton submit, cancel;
    JRadioButton yes, no, yes2, no2;
    JComboBox<String> incomes, educations, job, reli, countrys;
    String form;

    signupTwo(String form) {
        this.form = form;
        setLayout(null);

        setSize(850, 800);
        setLocation(350, 10);
        setLocationRelativeTo(null);
        setTitle("Sign Up");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon image = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\ITC.png");
        setIconImage(image.getImage());

        ImageIcon I1 = new ImageIcon("E:\\Java Y2\\Project(PDI)\\image.png");
        Image I2 = I1.getImage().getScaledInstance(100, 100, DO_NOTHING_ON_CLOSE);
        ImageIcon I3 = new ImageIcon(I2);
        JLabel label = new JLabel(I3);
        label.setBounds(48, 10, 100, 100);
        add(label);

        JLabel page2 = new JLabel("Page 2: Additional Details");
        page2.setFont(new Font("Tahoma", Font.BOLD, 20));
        page2.setBounds(300, 80, 400, 40);
        add(page2);

        JLabel countryLabel = new JLabel("Country: ");
        countryLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        countryLabel.setBounds(50, 150, 100, 40);
        add(countryLabel);

        String[] countries = { "Cambodia", "Thailand", "Vietnam", "Malaysia", "Laos", "Singapore", "Myanmar",
                "Indonesia" };
        countrys = new JComboBox<>(countries);
        countrys.setBounds(200, 160, 444, 25);
        add(countrys);

        JLabel religionLabel = new JLabel("Religion: ");
        religionLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        religionLabel.setBounds(50, 200, 200, 40);
        add(religionLabel);

        String[] religions = { "General", "Islam", "Hinduism", "Buddhism", "Christianity" };
        reli = new JComboBox<>(religions);
        reli.setBounds(200, 210, 444, 25);
        add(reli);

        JLabel incomeLabel = new JLabel("Income: ");
        incomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        incomeLabel.setBounds(50, 250, 200, 40);
        add(incomeLabel);

        String[] Income = { "Null", "< 500 $", "< 5,000 $", "< 5,000,000 $" };
        incomes = new JComboBox<>(Income);
        incomes.setBounds(200, 260, 444, 25);
        add(incomes);

        JLabel educationLabel = new JLabel("Educational : ");
        educationLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        educationLabel.setBounds(50, 300, 200, 40);
        add(educationLabel);

        String[] edu = { "High School", "Bachelor Degree", "Graduated", "Just a chill guy" };
        educations = new JComboBox<>(edu);
        educations.setBounds(200, 320, 444, 25);
        add(educations);

        JLabel jobLabel = new JLabel("Job: ");
        jobLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jobLabel.setBounds(50, 385, 200, 40);
        add(jobLabel);

        String[] jobs = { "Seller", "Self Employed", "Business", "Student", "Other" };
        job = new JComboBox<>(jobs);
        job.setBounds(200, 390, 444, 25);
        add(job);

        JLabel phoneLabel = new JLabel("Phone Number: ");
        phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        phoneLabel.setBounds(50, 450, 200, 40);
        add(phoneLabel);

        ph = new JTextField();
        ph.setBounds(200, 460, 444, 25);
        ph.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(ph);

        ph.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        JLabel IDcardLabel = new JLabel("National ID Card: ");
        IDcardLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        IDcardLabel.setBounds(50, 500, 200, 40);
        add(IDcardLabel);

        idCard = new JTextField();
        idCard.setBounds(200, 510, 444, 25);
        idCard.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(idCard);

        idCard.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        JLabel seniorLabel = new JLabel("Senior Citizen (60+): ");
        seniorLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        seniorLabel.setBounds(50, 550, 200, 40);
        add(seniorLabel);

        yes = new JRadioButton("Yes");
        no = new JRadioButton("No");
        ButtonGroup seniorGroup = new ButtonGroup();
        seniorGroup.add(yes);
        seniorGroup.add(no);

        yes.setBounds(250, 550, 100, 40);
        yes.setFocusable(false);
        no.setBounds(350, 550, 100, 40);
        no.setFocusable(false);
        add(yes);
        add(no);

        JLabel existingLabel = new JLabel("Existing Account: ");
        existingLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        existingLabel.setBounds(50, 600, 200, 40);
        add(existingLabel);

        yes2 = new JRadioButton("Yes");
        no2 = new JRadioButton("No");
        ButtonGroup existingGroup = new ButtonGroup();
        existingGroup.add(yes2);
        existingGroup.add(no2);

        yes2.setBounds(250, 600, 100, 40);
        yes2.setFocusable(false);
        no2.setBounds(350, 600, 100, 40);
        no2.setFocusable(false);
        add(yes2);
        add(no2);

        cancel = new JButton("Cancel");
        cancel.setBounds(420, 670, 100, 40);
        cancel.setFocusable(false);
        cancel.addActionListener(this);
        add(cancel);

        submit = new JButton("Submit");
        submit.setBounds(570, 670, 100, 40);
        submit.setFocusable(false);
        submit.addActionListener(this);
        add(submit);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            String country = (String) countrys.getSelectedItem();
            String Sreligion = (String) reli.getSelectedItem();
            String sincome = (String) incomes.getSelectedItem();
            String Edu = (String) educations.getSelectedItem();
            String jobss = (String) job.getSelectedItem();
            String senior = yes.isSelected() ? "Yes" : "No";
            String existingAccount = yes2.isSelected() ? "Yes" : "No";
            String phone = ph.getText();
            String id = idCard.getText();

            String cardNumber = "" + (1000000 + new Random().nextInt(9000000)); // Ensures 7-digit number
            String pinNumber = "" + (100 + new Random().nextInt(900)); // Ensures 3-digit number

            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem",
                    "root", "!@ann2024@!")) {

                String query1 = "INSERT INTO signupTwo (form, country, Sreligion, Income, Education, jobss, Pan, ID, SeniorCitizen, ExistingAccount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement ps1 = conn.prepareStatement(query1);
                ps1.setString(1, form);
                ps1.setString(2, country);
                ps1.setString(3, Sreligion);
                ps1.setString(4, sincome);
                ps1.setString(5, Edu);
                ps1.setString(6, jobss);
                ps1.setString(7, phone);
                ps1.setString(8, id);
                ps1.setString(9, senior);
                ps1.setString(10, existingAccount);
                ps1.executeUpdate();

                String query2 = "CALL insert_pin(?, ?, ?)";
                PreparedStatement ps2 = conn.prepareStatement(query2);
                ps2.setString(1, pinNumber);
                ps2.setString(2, form);
                ps2.setString(3, cardNumber);
                ps2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Card number: " + cardNumber + "\nPIN: " + pinNumber);
                setVisible(false);
                new Transaction(form).setVisible(true);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancel) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new signupTwo("");
    }
}
