import java.awt.*;
import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.*;

public class Withdraw extends JFrame implements ActionListener {

    JLabel text, amount;
    JTextField money;
    JButton withdraw, back;
    String pin;
    Withdraw(String pin){
        this.pin = pin;

        setLayout(null);

        ImageIcon image = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\ITC.png");
        setIconImage(image.getImage());

        setSize(530, 400);
        setLocation(300, 0);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#A1E3F9"));
        
        text = new JLabel("Withdraw");
        text.setFont(new Font("Tahoma", Font.BOLD, 25));
        text.setBounds(195,10,200,40);
        add(text);
        
        amount = new JLabel("Amount: ");
        amount.setFont(new Font("Tahoma", Font.BOLD, 15));
        amount.setBounds(80,100,100,40);
        add(amount);
        
        money = new JTextField();
        money.setFont(new Font("Tahoma", Font.BOLD, 15));
        money.setBounds(160,100,200,40);
        getContentPane().add(money);
        money.setColumns(15);

        // Set a limit on the input length
        ((AbstractDocument) money.getDocument()).setDocumentFilter(new DocumentFilter() {
            private static final int LIMIT = 10; // Set your limit here

            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if ((fb.getDocument().getLength() + string.length()) <= LIMIT) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if ((fb.getDocument().getLength() + text.length() - length) <= LIMIT) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });

        money.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if(!Character.isDigit(c)){
                    e.consume();
                }
            }
        });
        
        withdraw = new JButton("Withdraw");
        withdraw.setFont(new Font("Tahoma", Font.BOLD, 15));
        withdraw.setBounds(185,200,150,40);
        withdraw.addActionListener(this);
        add(withdraw);
        
        back = new JButton("Exit");
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.setBounds(185,250,150,40);
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
        new Withdraw("");
    }
}
