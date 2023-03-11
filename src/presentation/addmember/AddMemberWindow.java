package presentation.addmember;

import business.LibraryController;
import data.model.Address;
import data.model.LibraryMember;
import business.model.ModificationType;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.validator.RuleException;
import presentation.validator.Validator;
import presentation.validator.ValidatorFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AddMemberWindow implements UIFrame {
    private JPanel root;
    private JTextField memberIdTxt;
    private JTextField firstNameTxt;
    private JTextField lastNameTxt;
    private JTextField streetTxt;
    private JTextField cityTxt;
    private JTextField stateTxt;
    private JTextField zipTxt;
    private JTextField numberTxt;
    private JButton submitBtn;
    private JLabel messageLabel;

    private final LibraryController controller;
    private final Validator validator;

    public AddMemberWindow() {
        controller = LibraryController.getInstance();
        validator = ValidatorFactory.getValidator(this.getClass());

        submitBtn.addActionListener(e -> {
            Address newAddress = new Address(this.streetTxt.getText().trim(),
                    this.cityTxt.getText().trim(),
                    this.stateTxt.getText().trim(),
                    this.zipTxt.getText().trim());

            LibraryMember newMember = new LibraryMember(this.memberIdTxt.getText().trim(),
                    this.firstNameTxt.getText().trim(),
                    this.lastNameTxt.getText().trim(),
                    newAddress,
                    this.numberTxt.getText().trim()
            );
            try {
                validator.validate(this);
            } catch (RuleException ex) {
                String message = ex.getMessage();
                JOptionPane.showMessageDialog(root, message);
                return;
            }
            ModificationType status = controller.addMember(newMember);
            if (status == ModificationType.ADD) {
                messageLabel.setText("Add Member successfully: " + firstNameTxt.getText() + " " + lastNameTxt.getText());
            } else {
                messageLabel.setText("Member exists and get updated successfully: " + firstNameTxt.getText() + " " + lastNameTxt.getText());
            }
        });
    }

    public JTextField getMemberIdTxt() {
        return memberIdTxt;
    }

    public JTextField getFirstNameTxt() {
        return firstNameTxt;
    }

    public JTextField getLastNameTxt() {
        return lastNameTxt;
    }

    public  JTextField getNumberTxt(){
        return numberTxt;
    }

    @Override
    public void run() {
        memberIdTxt.setText("");
        firstNameTxt.setText("");
        lastNameTxt.setText("");
        streetTxt.setText("");
        cityTxt.setText("");
        stateTxt.setText("");
        zipTxt.setText("");
        numberTxt.setText("");
        messageLabel.setText("");
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
