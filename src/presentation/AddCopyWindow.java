package presentation;

import business.Controller;
import business.exception.AddBookCopyException;
import business.exception.RuleException;
import business.validation.Validation;
import business.validation.ValidationFactory;
import data.model.Book;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AddCopyWindow implements UIFrame, Initialization {
    private JPanel panel;
    private JTextField isbnTextField;
    private JButton addCopyButton;
    private JLabel isbnLabel;
    private JLabel messageLabel;

    private Controller controller;

    private Validation validation;

    public AddCopyWindow() {
        controller = Controller.getInstance();
        validation = ValidationFactory.getValidation(this.getClass());
        setUpListener();
    }

    private void setUpListener() {
        addCopyButton.addActionListener(e -> {
            try {
                validation.validate(this);
                Book book = controller.addBookCopy(this.isbnTextField.getText());
                this.messageLabel.setText("Add Copy Successfully. Number of Copy: " + book.getBookCopyList().size());
            } catch (RuleException | AddBookCopyException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
        });
    }

    @Override
    public void run() {
        isbnTextField.setText("");
        messageLabel.setText("");
    }

    @Override
    public void updateNavigationLink() {
        panel.setBorder(new TitledBorder(RootFrame.getInstance().getNavigationLink()));
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public String getIsbn() {
        return isbnTextField.getText();
    }
}
