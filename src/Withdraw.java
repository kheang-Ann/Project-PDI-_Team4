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
    Transaction transaction; // Reference to Transaction class

    // Constructor that accepts both pin and transaction
    Withdraw(String pin, Transaction transaction) {
        this.pin = pin;
        this.transaction = transaction;

        setLayout(null);

        ImageIcon image = new ImageIcon("E:\\Java Y2\\Project(PDI)\\Pictures\\ITC.png");
        setIconImage(image.getImage());

        setSize(530, 400);
        setLocation(300, 0);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#A1E3F9"));

        text = new JLabel("Withdraw");
        text.setFont(new Font("Tahoma", Font.BOLD, 25));
        text.setBounds(195, 10, 200, 40);
        add(text);

        amount = new JLabel("Amount: ");
        amount.setFont(new Font("Tahoma", Font.BOLD, 15));
        amount.setBounds(80, 100, 100, 40);
        add(amount);

        money = new JTextField();
        money.setFont(new Font("Tahoma", Font.BOLD, 15));
        money.setBounds(160, 100, 200, 40);
        getContentPane().add(money);
        money.setColumns(15);

        // Set a limit on the input length
        ((AbstractDocument) money.getDocument()).setDocumentFilter(new DocumentFilter() {
            private static final int LIMIT = 8; // Set your limit here

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

        money.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        withdraw = new JButton("Withdraw");
        withdraw.setFont(new Font("Tahoma", Font.BOLD, 15));
        withdraw.setBounds(185, 200, 150, 40);
        withdraw.setForeground(Color.WHITE);
        withdraw.setBackground(Color.BLUE);
        withdraw.setFocusable(false);
        withdraw.addActionListener(this);
        add(withdraw);

        back = new JButton("Exit");
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.setBounds(185, 250, 150, 40);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.RED);
        back.setFocusable(false);
        back.addActionListener(this);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdraw) {
            String number = money.getText();
            Date date = new Date();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw.");
            } else {
                try {
                    double amount = Double.parseDouble(number);
                    //DecimalFormat df = new DecimalFormat("#,##0.00"); // Ensures two decimal places
                    //String formattedAmount = df.format(amount);

                    Bank bank = new Bank();
                    String query = "INSERT INTO bank (pin, date, type, amount) VALUES ('" + pin + "', '" + date + "', 'Withdraw', '" + amount + "')";
                    bank.s.executeUpdate(query);

                    // Update the balance in the Transaction class
                    transaction.withdrawMoney(amount);

                    JOptionPane.showMessageDialog(null, "USD " + amount + " withdraw successfully.");
                    setVisible(false);
                    transaction.setVisible(true); // Return to the Transaction window
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
                } catch (Exception e) {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, "An error occurred. Please try again later.");
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            transaction.setVisible(true); // Return to the Transaction window
        }
    }

    public static void main(String[] args) {
        new Withdraw("", null); // For testing purposes
    }
}
