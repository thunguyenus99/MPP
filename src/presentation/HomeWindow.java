package presentation;

import data.model.Role;
import data.model.User;
import presentation.addbook.AddBookUiPlugin;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class HomeWindow implements UIFrame {
    private JPanel panel;
    private JButton checkoutBookButton;
    private JButton addMemberButton;
    private JButton printCheckOutRecordsButton;
    private JButton addCopyButton;
    private JButton searchBookButton;
    private JButton addBookButton;

    public HomeWindow() {
        setUpListener();
    }

    void setUpListener() {
        addCopyButton.addActionListener(e -> RootFrame.getInstance().addPanel(RootFrame.ADD_COPY_WINDOW, true));
        checkoutBookButton.addActionListener(e -> RootFrame.getInstance().addPanel(RootFrame.CHECKOUT_BOOK_WINDOW, true));
        addMemberButton.addActionListener(e -> RootFrame.getInstance().addPanel(RootFrame.ADD_MEMBER_WINDOW, true));
        addBookButton.addActionListener(e -> RootFrame.getInstance().addPanel(AddBookUiPlugin.ADD_BOOK_WINDOW, true));
        printCheckOutRecordsButton.addActionListener(e -> RootFrame.getInstance().addPanel(RootFrame.PRINT_CHECKOUT_RECORDS_WINDOW, true));
        searchBookButton.addActionListener(e -> RootFrame.getInstance().addPanel(RootFrame.SEARCH_BOOK_WINDOW, true));
    }

    public void authorizeFunction() {
        User user = LoggedInUser.get();
        if (user != null) {
            boolean isLibrarian = user.getAuthorizations().contains(Role.LIBRARIAN);
            boolean isAdmin = user.getAuthorizations().contains(Role.ADMIN);
            checkoutBookButton.setVisible(isLibrarian);
            printCheckOutRecordsButton.setVisible(isLibrarian);
            searchBookButton.setVisible(isLibrarian);
            addBookButton.setVisible(isAdmin);
            addMemberButton.setVisible(isAdmin);
            addCopyButton.setVisible(isAdmin);
        }
    }

    @Override
    public void run() {
        authorizeFunction();
    }

    @Override
    public void updateNavigationLink() {
        panel.setBorder(new TitledBorder(RootFrame.getInstance().getNavigationLink()));
    }

    @Override
    public JPanel getRoot() {
        return panel;
    }
}
