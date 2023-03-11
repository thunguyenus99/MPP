package presentation.addauthor;

import data.model.Address;
import data.model.Author;
import presentation.BackButton;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.UiUtils;
import presentation.addbook.AddBookWindow;
import presentation.dto.AddressDTO;
import presentation.dto.AuthorDTO;
import presentation.validator.RuleException;
import presentation.validator.ValidatorFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AddAuthorWindow implements UIFrame {
    private JTextField txtFirstName;
    private JTextField txtStreet;
    private JTextField txtState;
    private JTextField txtCredentials;
    private JTextField txtBiography;
    private JTextField txtLastName;
    private JTextField txtCity;
    private JTextField txtZip;
    private JButton btnAdd;
    private JTextField txtPhone;
    private JPanel panel;
    private JLabel lblFirstName;
    private JLabel lblLastName;
    private JLabel lblStreet;
    private JLabel lblCity;
    private JLabel lblState;
    private JLabel lblZip;
    private JLabel lblPhone;
    private JLabel lblCredentials;
    private JLabel lblBiography;
    private BackButton btnBack;

    private final AddBookWindow addBookWindow;

    public AddAuthorWindow(AddBookWindow addBookWindow) {
        this.addBookWindow = addBookWindow;
        setUpListener();
    }

    private void setUpListener() {
        btnAdd.addActionListener(e -> {
            try {
                ValidatorFactory.getValidator(this.getClass()).validate(this);
            } catch (RuleException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
                return;
            }
            AuthorDTO authorDTO = new AuthorDTO(
                    txtCredentials.getText(),
                    txtBiography.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    txtPhone.getText(),
                    new AddressDTO(
                            txtStreet.getText(),
                            txtCity.getText(),
                            txtState.getText(),
                            txtZip.getText()
                    )
            );
            this.addBookWindow.addAuthor(authorDTO);
            RootFrame.getInstance().removePanel(false);
        });
    }

    @Override
    public void run() {
        UiUtils.clearAllTextFields(getRoot());
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
        return txtFirstName.getText();
    }

    public String getLastName() {
        return txtLastName.getText();
    }

    public Address getAddress() {
        return new Address(
                txtStreet.getText(),
                txtCity.getText(),
                txtState.getText(),
                txtZip.getText()
        );
    }

    public String getPhone() {
        return txtPhone.getText();
    }

    public String getCredentials() {
        return txtCredentials.getText();
    }

    public String getBiography() {
        return txtBiography.getText();
    }
}
