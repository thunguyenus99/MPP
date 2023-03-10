package presentation;

import business.Controller;
import business.exception.LoginException;
import business.exception.RuleException;
import business.validation.Validation;
import business.validation.ValidationFactory;
import data.model.User;

import javax.swing.*;

public class LoginWindow implements UIFrame, Initialization {
    private JTextField userIdTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel userIdLabel;
    private JLabel passwordLabel;
    private JLabel messageLabel;
    private JPanel panel;

    private Controller controller;

    private Validation validation;

    LoginWindow() {
        controller = Controller.getInstance();
        validation = ValidationFactory.getValidation(this.getClass());
        setUpListener();
    }

    private void setUpListener() {
        loginButton.addActionListener(e -> {
            try {
                validation.validate(this);
                User user = controller.login(getUserId(), getPassword());
                // set logged-in user
                LoggedInUser.set(user);
                // navigate to another page
                RootFrame.getInstance().addPanel(RootFrame.HOME_WINDOW, true);
            } catch (RuleException | LoginException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
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
    public JPanel getPanel() {
        return panel;
    }

}
