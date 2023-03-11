package presentation.login;

import business.LibraryController;
import business.exception.LoginException;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.home.HomeUiPlugin;
import presentation.validator.RuleException;
import presentation.validator.ValidatorFactory;

import javax.swing.*;

public class LoginWindow implements UIFrame {
    private JTextField userIdTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel userIdLabel;
    private JLabel passwordLabel;
    private JLabel messageLabel;
    private JPanel panel;

    private final LibraryController controller;

    LoginWindow() {
        controller = LibraryController.getInstance();
        setUpListener();
    }

    private void setUpListener() {
        loginButton.addActionListener(e -> {
            try {
                ValidatorFactory.getValidator(this.getClass()).validate(this);
                controller.login(getUserId(), getPassword());
            } catch (RuleException | LoginException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
                return;
            }
            RootFrame.getInstance().addPanel(HomeUiPlugin.NAME, true);
        });
    }

    public String getUserId() {
        return userIdTextField.getText();
    }

    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

    @Override
    public void run() {
        userIdTextField.setText("");
        passwordField.setText("");
    }

    @Override
    public void updateNavigationLink() {

    }

    @Override
    public JPanel getRoot() {
        return panel;
    }

}
