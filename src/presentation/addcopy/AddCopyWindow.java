package presentation.addcopy;

import business.LibraryController;
import business.exception.AddBookCopyException;
import data.model.Book;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.validator.RuleException;
import presentation.validator.Validator;
import presentation.validator.ValidatorFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class AddCopyWindow implements UIFrame {
    private JPanel panel;
    private JTextField txtIsbn;
    private JButton btnAddCopy;
    private JLabel lblIsbn;
    private JLabel lblMessage;

    private final LibraryController controller;

    private final Validator validator;

    public AddCopyWindow() {
        controller = LibraryController.getInstance();
        validator = ValidatorFactory.getValidator(this.getClass());
        setUpListener();
    }

    private void setUpListener() {
        btnAddCopy.addActionListener(e -> {
            try {
                validator.validate(this);
                Book book = controller.addBookCopy(this.txtIsbn.getText());
                this.lblMessage.setText("Add Copy Successfully. Number of Copy: " + book.getBookCopyList().size());
            } catch (RuleException | AddBookCopyException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
            }
        });
    }

    @Override
    public void run() {
        txtIsbn.setText("");
        lblMessage.setText("");
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
        return txtIsbn.getText();
    }
}
