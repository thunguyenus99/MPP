package view;

import controller.Controller;
import entity.User;
import exception.LoginException;
import exception.RuleException;
import validation.Validation;
import validation.ValidationFactory;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel implements Initialization{
    private JTextField userIdTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel userIdLabel;
    private JLabel passwordLabel;

    private Controller controller;

    private Validation validation;

    LoginPanel() {
        controller = Controller.getInstance();
        validation = ValidationFactory.getValidation(getClass());
        initializeUI();
        initializeController();
    }

    private void initializeUI() {
        setLayout(new GridBagLayout());
        userIdLabel = new JLabel();
        userIdLabel.setText("User ID");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.anchor = GridBagConstraints.WEST;
        add(userIdLabel, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(spacer1, gbc);
        userIdTextField = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 50.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(userIdTextField, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 10.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(spacer3, gbc);
        passwordLabel = new JLabel();
        passwordLabel.setText("Password");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(passwordLabel, gbc);
        passwordField = new JPasswordField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passwordField, gbc);
        loginButton = new JButton();
        loginButton.setText("Log In");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(loginButton, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(spacer4, gbc);
    }

    private void initializeController() {
        loginButton.addActionListener(e -> {
            try {
                validation.validate(getThis());
                User user = controller.login(getUserId(), getPassword());
                // set logged-in user
                LoggedInUser.set(user);
                // navigate to another page
                RootFrame.getInstance().showPanel(RootFrame.HOME_PANEL);
            } catch (RuleException | LoginException ex) {
                String message = ex.getMessage();
                // show info to user

            }
        });
    }

    public String getUserId() {
        return userIdTextField.getText();
    }

    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

    private LoginPanel getThis() {
        return this;
    }

    @Override
    public void run() {

    }
}
