package presentation.addmember;

import business.LibraryController;
import business.model.ModificationType;
import data.model.Address;
import data.model.LibraryMember;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.UiUtils;
import presentation.validator.RuleException;
import presentation.validator.Validator;
import presentation.validator.ValidatorFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AddMemberWindow implements UIFrame {
    private JPanel root;
    private JTextField txtMemberId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtStreet;
    private JTextField txtCity;
    private JTextField txtState;
    private JTextField txtZip;
    private JTextField txtNumber;
    private JButton btnSubmit;
    private JLabel lblMessage;

    private final LibraryController controller;
    private final Validator validator;

    public AddMemberWindow() {
        controller = LibraryController.getInstance();
        validator = ValidatorFactory.getValidator(this.getClass());

        btnSubmit.addActionListener(e -> {
            Address newAddress = new Address(this.txtStreet.getText().trim(),
                    this.txtCity.getText().trim(),
                    this.txtState.getText().trim(),
                    this.txtZip.getText().trim()
            );

            LibraryMember newMember = new LibraryMember(this.txtMemberId.getText().trim(),
                    this.txtFirstName.getText().trim(),
                    this.txtLastName.getText().trim(),
                    newAddress,
                    this.txtNumber.getText().trim()
            );
            try {
                validator.validate(this);
            } catch (RuleException ex) {
                JOptionPane.showMessageDialog(root, ex.getMessage());
                return;
            }
            ModificationType status = controller.addMember(newMember);
            if (status == ModificationType.ADD) {
                lblMessage.setText("Add Member successfully: " + txtFirstName.getText() + " " + txtLastName.getText());
            } else {
                lblMessage.setText("Member exists and get updated successfully: " + txtFirstName.getText() + " " + txtLastName.getText());
            }
        });
    }

    public JTextField getTxtMemberId() {
        return txtMemberId;
    }

    public JTextField getTxtFirstName() {
        return txtFirstName;
    }

    public JTextField getTxtLastName() {
        return txtLastName;
    }

    public JTextField getTxtNumber() {
        return txtNumber;
    }

    @Override
    public void run() {
        UiUtils.clearAllTextFields(root);
    }

    @Override
    public void updateNavigationLink() {
        root.setBorder(new TitledBorder(RootFrame.getInstance().getNavigationLink()));
    }

    @Override
    public JPanel getRoot() {
        return root;
    }
}
