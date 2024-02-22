package lp.fe.swing;

import lp.fe.enums.Lang;
import lp.fe.fx.FXApplication;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * DON'T USE LOMBOK FOR THIS CLASS!<br>
 * It's not recommended to return dialog attribute.
 */
public class ApiOption {

    private static final JDialog DIALOG = new JDialog();
    private static final List<JButton> BUTTONS = new ArrayList<>();

    /**
     * Main method of this class. It shows dialog for choose GUI (java technologies e.i. swing, FX, spring etc.)
     * To add new button DON'T MODIFY THIS METHOD! To add button use {@link #addButtons} method
     */
    public static void showDialog() {
        BUTTONS.clear();
        addButtons();
        DIALOG.setLayout(null);
        DIALOG.setSize(200, 100 * BUTTONS.size());
        DIALOG.setLocationRelativeTo(null);
        final int[] incrementationArray = {0};
        BUTTONS.forEach(button -> {
            button.setSize(DIALOG.getWidth() - 100, DIALOG.getHeight() / BUTTONS.size() - 25);
            button.setLocation(DIALOG.getWidth() / 2 - button.getWidth() / 2,
                    incrementationArray[0]++ * button.getHeight());
            DIALOG.add(button);
        });
        DIALOG.setVisible(true);
    }

    /**
     * This method represents "list" of all possible GUI you can choose.
     * One button = one different GUI.
     * follow the principles:
     * <ol>
     *     <li>Use only one line for one GUI in this method</li>
     *     <li>For adding new button (new GUI) use {@link #addButton(String, java.awt.event.ActionListener)} method</li>
     *     <li>Don't type raw String text as name of button! Use Lang enumeration for button text</li>
     *     <li>Modify unit test and check if your GUI is really visible after clicking (all GUI should contain
     *     method for checking if it is correctly visible</li>
     * </ol>
     */
    private static void addButtons() {
        addButton(Lang.SWING_API.getText(), evt -> new SwingApplication().init());
        addButton(Lang.FX_API.getText(), evt -> javafx.application.Application.launch(FXApplication.class));
    }

    /**
     * Function method for adding new button which represents new GUI chooser.<br>
     * All buttons at first dispose dialog window and then use action you set as second parameter.
     *
     * @param text   NOT RAW STRING! Use Lang enumeration for button text
     * @param action anonymous class where you can use lambda for button action
     */
    private static void addButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(evt -> {
            DIALOG.dispose();
            action.actionPerformed(evt);
        });
        BUTTONS.add(button);
    }

    /**
     * This method is needed just for unit tests
     *
     * @return list of all buttons
     */
    public List<JButton> getButtons() {
        return BUTTONS;
    }
}
