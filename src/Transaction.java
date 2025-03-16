import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Transaction extends JFrame implements ActionListener{

    JLabel Bank_acc;
    JButton withdraw, deposit, Acc_list,transfer ,exit, changepin, logout;
    String pin;

    Transaction(String pin){
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

        // ImageIcon backgroundIcon = new ImageIcon("E:\\Java Y2\\Project(PDI)\\ATM.jpg");
        // Image backgroundImage = backgroundIcon.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        // backgroundIcon = new ImageIcon(backgroundImage);
        // JLabel backgroundLabel = new JLabel(backgroundIcon);
        // backgroundLabel.setBounds(0, 0, 800, 800);
        // add(backgroundLabel);

        //Icon Deposit
        ImageIcon D = new ImageIcon("E:\\Java Y2\\Project(PDI)\\Deposit.png");
        Image D1 = D.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon D2 = new ImageIcon(D1);

        ImageIcon T = new ImageIcon("E:\\Java Y2\\Project(PDI)\\transfer.png");
        Image T1 = T.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon T2 = new ImageIcon(T1);

        ImageIcon P = new ImageIcon("E:\\Java Y2\\Project(PDI)\\pinChange.png");
        Image P1 = P.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon P2 = new ImageIcon(P1);

        ImageIcon H = new ImageIcon("E:\\Java Y2\\Project(PDI)\\HIstory.png");
        Image H1 = H.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon H2 = new ImageIcon(H1);

        ImageIcon W = new ImageIcon("E:\\Java Y2\\Project(PDI)\\src\\image.png");
        Image W1 = W.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon W2 = new ImageIcon(W1);

        ImageIcon L = new ImageIcon("E:\\Java Y2\\Project(PDI)\\Logout.png");
        Image L1 = L.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon L2 = new ImageIcon(L1);

        ImageIcon E = new ImageIcon("E:\\Java Y2\\Project(PDI)\\E.png");
        Image E1 = E.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon E2 = new ImageIcon(E1);

        setSize(800, 800);
        setLocation(300,0);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#00BD99"));
        //setUndecorated(true);
        
        Bank_acc = new JLabel("Bank Account System");
        Bank_acc.setFont(new Font("Tahoma", Font.BOLD, 25));
        Bank_acc.setBounds(265, 200,300,40);
        add(Bank_acc);

        deposit = new JButton("Deposit", D2);
        deposit.setFont(new Font("Tahoma", Font.BOLD, 20));
        deposit.setBounds(280,300,250,40);
        deposit.setForeground(Color.WHITE);
        deposit.setBackground(Color.BLUE);
        deposit.addActionListener(this);
        deposit.setFocusable(false);
        add(deposit);

        withdraw = new JButton("Withdraw",W2);
        withdraw.setFont(new Font("Tahoma", Font.BOLD, 20));
        withdraw.setBounds(280,350,250,40);
        withdraw.setForeground(Color.WHITE);
        withdraw.setBackground(Color.BLUE);
        withdraw.addActionListener(this);
        withdraw.setFocusable(false);
        add(withdraw);
        
        
        transfer = new JButton("Transfer", T2);
        transfer.setFont(new Font("Tahoma", Font.BOLD, 20));
        transfer.setBounds(280,400,250,40);
        transfer.setForeground(Color.WHITE);
        transfer.setBackground(Color.BLUE);
        transfer.addActionListener(this);
        transfer.setFocusable(false);
        add(transfer);

        Acc_list = new JButton("View History", H2);
        Acc_list.setFont(new Font("Tahoma", Font.BOLD, 20));
        Acc_list.setBounds(280,500,250,40);
        Acc_list.setForeground(Color.WHITE);
        Acc_list.setBackground(Color.BLUE);
        Acc_list.addActionListener(this);
        Acc_list.setFocusable(false);
        add(Acc_list);

        changepin = new JButton("Change Pin", P2);
        changepin.setFont(new Font("Tahoma", Font.BOLD, 20));
        changepin.setBounds(280,450,250,40);
        changepin.setForeground(Color.WHITE);
        changepin.setBackground(Color.BLUE);
        changepin.addActionListener(this);
        changepin.setFocusable(false);
        add(changepin);
        
        exit = new JButton("Exit", E2);
        exit.setFont(new Font("Tahoma", Font.BOLD, 20));
        exit.setBounds(280,550,250,40);
        exit.setForeground(Color.white);
        exit.setBackground(Color.RED);
        exit.addActionListener(this);
        exit.setFocusable(false);
        add(exit);

        logout = new JButton("Log out", L2);
        logout.setFont(new Font("Tahoma", Font.BOLD, 20));
        logout.setBounds(280,600,250,40);
        logout.setForeground(Color.WHITE);
        logout.setBackground(Color.RED);
        logout.addActionListener(this);
        logout.setFocusable(false);
        add(logout);
        
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
        } else if(ae.getSource() == logout){
            setVisible(false);
            new Login().setVisible(true);
        }
    }
    public static void main(String[] args) {
        new Transaction("");
    }
}