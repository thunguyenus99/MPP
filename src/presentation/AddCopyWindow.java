package presentation;

import business.Controller;
import business.exception.AddBookCopyException;
import business.exception.RuleException;
import business.validation.Validation;
import business.validation.ValidationFactory;
import data.model.Book;

import javax.swing.*;

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

    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

    public String getIsbn() {
        return isbnTextField.getText();
    }
}
