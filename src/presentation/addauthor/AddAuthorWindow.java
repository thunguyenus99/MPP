package presentation.addauthor;

import data.model.Address;
import data.model.Author;
import presentation.BackButton;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.addbook.AddBookWindow;
import presentation.validator.RuleException;
import presentation.validator.ValidatorFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AddAuthorWindow implements UIFrame {
    private JTextField firstNameTextField;
    private JTextField streetTextField;
    private JTextField stateTextField;
    private JTextField credentialsTextField;
    private JTextField biographyTextField;
    private JTextField lastNameTextField;
    private JTextField cityTextField;
    private JTextField zipTextField;
    private JButton addButton;
    private JTextField phoneTextField;
    private JPanel panel;
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel streetLabel;
    private JLabel cityLabel;
    private JLabel stateLabel;
    private JLabel zipLabel;
    private JLabel phoneLabel;
    private JLabel credentialsLabel;
    private JLabel biographyLabel;
    private BackButton backButton;

    private final AddBookWindow addBookWindow;

    public AddAuthorWindow(AddBookWindow addBookWindow) {
        this.addBookWindow = addBookWindow;
        setUpListener();
    }

    private void setUpListener() {
        addButton.addActionListener(e -> {
            try {
                ValidatorFactory.getValidator(this.getClass()).validate(this);
            } catch (RuleException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
                return;
            }
            Author author = new Author(
                    credentialsTextField.getText(),
                    biographyTextField.getText(),
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    new Address(
                            streetTextField.getText(),
                            cityTextField.getText(),
                            stateTextField.getText(),
                            zipTextField.getText()
                    ),
                    phoneTextField.getText()
            );
            this.addBookWindow.addAuthor(author);
            RootFrame.getInstance().removePanel(false);
        });
    }

    private void reset() {
        firstNameTextField.setText("");
        streetTextField.setText("");
        stateTextField.setText("");
        credentialsTextField.setText("");
        biographyTextField.setText("");
        lastNameTextField.setText("");
        cityTextField.setText("");
        zipTextField.setText("");
        phoneTextField.setText("");
    }

    @Override
    public void run() {
        reset();
    }

    @Override
    public void updateNavigationLink() {
        panel.setBorder(new TitledBorder(RootFrame.getInstance().getNavigationLink()));
    }

    @Override
    public JPanel getRoot() {
        return panel;
    }

    public String getFirstName() {
        return firstNameTextField.getText();
    }

    public String getLastName() {
        return lastNameTextField.getText();
    }

    public Address getAddress() {
        return new Address(
                streetTextField.getText(),
                cityTextField.getText(),
                stateTextField.getText(),
                zipTextField.getText()
        );
    }

    public String getPhone() {
        return phoneTextField.getText();
    }

    public String getCredentials() {
        return credentialsTextField.getText();
    }

    public String getBiography() {
        return biographyTextField.getText();
    }
}
