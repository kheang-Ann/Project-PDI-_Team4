import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;
import javax.swing.*;

public class Infro_Developer extends JFrame{
    Button back;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Team Section");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1024, 700);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);

            // Background Panel
            JLabel background = new JLabel(new ImageIcon(new ImageIcon(
                "E:\\Java Y2\\Project(PDI)\\backgrond.jpg")
                .getImage().getScaledInstance(1024, 700, Image.SCALE_SMOOTH)));

            background.setLayout(new GridBagLayout()); // Center everything

            // Title
            JLabel titleLabel = new JLabel("Developer of Bank Account System", SwingConstants.CENTER);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Arial", Font.BOLD, 40));

            // Team Members Panel
            JPanel teamPanel = createTeamMembers();
            teamPanel.setOpaque(false);

            // Content Panel (Centering everything)
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BorderLayout());
            contentPanel.setOpaque(false);
            contentPanel.add(titleLabel, BorderLayout.NORTH);
            contentPanel.add(teamPanel, BorderLayout.CENTER);

            Button back = new Button("Back");
            back.setBounds(500,600,100,40);
            back.setFont(new Font("Tahoma", Font.BOLD, 15));
            add(back);

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

        teamPanel.add(createTeamMember("Backend", "Kheang Ann", "E:\\Java Y2\\Project(PDI)\\ann.JPG"));
        teamPanel.add(createTeamMember("Frontend", "Tat Chansereyvong", "E:\\Java Y2\\Project(PDI)\\chhun.jpg"));
        teamPanel.add(createTeamMember("Database and Security", "Try Khemchhun", "E:\\Java Y2\\Project(PDI)\\Vong.jpg"));

        return teamPanel;
    }

    private static JPanel createTeamMember(String role, String name, String imageUrl) {
        JPanel memberPanel = new JPanel();
        memberPanel.setLayout(new BoxLayout(memberPanel, BoxLayout.Y_AXIS));
        memberPanel.setOpaque(false);
        memberPanel.setPreferredSize(new Dimension(200, 300));

        // Profile Image
        JLabel profileLabel = new JLabel(new ImageIcon(new ImageIcon(imageUrl)
            .getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH)));

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
        portfolioButton.setFont(new Font("Arial", Font.PLAIN, 14));

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
