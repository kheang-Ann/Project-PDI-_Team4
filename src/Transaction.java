import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Transaction extends JFrame implements ActionListener{

    JLabel Bank_acc;
    JButton withdraw, deposit, Acc_list,transfer ,exit, changepin;
    String pin;

    Transaction(String pin){
        this.pin = pin;

        setLayout(null);

        ImageIcon image = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\ITC.png");
        setIconImage(image.getImage());

        ImageIcon l1 = new ImageIcon("E:\\Java Y2\\Project(PDI)\\src\\image.png");
        Image l2 = l1.getImage().getScaledInstance(180, 180, Image.SCALE_DEFAULT);
        ImageIcon l3 = new ImageIcon(l2);
        JLabel label = new JLabel(l3);
        label.setBounds(30, 0, 180, 180);
        add(label);

        setSize(800, 800);
        setLocation(300,0);
        getContentPane().setBackground(Color.decode("#00BD99"));
        //setUndecorated(true);
        
        Bank_acc = new JLabel("Bank Account System");
        Bank_acc.setFont(new Font("Tahoma", Font.BOLD, 25));
        Bank_acc.setBounds(265, 200,300,40);
        add(Bank_acc);

        deposit = new JButton("Deposit");
        deposit.setFont(new Font("Tahoma", Font.BOLD, 20));
        deposit.setBounds(250,300,300,40);
        deposit.addActionListener(this);
        deposit.setFocusable(false);
        add(deposit);

        withdraw = new JButton("Withdraw");
        withdraw.setFont(new Font("Tahoma", Font.BOLD, 20));
        withdraw.setBounds(250,350,300,40);
        withdraw.addActionListener(this);
        withdraw.setFocusable(false);
        add(withdraw);
        
        
        transfer = new JButton("Transfer");
        transfer.setFont(new Font("Tahoma", Font.BOLD, 20));
        transfer.setBounds(250,400,300,40);
        transfer.addActionListener(this);
        transfer.setFocusable(false);
        add(transfer);

        Acc_list = new JButton("View History");
        Acc_list.setFont(new Font("Tahoma", Font.BOLD, 20));
        Acc_list.setBounds(250,500,300,40);
        Acc_list.addActionListener(this);
        Acc_list.setFocusable(false);
        add(Acc_list);

        changepin = new JButton("Change Pin");
        changepin.setFont(new Font("Tahoma", Font.BOLD, 20));
        changepin.setBounds(250,450,300,40);
        changepin.addActionListener(this);
        changepin.setFocusable(false);
        add(changepin);
        
        exit = new JButton("Exit");
        exit.setFont(new Font("Tahoma", Font.BOLD, 20));
        exit.setBounds(250,550,300,40);
        exit.addActionListener(this);
        exit.setFocusable(false);
        add(exit);
        
        setResizable(false);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == exit){
            System.exit(0);
        } else if(ae.getSource() == deposit){
            setVisible(false);
            new Deposit(pin).setVisible(true);
        } else if (ae.getSource() == withdraw){
            setVisible(false);
            new Withdraw(pin).setVisible(true);
        } else if (ae.getSource() == transfer){
            setVisible(false);
            new Transfer(pin).setVisible(true);
        } else if(ae.getSource() == Acc_list){
            setVisible(true);
            new History(pin).setVisible(true);
        } else if(ae.getSource() == changepin){
            setVisible(false);
            new Pinchange(pin).setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Transaction("");
    }
}