import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

        ImageIcon image = new ImageIcon("E:\\Java Y2\\Project(PDI)\\Pictures\\ITC.png");
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
            pins.setForeground(Color.WHITE);
            pins.setBackground(Color.BLUE);
            pins.setFocusable(false);
            pins.addActionListener(this);
            add(pins);

            Back = new JButton("Back");
            Back.setBounds(150, 200, 100, 40);
            Back.setForeground(Color.WHITE);
            Back.setBackground(Color.RED);
            Back.setFocusable(false);
            Back.addActionListener(this);
            add(Back);

            setTitle("Verify the pin");
            setSize(420, 300);
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
                String enteredPin = fill_pin.getText();

                if (enteredPin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the pin.");
                    return;
                }


                try {
                    // Get the stored hashed PIN
                    String query = "SELECT pin FROM verify WHERE pin = ?";
                    PreparedStatement pstmt = b.c.prepareStatement(query);
                    pstmt.setString(1, code); // Use the stored PIN hash received from Transaction
                    ResultSet set = pstmt.executeQuery();
                    
                    if (set.next()) {
                        setVisible(false);
                        transferMoney();
                    } else {
                        JOptionPane.showMessageDialog(null, "PIN verification failed. Please contact support.");
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred while verifying the pin.");
                }
            }
        }

        private void transferMoney() {
            try {
                double amountValue = Double.parseDouble(amount);
                Bank bank = new Bank();
                Date date = new Date();
                java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                // Deduct from sender using prepared statement
                String query1 = "UPDATE UserBalance SET Balance = Balance - ? WHERE cardnumber = (SELECT cardnumber FROM login WHERE pin = ?)";
                PreparedStatement ps1 = bank.c.prepareStatement(query1);
                ps1.setDouble(1, amountValue);
                ps1.setString(2, code); // code is already the hashed PIN
                ps1.executeUpdate();

                // Add to receiver using prepared statement
                String query2 = "UPDATE UserBalance SET Balance = Balance + ? WHERE cardnumber = ?";
                PreparedStatement ps2 = bank.c.prepareStatement(query2);
                ps2.setDouble(1, amountValue);
                ps2.setString(2, targetCardNumber);
                ps2.executeUpdate();

                // Record transaction using prepared statement
                String query3 = "INSERT INTO bank (pin, date, type, amount) VALUES (?, ?, ?, ?)";
                PreparedStatement ps3 = bank.c.prepareStatement(query3);
                ps3.setString(1, code);
                ps3.setDate(2, sqlDate);
                ps3.setString(3, "Transferred");
                ps3.setDouble(4, amountValue);
                ps3.executeUpdate();

                JOptionPane.showMessageDialog(null, "USD " + amountValue + " transferred successfully to card number " + targetCardNumber);
                transaction.setVisible(true); // Return to the Transaction window
            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred while transferring money.");
            }
        }
    }

    public static void main(String[] args) {
        new Transfer("", null); // For testing purposes
    }
}
