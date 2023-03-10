package presentation.addbook;

import business.LibraryController;
import business.exception.AddBookException;
import data.model.Author;
import data.model.Book;
import presentation.BackButton;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.addauthor.AddAuthorWindow;
import presentation.validator.RuleException;
import presentation.validator.Validator;
import presentation.validator.ValidatorFactory;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.List;

public class AddBookWindow implements UIFrame {
    private JPanel panel;
    private JTextField isbnTextField;
    private JTextField titleTextField;
    private JTextField maxCheckoutLengthTextField;
    private JTextField numOfCopiesTextField;
    private JButton addButton;
    private JButton addAuthorButton;
    private JLabel authorListLabel;
    private BackButton backButton;
    private JLabel isbnLabel;
    private JLabel titleLabel;
    private JLabel maxCheckoutLengthLable;
    private JLabel numOfCopiesLabel;
    private JLabel authorsLabel;
    private JLabel messageLabel;

    private final LibraryController controller;

    private final List<Author> authorList;

    private final AddAuthorWindow addAuthorWindow;

    private final Validator validator;

    public AddBookWindow() {
        controller = LibraryController.getInstance();
        validator = ValidatorFactory.getValidator(this.getClass());
        authorList = new ArrayList<>();
        addAuthorWindow = new AddAuthorWindow(this);
        setUpListener();
    }

    private void setUpListener() {
        addAuthorButton.addActionListener(e -> RootFrame.getInstance().addPanel(RootFrame.ADD_AUTHOR_WINDOW, true));
        addButton.addActionListener(e -> {
            try {
                validator.validate(this);
            } catch (RuleException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
                return;
            }
            try {
                Book book = new Book(
                        isbnTextField.getText(),
                        titleTextField.getText(),
                        Integer.parseInt(maxCheckoutLengthTextField.getText()),
                        Integer.parseInt(numOfCopiesTextField.getText()),
                        authorList
                );
                controller.addBook(book);
                messageLabel.setText("Add Book successfully: " + titleTextField.getText() + " (" + isbnTextField.getText() + ")");
            } catch (AddBookException ex) {
                JOptionPane.showMessageDialog(panel, "Book exists and get updated.");
                messageLabel.setText("Update Book successfully: " + titleTextField.getText() + " (" + isbnTextField.getText() + ")");
            }
        });
    }

    public AddAuthorWindow getAddAuthorWindow() {
        return addAuthorWindow;
    }

    @Override
    public void run() {
        isbnTextField.setText("");
        titleTextField.setText("");
        maxCheckoutLengthTextField.setText("");
        numOfCopiesTextField.setText("");
        authorListLabel.setText("List of Authors: ");
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

    public void addAuthor(Author author) {
        authorList.add(author);
        updateAuthorListLabel(author);
    }

    private void updateAuthorListLabel(Author author) {
        if (authorList.size() == 1) {
            authorListLabel.setText(authorListLabel.getText() + author.getFirstName() + " " + author.getLastName());
        } else {
            authorListLabel.setText(authorListLabel.getText() + ", " + author.getFirstName() + " " + author.getLastName());
        }
    }

    public String getIsbn() {
        return isbnTextField.getText();
    }

    public String getTitle() {
        return titleTextField.getText();
    }

    public String getMaxCheckoutLength() {
        return maxCheckoutLengthTextField.getText();
    }

    public String getNumOfCopies() {
        return numOfCopiesTextField.getText();
    }

    public String getAuthorList() {
        return authorListLabel.getText();
    }
}
