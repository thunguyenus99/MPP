package presentation.addbook;

import business.LibraryController;
import business.model.ModificationType;
import data.model.Author;
import data.model.Book;
import presentation.BackButton;
import presentation.RootFrame;
import presentation.UIFrame;
import presentation.addauthor.AddAuthorUiPlugin;
import presentation.validator.RuleException;
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

    public AddBookWindow() {
        controller = LibraryController.getInstance();
        authorList = new ArrayList<>();
        setUpListener();
    }

    private void setUpListener() {
        addAuthorButton.addActionListener(e -> RootFrame.getInstance().addPanel(AddAuthorUiPlugin.NAME, true));
        addButton.addActionListener(e -> {
            try {
                ValidatorFactory.getValidator(this.getClass()).validate(this);
            } catch (RuleException ex) {
                JOptionPane.showMessageDialog(panel, ex.getMessage());
                return;
            }
            Book book = new Book(
                    isbnTextField.getText(),
                    titleTextField.getText(),
                    Integer.parseInt(maxCheckoutLengthTextField.getText()),
                    Integer.parseInt(numOfCopiesTextField.getText()),
                    authorList
            );
            ModificationType status = controller.addBook(book);
            if (status == ModificationType.ADD) {
                messageLabel.setText("Add Book successfully: " + titleTextField.getText() + " (" + isbnTextField.getText() + ")");
            } else {
                messageLabel.setText("Book exists and get updated successfully: " + titleTextField.getText() + " (" + isbnTextField.getText() + ")");
            }
        });
    }

    @Override
    public void run() {
        isbnTextField.setText("");
        titleTextField.setText("");
        maxCheckoutLengthTextField.setText("");
        numOfCopiesTextField.setText("");
        authorListLabel.setText("List of Authors: ");
        messageLabel.setText("");
        authorList.clear();
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
        updateAuthorListLabel();
    }

    private void updateAuthorListLabel() {
        StringBuilder label = new StringBuilder("List of Authors: ");
        for (int i = 0; i < authorList.size(); i++) {
            Author author = authorList.get(i);
            label.append(author.getFirstName()).append(" ").append(author.getLastName());
            if (i != authorList.size() - 1) {
                label.append(", ");
            }
        }
        authorListLabel.setText(label.toString());
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
