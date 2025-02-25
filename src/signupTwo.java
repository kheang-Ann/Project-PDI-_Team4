import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class signupTwo extends JFrame implements ActionListener{
    JTextField idCard, pan, Senior;
    JButton Submit, cancel;
    JRadioButton yes, no , yes2, no2;
    JComboBox incomes, educations, job, reli, countrys;
    JLabel Existing, page2, country, religion, income, education, quailification, Job, PAN, IDcard;
    String form;

    signupTwo(String form){
        this.form = form;

        setLayout(null);

        
        setSize(850, 800);
        setLocation(350, 10);
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ImageIcon I1 = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\Logo.png");
        Image I2 = I1.getImage().getScaledInstance(100, 100, DO_NOTHING_ON_CLOSE);
        ImageIcon I3 = new ImageIcon(I2);
        JLabel label = new JLabel(I3);
        label.setBounds(60, 10, 100, 100);
        add(label);
        
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2 ");
        
        
        page2 = new JLabel("Page 2: Additional Details");
        page2.setFont(new Font("Tahoma", Font.BOLD, 20));
        page2.setBounds(300, 80,400,40);
        add(page2);
        
        country = new JLabel("Country: "); //Religion
        country.setFont(new Font("Tahoma", Font.PLAIN, 15));
        country.setBounds(50, 150, 100, 40);
        add(country);
        
        String countries[] = {"Cambodia", "Thailand"," Vietnam"," Malaysia", " Laos", "Singapore","Myanmar", "Indonesia"};
        countrys = new JComboBox(countries);
        countrys.setBounds(200,160,444,25);
        add(countrys);
        
        religion = new JLabel("Religions: ");       //Category
        religion.setFont(new Font("Tahoma", Font.PLAIN, 15));
        religion.setBounds(50,200,200,40);
        add(religion);
        
        String religions[] = {"General","Islam", "Hinduism", "Buddhism", "Christianity"};
        reli = new JComboBox(religions);
        reli.setBounds(200,210, 444,25);
        add(reli);
        
        
        income = new JLabel("Income: ");    //Income
        income.setFont(new Font("Tahoma", Font.PLAIN, 15));
        income.setBounds(50,250,200,40);
        add(income);
        
        String Income[] = {"Null","< 500 $", "< 5,000 $  ", "< 5,000,000 $ "};
        incomes = new JComboBox(Income);
        incomes.setBounds(200,260,444,25);
        add(incomes);
        
        education = new JLabel("Educational ");    // Education
        education.setFont(new Font("Tahoma", Font.PLAIN, 15));
        education.setBounds(50,300,200,40);
        add(education);
        
        String edu[] = {"High School","Bachelor Degree", "Graduated ", "Just a chill guy"};
        educations = new JComboBox(edu);
        educations.setBounds(200,320,444,25);
        add(educations);
        
        quailification = new JLabel("Quailifcation ");
        quailification.setFont(new Font("Tahoma", Font.PLAIN, 15));
        quailification.setBounds(50,320,200,40);
        add(quailification);
        
        Job = new JLabel("Job: ");   //job
        Job.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Job.setBounds(50,385,200,40);
        add(Job);
        
        String jobs[] = {"Saller","Self Employed", "Bussiness", "Student", "Other"};
        job = new JComboBox(jobs);
        job.setBounds(200,390,444,25);
        add(job);
        
        PAN = new JLabel("PAN Number: ");        //PAN number
        PAN.setFont(new Font("Tahoma", Font.PLAIN, 15));
        PAN.setBounds(50,450,200,40);
        add(PAN);
        pan = new JTextField();
        pan.setBounds(200,460, 444,25);
        pan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(pan);
        pan.setColumns(15);
        
        IDcard = new JLabel("National ID card: ");     //National ID card
        IDcard.setFont(new Font("Tahoma", Font.PLAIN, 15));
        IDcard.setBounds(50,500,200,40);
        add(IDcard);
        idCard = new JTextField();
        idCard.setBounds(200,510, 444,25);
        idCard.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(idCard);
        idCard.setColumns(15);
        
        JLabel Senior = new JLabel("Senior Citizen(60 up): ");      //Senior Citizen
        Senior.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Senior.setBounds(50,550,200,40);
        add(Senior);
        
        yes = new JRadioButton("Yes");
        yes.setBounds(250,550,100,40);
        add(yes);
        
        no = new JRadioButton("No");
        no.setBounds(350,550,100,40);
        add(no);
        
        ButtonGroup citi = new ButtonGroup();
        citi.add(yes);
        citi.add(no);
        
        
        Existing = new JLabel("Existing Account: ");
        Existing.setFont(new Font("Tahoma", Font.PLAIN, 15));
        Existing.setBounds(50,600,200,40);
        add(Existing);
        
        yes2 = new JRadioButton("Yes");
        yes2.setBounds(250,600,100,40);
        add(yes2);
        
        no2 = new JRadioButton("No");
        no2.setBounds(350,600,100,40);
        add(no2);
        
        ButtonGroup exist = new ButtonGroup();
        exist.add(yes2);
        exist.add(no2);
        
        cancel = new JButton("Cancel");
        cancel.setFont(new Font("Raleway", Font.BOLD,14));
        cancel.setBounds(420,670,100,40);
        cancel.addActionListener(this);
        add(cancel);
        
        //button next
        Submit = new JButton("Submit");
        Submit.setBounds(570,670,100,40);
        Submit.addActionListener(this);
        add(Submit);
        
        setVisible(true); 
        }

    public void actionPerformed(ActionEvent as){
        String countryy = (String) countrys.getSelectedItem(); //setText
        String Sreligion = (String) reli.getSelectedItem();
        String sincome = (String) incomes.getSelectedItem();
        String Edu = (String) educations.getSelectedItem();
        String jobss = (String) job.getSelectedItem();
        String citi = null;
        if (yes.isSelected()){
            citi = "Yes";
        } else if (no.isSelected()){
            citi = "No";
        }

        String exist = null;
        if(yes2.isSelected()){
            exist = "Yes";
        } else if (no2.isSelected()){
            exist = "No";
        }

        String span = pan.getText();
        String card = idCard.getText();

        if(as.getSource() == Submit){
            Random random = new Random();
            String cardnumber = "" + Math.abs((random.nextLong() % 9000000L));
    
            String pinnumber = "" + Math.abs((random.nextLong() % 900L));
            try {
                
                Bank c = new Bank();
                String query =  "insert into signupTwo value('" +form +"', '" + countryy + "', '" + Sreligion + "', '" + sincome + "', '" + Edu + "', '" + jobss + "', '" + span + "', '" + card + "', '" + citi + "', '" + exist + "')";
                String login = "insert into login value('" +form +"', '" + cardnumber + "', '" + pinnumber + "')";
                
                c.s.executeUpdate(query);
                c.s.executeUpdate(login);
    
                JOptionPane.showMessageDialog(null, "Card number " + cardnumber + "\n Pin " + pinnumber);   
    
                setVisible(false);
                new Transaction(form).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else if(as.getSource() == cancel){
            setVisible(false);
            new Login().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new signupTwo("");
    }
}
