import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pinchange extends JFrame implements ActionListener{
    JLabel text, pintext, repeat;
    JPasswordField pinsField, repField;
    JButton change, back;
    String pinchange;
    Pinchange(String pinchange){
        this.pinchange = pinchange;
        setLayout(null);

        ImageIcon image = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\ITC.png");
        setIconImage(image.getImage());


        text = new JLabel("CHANGE YOUR PIN");
        text.setFont(new Font("System", Font.BOLD, 25));
        text.setBounds(130,50,300 ,40);
        add(text);

        pintext = new JLabel("New PIN:");
        pintext.setFont(new Font("System", Font.BOLD, 20));
        pintext.setBounds(80,150,200 ,40);
        add(pintext);

        pinsField = new JPasswordField();
        pinsField.setFont(new Font("Raleway", Font.BOLD,25));
        pinsField.setBounds(220,155,200,30);
        add(pinsField);

        repeat = new JLabel("Enter again:");
        repeat.setFont(new Font("System", Font.BOLD, 20));
        repeat.setBounds(80,220,200 ,40);
        add(repeat);

        repField  = new JPasswordField();
        repField.setFont(new Font("Raleway",Font.BOLD, 25));
        repField.setBounds(220,225,200,30);
        add(repField);

        change = new JButton("Change");
        change.setFont(new Font("Tahoma", Font.BOLD, 20));
        change.setBounds(150,300,200,40);
        change.addActionListener(this);
        add(change);

        back = new JButton("Back");
        back.setFont(new Font("Tahoma", Font.BOLD, 20));
        back.setBounds(150,350,200,40);
        back.addActionListener(this);
        add(back);

        setSize(520,500);
        setLocation(300,0);
        getContentPane().setBackground(Color.decode("#3674B5"));
        //setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == change){
            try {
                String npin = pinsField.getText();
                String again = repField.getText();

                if(!npin.equals(again)){
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match.");
                    return;
                }
                if (npin.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter PIN.");
                    return;
                }
                if(again.equals("")){
                    JOptionPane.showMessageDialog(null, "Please enter a repeat PIN.");
                    return;
                }
                Bank bank = new Bank();

                String query1 = "update bank set pin = '" +npin+"' where pin ='" +pinchange+"'";
                String query2 = "update login set pin = '" +npin+"' where pin ='" +pinchange+"'";
                String query3 = "update signupTwo set pin = '" +npin+"' where pin ='" +pinchange+"'";

                bank.s.executeUpdate(query1);
                bank.s.executeUpdate(query2);
                bank.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                setVisible(false);
                new Transaction(npin).setVisible(true);

            } catch (Exception ae) {
                System.out.println("Change button clicked!");
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Transaction(pinchange).setVisible(true);
        }
    }


    public static void main(String[] args) {
        new Pinchange("");
    }
}
