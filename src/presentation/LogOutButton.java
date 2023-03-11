package presentation;

import business.LoggedInUser;

import javax.swing.*;

public class LogOutButton extends JButton {
    public LogOutButton() {
        this.setText("Log Out");
        this.addActionListener(e -> {
            LoggedInUser.set(null);
            RootFrame.getInstance().removePanel(true);
        });
    }
}
