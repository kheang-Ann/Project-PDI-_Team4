import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.*;

public class Transfer extends JFrame implements ActionListener {

    JLabel text, amount, Card_no;
    JTextField money, card;
    JButton transfer, back;
    String pin;
    Transaction transaction; // Reference to Transaction class

    // Constructor that accepts both pin and transaction
    Transfer(String pin, Transaction transaction) {
        this.pin = pin;
        this.transaction = transaction;

        setLayout(null);

        ImageIcon image = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\ITC.png");
        setIconImage(image.getImage());

        setTitle("Transfer");
        setSize(520, 400);
        setLocation(300, 0);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#578FCA"));

        text = new JLabel("Transfer");
        text.setFont(new Font("Tahoma", Font.BOLD, 25));
        text.setBounds(200, 10, 200, 40);
        add(text);

        Card_no = new JLabel("Card Number: ");
        Card_no.setFont(new Font("Tahoma", Font.BOLD, 15));
        Card_no.setBounds(40, 100, 200, 40);
        add(Card_no);

        card = new JTextField();
        card.setFont(new Font("Tahoma", Font.BOLD, 15));
        card.setBounds(160, 100, 200, 40);
        add(card);

        card.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        amount = new JLabel("Amount: ");
        amount.setFont(new Font("Tahoma", Font.BOLD, 15));
        amount.setBounds(80, 150, 100, 40);
        add(amount);

        money = new JTextField();
        money.setFont(new Font("Tahoma", Font.BOLD, 15));
        money.setBounds(160, 150, 200, 40);
        money.setColumns(15);
        getContentPane().add(money);

        // Set a limit on the input length
        ((AbstractDocument) money.getDocument()).setDocumentFilter(new DocumentFilter() {
            private static final int LIMIT = 10; // Increased to allow decimal values

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
                if (!Character.isDigit(c) && c != '.') { // Allow digits and decimal point
                    e.consume();
                }
            }
        });

        transfer = new JButton("Transfer");
        transfer.setFont(new Font("Tahoma", Font.BOLD, 15));
        transfer.setBounds(185, 220, 150, 40);
        transfer.setForeground(Color.WHITE);
        transfer.setBackground(Color.BLUE);
        transfer.addActionListener(this);
        transfer.setFocusable(false);
        add(transfer);

        back = new JButton("Exit");
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.setBounds(185, 270, 150, 40);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.RED);
        back.addActionListener(this);
        back.setFocusable(false);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == transfer) {
            String number = money.getText();
            String targetCardNumber = card.getText();
            if (number.equals("") || targetCardNumber.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount and target card number.");
            } else {
                try {
                    double amount = Double.parseDouble(number);
                    if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid amount greater than zero.");
                        return;
                    }

                    setVisible(false);
                    new verify(pin, targetCardNumber, number, transaction).setVisible(true);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a valid number.");
                }
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            transaction.setVisible(true); // Return to the Transaction window
        }
    }

    class verify extends JFrame implements ActionListener {
        JButton pins, Back;
        JPasswordField fill_pin;
        JLabel pinn, text;
        String code;
        String amount;
        String targetCardNumber;
        Transaction transaction; // Reference to Transaction class

        public verify(String code, String targetCardNumber, String amount, Transaction transaction) {
            this.code = code;
            this.amount = amount;
            this.targetCardNumber = targetCardNumber;
            this.transaction = transaction;

            setLayout(null);

            text = new JLabel("Verify the pin first before transferring money");
            text.setFont(new Font("Tahoma", Font.BOLD, 15));
            text.setBounds(30, 30, 400, 20);
            add(text);

            pinn = new JLabel("Pin: ");
            pinn.setFont(new Font("Tahoma", Font.BOLD, 15));
            pinn.setBounds(50, 100, 40, 40);
            add(pinn);

            fill_pin = new JPasswordField();
            fill_pin.setBounds(100, 105, 200, 30);
            add(fill_pin);

            fill_pin.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if (!Character.isDigit(c)) {
                        e.consume();
                    }
                }
            });

            pins = new JButton("Verify");
            pins.setBounds(150, 150, 100, 40);
            pins.setFocusable(false);
            pins.addActionListener(this);
            add(pins);

            Back = new JButton("Back");
            Back.setBounds(150, 200, 100, 40);
            Back.setFocusable(false);
            Back.addActionListener(this);
            add(Back);

            setTitle("Verify the pin");
            setSize(400, 300);
            setLocation(300, 0);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent ea) {
            if (ea.getSource() == Back) {
                setVisible(false);
                new Transfer(code, transaction).setVisible(true);
            } else if (ea.getSource() == pins) {
                Bank b = new Bank();
                @SuppressWarnings("deprecation")
                String pin = fill_pin.getText();

                if (pin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the pin.");
                    return;
                }

                String query = "select * from verify where pin = '" + pin + "'";
                try {
                    ResultSet set = b.s.executeQuery(query);
                    if (set.next()) {
                        setVisible(false);
                        transferMoney();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect pin number!");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred while verifying the pin.");
                }
            }
        }

        private void transferMoney() {
            try {
                double amountValue = Double.parseDouble(amount);
                DecimalFormat df = new DecimalFormat("#,##0.00"); // Ensures two decimal places
                String formattedAmount = df.format(amountValue);

                Bank bank = new Bank();
                Date date = new Date();

                // Deduct from sender
                String query1 = "UPDATE UserBalance SET Balance = Balance - " + amountValue + " WHERE cardnumber = (SELECT cardnumber FROM login WHERE pin = '" + code + "')";
                bank.s.executeUpdate(query1);

                // Add to receiver
                String query2 = "UPDATE UserBalance SET Balance = Balance + " + amountValue + " WHERE cardnumber = '" + targetCardNumber + "'";
                bank.s.executeUpdate(query2);

                // Record the transaction
                String query3 = "INSERT INTO bank (pin, date, type, amount) VALUES ('" + code + "', '" + date + "', 'Transferred', '" + formattedAmount + "')";
                bank.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "USD " + formattedAmount + " transferred successfully to card number " + targetCardNumber);
                transaction.setVisible(true); // Return to the Transaction window
            } catch (Exception e) {
                System.out.println(e);
                JOptionPane.showMessageDialog(null, "An error occurred while transferring money.");
            }
        }
    }

    public static void main(String[] args) {
        new Transfer("", null); // For testing purposes
    }
}