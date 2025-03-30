import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.ResultSet;

public class Pinchange extends JFrame implements ActionListener {
    JLabel text, pintext, repeat;
    JPasswordField pinsField, repField;
    JButton change, back;
    String pinchange;

    Pinchange(String pinchange) {
        this.pinchange = pinchange;
        setLayout(null);

        ImageIcon image = new ImageIcon("Project-PDI-_Team4/Pictures/ITC.png");
        setIconImage(image.getImage());

        text = new JLabel("CHANGE YOUR PIN");
        text.setFont(new Font("System", Font.BOLD, 25));
        text.setBounds(130, 50, 300, 40);
        add(text);

        pintext = new JLabel("New PIN:");
        pintext.setFont(new Font("System", Font.BOLD, 20));
        pintext.setBounds(80, 150, 200, 40);
        add(pintext);

        pinsField = new JPasswordField();
        pinsField.setFont(new Font("Raleway", Font.BOLD, 25));
        pinsField.setBounds(220, 155, 200, 30);
        add(pinsField);

        pinsField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        repeat = new JLabel("Enter again:");
        repeat.setFont(new Font("System", Font.BOLD, 20));
        repeat.setBounds(80, 220, 200, 40);
        add(repeat);

        repField = new JPasswordField();
        repField.setFont(new Font("Raleway", Font.BOLD, 25));
        repField.setBounds(220, 225, 200, 30);
        add(repField);

        repField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
            }
        });

        change = new JButton("Change");
        change.setFont(new Font("Tahoma", Font.BOLD, 20));
        change.setBounds(150, 300, 200, 40);
        change.setBackground(Color.green);
        change.setForeground(Color.BLACK);
        change.setFocusable(false);
        change.addActionListener(this);
        change.setFocusable(false);
        add(change);

        back = new JButton("Back");
        back.setFont(new Font("Tahoma", Font.BOLD, 20));
        back.setBounds(150, 350, 200, 40);
        back.setBackground(Color.RED);
        back.setForeground(Color.BLACK);
        back.setFocusable(false);
        back.addActionListener(this);
        back.setFocusable(false);
        add(back);

        setSize(520, 500);
        setLocation(300, 0);
        getContentPane().setBackground(Color.decode("#3674B5"));
        // setUndecorated(true);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == change) {
            try {
                String npin = new String(pinsField.getPassword());
                String again = new String(repField.getPassword());

                // Validate PIN inputs
                if (!npin.equals(again)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match.");
                    return;
                }
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter PIN.");
                    return;
                }
                if (again.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please enter a repeat PIN.");
                    return;
                }

                // PIN validation for security
                if (npin.length() < 3) {
                    JOptionPane.showMessageDialog(null, "PIN must be at least 3 digits.");
                    return;
                }

                Bank bank = new Bank();

                try {
                    // Begin transaction
                    bank.s.getConnection().setAutoCommit(false);

                    // CRITICAL: First, insert the new PIN into verify table
                    String insertVerifyQuery = "INSERT IGNORE INTO verify (pin) VALUES ('" + npin + "')";
                    bank.s.executeUpdate(insertVerifyQuery);

                    // Then update all tables that reference the verify table
                    // Update login table
                    String updateLoginQuery = "UPDATE login SET pin = '" + npin + "' WHERE pin = '" + pinchange + "'";
                    int loginResult = bank.s.executeUpdate(updateLoginQuery);

                    // Update bank table
                    String updateBankQuery = "UPDATE bank SET pin = '" + npin + "' WHERE pin = '" + pinchange + "'";
                    int bankResult = bank.s.executeUpdate(updateBankQuery);

                    // Delete the old PIN from verify table AFTER updating all references
                    // Only if it's no longer referenced by any other table
                    String checkReferencesQuery = "SELECT COUNT(*) FROM login WHERE pin = '" + pinchange +
                            "' UNION SELECT COUNT(*) FROM bank WHERE pin = '" + pinchange + "'";
                    ResultSet rs = bank.s.executeQuery(checkReferencesQuery);
                    boolean canDeleteOldPin = true;
                    while (rs.next()) {
                        if (rs.getInt(1) > 0) {
                            canDeleteOldPin = false;
                            break;
                        }
                    }

                    if (canDeleteOldPin) {
                        String deleteOldPinQuery = "DELETE FROM verify WHERE pin = '" + pinchange + "'";
                        bank.s.executeUpdate(deleteOldPinQuery);
                    }

                    // Commit the transaction
                    bank.s.getConnection().commit();

                    if (loginResult > 0 || bankResult > 0) {
                        JOptionPane.showMessageDialog(null, "PIN changed successfully");
                        setVisible(false);
                        new Transaction(npin).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "No records found to update. PIN change failed.");
                    }

                } catch (Exception sqlEx) {
                    // Roll back in case of error
                    try {
                        bank.s.getConnection().rollback();
                    } catch (Exception rbEx) {
                        rbEx.printStackTrace();
                    }
                    throw sqlEx; // Rethrow for outer catch
                } finally {
                    // Reset auto-commit
                    try {
                        bank.s.getConnection().setAutoCommit(true);
                    } catch (Exception acEx) {
                        acEx.printStackTrace();
                    }
                }

            } catch (Exception ae) {
                ae.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error changing PIN: " + ae.getMessage());
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
