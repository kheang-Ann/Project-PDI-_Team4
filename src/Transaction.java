import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;

public class Transaction extends JFrame implements ActionListener {

    JLabel Bank_acc, balanceLabel; // Added balanceLabel
    JButton withdraw, deposit, Acc_list, transfer, exit, changepin;
    String pin;

    Transaction(String pin) {
        this.pin = pin;

        setLayout(null);

        ImageIcon image = new ImageIcon("E:\\Java Y2\\Project(PDI)\\ITC.png");
        setIconImage(image.getImage());

        ImageIcon l1 = new ImageIcon("E:\\Java Y2\\Project(PDI)\\image.png");
        Image l2 = l1.getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        ImageIcon l3 = new ImageIcon(l2);
        JLabel label = new JLabel(l3);
        label.setBounds(30, 0, 180, 180);
        add(label);

        setSize(800, 800);
        setLocation(300, 0);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#00BD99"));

        Bank_acc = new JLabel("Bank Account System");
        Bank_acc.setFont(new Font("Tahoma", Font.BOLD, 25));
        Bank_acc.setBounds(265, 200, 300, 40);
        add(Bank_acc);

        // Balance Label
        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        balanceLabel.setBounds(250, 250, 300, 30);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(balanceLabel);

        // Fetch and display the balance when the Transaction page is opened
        updateBalanceLabel();

        deposit = new JButton("Deposit");
        deposit.setFont(new Font("Tahoma", Font.BOLD, 20));
        deposit.setBounds(250, 300, 300, 40);
        deposit.addActionListener(this);
        deposit.setFocusable(false);
        add(deposit);

        withdraw = new JButton("Withdraw");
        withdraw.setFont(new Font("Tahoma", Font.BOLD, 20));
        withdraw.setBounds(250, 350, 300, 40);
        withdraw.addActionListener(this);
        withdraw.setFocusable(false);
        add(withdraw);

        transfer = new JButton("Transfer");
        transfer.setFont(new Font("Tahoma", Font.BOLD, 20));
        transfer.setBounds(250, 400, 300, 40);
        transfer.addActionListener(this);
        transfer.setFocusable(false);
        add(transfer);

        Acc_list = new JButton("View History");
        Acc_list.setFont(new Font("Tahoma", Font.BOLD, 20));
        Acc_list.setBounds(250, 500, 300, 40);
        Acc_list.addActionListener(this);
        Acc_list.setFocusable(false);
        add(Acc_list);

        changepin = new JButton("Change Pin");
        changepin.setFont(new Font("Tahoma", Font.BOLD, 20));
        changepin.setBounds(250, 450, 300, 40);
        changepin.addActionListener(this);
        changepin.setFocusable(false);
        add(changepin);

        exit = new JButton("Exit");
        exit.setFont(new Font("Tahoma", Font.BOLD, 20));
        exit.setBounds(250, 550, 300, 40);
        exit.addActionListener(this);
        exit.setFocusable(false);
        add(exit);

        setResizable(false);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == exit) {
            System.exit(0);
        } else if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit(pin, this).setVisible(true); // Pass 'this' to Deposit
        } else if (ae.getSource() == withdraw) {
            setVisible(false);
            new Withdraw(pin, this).setVisible(true); // Pass 'this' to Withdraw
        } else if (ae.getSource() == transfer) {
            setVisible(false);
            new Transfer(pin, this).setVisible(true); // Pass 'this' to Transfer
        } else if (ae.getSource() == Acc_list) {
            setVisible(true);
            new History(pin).setVisible(true);
        } else if (ae.getSource() == changepin) {
            setVisible(false);
            new Pinchange(pin).setVisible(true);
        }
    }

    // Method to fetch and update the balance label
    public void updateBalanceLabel() {
        Bank bank = new Bank();
        String query = "SELECT Balance FROM UserBalance WHERE cardnumber = (SELECT cardnumber FROM login WHERE pin = '"
                + pin + "')";
        try {
            ResultSet set = bank.s.executeQuery(query);
            if (set.next()) {
                String balance = set.getString("Balance");
                balanceLabel.setText("Current Balance: $" + balance);
            } else {
                balanceLabel.setText("Balance: $0.00");
            }
        } catch (Exception e) {
            System.out.println(e);
            balanceLabel.setText("Error fetching balance");
        }
    }

    // Method to deposit money
    public boolean depositMoney(double amount) {
        Bank bank = new Bank();
        String query = "UPDATE UserBalance SET Balance = Balance + " + amount
                + " WHERE cardnumber = (SELECT cardnumber FROM login WHERE pin = '" + pin + "')";
        try {
            int rowsUpdated = bank.s.executeUpdate(query);
            if (rowsUpdated > 0) {
                updateBalanceLabel(); // Update the balance label after deposit
                return true; // Return success
            } else {
                return false; // Return failure
            }
        } catch (Exception e) {
            System.out.println(e);
            return false; // Return failure
        }
    }

    // Method to withdraw money
    public boolean withdrawMoney(double amount) {
        Bank bank = new Bank();
        String query = "UPDATE UserBalance SET Balance = Balance - " + amount
                + " WHERE cardnumber = (SELECT cardnumber FROM login WHERE pin = '" + pin + "')";
        try {
            int rowsUpdated = bank.s.executeUpdate(query);
            if (rowsUpdated > 0) {
                updateBalanceLabel(); // Update the balance label after withdrawal
                return true; // Return success
            } else {
                return false; // Return failure
            }
        } catch (Exception e) {
            System.out.println(e);
            return false; // Return failure
        }
    }

    // Method to transfer money
    public boolean transferMoney(String targetCardNumber, double amount) {
        Bank bank = new Bank();
        String query1 = "UPDATE UserBalance SET Balance = Balance - " + amount
                + " WHERE cardnumber = (SELECT cardnumber FROM login WHERE pin = '" + pin + "')";
        String query2 = "UPDATE UserBalance SET Balance = Balance + " + amount + " WHERE cardnumber = '"
                + targetCardNumber + "'";
        try {
            int rowsUpdated1 = bank.s.executeUpdate(query1); // Deduct from sender
            int rowsUpdated2 = bank.s.executeUpdate(query2); // Add to receiver
            if (rowsUpdated1 > 0 && rowsUpdated2 > 0) {
                updateBalanceLabel(); // Update the balance label after transfer
                return true; // Return success
            } else {
                return false; // Return failure
            }
        } catch (Exception e) {
            System.out.println(e);
            return false; // Return failure
        }
    }

    public static void main(String[] args) {
        new Transaction("");
    }
}
