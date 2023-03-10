package presentation;

import business.LibraryController;
import business.exception.LoginException;
import data.model.User;
import presentation.validator.RuleException;
import presentation.validator.Validator;
import presentation.validator.ValidatorFactory;

import javax.swing.*;

public class LoginWindow implements UIFrame, Initialization {
    private JTextField userIdTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel userIdLabel;
    private JLabel passwordLabel;
    private JLabel messageLabel;
    private JPanel panel;

    private final LibraryController controller;

    private final Validator validator;

    LoginWindow() {
        controller = LibraryController.getInstance();
        validator = ValidatorFactory.getValidator(this.getClass());
        setUpListener();
    }

    private void setUpListener() {
        loginButton.addActionListener(e -> {
            try {
                validator.validate(this);
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
