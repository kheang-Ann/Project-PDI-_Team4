import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.swing.*;

public class History extends JFrame implements ActionListener {

    String pin;
    JButton back, download;
    JTextArea historyArea;

    public History(String pin) {
        this.pin = pin;

        ImageIcon image = new ImageIcon("E:\\Java Y2\\Project(PDI)\\ITC.png");
        setIconImage(image.getImage());

        setSize(750, 750);
        setLocation(300, 0);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#00BD99"));
        setLayout(null);

        JLabel title = new JLabel("Transaction History");
        title.setFont(new Font("Tahoma", Font.BOLD, 20));
        title.setBounds(280, 20, 200, 30);
        add(title);

        historyArea = new JTextArea();
        historyArea.setBounds(50, 60, 650, 500);
        historyArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
        historyArea.setEditable(false);
        add(historyArea);

        back = new JButton("Back");
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.setBounds(280, 600, 150, 40);
        back.setFocusable(false);
        back.addActionListener(this);
        add(back);
        
        download = new JButton("Download CSV");
        download.setFont(new Font("Tahoma", Font.BOLD, 15));
        download.setBounds(450, 600, 150, 40);
        download.setFocusable(false);
        download.addActionListener(this);
        add(download);

        Bank bank = new Bank();
        List<String> history = bank.getTransactionHistory(pin);

        System.out.println("PIN: " + pin);
        System.out.println("Number of transactions: " + history.size());

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
        } else if (ae.getSource() == download) {
            saveHistoryAsCSV();
        }
    }

    private void saveHistoryAsCSV() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save CSV");
            int userSelection = fileChooser.showSaveDialog(this);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                String filePath = fileChooser.getSelectedFile().getAbsolutePath() + ".csv";
                FileWriter writer = new FileWriter(filePath);
                writer.write("Transaction History\n");
                writer.write(historyArea.getText().replace("\n", "\r\n"));
                writer.close();
                JOptionPane.showMessageDialog(this, "History saved successfully!");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving history", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new History("");
    }
}