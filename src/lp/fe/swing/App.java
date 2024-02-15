package lp.fe.swing;

import lp.Manager;
import lp.fe.Lang;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class App {

    private final Manager manager = Manager.getInstance();
    private final JTextField firstNameTextField = new JTextField(20);
    private final JTextField surnameTextField = new JTextField(20);
    private final JTextField addressTextField = new JTextField(20);
    private final JTextField phoneTextField = new JTextField(10);
    private final JTextArea output = new JTextArea();
    private JPanel mainPanel;

    public void init() {
        JFrame frame = new JFrame();
        frame.setSize(450, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        addPanel(frame);
        addComponents();
    }

    private void addComponents() {
        JPanel formPanel = new JPanel();
        formPanel.setSize(430, 200);
        formPanel.setLayout(new GridBagLayout());
        mainPanel.add(formPanel);

        addLabels(formPanel);
        addTextFields(formPanel);
        addButtons(formPanel);

        JScrollPane outputScrollPane = new JScrollPane(output);
        outputScrollPane.setLocation(0, formPanel.getHeight());
        outputScrollPane.setSize(380, 250);
        mainPanel.add(outputScrollPane);

        mainPanel.repaint();
        mainPanel.revalidate();
    }

    private void addTextFields(JPanel parentPanel) {
        addTextField(parentPanel, firstNameTextField, 0, 2);
        addTextField(parentPanel, surnameTextField, 1, 2);
        addTextField(parentPanel, addressTextField, 2, 2);
        addTextField(parentPanel, phoneTextField, 3, 1);
    }

    private void addTextField(JPanel parentPanel, JTextField textField, int position, int size) {
        addItem(parentPanel, textField, 1, position, size, GridBagConstraints.WEST);
    }

    private void addLabels(JPanel parentPanel) {
        addItem(parentPanel, new JLabel(Lang.FIRST_NAME.getText()), 0, 0, 1, GridBagConstraints.EAST);
        addItem(parentPanel, new JLabel(Lang.SURNAME.getText()), 0, 1, 1, GridBagConstraints.EAST);
        addItem(parentPanel, new JLabel(Lang.ADDRESS.getText()), 0, 2, 1, GridBagConstraints.EAST);
        addItem(parentPanel, new JLabel(Lang.PHONE.getText()), 0, 3, 1, GridBagConstraints.EAST);
    }

    private void addButtons(JPanel parentPanel) {
        addButton(Lang.SAVE.getText(), 0, parentPanel, evt -> {
            int answer = manager.saveNewPerson(
                    obtainKey(),
                    firstNameTextField.getText(),
                    surnameTextField.getText(),
                    addressTextField.getText(),
                    phoneTextField.getText());
            if (answer == -2) {
                JOptionPane.showMessageDialog(parentPanel, Lang.EMPTY_INPUTS.getText());
            } else if (answer == -1) {
                JOptionPane.showMessageDialog(parentPanel, Lang.PERSON_ALREADY_EXISTS.getText());
            }
            clearTextFields();
        });
        addButton(Lang.EDIT.getText(), 1, parentPanel, evt -> {
            if (manager.editPerson(obtainKey(), addressTextField.getText(), phoneTextField.getText()) == -1) {
                JOptionPane.showMessageDialog(parentPanel, Lang.PERSON_NOT_EXISTS.getText());
            }
            clearTextFields();
        });
        addButton(Lang.LOAD.getText(), 2, parentPanel, evt -> {
            clearTextArea();
            manager.getData().forEach(person -> output.setText(output.getText() + person + Lang.NEW_LINE.getText()));
        });
        addButton(Lang.CLEAR_LOG.getText(), 3, parentPanel, evt -> clearTextArea());
    }

    private void clearTextArea() {
        output.setText(Lang.EMPTY.getText());
    }

    private void clearTextFields() {
        firstNameTextField.setText(Lang.EMPTY.getText());
        surnameTextField.setText(Lang.EMPTY.getText());
        addressTextField.setText(Lang.EMPTY.getText());
        phoneTextField.setText(Lang.EMPTY.getText());
    }

    private void addButton(String text, int position, JPanel parentPanel, ActionListener action) {
        JButton button = new JButton(text);
        addItem(parentPanel, button, position, 4, 1, GridBagConstraints.EAST);
        button.addActionListener(action);
    }

    private String obtainKey() {
        return firstNameTextField.getText().trim() + surnameTextField.getText().trim();
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