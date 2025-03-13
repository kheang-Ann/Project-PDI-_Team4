import java.awt.*;
import java.net.URI;

import javax.swing.*;

public class Infro_Developer extends JFrame{
    Button back;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("About Us");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1024, 700);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            ImageIcon image = new ImageIcon("C:\\Users\\ASUS\\OneDrive\\Pictures\\Saved Pictures\\ITC.png");
            frame.setIconImage(image.getImage());

            // Background Panel
            JLabel background = new JLabel(new ImageIcon(new ImageIcon(
                "D:\\All of my lessons\\Project-PDI-Team4\\Project-PDI-_Team4\\src\\background.jpg")
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

            // Button back = new Button("Back");
            // back.setBounds(500,600,100,40);
            // back.setFont(new Font("Tahoma", Font.BOLD, 15));
            // add(back);

            // Center content in background
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.CENTER;
            background.add(contentPanel, gbc);

            frame.setContentPane(background);
            frame.setVisible(true);
        });
        // public void actionPerformed(ActionEvent e){
        //     if(e.getSource() == back){

        //     }
        // }
    }

    private static JPanel createTeamMembers() {
        JPanel teamPanel = new JPanel();
        teamPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20)); // Add spacing
        teamPanel.setOpaque(false);

        teamPanel.add(createTeamMember("Backend", "Kheang Ann", "D:\\All of my lessons\\Project-PDI-Team4\\Project-PDI-_Team4\\src\\ann.jpg", "https://kheangann.netlify.app/"));
        teamPanel.add(createTeamMember("Frontend", "Tat Chansereyvong", "D:\\All of my lessons\\Project-PDI-Team4\\Project-PDI-_Team4\\src\\Vong.JPG", "https://practice-uxui.netlify.app/"));
        teamPanel.add(createTeamMember("Database and Security", "Try Khemchhun", "D:\\All of my lessons\\Project-PDI-Team4\\Project-PDI-_Team4\\src\\chhun.jpg", "https://practice-uxui.netlify.app/"));

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
}
