package lp.fe.swing;

import lp.Manager;
import lp.be.service.LoggerService;
import lp.be.serviceimpl.LoggerServiceImpl;
import lp.fe.enums.Lang;
import org.apache.log4j.Logger;

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
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class SwingApplication {

    private final Manager manager = Manager.getInstance();
    private final JTextField firstNameTextField = new JTextField(20);
    private final JTextField surnameTextField = new JTextField(20);
    private final JTextField addressTextField = new JTextField(20);
    private final JTextField phoneTextField = new JTextField(10);
    private final JTextArea output = new JTextArea();
    private final LoggerService loggerService = LoggerServiceImpl.getInstance(SwingApplication.class, true);
    private final Logger log = loggerService.getLog();
    private JPanel mainPanel;
    private JPanel formPanel;
    private JPanel buttonsPanel;
    private JScrollPane outputScrollPanel;

    /**
     * Basic initial method where the main frame is setting. For more JComponents use separate methods
     * like {@link #addPanels(javax.swing.JFrame)} method
     */
    public void init() {
        JFrame frame = new JFrame();
        frame.setSize(600, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        addPanels(frame);
        fillPanels();
    }

    /**
     * All panels for application are sets in this method. We assume that the swing GUI consists of panels,
     * and each panel has components. All panels (except main panel) are attributes so other method can access
     * these panels simply
     *
     * @param frame main frame of application
     */
    private void addPanels(JFrame frame) {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setSize(frame.getWidth(), frame.getHeight());
        frame.add(mainPanel);

        formPanel = new JPanel();
        formPanel.setSize(mainPanel.getWidth(), mainPanel.getHeight() / 3);
        formPanel.setLayout(new GridBagLayout());
        mainPanel.add(formPanel);

        buttonsPanel = new JPanel();
        buttonsPanel.setSize(mainPanel.getWidth(), mainPanel.getHeight() / 10);
        buttonsPanel.setLocation(0, formPanel.getHeight());
        buttonsPanel.setLayout(new FlowLayout());
        mainPanel.add(buttonsPanel);

        outputScrollPanel = new JScrollPane();
        outputScrollPanel.setSize(mainPanel.getWidth() - 20, 17 * mainPanel.getHeight() / 30 - 39);
        outputScrollPanel.setLocation(0, buttonsPanel.getY() + buttonsPanel.getHeight());
        mainPanel.add(outputScrollPanel);
    }

    /**
     * When you set all panels in {@link #addPanels(javax.swing.JFrame)} method, you can declare methods to fill
     * these panels here.
     */
    private void fillPanels() {
        fillFormPanel();
        fillOutputScrollPanel();

        mainPanel.repaint();
        mainPanel.revalidate();
    }

    /**
     * This method just follow convention.
     * It's part of {@link #fillPanels()} method where all setting for panels are sets via methods.
     */
    private void fillOutputScrollPanel() {
        outputScrollPanel.setViewportView(output);
    }

    /**
     * Adding components for FormPanel. Component on this panel:
     * <ul>
     *     <li>4x Label</li>
     *     <li>4x TextField (need to be declared as attributes)</li>
     *     <li>5x Buttons</li>
     * </ul>
     */
    private void fillFormPanel() {
        addItem(new JLabel(Lang.FIRST_NAME.getText()), 0, 0, 1, GridBagConstraints.EAST);
        addItem(new JLabel(Lang.SURNAME.getText()), 0, 1, 1, GridBagConstraints.EAST);
        addItem(new JLabel(Lang.ADDRESS.getText()), 0, 2, 1, GridBagConstraints.EAST);
        addItem(new JLabel(Lang.PHONE.getText()), 0, 3, 1, GridBagConstraints.EAST);
        addTextField(firstNameTextField, 0, 2);
        addTextField(surnameTextField, 1, 2);
        addTextField(addressTextField, 2, 2);
        addTextField(phoneTextField, 3, 1);
        addButtons();
    }

    /**
     * This method use {@link #addItem(javax.swing.JComponent, int, int, int, int)} method. The reason why we don't
     * use that method immediately is that we need to use text fields to get their values. This is why text fields
     * are declared as attributes.
     *
     * @param textField JTextField declared as attribute
     * @param column    Number of column
     * @param width     Number width size of JComponent (how many cells it takes horizontally)
     */
    private void addTextField(JTextField textField, int column, int width) {
        addItem(textField, 1, column, width, GridBagConstraints.WEST);
    }

    /**
     * Added all function buttons for CRUD and text area control.
     */
    private void addButtons() {
        addButton(Lang.SAVE.getText(), evt -> createMethod());
        addButton(Lang.EDIT.getText(), evt -> updateMethod());
        addButton(Lang.LOAD.getText(), evt -> {
            clearTextArea();
            manager.getData().forEach(person -> output.setText(output.getText() + person + Lang.NEW_LINE.getText()));
        });
        addButton(Lang.DELETE.getText(), evt -> {
            clearTextArea();
            manager.deleteData();
        });
        addButton(Lang.CLEAR_LOG.getText(), evt -> clearTextArea());
    }

    /**
     * CRUD create method
     */
    private void createMethod() {
        String key = firstNameTextField.getText().trim() + surnameTextField.getText().trim();
        int answer = manager.saveNewPerson(
                key,
                firstNameTextField.getText(),
                surnameTextField.getText(),
                addressTextField.getText(),
                phoneTextField.getText());
        if (answer == -2) {
            JOptionPane.showMessageDialog(mainPanel, Lang.EMPTY_INPUTS.getText());
        } else if (answer == -1) {
            JOptionPane.showMessageDialog(mainPanel, Lang.PERSON_ALREADY_EXISTS.getText());
        } else {
            log.info(Lang.SUCCESSFULLY_SAVED.getText());
            clearTextFields();
        }
    }

    /**
     * CRUD update method
     */
    private void updateMethod() {
        String key = firstNameTextField.getText().trim() + surnameTextField.getText().trim();
        if (manager.editPerson(key, addressTextField.getText(), phoneTextField.getText()) == 0) {
            log.info(Lang.SUCCESSFULLY_EDITED.getText());
            clearTextFields();
        } else {
            JOptionPane.showMessageDialog(mainPanel, Lang.PERSON_NOT_EXISTS.getText());
        }
    }

    /**
     * Simply remove all texts in JTextArea.
     */
    private void clearTextArea() {
        output.setText(Lang.EMPTY.getText());
    }

    /**
     * Clear all input text fields.
     */
    private void clearTextFields() {
        firstNameTextField.setText(Lang.EMPTY.getText());
        surnameTextField.setText(Lang.EMPTY.getText());
        addressTextField.setText(Lang.EMPTY.getText());
        phoneTextField.setText(Lang.EMPTY.getText());
    }

    /**
     * Add one specify button.
     *
     * @param buttonText Use Lang enumeration for text, not raw String!
     * @param action     Anonymous class that you have to edit any action for button
     */
    private void addButton(String buttonText, ActionListener action) {
        JButton button = new JButton(buttonText);
        button.setSize(100, 75);
        buttonsPanel.add(button);
        button.addActionListener(action);
    }

    /**
     * In swing GUI this method is needed to add specify JComponent added to GridPanel.
     *
     * @param c     JComponent
     * @param x     Number of row
     * @param y     Number of column
     * @param width Number width size of JComponent (how many cells it takes horizontally)
     * @param align Alignment of JComponent (by using number or {@link GridBagConstraints})
     */
    private void addItem(JComponent c, int x, int y, int width, int align) {
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
        formPanel.add(c, gc);
    }
}