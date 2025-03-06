import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;

public class Transfer extends JFrame implements ActionListener {

    JLabel text, amount, Card_no;
    JTextField money, card;
    JButton withdraw, back;
    String pin;
    Transfer(String pin){
        this.pin = pin;

        setLayout(null);

        ImageIcon image = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\ITC.png");
        setIconImage(image.getImage());

        setSize(520, 400);
        setLocation(300, 0);
        getContentPane().setBackground(Color.decode("#0BC7D2"));
        
        text = new JLabel("Transfer");
        text.setFont(new Font("Tahoma", Font.BOLD, 25));
        text.setBounds(200,10,200,40);
        add(text);
        
        Card_no = new JLabel("Card Number: ");
        Card_no.setFont(new Font("Tahoma", Font.BOLD, 15));
        Card_no.setBounds(80,100,200,40);
        add(Card_no);
        
        card = new JTextField();
        card.setFont(new Font("Tahoma", Font.BOLD, 15));
        card.setBounds(160, 100, 200, 40);
        
        amount = new JLabel("Amount: ");
        amount.setFont(new Font("Tahoma", Font.BOLD, 15));
        amount.setBounds(80,150,100,40);
        add(amount);
        
        money = new JTextField();
        money.setFont(new Font("Tahoma", Font.BOLD, 15));
        money.setBounds(160,150,200,40);
        getContentPane().add(money);
        money.setColumns(15);
        
        withdraw = new JButton("Transfer");
        withdraw.setFont(new Font("Tahoma", Font.BOLD, 15));
        withdraw.setBounds(185,220,150,40);
        withdraw.addActionListener(this);
        add(withdraw);
        
        back = new JButton("Exit");
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.setBounds(185,270,150,40);
        back.addActionListener(this);
        add(back);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == withdraw){
            String number = money.getText();
            Date date = new Date();
            if(number.equals("")){
                JOptionPane.showMessageDialog(null, "Please enter amount that you want to withdraw.");
            } else {
                try {
                    Bank bank = new Bank();
                    String query = "insert into bank value('" +pin+"', '" + date + "', 'Withdraw' , '" +number+ "')";
                    bank.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "USD "+number+" Withdraw successfully");
                    setVisible(false);
                    new Transaction(pin).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }else if (ae.getSource() == back){
            setVisible(false);
            new Transaction(pin).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Transfer("");
    }
}
