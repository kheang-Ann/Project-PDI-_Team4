import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;

public class Transfer extends JFrame implements ActionListener {

    JLabel text, amount, Card_no;
    JTextField money, card;
    JButton transfer, back;
    String pin;    

    Transfer(String pin) {
        this.pin = pin;

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

        money.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        transfer = new JButton("Transfer");
        transfer.setFont(new Font("Tahoma", Font.BOLD, 15));
        transfer.setBounds(185, 220, 150, 40);
        transfer.addActionListener(this);
        transfer.setFocusable(false);
        add(transfer);

        back = new JButton("Exit");
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.setBounds(185, 270, 150, 40);
        back.addActionListener(this);
        back.setFocusable(false);
        add(back);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == transfer) {
            String number = money.getText();
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount that you want to transfer.");
            } else {
                setVisible(false);
                new verify(pin, number).setVisible(true);
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pin).setVisible(true);
        }
    }

    class verify extends JFrame implements ActionListener {
        JButton pins, Back;
        JPasswordField fill_pin;
        JLabel pinn, text;
        String code;
        String amount;

        public verify(String code, String amount) {
            this.code = code;
            this.amount = amount;

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
                new Transfer(code).setVisible(true);
            } else if (ea.getSource() == pins) {
                Bank b = new Bank();
                String pin = fill_pin.getText();

                if (pin.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter the pin.");
                    return;
                }

                String query = "select * from verify where pin = '" + pin + "'";
                System.out.println("Executing query: " + query);
                try {
                    ResultSet set = b.s.executeQuery(query);
                    if (set.next()) {
                        System.out.println("Pin verified successfully.");
                        setVisible(false);
                        transferMoney();
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect pin number!");
                    }
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex);
                    JOptionPane.showMessageDialog(null, "An error occurred while verifying the pin.");
                }
            }
        }

        private void transferMoney() {
            try {
                Bank bank = new Bank();
                Date date = new Date();
                String query = "insert into bank value('" + code + "', '" + date + "', 'Transfered' , '" + amount
                        + "')";
                bank.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "USD " + amount + " transferred successfully");
                new Transaction(code).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new Transfer("");
    }
}
