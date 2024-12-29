import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.plaf.basic.BasicScrollBarUI;
abstract class Artifact 
{
    private String name;
    private String type;
    private String origin;
    private String curse;
    private String powers;
    public Artifact(String name, String type, String origin, String curse, String powers) 
    {
        this.name = name;
        this.type = type;
        this.origin = origin;
        this.curse = curse;
        this.powers = powers;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getOrigin() {
        return origin;
    }
    public String getCurse() {
        return curse;
    }
    public String getPowers() {
        return powers;
    }
    @Override
    public String toString() {
        return "🗡️ Treasure: " + name + "\n" +
                "⚔️ Type of Relic: " + type + "\n" +
                "📜 Tale of Origin: " + origin + "\n" +
                "🔮 Nature of Curse: " + curse + "\n" +
                "✨ Hidden Powers: " + powers + "\n" +
                "☠️━━━━━━━━━━━━━━━━━━━━━━━━━━━━━☠️\n";
    }
    public boolean matches(String searchTerm) {
        searchTerm = searchTerm.toLowerCase();
        return name.toLowerCase().contains(searchTerm) ||
                type.toLowerCase().contains(searchTerm) ||
                origin.toLowerCase().contains(searchTerm) ||
                curse.toLowerCase().contains(searchTerm) ||
                powers.toLowerCase().contains(searchTerm);
    }
    public abstract String getArtifactDetails();
}
class CursedArtifact extends Artifact 
{
    public CursedArtifact(String name, String type, String origin, String curse, String powers) {
        super(name, type, origin, curse, powers);
    }
    @Override
    public String getArtifactDetails() {
        return toString();
    }
}
class ArtifactManager 
{
    private List<Artifact> artifacts = new ArrayList<>();
    public void addArtifact(Artifact artifact) {
        artifacts.add(artifact);
    }
    public List<Artifact> searchArtifacts(String searchTerm) {
        List<Artifact> results = new ArrayList<>();
        for (Artifact artifact : artifacts) {
            if (artifact.matches(searchTerm)) {
                results.add(artifact);
            }
        }
        return results;
    }
    public String getAllArtifacts() {
        StringBuilder sb = new StringBuilder();
        for (Artifact artifact : artifacts) {
            sb.append(artifact.toString());
        }
        return sb.toString();
    }
}
class ParchmentPanel extends JPanel 
{
    private final Image backgroundImage;
    public ParchmentPanel(Image backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
public class CursedArtifactsArchive 
{
    private static final ArtifactManager manager = new ArtifactManager();
    private static Font customFont;
    private static final Color BROWN_DERBY = new Color(0x3E2723);
    private static final Color AGED_PARCHMENT = new Color(0xF4E4BC);
    private static final Color DARK_WOOD = new Color(0x543D2C);
    static {
        try {
            customFont = new Font("Serif", Font.PLAIN, 24);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static Image createParchmentBackground(int width, int height) 
    {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        GradientPaint gp = new GradientPaint(0, 0, AGED_PARCHMENT,
                width, height, new Color(0xE6D5AC));
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(new Color(0x452510, true));
        for (int i = 0; i < width; i += 3) {
            for (int j = 0; j < height; j += 3) {
                if (Math.random() < 0.2) {
                    g2d.fillRect(i, j, 1, 1);
                }
            }
        }
        g2d.dispose();
        return image;
    }
    private static void setupComponents(JFrame frame, JPanel backgroundPanel) {
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1.0;

        JLabel titleLabel = new JLabel("\u2620 Cursed Artifacts Archive \u2620", SwingConstants.CENTER);
        titleLabel.setFont(customFont.deriveFont(Font.BOLD, 28f));
        titleLabel.setForeground(BROWN_DERBY);

        Component[] labels = createStyledLabels();
        JTextField[] fields = createStyledFields();
        JTextArea outputArea = createStyledOutputArea();
        JScrollPane outputScrollPane = new JScrollPane(outputArea);
        styleScrollPane(outputScrollPane);

        JButton addButton = createStyledButton("⚔️ Add to Collection ⚔️");
        JButton searchButton = createStyledButton("🔍 Search the Archives 🔍");

        layoutComponents(inputPanel, titleLabel, labels, fields, c);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setOpaque(false);
        buttonPanel.add(addButton);
        buttonPanel.add(searchButton);

        setupActionListeners(addButton, searchButton, fields, outputArea);

        backgroundPanel.add(inputPanel, BorderLayout.NORTH);
        backgroundPanel.add(Box.createVerticalStrut(10), BorderLayout.CENTER);
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);
        backgroundPanel.add(outputScrollPane, BorderLayout.SOUTH);
    }
    private static Component[] createStyledLabels() {
        String[] labelTexts = {"🗡️ Name of Treasure:", "⚔️ Type of Relic:",
                "📜 Tale of Origin:", "🔮 Nature of Curse:", "✨ Hidden Powers:"};
        Component[] labels = new Component[labelTexts.length];

        for (int i = 0; i < labelTexts.length; i++) {
            JLabel label = new JLabel(labelTexts[i]);
            label.setFont(customFont.deriveFont(Font.BOLD, 16f));
            label.setForeground(BROWN_DERBY);
            labels[i] = label;
        }
        return labels;
    }
    private static JTextField[] createStyledFields() {
        JTextField[] fields = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            JTextField field = new JTextField(20);
            field.setFont(customFont.deriveFont(16f));
            field.setForeground(BROWN_DERBY);
            field.setBackground(new Color(0xEFEBE9));
            field.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(0x6D4C41), 2),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            field.setCaretColor(BROWN_DERBY);
            fields[i] = field;
        }
        return fields;
    }
    private static JTextArea createStyledOutputArea() {
        JTextArea outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setFont(customFont.deriveFont(16f));
        outputArea.setForeground(BROWN_DERBY);
        outputArea.setBackground(new Color(0xEFEBE9));
        outputArea.setBorder(BorderFactory.createLineBorder(new Color(0x6D4C41), 2));
        outputArea.setCaretColor(BROWN_DERBY);
        return outputArea;
    }
    private static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createTitledBorder("Artifact Collection"));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setVerticalScrollBar(new JScrollBar() {
            {
                setUI(new BasicScrollBarUI() {
                    @Override
                    protected void configureScrollBarColors() {
                        this.thumbColor = new Color(0x795548);
                        this.trackColor = new Color(0xD7CCC8);
                    }
                });
            }
        });
    }
    private static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(customFont.deriveFont(20f));
        button.setForeground(Color.WHITE);
        button.setBackground(DARK_WOOD);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    private static void layoutComponents(JPanel inputPanel, JLabel titleLabel, Component[] labels, JTextField[] fields, GridBagConstraints c) {
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        inputPanel.add(titleLabel, c);
        c.gridwidth = 1;

        for (int i = 0; i < labels.length; i++) {
            c.gridx = 0;
            c.gridy++;
            inputPanel.add(labels[i], c);
            c.gridx = 1;
            inputPanel.add(fields[i], c);
        }
    }
    private static void setupActionListeners(JButton addButton, JButton searchButton, JTextField[] fields, JTextArea outputArea) {
        addButton.addActionListener(e -> {
            String name = fields[0].getText().trim();
            String type = fields[1].getText().trim();
            String origin = fields[2].getText().trim();
            String curse = fields[3].getText().trim();
            String powers = fields[4].getText().trim();

            if (!name.isEmpty() && !type.isEmpty() && !origin.isEmpty() && !curse.isEmpty() && !powers.isEmpty()) {
                manager.addArtifact(new CursedArtifact(name, type, origin, curse, powers));
                outputArea.setText(manager.getAllArtifacts());
                clearFields(fields);
            } else {
                JOptionPane.showMessageDialog(null, "Please fill all fields before adding an artifact.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        searchButton.addActionListener(e -> {
            String searchTerm = JOptionPane.showInputDialog(null, "Enter search term:");
            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
                List<Artifact> results = manager.searchArtifacts(searchTerm.trim());
                if (results.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No artifacts found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (Artifact artifact : results) {
                        sb.append(artifact.getArtifactDetails()).append("\n");
                    }
                    outputArea.setText(sb.toString());
                }
            }
        });
    }
    private static void clearFields(JTextField[] fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cursed Artifacts Archive");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        Image backgroundImage = createParchmentBackground(800, 600);
        ParchmentPanel backgroundPanel = new ParchmentPanel(backgroundImage);
        backgroundPanel.setLayout(new BorderLayout());
        setupComponents(frame, backgroundPanel);

        frame.setContentPane(backgroundPanel);
        frame.setVisible(true);
    }
}
