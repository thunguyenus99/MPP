package presentation;

import javax.swing.*;

public class BackButton extends JButton {
    public BackButton() {
        this.setText("Back");
        this.addActionListener(e -> RootFrame.getInstance().showPanel(RootFrame.HOME_FRAME));
    }
}
