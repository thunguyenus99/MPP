package presentation;

import business.LibraryController;
import business.exception.AddBookCopyException;
import data.model.Book;
import presentation.validator.RuleException;
import presentation.validator.Validator;
import presentation.validator.ValidatorFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AddCopyWindow implements UIFrame {
    private JPanel panel;
    private JTextField isbnTextField;
    private JButton addCopyButton;
    private JLabel isbnLabel;
    private JLabel messageLabel;

    private final LibraryController controller;

    private final Validator validator;

    public AddCopyWindow() {
        controller = LibraryController.getInstance();
        validator = ValidatorFactory.getValidator(this.getClass());
        setUpListener();
    }

    private void setUpListener() {
        addCopyButton.addActionListener(e -> {
            try {
                validator.validate(this);
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
    public JPanel getRoot() {
        return panel;
    }

    public String getIsbn() {
        return isbnTextField.getText();
    }
}
