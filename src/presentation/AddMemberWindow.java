package presentation;

import business.Controller;
import business.exception.AddMemberException;
import business.exception.RuleException;
import business.validation.Validation;
import business.validation.ValidationFactory;
import data.model.Address;
import data.model.LibraryMember;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class AddMemberWindow  implements UIFrame, Initialization {
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

    private Controller controller;
    private Validation validation;
    public AddMemberWindow() {
        controller = Controller.getInstance();
        validation = ValidationFactory.getValidation(this.getClass());

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
                validation.validate(this);
            } catch (RuleException ex) {
                String message = ex.getMessage();
                JOptionPane.showMessageDialog(root, message);
                return;
            }
            try {
                controller.addMember(newMember);
                messageLabel.setText("Add Member successfully: " + firstNameTxt.getText() + " " + lastNameTxt.getText());
            } catch (AddMemberException ex) {
                JOptionPane.showMessageDialog(root, "Member exists and get updated.");
                messageLabel.setText("Update Member successfully: " + firstNameTxt.getText() + " " + lastNameTxt.getText());
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

    }

    @Override
    public void updateNavigationLink() {
        root.setBorder(new TitledBorder(RootFrame.getInstance().getNavigationLink()));
    }

    @Override
    public JPanel getPanel() {
        return root;
    }
}
