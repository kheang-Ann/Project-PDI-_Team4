import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class History extends JFrame implements ActionListener {

    String pin;
    JButton back;

    public History(String pin) {
        this.pin = pin;

        setSize(750, 750);
        setLocation(300, 0);
        getContentPane().setBackground(Color.decode("#00BD99"));
        setLayout(null);

        JLabel title = new JLabel("Transaction History");
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setBounds(280, 20, 200, 30);
        add(title);

        JTextArea historyArea = new JTextArea();
        historyArea.setBounds(50, 60, 650, 500);
        historyArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
        historyArea.setEditable(false);
        add(historyArea);

        back = new JButton("Back");
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.setBounds(280, 600, 150, 40);
        back.addActionListener(this);
        add(back);

        Bank bank = new Bank();
        List<String> history = bank.getTransactionHistory(pin);

        // Debugging: Print the pin and the size of the history list
        System.out.println("PIN: " + pin);
        System.out.println("Number of transactions: " + history.size());

        // Reverse the order of the transactions
        Collections.reverse(history);

        for (String transaction : history) {
            historyArea.append(transaction + "\n");
        }

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Transaction(pin).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new History("");
    }
}
