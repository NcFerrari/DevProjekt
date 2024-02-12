package lp.fe;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

public class App {

    private final Map<String, String> database = new HashMap<>();
    private final JTextField name = new JTextField(20);
    private final JTextField surname = new JTextField(20);
    private final JTextField address = new JTextField(20);
    private final JTextField phone = new JTextField(10);
    private JPanel mainPanel;

    public void init() {
        JFrame frame = new JFrame();
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        addPanel(frame);
        addComponents();
    }

    private void addComponents() {
        JPanel formPanel = new JPanel();
        formPanel.setSize(380, 200);
        formPanel.setLayout(new GridBagLayout());
        mainPanel.add(formPanel);

        addItem(formPanel, new JLabel("Jméno:"), 0, 0, 1, GridBagConstraints.EAST);
        addItem(formPanel, new JLabel("Příjmení:"), 0, 1, 1, GridBagConstraints.EAST);
        addItem(formPanel, new JLabel("Adresa:"), 0, 2, 1, GridBagConstraints.EAST);
        addItem(formPanel, new JLabel("Telefon:"), 0, 3, 1, GridBagConstraints.EAST);

        addItem(formPanel, name, 1, 0, 2, GridBagConstraints.WEST);
        addItem(formPanel, surname, 1, 1, 2, GridBagConstraints.WEST);
        addItem(formPanel, address, 1, 2, 2, GridBagConstraints.WEST);
        addItem(formPanel, phone, 1, 3, 1, GridBagConstraints.WEST);

        JButton saveButton = new JButton("Uložit");
        addItem(formPanel, saveButton, 0, 4, 1, GridBagConstraints.EAST);
        JButton editButton = new JButton("Upravit");
        addItem(formPanel, editButton, 1, 4, 1, GridBagConstraints.EAST);
        JButton loadButton = new JButton("Načíst");
        addItem(formPanel, loadButton, 2, 4, 1, GridBagConstraints.EAST);

        JTextArea output = new JTextArea();
        JScrollPane outputScrollPane = new JScrollPane(output);
        outputScrollPane.setLocation(0, formPanel.getHeight());
        outputScrollPane.setSize(350, 250);
        mainPanel.add(outputScrollPane);

        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private void addPanel(JFrame frame) {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        frame.add(mainPanel);
    }

    private void addItem(JPanel panel, JComponent c, int x, int y, int width, int align) {
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = x;
        gc.gridy = y;
        gc.gridwidth = width;
        gc.gridheight = 1;
        gc.weightx = 100.0;
        gc.weighty = 100.0;
        gc.insets = new Insets(5, 5, 5, 5);
        gc.anchor = align;
        gc.fill = GridBagConstraints.NONE;
        panel.add(c, gc);
    }
}