import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import javax.swing.*;

public class Info_Developer extends JFrame implements ActionListener {
    private JButton back;

    public Info_Developer() {
        setTitle("About Us");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        ImageIcon image = new ImageIcon("E:\\Java Y2\\Project(PDI)\\ITC.png");
        setIconImage(image.getImage());

        // Background Panel
        JLabel background = new JLabel(new ImageIcon(new ImageIcon(
            "E:\\Java Y2\\Project(PDI)\\backgrond.jpg")
            .getImage().getScaledInstance(1024, 700, Image.SCALE_SMOOTH)));

        background.setLayout(new GridBagLayout()); // Center everything
        // Title
        JLabel titleLabel = new JLabel("Developer Of Bank Account System", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));

        // Team Members Panel
        JPanel teamPanel = createTeamMembers();
        teamPanel.setOpaque(false);

        // Content Panel (Centering everything)
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.add(titleLabel, BorderLayout.NORTH);
        contentPanel.add(teamPanel, BorderLayout.CENTER);

        // Back Button Panel
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.setOpaque(false); // Make panel transparent
                
        back = new JButton("Back");
        back.setPreferredSize(new Dimension(100, 40)); // Set the preferred size of the button
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.setBounds(300,600,100,40);
        back.addActionListener(this);
        back.setFocusable(false);
        back.setForeground(Color.BLACK); // Change text color for visibility
                
        backPanel.add(back);
        contentPanel.add(backPanel, BorderLayout.SOUTH); // Add back button panel to content panel
                
        // Center content in background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        background.add(contentPanel, gbc);

        setContentPane(background);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    private static JPanel createTeamMembers() {
        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20)); // Add spacing
        teamPanel.setOpaque(false);

        teamPanel.add(createTeamMember("Backend(Leader)", "Kheang Ann", "E:\\Java Y2\\Project(PDI)\\ann.JPG", "https://kheangann.netlify.app/"));
        teamPanel.add(createTeamMember("Frontend(Assistant)", "Tat Chansereyvong", "E:\\Java Y2\\Project(PDI)\\Vong.jpg", "https://practice-uxui.netlify.app/"));
        teamPanel.add(createTeamMember("Database(Assistant)", "Try Khemchhun", "E:\\Java Y2\\Project(PDI)\\chhun.jpg", "https://trykhemchhun.netlify.app/"));

        return teamPanel;
    }

    private static JPanel createTeamMember(String role, String name, String imageUrl, String PortfolioUrl) {
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        memberPanel.setOpaque(false);
        memberPanel.setPreferredSize(new Dimension(200, 300));

        // Profile Image
        ImageIcon originalIcon = new ImageIcon(imageUrl);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel profileLabel = new JLabel(scaledIcon);

        // Role Label
        JLabel roleLabel = new JLabel(role, SwingConstants.CENTER);
        roleLabel.setForeground(Color.WHITE);
        roleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        // Name Label
        JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));

        // Portfolio Button
        JButton portfolioButton = new JButton("Portfolio");
        portfolioButton.setBackground(Color.WHITE);
        portfolioButton.setForeground(Color.BLACK);
        portfolioButton.setFocusable(false);
        portfolioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        portfolioButton.addActionListener(e -> {
            try {
                Desktop.getDesktop().browse(new URI(PortfolioUrl));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Center the components inside member panel
        profileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        portfolioButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components
        memberPanel.add(profileLabel);
        memberPanel.add(Box.createVerticalStrut(10));
        memberPanel.add(roleLabel);
        memberPanel.add(Box.createVerticalStrut(5));
        memberPanel.add(nameLabel);
        memberPanel.add(Box.createVerticalStrut(10));
        memberPanel.add(portfolioButton);

        return memberPanel;
    }

    public static void main(String[] args) {
        new Info_Developer();
    }
}

