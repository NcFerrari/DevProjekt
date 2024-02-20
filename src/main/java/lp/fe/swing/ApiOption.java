package lp.fe.swing;

import lp.fe.enums.Lang;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ApiOption {

    private static final JDialog dialog = new JDialog();
    private static final List<JButton> buttons = new ArrayList<>();

    static {
        dialog.setSize(200, 200);
        dialog.setLocationRelativeTo(null);
        dialog.setLayout(null);
    }

    public static void showDialog() {
        buttons.clear();
        addButtons();
        final int[] incrementationArray = {0};
        buttons.forEach(button -> {
            button.setSize(dialog.getWidth() - 100, dialog.getHeight() / buttons.size() - 25);
            button.setLocation(dialog.getWidth() / 2 - button.getWidth() / 2,
                    incrementationArray[0]++ * button.getHeight());
            dialog.add(button);
        });
        dialog.setVisible(true);
    }

    private static void addButtons() {
        addButton(Lang.SWING_API.getText(), evt -> new App().init());
        addButton(Lang.FX_API.getText(), evt -> javafx.application.Application.launch(lp.fe.fx.App.class));
    }

    private static void addButton(String text, ActionListener action) {
        JButton button = new JButton(text);
        button.addActionListener(evt -> {
            dialog.dispose();
            action.actionPerformed(evt);
        });
        buttons.add(button);
    }

    private ApiOption() {

    }
}
